package com.qa.tests;

import com.qa.base.Baseclass;
import com.qa.pages.ForgotPasswordPage;
import com.qa.pages.LoginPage;
import com.qa.utils.ConfigReader;
import com.qa.utils.DataProviderClass;
import com.qa.utils.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * ForgotPasswordTests.java
 * -------------------------
 * TestNG test class covering all 12 Forgot Password module test cases.
 *
 * TC IDs Covered:
 *  FP_001 - Reset page loads successfully
 *  FP_002 - Page URL verification
 *  FP_003 - Reset Password heading visibility
 *  FP_004 - Instruction text visibility
 *  FP_005 - Username input visibility
 *  FP_006 - Cancel button visibility
 *  FP_007 - Reset Password button visibility
 *  FP_008 - Reset with valid username (data-driven)
 *  FP_009 - Reset with invalid username (data-driven)
 *  FP_010 - Blank username validation
 *  FP_011 - Cancel button navigates to login
 *  FP_012 - Valid characters accepted in username field
 */
public class ForgotPasswordTests extends Baseclass {

    private static final Logger log = LogManager.getLogger(ForgotPasswordTests.class);

    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;

    /**
     * Navigate to Forgot Password page before each test.
     * Flow: Open app → Login page → Click Forgot Password → Forgot Password page.
     */
    @BeforeMethod(alwaysRun = true)
    public void navigateToForgotPasswordPage() {
        loginPage = new LoginPage(getDriver());
        forgotPasswordPage = loginPage.clickForgotPasswordLink();
        log.info("Navigated to Forgot Password page via Login page link.");
    }

    // ===========================================================
    // FP_001: Verify reset password page loads successfully
    // ===========================================================
    @Test(priority = 1,
          description = "FP_001 | Verify reset password page loads successfully",
          groups = {"smoke", "functional"})
    public void verifyForgotPasswordPageLoads() {
        log.info("Executing: FP_001 - Verify Forgot Password page loads");

        boolean isLoaded = forgotPasswordPage.isForgotPasswordPageLoaded();
        ExtentReportManager.getTest().info("Page loaded: " + isLoaded);

        Assert.assertTrue(isLoaded,
            "FP_001 FAILED: Reset Password page did not load successfully.");
        log.info("FP_001 PASSED.");
    }

    // ===========================================================
    // FP_002: Verify page URL
    // ===========================================================
    @Test(priority = 2,
          description = "FP_002 | Verify forgot password page URL matches expected",
          groups = {"smoke", "ui"})
    public void verifyForgotPasswordPageUrl() {
        log.info("Executing: FP_002 - Verify Forgot Password page URL");

        String actualUrl   = forgotPasswordPage.getPageUrl();
        String expectedUrl = ConfigReader.getForgotPasswordUrl();

        ExtentReportManager.getTest().info("Actual URL:   " + actualUrl);
        ExtentReportManager.getTest().info("Expected URL: " + expectedUrl);

        Assert.assertTrue(actualUrl.contains("requestPasswordResetCode"),
            "FP_002 FAILED: URL does not match expected. Actual: " + actualUrl);
        log.info("FP_002 PASSED. URL: " + actualUrl);
    }

    // ===========================================================
    // FP_003: Verify Reset Password heading visibility
    // ===========================================================
    @Test(priority = 3,
          description = "FP_003 | Verify Reset Password heading is visible",
          groups = {"smoke", "ui"})
    public void verifyResetPasswordHeadingVisibility() {
        log.info("Executing: FP_003 - Verify heading visibility");

        String headingText = forgotPasswordPage.getResetPasswordHeadingText();
        ExtentReportManager.getTest().info("Heading text: " + headingText);

        Assert.assertFalse(headingText.isEmpty(),
            "FP_003 FAILED: Reset Password heading is not visible.");
        Assert.assertTrue(headingText.toLowerCase().contains("reset"),
            "FP_003 FAILED: Heading does not contain 'reset'. Actual: " + headingText);
        log.info("FP_003 PASSED. Heading: " + headingText);
    }

