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

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("./output/Extent-report.html");
        extent.attachReporter(spark);
    }


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
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
        }
        driver.quit();
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
        extent.flush();
    }
}
