package tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.example.utils.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    // https://www.saucedemo.com/inventory.html -> url when successfully logged in.

    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void login(String username, String password) {
        test = extent.createTest("Valid login test.");
        try {
            driver.get("https://www.saucedemo.com/");
            test.info("On the webpage: " + driver.getCurrentUrl());
            LoginPage loginPage = new LoginPage(driver);
            test.info("Entering data: " + username + "+" + password);
            loginPage.login(username, password);
            Thread.sleep(1500);
            if(loginPage.isErrorMessageDisplayed()) {
                Assert.fail("Login failed: Error message is displayed: "+loginPage.getErrorMessage());
            } else {
                test.pass("Test Data has successfully logged in.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
