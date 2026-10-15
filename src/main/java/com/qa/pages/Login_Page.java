package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utils.WaitUtils;

/**
 * Page Object for the Login page.
 * 
 * Full TC mapping: 'Automation Test Cases' sheet.
 */
public class Login_Page {

	private static final Logger log = LogManager.getLogger(Login_Page.class);

	private static final String ATTR_PLACEHOLDER = "placeholder";
	private static final String ATTR_VALUE = "value";
	private static final String ATTR_TYPE = "type";

	private final WebDriver driver;

	// ===================== CONSTRUCTOR =====================

	public Login_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===================== ELEMENTS =====================

	// TC: TC_LGN_001 — Login page identifier
	@FindBy(xpath = "//img[@alt='client brand banner']")
	private WebElement loginPageIdentifier;

	// TC: TC_LGN_003 — Login heading
	@FindBy(xpath = "//div//h5[text()='Login']")
	private WebElement loginHeading;

	// TC: TC_LGN_004 — Username input field
	@FindBy(name = "username")
	private WebElement usernameField;

	// TC: TC_LGN_005 — Password input field
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;

	// TC: TC_LGN_006 — Login / Submit button
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	// TC: TC_LGN_007 — Forgot your password? link
	@FindBy(xpath = "//p[text()='Forgot your password? ']")
	private WebElement forgotPasswordLink;

	// TC: TC_LGN_021 — Invalid credentials error message
	@FindBy(xpath = "//p[text()='Invalid credentials']")
	private WebElement errorMessage;

	// TC: TC_LGN_028 — Username 'Required' validation message
	@FindBy(xpath = "//input[@name='username']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']")
	private WebElement usernameRequiredMessage;

	// TC: TC_LGN_029 — Password 'Required' validation message
	@FindBy(xpath = "//input[@type='password']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']")
	private WebElement passwordRequiredMessage;

	// ===================== PAGE =====================

	public boolean isLoginPageLoaded() {
		try {
			WaitUtils.waitForVisibility(driver, loginPageIdentifier);
			return loginPageIdentifier.isDisplayed();
		} catch (Exception e) {
			log.error("Login page not loaded: " + e.getMessage());
			return false;
		}
	}

