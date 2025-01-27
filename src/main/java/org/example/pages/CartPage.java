package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToCheckout() {
        checkoutButton.click();
    }

    public String getMyCartText() {
        return pageTitle.getText();
    }
    //Your Cart

}
