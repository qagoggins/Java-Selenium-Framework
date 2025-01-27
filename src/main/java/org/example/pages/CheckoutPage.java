package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;
    @FindBy(id = "last-name")
    private WebElement lastNameInput;
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }
    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }
    public void enterPostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
    }
    public CheckoutStepTwo goToSecondStep() {
        continueButton.click();
        return new CheckoutStepTwo(driver);
    }


}