	public String getPageTitle() {
		String title = driver.getTitle();
		log.debug("Page title: " + title);
		return title;
	}

	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		log.debug("Current URL: " + url);
		return url;
	}

	// ===================== HEADING =====================

	public boolean isLoginHeadingDisplayed() {
		WaitUtils.waitForVisibility(driver, loginHeading);
		boolean visible = loginHeading.isDisplayed();
		log.debug("Login heading - displayed: " + visible);
		return visible;
	}

	public String getLoginHeadingText() {
		WaitUtils.waitForVisibility(driver, loginHeading);
		String text = loginHeading.getText().trim();
		log.debug("Login heading text: " + text);
		return text;
	}

	// ===================== USERNAME =====================

	public boolean isUsernameFieldDisplayed() {
		WaitUtils.waitForVisibility(driver, usernameField);
		boolean visible = usernameField.isDisplayed();
		boolean enabled = usernameField.isEnabled();
		log.debug("Username field - displayed: " + visible + " | enabled: " + enabled);
		return visible && enabled;
	}

	public String getUsernamePlaceholder() {
		WaitUtils.waitForVisibility(driver, usernameField);
		String placeholder = usernameField.getAttribute(ATTR_PLACEHOLDER);
		log.debug("Username placeholder: " + placeholder);
		return placeholder;
	}

	public String getUsernameValue() {
		WaitUtils.waitForVisibility(driver, usernameField);
		String value = usernameField.getAttribute(ATTR_VALUE);
		log.debug("Username field value: " + value);
		return value;
	}

	public void clickUsernameField() {
		WaitUtils.waitForVisibility(driver, usernameField);
		usernameField.click();
		log.debug("Clicked on username field");
	}

	public void enterUsername(String username) {
		WaitUtils.waitForVisibility(driver, usernameField);
		usernameField.sendKeys(username);
		log.debug("Entered username: " + username);
	}

	public void clearUsername() {
		WaitUtils.waitForVisibility(driver, usernameField);
		usernameField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		log.debug("Cleared username field");
	}

	// ===================== PASSWORD =====================

	public boolean isPasswordFieldDisplayed() {
		WaitUtils.waitForVisibility(driver, passwordField);
		boolean visible = passwordField.isDisplayed();
		boolean enabled = passwordField.isEnabled();
		log.debug("Password field - displayed: " + visible + " | enabled: " + enabled);
		return visible && enabled;
	}

	public String getPasswordPlaceholder() {
		WaitUtils.waitForVisibility(driver, passwordField);
		String placeholder = passwordField.getAttribute(ATTR_PLACEHOLDER);
		log.debug("Password placeholder: " + placeholder);
		return placeholder;
	}

	public String getPasswordValue() {
		WaitUtils.waitForVisibility(driver, passwordField);
		log.debug("Read password field value");
		return passwordField.getAttribute(ATTR_VALUE);
	}

	public String getPasswordFieldType() {
		WaitUtils.waitForVisibility(driver, passwordField);
		String type = passwordField.getAttribute(ATTR_TYPE);
		log.debug("Password field type: " + type);
		return type;
	}

	public void clickPasswordField() {
		WaitUtils.waitForVisibility(driver, passwordField);
		passwordField.click();
		log.debug("Clicked on password field");
	}

	public void enterPassword(String password) {
		WaitUtils.waitForVisibility(driver, passwordField);
		passwordField.sendKeys(password);
		log.debug("Entered password: ******");
	}

	public void clearPassword() {
		WaitUtils.waitForVisibility(driver, passwordField);
		passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		log.debug("Cleared password field");
	}

	// ===================== LOGIN BUTTON / FLOWS =====================

	public boolean isLoginButtonDisplayed() {
		WaitUtils.waitForVisibility(driver, loginButton);
		boolean visible = loginButton.isDisplayed();
		boolean enabled = loginButton.isEnabled();
		log.debug("Login button - displayed: " + visible + " | enabled: " + enabled);
		return visible && enabled;
	}

	public void clickLoginButton() {
		WaitUtils.waitForVisibility(driver, loginButton);
		loginButton.click();
		log.debug("Clicked on Login button");
	}

	public void login(String username, String password) {
		log.info("Logging in with username: " + username);
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
	}

	public void loginUsingEnterKey(String username, String password) {
		log.info("Logging in using Enter key with username: " + username);
		enterUsername(username);
		enterPassword(password);
		passwordField.sendKeys(Keys.ENTER);
	}

	// ===================== FORGOT PASSWORD LINK =====================

	public boolean isForgotPasswordLinkDisplayed() {
		WaitUtils.waitForVisibility(driver, forgotPasswordLink);
		boolean visible = forgotPasswordLink.isDisplayed();
		log.debug("Forgot password link - displayed: " + visible);
		return visible;
	}

	public void clickForgotPasswordLink() {
		WaitUtils.waitForVisibility(driver, forgotPasswordLink);
		forgotPasswordLink.click();
		log.debug("Clicked on Forgot your password? link");
	}

	// ===================== ERROR / VALIDATION MESSAGES =====================

	public String getErrorMessage() {
		WaitUtils.waitForVisibility(driver, errorMessage);
		String msg = errorMessage.getText().trim();
		log.debug("Error message text: " + msg);
		return msg;
	}

	public boolean isErrorMessageDisplayed() {
		try {
			return errorMessage.isDisplayed();
		} catch (Exception e) {
			log.debug("Error message not present");
			return false;
		}
	}

	public String getUsernameRequiredMessage() {
		WaitUtils.waitForVisibility(driver, usernameRequiredMessage);
		String msg = usernameRequiredMessage.getText().trim();
		log.debug("Username validation message: " + msg);
		return msg;
	}

	public boolean isUsernameRequiredMessageDisplayed() {
		try {
			return usernameRequiredMessage.isDisplayed();
		} catch (Exception e) {
			log.debug("Username validation message not present");
			return false;
		}
	}

	public String getPasswordRequiredMessage() {
		WaitUtils.waitForVisibility(driver, passwordRequiredMessage);
		String msg = passwordRequiredMessage.getText().trim();
		log.debug("Password validation message: " + msg);
		return msg;
	}

	public boolean isPasswordRequiredMessageDisplayed() {
		try {
			return passwordRequiredMessage.isDisplayed();
		} catch (Exception e) {
			log.debug("Password validation message not present");
			return false;
		}
	}

	// ===================== KEYBOARD NAVIGATION =====================

	public void pressTab() {
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		log.debug("Pressed TAB key");
	}

	public boolean isUsernameFocused() {
		return driver.switchTo().activeElement().equals(usernameField);
	}

	public boolean isPasswordFocused() {
		return driver.switchTo().activeElement().equals(passwordField);
	}

	public boolean isLoginButtonFocused() {
		return driver.switchTo().activeElement().equals(loginButton);
	}

	public boolean isForgotPasswordLinkFocused() {
		return driver.switchTo().activeElement().equals(forgotPasswordLink);
	}
}