    // ===========================================================
    // FP_004: Verify instruction text visibility
    // ===========================================================
    @Test(priority = 4,
          description = "FP_004 | Verify instruction text is visible on the page",
          groups = {"ui"})
    public void verifyInstructionTextVisibility() {
        log.info("Executing: FP_004 - Verify instruction text visibility");

        String instructionText = forgotPasswordPage.getInstructionText();
        ExtentReportManager.getTest().info("Instruction text: " + instructionText);

        Assert.assertFalse(instructionText.isEmpty(),
            "FP_004 FAILED: Instruction text is not visible on the page.");
        log.info("FP_004 PASSED. Instruction text visible: " + instructionText);
    }

    // ===========================================================
    // FP_005: Verify username input visibility
    // ===========================================================
    @Test(priority = 5,
          description = "FP_005 | Verify username input field is visible and enabled",
          groups = {"smoke", "ui"})
    public void verifyUsernameFieldVisibility() {
        log.info("Executing: FP_005 - Verify username field visibility");

        boolean isVisible = forgotPasswordPage.isUsernameFieldDisplayed();
        ExtentReportManager.getTest().info("Username field visible & enabled: " + isVisible);

        Assert.assertTrue(isVisible,
            "FP_005 FAILED: Username input field is not visible or not enabled.");
        log.info("FP_005 PASSED.");
    }

    // ===========================================================
    // FP_006: Verify Cancel button visibility
    // ===========================================================
    @Test(priority = 6,
          description = "FP_006 | Verify Cancel button is visible and clickable",
          groups = {"smoke", "ui"})
    public void verifyCancelButtonVisibility() {
        log.info("Executing: FP_006 - Verify Cancel button visibility");

        boolean isVisible = forgotPasswordPage.isCancelButtonDisplayed();
        ExtentReportManager.getTest().info("Cancel button visible & enabled: " + isVisible);

        Assert.assertTrue(isVisible,
            "FP_006 FAILED: Cancel button is not visible or not enabled.");
        log.info("FP_006 PASSED.");
    }

    // ===========================================================
    // FP_007: Verify Reset Password button visibility
    // ===========================================================
    @Test(priority = 7,
          description = "FP_007 | Verify Reset Password button is visible and clickable",
          groups = {"smoke", "ui"})
    public void verifyResetPasswordButtonVisibility() {
        log.info("Executing: FP_007 - Verify Reset Password button visibility");

        boolean isVisible = forgotPasswordPage.isResetPasswordButtonDisplayed();
        ExtentReportManager.getTest().info("Reset Password button visible & enabled: " + isVisible);

        Assert.assertTrue(isVisible,
            "FP_007 FAILED: Reset Password button is not visible or not enabled.");
        log.info("FP_007 PASSED.");
    }

    // ===========================================================
    // FP_008: Verify reset request with valid username (Data-Driven)
    // ===========================================================
    @Test(priority = 8,
          description = "FP_008 | Verify reset request submitted successfully with valid username",      
          dataProvider = "validResetData",
          dataProviderClass = DataProviderClass.class,

          groups = {"regression", "positive"})
    public void verifyResetWithValidUsername(String tcId, String username,
                                              String expectedResult) {
        log.info("Executing: " + tcId + " - Valid username reset | User: " + username);
        ExtentReportManager.getTest().info("TC: " + tcId + " | Username: " + username);

        forgotPasswordPage.submitResetRequest(username);

        // After valid reset, OrangeHRM shows a "Reset Password link sent" confirmation
        String successMsg = forgotPasswordPage.getSuccessMessage();
        ExtentReportManager.getTest().info("Success message: " + successMsg);

        Assert.assertEquals(successMsg.trim(), expectedResult.trim(),
            tcId + " FAILED: No success/confirmation message shown after valid reset.");
        log.info(tcId + " PASSED - Success message: " + successMsg);
    }

