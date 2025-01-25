package tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    // https://www.saucedemo.com/inventory.html -> url when successfully logged in.

    @Test
    public void login() {
        test = extent.createTest("Valid login test.");
        driver.get("https://www.saucedemo.com/");
        test.info("On the webpage: "+driver.getCurrentUrl());
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering data");
        loginPage.login                                                                                                     ("standard_usaser", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URLs not matching, login has failed");
        test.pass("Have successfully logged in.");
    }
}
