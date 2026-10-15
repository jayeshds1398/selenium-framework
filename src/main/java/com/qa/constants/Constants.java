package com.qa.constants;

/**
 * Constants.java
 * ---------------
 * Central place for all constant values used across the framework.
 * Avoids magic strings scattered across test files.
 */
public class Constants {

    // ===================== CONFIG FILE =====================
    public static final String CONFIG_FILE_PATH = "src/test/resources/config/config.properties";

    // ===================== TEST DATA FILES =====================
    public static final String LOGIN_DATA_FILE    = "src/test/resources/testdata/LoginData.xlsx";
    public static final String FP_DATA_FILE       = "src/test/resources/testdata/ForgotPasswordData.xlsx";

    public static final String LOGIN_SHEET_NAME   = "LoginTests";
    public static final String FP_SHEET_NAME      = "ForgotPasswordTests";

    // ===================== SCREENSHOT =====================
    public static final String SCREENSHOT_DIR     = "screenshots/";
    public static final String SCREENSHOT_EXT     = ".png";

    // ===================== REPORT =====================
    public static final String REPORT_DIR         = "reports/";
    public static final String REPORT_FILE        = "reports/AutomationReport.html";
    public static final String REPORT_NAME        = "Automation Test Report";
    public static final String REPORT_DOCUMENT    = "Login & Forgot Password Module";

    // ===================== BROWSER =====================
    public static final String CHROME   = "chrome";
    public static final String FIREFOX  = "firefox";
    public static final String EDGE     = "edge";

    // ===================== PAGE TITLES / HEADINGS =====================
    public static final String LOGIN_PAGE_TITLE       = "OrangeHRM";
    public static final String DASHBOARD_URL_KEYWORD  = "dashboard";
    public static final String RESET_PAGE_HEADING     = "Reset Password";

    // ===================== ERROR / VALIDATION MESSAGES =====================
    public static final String INVALID_CREDS_MSG      = "Invalid credentials";
    public static final String REQUIRED_FIELD_MSG     = "Required";

    // ===================== WAIT TIMES =====================
    public static final int DEFAULT_WAIT   = 10;
    public static final int EXPLICIT_WAIT  = 15;
    public static final int PAGE_LOAD_WAIT = 30;

    // Prevent instantiation
    private Constants() {}
}
