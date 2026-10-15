package com.qa.pages;

import com.qa.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * ForgotPasswordPage.java
 * ------------------------
 * Page Object Model class for the Forgot Password / Reset Password page.
 * Uses Page Factory (@FindBy) to initialise WebElements lazily.
 *
 * Covers TC IDs: FP_001 to FP_012
 *
 * HOW TO ADD LOCATORS:
 * --------------------
 * @FindBy(id = "username")               → by ID
 * @FindBy(name = "username")             → by name attribute
 * @FindBy(xpath = "//input[@id='user']") → by XPath
 * @FindBy(css = "input#username")        → by CSS Selector
 * @FindBy(className = "reset-btn")       → by Class Name
 * @FindBy(linkText = "Cancel")           → by exact link text
 */
public class ForgotPasswordPage {

    private static final Logger log = LogManager.getLogger(ForgotPasswordPage.class);
    private WebDriver driver;

    // =====================================================================
    // FORGOT PASSWORD PAGE ELEMENTS — ADD YOUR LOCATORS BELOW
    // =====================================================================

    // TC: FP_001, FP_003 — Any element confirming this page is loaded
    // e.g. the "Reset Password" heading / title text element
  
    @FindBy(xpath = "//h6[text()='Reset Password']")
    private WebElement resetPasswordHeading;
    
    // TC: FP_004 — Instruction text below the heading
    // e.g. "Please enter your username to reset your password"
    
    @FindBy(xpath = "//p[text()='Please enter your username to identify your account to reset your password']")
    private WebElement instructionText;
  

    // TC: FP_005 — Username input field on the reset page
    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameField;

    // TC: FP_006 — Cancel button (navigates back to Login)
    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement cancelButton;

    // TC: FP_007 — Reset Password submit button
    @FindBy(xpath = "//button[normalize-space()='Reset Password']")
    private WebElement resetPasswordButton;

    // TC: FP_010 — Validation message shown when username is left blank
    // e.g. "Required" or "This field cannot be empty"
    @FindBy(xpath = "//span[text()='Required']")
    private WebElement usernameValidationMsg;

    // TC: FP_008, FP_009 — Success or response message after reset is submitted
    // e.g. "Reset link sent" or "If username exists, email has been sent"
    @FindBy(xpath = "//h6[text()='Reset Password link sent successfully']")
    private WebElement successMessage;

    // =====================================================================
    // CONSTRUCTOR
    // =====================================================================

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("ForgotPasswordPage initialised.");
    }

    // =====================================================================
    // ACTION METHODS — No changes needed here after adding locators above
    // =====================================================================

    public void enterUsername(String username) {
        WebElement ele = WaitUtils.waitForVisibility(driver, usernameField);
        ele.clear();
        ele.sendKeys(username);
        log.debug("Entered username: " + username);
    }

    public void clickResetPasswordButton() {
        WebElement ele = WaitUtils.waitForClickability(driver, resetPasswordButton);
        ele.click();
        log.info("Clicked Reset Password button.");
    }

    public LoginPage clickCancelButton() {
        WebElement ele = WaitUtils.waitForClickability(driver, cancelButton);
        ele.click();
        log.info("Clicked Cancel button.");
        return new LoginPage(driver);
    }

    public void submitResetRequest(String username) {
        enterUsername(username);
        clickResetPasswordButton();
    }

    // =====================================================================
    // VERIFICATION METHODS — No changes needed here
    // =====================================================================

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isForgotPasswordPageLoaded() {
        try {
            WaitUtils.waitForVisibility(driver, resetPasswordHeading);
            return resetPasswordHeading.isDisplayed();
        } catch (Exception e) {
            log.error("ForgotPassword page not loaded: " + e.getMessage());
            return false;
        }
    }

    public String getResetPasswordHeadingText() {
        WaitUtils.waitForVisibility(driver, resetPasswordHeading);
        return resetPasswordHeading.getText().trim();
    }

    public String getInstructionText() {
        WaitUtils.waitForVisibility(driver, instructionText);
        return instructionText.getText().trim();
    }

    public boolean isUsernameFieldDisplayed() {
        boolean visible = usernameField.isDisplayed();
        boolean enabled = usernameField.isEnabled();
        log.debug("FP Username field - displayed: " + visible + " | enabled: " + enabled);
        return visible && enabled;
    }

    public boolean isCancelButtonDisplayed() {
        boolean visible = cancelButton.isDisplayed();
        boolean enabled = cancelButton.isEnabled();
        log.debug("Cancel button - displayed: " + visible + " | enabled: " + enabled);
        return visible && enabled;
    }

    public boolean isResetPasswordButtonDisplayed() {
        boolean visible = resetPasswordButton.isDisplayed();
        boolean enabled = resetPasswordButton.isEnabled();
        log.debug("Reset Password button - displayed: " + visible + " | enabled: " + enabled);
        return visible && enabled;
    }

    public String getUsernameValidationMessage() {
        WaitUtils.waitForVisibility(driver, usernameValidationMsg);
        return usernameValidationMsg.getText().trim();
    }

    public String getSuccessMessage() {
        WaitUtils.waitForVisibility(driver, successMessage);
        return successMessage.getText().trim();
    }

    public boolean isRedirectedToLoginPage() {
        // TODO: Change "login" to the keyword that appears in YOUR app's login URL
        return WaitUtils.waitForUrlContains(driver, "login");
    }
}
