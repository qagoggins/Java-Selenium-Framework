package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.base.BaseTest;
import org.example.pages.CatalogPage;
import org.example.utils.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddToCartTest extends BaseTest {

    @Test(groups = "addToCartGroup", dependsOnGroups = "loginGroup")
    public void addToCart() {
        try {
            driver.get("https://www.saucedemo.com/inventory.html");
            Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/inventory.html"), "Not on inventory page!");
            CatalogPage catalogPage = new CatalogPage(driver);
            getTest().info("On the page: " + driver.getCurrentUrl() + "-" + driver.getTitle());
            String productName = "Sauce Labs Bolt T-Shirt";
            if (catalogPage.isProductAvailable(productName)) {
                catalogPage.addProductToCart(productName);
                getTest().pass("Product added: " + productName);
                catalogPage.goToCart();
                Thread.sleep(3000);
            } else {
                getTest().fail("Product not found: " + productName);
            }
        } catch (Exception e) {
            getTest().fail("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Test(groups = "addToCartGroup", dependsOnGroups = "loginGroup", dataProvider = "productsData", dataProviderClass = TestData.class)
    public void addMultipleProductsToCart(List<String> productNames) {
        try {
            driver.get("https://www.saucedemo.com/inventory.html");
            Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/inventory.html"), "Not on inventory page!");
            CatalogPage catalogPage = new CatalogPage(driver);
            getTest().info("On the page: " + driver.getCurrentUrl() + "-" + driver.getTitle());
            for (String productName : productNames) {
                if (catalogPage.isProductAvailable(productName)) {
                    catalogPage.addProductToCart(productName);
                    getTest().pass("Product added: " + productName);
                } else {
                    getTest().fail("Product not available: " + productName);
                }
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