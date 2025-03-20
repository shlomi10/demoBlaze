package utilities;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * this class represents the main functions of all pages
 *
 * @author Shlomi
 */


public abstract class BasePageFunctions {

    // get webDriver
    @Getter
    private static final Logger logger = LoggerFactory.getLogger(BasePageFunctions.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String siteURL;
    protected Alert popup;
    protected Random random;

    // constructor
    public BasePageFunctions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.siteURL = EnvReader.getEnvValue("SITE_URL");
    }

    // navigate to URL
    public Boolean navigateToURL(String url) {
        try {
            String targetUrl = url != null ? url : this.siteURL;
            logger.info("Navigating to URL: {}", targetUrl);
            driver.navigate().to(targetUrl);
            return true;
        } catch (Exception e) {
            logger.error("Failed to navigate to site: {}", e.getMessage());
            return false;
        }
    }

    // get back webElement
    public WebElement getWebElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            logger.error("Element not found: {} - {}", locator, e.getMessage());
            throw e;
        }
    }

    // wait for popup
    public boolean waitForPopUp() {
        try {
            logger.debug("Waiting for alert to be present");
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            logger.error("Alert did not appear within timeout period: {}", e.getMessage());
            return false;
        }
    }

    // accept popup
    public void acceptPopUp() {
        try {
            popup = driver.switchTo().alert();
            logger.debug("Accepting alert with text: {}", popup.getText());
            popup.accept();
        } catch (NoAlertPresentException e) {
            logger.error("No alert present to accept: {}", e.getMessage());
        }
    }

    // get the text of popup
    public String getTextFromPopUp() {
        try {
            popup = driver.switchTo().alert();
            String alertText = popup.getText();
            logger.debug("Retrieved text from alert: {}", alertText);
            return alertText;
        } catch (NoAlertPresentException e) {
            logger.error("No alert present to get text from: {}", e.getMessage());
            return null;
        }
    }

    // click on element
    public Boolean clickOnElement(By locator) {
        try {
            WebElement element = getWebElement(locator);
            logger.debug("Clicking on element: {}", locator);
            element.click();
            return true;
        } catch (Exception e) {
            logger.error("Failed to click on element {}: {}", locator, e.getMessage());
            return false;
        }
    }

            // click on element
    public Boolean clickOnElement(WebElement element) {
        try {
            logger.debug("Clicking on WebElement");
            element.click();
            return true;
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", e.getMessage());
            return false;
        }
    }

    // clear field and then type text
    public Boolean clearAndTypeTextToElem(By locator, String text) {
        try {
            WebElement textField = getWebElement(locator);
            logger.debug("Clearing and typing text to element {}: '{}'", locator, text);
            textField.clear();
            textField.sendKeys(text);
            return true;
        } catch (Exception e) {
            logger.error("Failed to clear and type text to element {}: {}", locator, e.getMessage());
            return false;
        }
    }

    // element to be clickable and click it
    public Boolean waitForElementToBeClickableAndClickIt(By locator) {
        try {
            logger.debug("Waiting for element to be clickable and clicking it: {}", locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            return true;
        } catch (Exception e) {
            logger.error("Element {} not clickable within timeout: {}", locator, e.getMessage());
            return false;
        }
    }

    // wait for element to be visible
    public Boolean waitForElementToBeVisible(By locator) {
        try {
            logger.debug("Waiting for element to be visible: {}", locator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            logger.error("Element {} not visible within timeout: {}", locator, e.getMessage());
            return false;
        }
    }

    public void smallWait() {
        logger.debug("Performing small wait of 2 seconds");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    }

    // get text from element
    public String getTextFromElement(By locator) {
        try {
            String text = getWebElement(locator).getText();
            logger.debug("Retrieved text from element {}: '{}'", locator, text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element {}: {}", locator, e.getMessage());
            return null;
        }
    }

    // select randomize element
    public Boolean selectRandomizeElement(By locator) {
        smallWait();
        try {
            List<WebElement> elementList = driver.findElements(locator);
            if (elementList.isEmpty()) {
                logger.warn("No elements found for locator: {}", locator);
                return false;
            } else {
                random = new Random();
                int randomIndex = random.nextInt(elementList.size());
                logger.info("Selecting random element {} from {} elements", randomIndex + 1, elementList.size());
                clickOnElement(elementList.get(randomIndex));
                return true;
            }
        } catch (Exception e) {
            logger.error("Failed to select random element {}: {}", locator, e.getMessage());
            return false;
        }
    }
}
