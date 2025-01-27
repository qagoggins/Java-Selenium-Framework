package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwo {
    WebDriver driver;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement successfulOrderText;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CheckoutStepTwo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }
    public void buy() {
        finishButton.click();
    }

    public String getSuccessfulOrderText() {
        return successfulOrderText.getText();
    }

}
