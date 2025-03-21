package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * this class represents the test runner
 *
 * @author Shlomi
 */

@Listeners({AllureTestNg.class, ScreenshotListener.class})
public class MainRunnerTest extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify user registration with valid details")
    @Feature("Registration Feature")
    @Story("User registers with valid details")
    @Step("User enters username, password, and other required details, then clicks register")
    @Test(priority = 1, description = "user registration test")
    public void userRegistrationTest() {
        homePage.getWebSite();
        homePage.goToSignUp();
        loginPage.enterUsername(faker.name().username());
        loginPage.enterPassword(faker.internet().password());
        loginPage.clickSignUp();
        String actualPopupText = loginPage.getTextFromAlert();
        String expectedPopupText = "Sign up successful.";
        Assert.assertEquals(actualPopupText, expectedPopupText, "Wrong popup text");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify user login with valid credentials")
    @Feature("Login Feature")
    @Story("User logs in with valid credentials")
    @Step("User enters username and password, then clicks login")
    @Test(priority = 2, description = "login test")
    public void loginTest() {
        homePage.getWebSite();
        homePage.selectLoginBTN();
        loginPage.enterLoginUsername("test");
        loginPage.enterLoginPassword("test");
        loginPage.clickLogin();
        String actualUser = homePage.getCurrentConnectedUser();
        Assert.assertEquals(actualUser, "test", "Wrong username or password");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that the user can add a specific product to the cart successfully")
    @Feature("Cart Feature")
    @Story("User adds a specific product to the cart")
    @Step("User selects a specific product and adds it to the shopping cart")
    @Test(priority = 3, description = "add specific product to cart test")
    public void addProductToCartTest() {
        homePage.getWebSite();
        homePage.selectLoginBTN();
        loginPage.enterLoginUsername("test");
        loginPage.enterLoginPassword("test");
        loginPage.clickLogin();
        homePage.getCurrentConnectedUser();
        homePage.selectGalaxyS6();
        productPage.addItemToCart();
        String actualPopupText = productPage.getTextFromAlert();
        String expectedPopupText = "Product added.";
        Assert.assertEquals(actualPopupText, expectedPopupText, "Item was not added to cart");
        productPage.acceptAlert();
        productPage.selectCart();
        String actualItemInTable = cartPage.getItemsNameInTable();
        String expectedItemInTable = "Samsung galaxy s6";
        Assert.assertEquals(actualItemInTable, expectedItemInTable, "Samsung galaxy s6 was not added to cart");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that the user can purchase randomized product successfully")
    @Feature("Purchase Feature")
    @Story("User purchases randomized product after adding it to the cart")
    @Step("User adds a randomized product to the cart and proceeds to checkout to purchase")
    @Test(priority = 4, description = "purchase randomized product test")
    public void purchaseRandomizedProductTest() {
        homePage.getWebSite();
        homePage.selectLoginBTN();
        loginPage.enterLoginUsername("test");
        loginPage.enterLoginPassword("test");
        loginPage.clickLogin();
        homePage.getCurrentConnectedUser();
        homePage.selectRandomizeItem();
        productPage.addItemToCart();
        String actualPopupText = productPage.getTextFromAlert();
        String expectedPopupText = "Product added.";
        Assert.assertEquals(actualPopupText, expectedPopupText, "Item was not added to cart");
        productPage.acceptAlert();
        productPage.selectCart();
        productPage.selectPlaceOrder();
        String randomMonthNumber = String.valueOf(faker.number().numberBetween(1, 13));
        String randomYear = String.valueOf(faker.number().numberBetween(1900, 2025));
        placeOrderPage.fillOrderDetails(faker.name().name(), faker.country().name(), faker.address().cityName(), faker.business().creditCardNumber(), randomMonthNumber, randomYear);
        placeOrderPage.waitForConfirmationWindow();
        Boolean isPurchaseMade = confirmationWindow.getConfirmationPurchase();
        Assert.assertTrue(isPurchaseMade, "The confirmation purchase was not successful");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that the user can log out successfully")
    @Feature("Authentication Feature")
    @Story("User logs out from the application after a successful login")
    @Step("User logs in, clicks the logout button, and verifies the session is terminated")
    @Test(priority = 5, description = "Logout test")
    public void logoutTest() {
        homePage.getWebSite();
        homePage.selectLoginBTN();
        loginPage.enterLoginUsername("test");
        loginPage.enterLoginPassword("test");
        loginPage.clickLogin();
        String actualUser = homePage.getCurrentConnectedUser();
        Assert.assertEquals(actualUser, "test", "Wrong username or password");
        homePage.selectLogoutBTN();
        String actualSignupBtnText = homePage.validateSignupVisible();
        String expectedSignupBtnText = "Sign up";
        Assert.assertEquals(actualSignupBtnText, expectedSignupBtnText, "Logout was not successful");
    }

}
