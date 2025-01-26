package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.example.utils.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void login(String username, String password) {
        try {
            driver.get("https://www.saucedemo.com/");
            getTest().info("On the webpage: " + driver.getCurrentUrl());
            LoginPage loginPage = new LoginPage(driver);
            getTest().info("Entering data: " + username + "+" + password);
            loginPage.login(username, password);
            Thread.sleep(1500);

            if (loginPage.isErrorMessageDisplayed()) {
                getTest().fail("Login failed: Error message is displayed: " + loginPage.getErrorMessage());
                Assert.fail("Login failed: Error message is displayed: " + loginPage.getErrorMessage());
            } else {
                getTest().pass("Test Data has successfully logged in.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to get the current test
    private ExtentTest getTest() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return tests.get(methodName);
    }
}