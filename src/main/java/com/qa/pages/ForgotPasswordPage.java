package com.qa.pages;

import com.qa.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * ForgotPasswordPage.java ------------------------ Page Object Model class for
 * the Forgot Password / Reset Password page. Uses Page Factory (@FindBy) to
 * initialise WebElements lazily.
 *
 * Covers TC IDs: FP_001 to FP_012
 *
 * HOW TO ADD LOCATORS: --------------------
 * 
 * @FindBy(id = "username") → by ID
 * @FindBy(name = "username") → by name attribute
 * @FindBy(xpath = "//input[@id='user']") → by XPath
 * @FindBy(css = "input#username") → by CSS Selector
 * @FindBy(className = "reset-btn") → by Class Name
 * @FindBy(linkText = "Cancel") → by exact link text
 */
public class ForgotPasswordPage {

	private final WebDriver driver;
	private static final Logger log = LogManager.getLogger(ForgotPasswordPage.class);

	// ===================== CONSTRUCTOR =====================

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===================== ELEMENTS =====================

	// TC: TC_FPW_062 — Page identifier
	@FindBy(xpath = "//h6[text()='Reset Password']")
	private WebElement resetPasswordHeading;

	// TC: TC_FPW_062
	@FindBy(xpath = "//p[text()='Please enter your username to identify your account to reset your password']")
	private WebElement instructionText;

	// TC: TC_FPW_062, 065, 066, 067
	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameField;

	// TC: TC_FPW_065, 067 — 'Required' message
	@FindBy(xpath = "//span[text()='Required']")
	private WebElement usernameError;

	// TC: TC_FPW_062, 069
	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	private WebElement cancelButton;

	// TC: TC_FPW_062, 065, 066, 067
	@FindBy(xpath = "//button[normalize-space()='Reset Password']")
	private WebElement resetPasswordButton;

	// TC: TC_FPW_066 — Message shown after successful submit
	@FindBy(xpath = "//h6[text()='Reset Password link sent successfully']")
	private WebElement resetLinkSentMessage;

	// ===================== PAGE STATE =====================

	public boolean isResetPasswordPageLoaded() {
		try {
			WaitUtils.waitForVisibility(driver, resetPasswordHeading);
			return resetPasswordHeading.isDisplayed();
		} catch (Exception e) {
			log.error("Reset Password page not loaded: " + e.getMessage());
			return false;
		}
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getHeadingText() {
		WaitUtils.waitForVisibility(driver, resetPasswordHeading);
		return resetPasswordHeading.getText().trim();
	}

	public String getInstructionText() {
		WaitUtils.waitForVisibility(driver, instructionText);
		return instructionText.getText().trim();
	}

	public boolean isUsernameFieldDisplayed() {
		return isDisplayed(usernameField);
	}

	public boolean isCancelButtonDisplayed() {
		return isDisplayed(cancelButton);
	}

	public boolean isResetPasswordButtonDisplayed() {
		boolean visible = resetPasswordButton.isDisplayed();
		boolean enabled = resetPasswordButton.isEnabled();
		log.debug("Reset Password button - displayed: " + visible + " | enabled: " + enabled);
		return visible && enabled;
	}
	
	// ===================== VALIDATIONS =====================

		public boolean isUsernameErrorDisplayed() {
			return isDisplayed(usernameError);
		}

		public String getUsernameErrorText() {
			WaitUtils.waitForVisibility(driver, usernameError);
			return usernameError.getText().trim();
		}

		public boolean isResetLinkSentMessageDisplayed() {
			return isDisplayed(resetLinkSentMessage);
		}

	// ===================== ACTIONS =====================

	public void enterUsername(String username) {
		WaitUtils.waitForVisibility(driver, usernameField);
		usernameField.clear();
		usernameField.sendKeys(username);
		log.info("Entered username on Reset Password page");
	}

	public void clickResetPassword() {
		WaitUtils.waitForVisibility(driver, resetPasswordButton);
		resetPasswordButton.click();
		log.info("Clicked Reset Password button");
	}

	public Login_Page clickCancel() {
		WaitUtils.waitForVisibility(driver, cancelButton);
		cancelButton.click();
		log.info("Clicked Cancel button");
		return new Login_Page(driver);
	}


	// ===================== HELPERS =====================

	private boolean isDisplayed(WebElement element) {
		try {
			WaitUtils.waitForVisibility(driver, element);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
