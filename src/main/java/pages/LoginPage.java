package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;
import utilities.EnvReader;

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
    String userName;
    String password;

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
    public boolean enterLoginUsername() {
        waitForElementToBeVisible(loginUsernameField);
        return clearAndTypeTextToElem(loginUsernameField,  EnvReader.getEnvValue("USERNAME"));
    }

    // enter password in login window
    public boolean enterLoginPassword() {
        waitForElementToBeVisible(loginPasswordField);
        return clearAndTypeTextToElem(loginPasswordField,  EnvReader.getEnvValue("PASSWORD"));
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
