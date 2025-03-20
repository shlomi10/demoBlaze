package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the login page
 *
 * @author Shlomi
 */

public class LoginPage extends BasePageFunctions {

    // constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By usernameField = By.id("sign-username");
    By passwordField = By.id("sign-password");
    By signupBtn = By.xpath("//button[@onclick='register()']");
    By loginUsernameField = By.id("loginusername");
    By loginPasswordField = By.id("loginpassword");
    By loginBtn = By.xpath("//button[@onclick='logIn()']");

    // enter username
    public boolean enterUsername(String userName) {
        waitForElementToBeVisible(usernameField);
        return clearAndTypeTextToElem(usernameField, userName);
    }

    // enter password
    public boolean enterPassword(String password) {
        waitForElementToBeVisible(passwordField);
        return clearAndTypeTextToElem(passwordField, password);
    }

    // click sign up
    public boolean clickSignUp() {
        return waitForElementToBeClickableAndClickIt(signupBtn);
    }

    // enter username in login window
    public boolean enterLoginUsername(String userName) {
        waitForElementToBeVisible(loginUsernameField);
        return clearAndTypeTextToElem(loginUsernameField, userName);
    }

    // enter password in login window
    public boolean enterLoginPassword(String password) {
        waitForElementToBeVisible(loginPasswordField);
        return clearAndTypeTextToElem(loginPasswordField, password);
    }

    // click login
    public boolean clickLogin() {
        return waitForElementToBeClickableAndClickIt(loginBtn);
    }

    // get text from alert box
    public String getTextFromAlert() {
        waitForPopUp();
        return getTextFromPopUp();
    }
}
