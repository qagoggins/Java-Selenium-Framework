package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.base.BaseTest;
import org.example.pages.CatalogPage;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test(dependsOnMethods = "tests.LoginTest.login")
    public void addToCart() {
        try {
            driver.get("https://www.saucedemo.com/inventory.html");
            CatalogPage catalogPage = new CatalogPage(driver);
            getTest().info("On the page: " + driver.getCurrentUrl() + "-" + driver.getTitle());
            String productName = "Sauce Labs Backpack";
            if (catalogPage.isProductAvailable(productName)) {
                catalogPage.addProductToCart(productName);
                getTest().pass("Product added: " + productName);
                catalogPage.goToCart();
            } else {
                getTest().fail("Product not found: " + productName);
            }
        } catch (Exception e) {
            getTest().fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to get the current test
    private ExtentTest getTest() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return tests.get(methodName);
    }
}