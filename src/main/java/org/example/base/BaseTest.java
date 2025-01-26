package org.example.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class BaseTest {
    protected static WebDriver driver;
    protected static ExtentReports extent;
    protected static final ConcurrentHashMap<String, ExtentTest> tests = new ConcurrentHashMap<>();

    @BeforeSuite
    public void setupReport() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("./output/Extent-report.html");
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setup(ITestResult result) {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(methodName);
        tests.put(methodName, test);
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        String methodName = testResult.getMethod().getMethodName();
        ExtentTest test = tests.get(methodName);

        if (test != null) {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                // Log the failure reason
                test.log(Status.FAIL, "Test failed because of: " + testResult.getThrowable());
                // Capture screenshot as Base64
                String screenshotBase64 = captureScreenshotAsBase64();
                // Embed the Base64 screenshot directly into the Extent Report
                if (screenshotBase64 != null) {
                    test.addScreenCaptureFromBase64String(screenshotBase64, "Failure Screenshot");
                } else {
                    test.log(Status.WARNING, "Screenshot could not be captured.");
                }
            } else if (testResult.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test passed.");
            } else if (testResult.getStatus() == ITestResult.SKIP) {
                test.log(Status.SKIP, "Test skipped.");
            }

            tests.remove(methodName);
        }

        // Do not quit the driver here to maintain session across tests
    }

    public String captureScreenshotAsBase64() {
        try {
            // Capture screenshot as a file
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            // Convert the screenshot file to a Base64 string
            byte[] fileContent = FileUtils.readFileToByteArray(src);
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @AfterSuite
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}