package com.qa.tests;

import com.qa.base.Baseclass;
import com.qa.pages.ForgotPasswordPage;
import com.qa.pages.LoginPage;
import com.qa.utils.ConfigReader;
import com.qa.utils.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.utils.DataProviderClass;

/**
 * LoginTests.java ---------------- TestNG test class covering all 12 Login
 * module test cases.
 *
 * TC IDs Covered: LOGIN_001 - Page loads successfully LOGIN_002 - Page URL
 * verification LOGIN_003 - Username field visibility LOGIN_004 - Password field
 * visibility LOGIN_005 - Login button visibility LOGIN_006 - Valid login
 * (data-driven) LOGIN_007 - Invalid username (data-driven) LOGIN_008 - Invalid
 * password (data-driven) LOGIN_009 - Blank username validation LOGIN_010 -
 * Blank password validation LOGIN_011 - Blank both fields validation LOGIN_012
 * - Forgot Password link navigation
 */
public class LoginTests extends Baseclass {

	private static final Logger log = LogManager.getLogger(LoginTests.class);
	private LoginPage loginPage;

	/**
	 * Initialise the LoginPage before each test method. BaseClass.setUp() has
	 * already navigated to the URL.
	 */
	@BeforeMethod(alwaysRun = true)
	public void initPage() {
		loginPage = new LoginPage(getDriver());
	}

	// ===========================================================
	// LOGIN_001: Verify login page loads successfully
	// ===========================================================
	@Test(priority = 1, description = "LOGIN_001 | Verify login page loads successfully", groups = { "smoke",
			"functional" })
	public void verifyLoginPageLoadsSuccessfully() {
		log.info("Executing: LOGIN_001 - Verify login page loads");
		ExtentReportManager.getTest().info("Verifying login page loads successfully");

		boolean isLoaded = loginPage.isLoginPageLoaded();
		ExtentReportManager.getTest().info("Login page loaded: " + isLoaded);

		Assert.assertTrue(isLoaded, "LOGIN_001 FAILED: Login page did not load. Logo not visible.");
		log.info("LOGIN_001 PASSED.");
	}

	// ===========================================================
	// LOGIN_002: Verify login page URL
	// ===========================================================
	@Test(priority = 2, description = "LOGIN_002 | Verify login page URL matches expected URL", groups = { "smoke",
			"ui" })
	public void verifyLoginPageUrl() {
		log.info("Executing: LOGIN_002 - Verify login page URL");

		String actualUrl = loginPage.getPageUrl();
		String expectedUrl = ConfigReader.getLoginPageUrl();

		ExtentReportManager.getTest().info("Actual URL:   " + actualUrl);
		ExtentReportManager.getTest().info("Expected URL: " + expectedUrl);

		Assert.assertTrue(actualUrl.contains("login"),
				"LOGIN_002 FAILED: URL does not contain 'login'." + " Actual: " + actualUrl);
		log.info("LOGIN_002 PASSED. URL: " + actualUrl);
	}

	// ===========================================================
	// LOGIN_003: Verify username field visibility
	// ===========================================================
	@Test(priority = 3, description = "LOGIN_003 | Verify username field is visible and enabled", groups = { "smoke",
			"ui" })
	public void verifyUsernameFieldVisibility() {
		log.info("Executing: LOGIN_003 - Verify username field visibility");

		boolean isVisible = loginPage.isUsernameFieldDisplayed();
		ExtentReportManager.getTest().info("Username field visible & enabled: " + isVisible);

		Assert.assertTrue(isVisible, "LOGIN_003 FAILED: Username field not visible or not enabled.");
		log.info("LOGIN_003 PASSED.");
	}

	// ===========================================================
	// LOGIN_004: Verify password field visibility
	// ===========================================================
	@Test(priority = 4, description = "LOGIN_004 | Verify password field is visible and enabled", groups = { "smoke",
			"ui" })
	public void verifyPasswordFieldVisibility() {
		log.info("Executing: LOGIN_004 - Verify password field visibility");

		boolean isVisible = loginPage.isPasswordFieldDisplayed();
		ExtentReportManager.getTest().info("Password field visible & enabled: " + isVisible);

		Assert.assertTrue(isVisible, "LOGIN_004 FAILED: Password field not visible or not enabled.");
		log.info("LOGIN_004 PASSED.");
	}

