package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the place order page
 *
 * @author Shlomi
 */

public class PlaceOrderPage extends BasePageFunctions {

    // constructor
    public PlaceOrderPage(WebDriver driver) {
        super(driver);
    }

    By purchaseBtn = By.xpath("//button[@onclick='purchaseOrder()']");
    By nameField = By.id("name");
    By countryField = By.id("country");
    By cityField = By.id("city");
    By creditCardField = By.id("card");
    By monthField = By.id("month");
    By yearField = By.id("year");
    By confirmationWindow = By.cssSelector(".sweet-alert.showSweetAlert.visible");

    // fill order details
    public Boolean fillOrderDetails(String name, String country, String city, String creditCard, String month, String year) {
        waitForElementToBeVisible(purchaseBtn);
        clearAndTypeTextToElem(nameField, name);
        clearAndTypeTextToElem(countryField, country);
        clearAndTypeTextToElem(cityField, city);
        clearAndTypeTextToElem(creditCardField, creditCard);
        clearAndTypeTextToElem(monthField, month);
        clearAndTypeTextToElem(yearField, year);
        return waitForElementToBeClickableAndClickIt(purchaseBtn);
    }

    // wait for confirmation page
    public Boolean waitForConfirmationWindow(){
        return waitForElementToBeVisible(confirmationWindow);
    }

}
