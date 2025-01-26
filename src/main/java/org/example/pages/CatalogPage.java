package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CatalogPage {
    WebDriver driver;

    @FindBy(className = "shopping_cart_link")
    private WebElement cart;

    @FindBy(css = "div.inventory_item_label a div[data-test='inventory-item-name']")
    private List<WebElement> products;

    @FindBy(css = "div.pricebar button[data-test^='add-to-cart-']")
    private List<WebElement> buttons;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToCart() {
        cart.click();
    }

    public void addProductToCart(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getText().equals(productName)) {
                    WebElement addButton = buttons.get(i);

                    // Wait until the button is clickable
                    wait.until(ExpectedConditions.elementToBeClickable(addButton));

                    addButton.click();
                    System.out.println("Added product: " + products.get(i).getText());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to add product to cart: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Optional: Method to check if a product exists in the catalog
    public boolean isProductAvailable(String productName) {
        for (WebElement product : products) {
            if (product.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}