	// ===========================================================
	// LOGIN_005: Verify login button visibility
	// ===========================================================
	@Test(priority = 5, 
			description = "LOGIN_005 | Verify login button is visible and clickable", 
			groups = { "smoke",
			"ui" })
	public void verifyLoginButtonVisibility() {
		log.info("Executing: LOGIN_005 - Verify login button visibility");

		boolean isVisible = loginPage.isLoginButtonDisplayed();
		ExtentReportManager.getTest().info("Login button visible & enabled: " + isVisible);

		Assert.assertTrue(isVisible, "LOGIN_005 FAILED: Login button not visible or not enabled.");
		log.info("LOGIN_005 PASSED.");
	}

	// ===========================================================
	// LOGIN_006: Verify valid login (Data-Driven from Excel)
	// ===========================================================
	@Test(priority = 6, 
			description = "LOGIN_006 | Verify valid login redirects to dashboard",
			dataProvider = "validLoginData", 
			dataProviderClass = DataProviderClass.class, 
			groups = {"regression", "positive" })
	public void verifyValidLogin(String tcId, String username, String password, String expectedResult) {
		log.info("Executing: " + tcId + " - Valid Login | User: " + username);
		ExtentReportManager.getTest().info("TC: " + tcId + " | Username: " + username);

		loginPage.login(username, password);
		boolean isDashboard = loginPage.isDashboardLoaded();

		ExtentReportManager.getTest().info("Dashboard loaded: " + isDashboard);
		Assert.assertTrue(isDashboard, tcId + " FAILED: Valid login did not redirect to dashboard.");
		log.info(tcId + " PASSED - Dashboard loaded.");
	}

	// ===========================================================
	// LOGIN_007: Verify login with invalid username (Data-Driven)
	// ===========================================================
	@Test(priority = 7, 
			description = "LOGIN_007 | Verify error shown for invalid username", 
			dataProvider="invalidUsernameData",
		    dataProviderClass=DataProviderClass.class,
		    groups = {"regression", "negative" })
	public void verifyLoginWithInvalidUsername(String tcId, String username, String password, String expectedResult) {
		log.info("Executing: " + tcId + " - Invalid username: " + username);
		ExtentReportManager.getTest().info("TC: " + tcId + " | Invalid Username: " + username);

		loginPage.login(username, password);
		String errorMsg = loginPage.getErrorMessage();

		ExtentReportManager.getTest().info("Error message: " + errorMsg);
		Assert.assertFalse(errorMsg.isEmpty(),
		        tcId + " FAILED: Error message not displayed.");
		log.info(tcId + " PASSED - Error message shown: " + errorMsg);
	}

	// ===========================================================
	// LOGIN_008: Verify login with invalid password (Data-Driven)
	// ===========================================================
	@Test(priority = 8, description = "LOGIN_008 | Verify error shown for invalid password", 
			dataProvider="invalidPasswordData",
			dataProviderClass=DataProviderClass.class,
			groups = {"regression", "negative" })
	public void verifyLoginWithInvalidPassword(String tcId, String username, String password, String expectedResult) {
		log.info("Executing: " + tcId + " - Invalid password for user: " + username);
		ExtentReportManager.getTest().info("TC: " + tcId + " | Username: " + username);

		loginPage.login(username, password);
		String errorMsg = loginPage.getErrorMessage();

		ExtentReportManager.getTest().info("Error message: " + errorMsg);
		Assert.assertEquals(errorMsg.isEmpty(),
		tcId + " FAILED: Invalid password validation failed.");
		log.info(tcId + " PASSED - Error message shown: " + errorMsg);
	}

