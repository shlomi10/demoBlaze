package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.*;

/**
 * this class represents the base of all tests
 * this will be before each test in the testNG xml
 *
 * @author Shlomi
 */

public class BaseTest{

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    PlaceOrderPage placeOrderPage;
    ConfirmationWindow confirmationWindow;
    Faker faker;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser) {

        // If browser parameter is not provided, default to chrome
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        // Initialize WebDriver based on browser parameter
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }

        faker = new Faker();

        // maximize the browser window
        driver.manage().window().maximize();

        // load main search page
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        placeOrderPage = new PlaceOrderPage(driver);
        confirmationWindow = new ConfirmationWindow(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.err.println("Exception during driver cleanup: " + e.getMessage());
        }
    }

}