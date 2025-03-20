package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the product page
 *
 * @author Shlomi
 */

public class ProductPage extends BasePageFunctions {

    // constructor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    By addToCartBtn = By.cssSelector(".btn.btn-success.btn-lg");
    By cartBtn = By.id("cartur");
    By placeOrderBtn = By.xpath("//button[@data-target='#orderModal']");

    // add item to cart
    public boolean addItemToCart() {
        waitForElementToBeVisible(addToCartBtn);
        return waitForElementToBeClickableAndClickIt(addToCartBtn);
    }

    // get text from popup
    public String getTextFromAlert() {
        smallWait();
        return getTextFromPopUp();
    }

    // add item to cart
    public void acceptAlert() {
        acceptPopUp();
    }

    // select cart
    public boolean selectCart() {
        return waitForElementToBeClickableAndClickIt(cartBtn);
    }

    // select place order
    public boolean selectPlaceOrder() {
        return waitForElementToBeClickableAndClickIt(placeOrderBtn);
    }
}
