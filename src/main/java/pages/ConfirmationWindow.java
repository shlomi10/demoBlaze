package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the confirmation window
 *
 * @author Shlomi
 */

public class ConfirmationWindow extends BasePageFunctions {

    // constructor
    public ConfirmationWindow(WebDriver driver) {
        super(driver);
    }

    By purchaseWindow = By.className("lead");

    // get get confirmation purchase
    public Boolean getConfirmationPurchase() {
        String text = getTextFromElement(purchaseWindow);
        return text.contains("Id:") && text.contains("Amount:") && text.contains("Card Number:") && text.contains("Date:");
    }

}
