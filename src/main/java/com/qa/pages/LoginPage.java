/*package com.qa.pages;

import com.qa.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;*/

/**
 * LoginPage.java
 * ---------------
 * Page Object Model class for the Login page.
 * Uses Page Factory (@FindBy) to initialise WebElements lazily.
 *
 * Covers TC IDs: LOGIN_001 to LOGIN_012
 *
 * HOW TO ADD LOCATORS:
 * --------------------
 * @FindBy(id = "username")               → by ID
 * @FindBy(name = "username")             → by name attribute
 * @FindBy(xpath = "//input[@id='user']") → by XPath
 * @FindBy(css = "input#username")        → by CSS Selector
 * @FindBy(className = "login-btn")       → by Class Name
 * @FindBy(linkText = "Forgot Password")  → by exact link text
 */

/*public class LoginPage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private final WebDriver driver;*/

    // =====================================================================
    // LOGIN PAGE ELEMENTS — ADD YOUR LOCATORS BELOW
    // =====================================================================

    // TC: LOGIN_003 — Username input field
    
  /*  @FindBy(name = "username")
    private WebElement usernameField;

    // TC: LOGIN_004 — Password input field
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    // TC: LOGIN_005 — Login / Submit button
    @FindBy(xpath="//button[@type='submit']")
    private WebElement loginButton;

    // TC: LOGIN_007, LOGIN_008 — Error message shown after invalid credentials
    // e.g. "Invalid username or password"
    @FindBy(xpath="//p[text()='Invalid credentials']")
    private WebElement errorMessage;

    // TC: LOGIN_009 — Validation message under username field when left blank
    // e.g. "Required" or "This field is required"
    @FindBy(xpath = "//input[@name='username']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']")
    private WebElement usernameValidationMsg;

    // TC: LOGIN_010 — Validation message under password field when left blank
    @FindBy(xpath = "//input[@type='password']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']")
    private WebElement passwordValidationMsg;

    // TC: LOGIN_012 — "Forgot Password" clickable link on login page
    @FindBy(xpath="//p[text()='Forgot your password? ']")
    private WebElement forgotPasswordLink;

    // TC: LOGIN_001 — Any element that confirms login page has loaded
    // e.g. logo image, login form container, page heading
    @FindBy(xpath = "//img[@alt='client brand banner']")
    private WebElement loginPageIdentifier;

    // ----- CONSTRUCTOR -----

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("LoginPage initialised.");
    }

    /*=====================================================================
     ACTION METHODS —
    =====================================================================*/

   /* public void enterUsername(String username) {
        WebElement ele = WaitUtils.waitForVisibility(driver, usernameField);
        ele.clear();
        ele.sendKeys(username);
        log.debug("Entered username: " + username);
    }

    public void enterPassword(String password) {
        WebElement ele = WaitUtils.waitForVisibility(driver, passwordField);
        ele.clear();
        ele.sendKeys(password);
        log.debug("Entered password: [hidden]");
    }

    public void clickLoginButton() {
        WebElement ele = WaitUtils.waitForClickability(driver, loginButton);
        ele.click();
        log.info("Clicked Login button.");
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public ForgotPasswordPage clickForgotPasswordLink() {
        WebElement ele = WaitUtils.waitForClickability(driver, forgotPasswordLink);
        ele.click();
        log.info("Clicked Forgot Password link.");
        return new ForgotPasswordPage();
    }

    // =====================================================================
    // VERIFICATION METHODS —
    // =====================================================================

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isLoginPageLoaded() {
        try {
            WaitUtils.waitForVisibility(driver, loginPageIdentifier);
            return loginPageIdentifier.isDisplayed();
        } catch (Exception e) {
            log.error("Login page not loaded: " + e.getMessage());
            return false;
        }
    }

    public boolean isUsernameFieldDisplayed() {
        boolean visible = usernameField.isDisplayed();
        boolean enabled = usernameField.isEnabled();
        log.debug("Username field - displayed: " + visible + " | enabled: " + enabled);
        return visible && enabled;
    }

    public boolean isPasswordFieldDisplayed() {
        boolean visible = passwordField.isDisplayed();
        boolean enabled = passwordField.isEnabled();
        log.debug("Password field - displayed: " + visible + " | enabled: " + enabled);
        return visible && enabled;
    }

    public boolean isLoginButtonDisplayed() {
        boolean visible = loginButton.isDisplayed();
        boolean enabled = loginButton.isEnabled();
        log.debug("Login button - displayed: " + visible + " | enabled: " + enabled);
        return visible && enabled;
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return forgotPasswordLink.isDisplayed();
    }

    public String getErrorMessage() {
        WaitUtils.waitForVisibility(driver, errorMessage);
        String msg = errorMessage.getText().trim();
        log.debug("Error message text: " + msg);
        return msg;
    }

    public String getUsernameValidationMessage() {
        WaitUtils.waitForVisibility(driver, usernameValidationMsg);
        return usernameValidationMsg.getText().trim();
    }

    public String getPasswordValidationMessage() {
        WaitUtils.waitForVisibility(driver, passwordValidationMsg);
        return passwordValidationMsg.getText().trim();
    }

    public boolean isDashboardLoaded() {
        return WaitUtils.waitForUrlContains(driver, "dashboard");
    }
}*/