    // ===========================================================
    // FP_009: Verify reset request with invalid username (Data-Driven)
    // ===========================================================
    @Test(priority = 9,
    	      description = "FP_009 | Verify proper validation shown for invalid username",
    	      dataProvider = "invalidResetData",
    	      dataProviderClass = DataProviderClass.class,
    	      groups = {"regression", "negative"})
    public void verifyResetWithInvalidUsername(String tcId, String username,
                                                String expectedResult) {
        log.info("Executing: " + tcId + " - Invalid username: " + username);
        ExtentReportManager.getTest().info("TC: " + tcId + " | Invalid Username: " + username);

        forgotPasswordPage.submitResetRequest(username);

        // OrangeHRM may show a success-style message even for invalid users (security best practice)
        // OR may show an error. Verify some response appears.
        String responseMsg = forgotPasswordPage.getSuccessMessage();
        ExtentReportManager.getTest().info("Response message: " + responseMsg);

        Assert.assertEquals(responseMsg.trim(), expectedResult.trim(),
            tcId + " FAILED: No response message shown for invalid username.");
        log.info(tcId + " PASSED - Response shown: " + responseMsg);
    }

    // ===========================================================
    // FP_010: Verify blank username validation
    // ===========================================================
    @Test(priority = 10,
          description = "FP_010 | Verify required validation shown when username is blank",
          groups = {"regression", "validation"})
    public void verifyBlankUsernameValidation() {
        log.info("Executing: FP_010 - Blank username validation");

        // Don't enter anything, click Reset Password directly
        forgotPasswordPage.clickResetPasswordButton();

        String validationMsg = forgotPasswordPage.getUsernameValidationMessage();
        ExtentReportManager.getTest().info("Validation message: " + validationMsg);

        Assert.assertFalse(validationMsg.isEmpty(),
            "FP_010 FAILED: No validation message shown for blank username.");
        log.info("FP_010 PASSED - Validation message: " + validationMsg);
    }

    // ===========================================================
    // FP_011: Verify Cancel button navigates back to Login page
    // ===========================================================
    @Test(priority = 11,
          description = "FP_011 | Verify Cancel button navigates back to Login page",
          groups = {"regression", "functional"})
    public void verifyCancelButtonNavigation() {
        log.info("Executing: FP_011 - Cancel button navigation");

        forgotPasswordPage.clickCancelButton();

        boolean isOnLoginPage = forgotPasswordPage.isRedirectedToLoginPage();
        ExtentReportManager.getTest().info("Redirected to Login page: " + isOnLoginPage);

        Assert.assertTrue(isOnLoginPage,
            "FP_011 FAILED: Cancel did not navigate back to Login page.");
        log.info("FP_011 PASSED - Navigated back to Login page.");
    }

    // ===========================================================
    // FP_012: Verify username field accepts valid characters
    // ===========================================================
    @Test(priority = 12,
          description = "FP_012 | Verify username field accepts valid alphanumeric characters",
        		  dataProvider="validCharData",
        		  dataProviderClass=DataProviderClass.class,
          groups = {"regression", "validation"})
    public void verifyUsernameAcceptsValidCharacters(String tcId, String username,
                                                      String expectedResult) {
        log.info("Executing: " + tcId + " - Valid characters test | Input: " + username);
        ExtentReportManager.getTest().info("TC: " + tcId + " | Username input: " + username);

        forgotPasswordPage.enterUsername(username);

        // Verify the Reset Password button is still clickable after input
        boolean buttonEnabled = forgotPasswordPage.isResetPasswordButtonDisplayed();
        ExtentReportManager.getTest().info("Reset button still enabled: " + buttonEnabled);

        Assert.assertTrue(buttonEnabled,
            tcId + " FAILED: Reset button not available after entering valid characters.");
        log.info(tcId + " PASSED - Valid characters accepted.");
    }

    
  
}