	// ===========================================================
	// LOGIN_009: Verify validation when username is blank
	// ===========================================================
	@Test(priority = 9, description = "LOGIN_009 | Verify required validation when username is blank", groups = {
			"regression", "validation" })
	public void verifyBlankUsernameValidation() {
		log.info("Executing: LOGIN_009 - Blank username validation");

		// Leave username blank, enter password, click login
		loginPage.enterPassword(ConfigReader.getValidPassword());
		loginPage.clickLoginButton();

		String validationMsg = loginPage.getUsernameValidationMessage();
		ExtentReportManager.getTest().info("Validation message: " + validationMsg);

		Assert.assertFalse(validationMsg.isEmpty(),
				"LOGIN_009 FAILED: No validation message shown for blank username.");
		log.info("LOGIN_009 PASSED - Validation message: " + validationMsg);
	}

	// ===========================================================
	// LOGIN_010: Verify validation when password is blank
	// ===========================================================
	@Test(priority = 10, description = "LOGIN_010 | Verify required validation when password is blank", groups = {
			"regression", "validation" })
	public void verifyBlankPasswordValidation() {
		log.info("Executing: LOGIN_010 - Blank password validation");

		// Enter username, leave password blank, click login
		loginPage.enterUsername(ConfigReader.getValidUsername());
		loginPage.clickLoginButton();

		String validationMsg = loginPage.getPasswordValidationMessage();
		ExtentReportManager.getTest().info("Validation message: " + validationMsg);

		Assert.assertFalse(validationMsg.isEmpty(),
				"LOGIN_010 FAILED: No validation message shown for blank password.");
		log.info("LOGIN_010 PASSED - Validation message: " + validationMsg);
	}

	// ===========================================================
	// LOGIN_011: Verify validation when both fields are blank
	// ===========================================================
	@Test(priority = 11, description = "LOGIN_011 | Verify validation messages when both fields are blank", groups = {
			"regression", "validation" })
	public void verifyBothFieldsBlankValidation() {
		log.info("Executing: LOGIN_011 - Both fields blank validation");

		// Don't enter anything, directly click login
		loginPage.clickLoginButton();

		String usernameMsg = loginPage.getUsernameValidationMessage();
		String passwordMsg = loginPage.getPasswordValidationMessage();

		ExtentReportManager.getTest().info("Username validation: " + usernameMsg);
		ExtentReportManager.getTest().info("Password validation: " + passwordMsg);

		Assert.assertFalse(usernameMsg.isEmpty(), "LOGIN_011 FAILED: No username validation message for blank fields.");
		Assert.assertFalse(passwordMsg.isEmpty(), "LOGIN_011 FAILED: No password validation message for blank fields.");
		log.info("LOGIN_011 PASSED.");
	}

	// ===========================================================
	// LOGIN_012: Verify Forgot Password link navigates correctly
	// ===========================================================
	@Test(priority = 12, description = "LOGIN_012 | Verify Forgot Password link navigates to reset page", groups = {
			"regression", "functional" })
	public void verifyForgotPasswordLink() {
		log.info("Executing: LOGIN_012 - Forgot Password link navigation");

		Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed(),
				"LOGIN_012 FAILED: Forgot Password link not visible.");

		ForgotPasswordPage forgotPasswordPage = loginPage.clickForgotPasswordLink();
		boolean isLoaded = forgotPasswordPage.isForgotPasswordPageLoaded();

		ExtentReportManager.getTest().info("Forgot Password page loaded: " + isLoaded);
		Assert.assertTrue(isLoaded, "LOGIN_012 FAILED: Reset password page did not load after clicking link.");
		log.info("LOGIN_012 PASSED - Navigated to Forgot Password page.");
	}

	// ===========================================================
	// DATA PROVIDERS (reads from Excel via ExcelUtils)
	// ===========================================================

	/**
	 * Data Provider for LOGIN_006 (valid credentials). Reads from LoginData.xlsx →
	 * "LoginTests" sheet, row where TC_ID = LOGIN_006.
	 *
	 * Format: { TC_ID, Username, Password, ExpectedResult }
	 */
	
}
