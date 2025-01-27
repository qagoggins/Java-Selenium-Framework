package tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseTest;
import org.example.pages.CheckoutPage;
import org.example.pages.CheckoutStepTwo;
import org.example.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutAndBuyTest extends BaseTest {
    @Test(dataProvider = "checkoutFormData", dataProviderClass = TestData.class, groups = "checkoutAndBuyGroup", dependsOnGroups = "addToCartGroup")
    public void checkoutAndBuyProduct(String firstName, String lastName, String postalCode) {
        try {
            driver.get("https://www.saucedemo.com/checkout-step-one.html");
            getTest().info("On: "+driver.getCurrentUrl());
            Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/checkout-step-one.html"), "Not on checkout page!");
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            getTest().info(checkoutPage.getPageTitle());
            Assert.assertEquals(checkoutPage.getPageTitle(), "Checkout: Your Information", "Not on the checkoutPage but clicked on checkout!");

            checkoutPage.enterFirstName(firstName);
            checkoutPage.enterLastName(lastName);
            checkoutPage.enterPostalCode(postalCode);
            getTest().info("Entered: "+firstName+lastName+postalCode);

            CheckoutStepTwo checkoutStepTwo = checkoutPage.goToSecondStep();
            Assert.assertEquals(checkoutStepTwo.getPageTitle(), "Checkout: Overview");
            getTest().info("On the page: " +driver.getCurrentUrl()+" "+checkoutStepTwo.getPageTitle());
            checkoutStepTwo.buy();

            Assert.assertEquals(checkoutStepTwo.getSuccessfulOrderText(), "Thank you for your order!", "Failed trying to buy a product!");
            getTest().pass("Test passed: "+checkoutStepTwo.getSuccessfulOrderText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ExtentTest getTest() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return tests.get(methodName);
    }
}
