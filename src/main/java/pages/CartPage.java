package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the cart page
 *
 * @author Shlomi
 */

public class CartPage extends BasePageFunctions {

    // constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    By galaxyS6Item = By.xpath("//table//td[text()='Samsung galaxy s6']");

    // get text from element
    public String getItemsNameInTable() {
        waitForElementToBeVisible(galaxyS6Item);
        return getTextFromElement(galaxyS6Item);
    }

}
