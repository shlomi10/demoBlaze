package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the home page
 *
 * @author Shlomi
 */

public class HomePage extends BasePageFunctions {

    // constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By signUpButton = By.id("signin2");
    By loginButton = By.id("login2");
    By userNameField = By.id("nameofuser");
    By galaxyS6 = By.xpath("//a[contains(text(), 'galaxy s6')]");
    By randomizeItem = By.cssSelector(".card-title");
    By logoutButton = By.id("logout2");


    // navigate to home page
    public Boolean getWebSite() {
        return navigateToURL(siteURL);
    }

    // select signup
    public Boolean goToSignUp() {
        return clickOnElement(signUpButton);
    }

    // select login
    public Boolean selectLoginBTN() {
        return clickOnElement(loginButton);
    }

    // select logout
    public Boolean selectLogoutBTN() {
        return clickOnElement(logoutButton);
    }

    // get the current connected user
    public String getCurrentConnectedUser(){
        waitForElementToBeVisible(userNameField);
        return getTextFromElement(userNameField).split(" ")[1];
    }

    // validate signup visible
    public String validateSignupVisible(){
        waitForElementToBeVisible(signUpButton);
        return getTextFromElement(signUpButton);
    }

    // select galaxy s6
    public void selectGalaxyS6() {
        smallWait();
        waitForElementToBeVisible(galaxyS6);
        waitForElementToBeClickableAndClickIt(galaxyS6);
    }

    // select randomize item
    public void selectRandomizeItem() {
        selectRandomizeElement(randomizeItem);
    }



}
