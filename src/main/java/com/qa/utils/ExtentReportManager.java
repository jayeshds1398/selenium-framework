package com.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ExtentReportManager.java
 * -------------------------
 * Manages creation and tear-down of Extent Reports.
 * Uses ThreadLocal<ExtentTest> so parallel test runs stay thread-safe.
 *
 * Flow:
 *   1. initReports() called once in @BeforeSuite listener
 *   2. createTest()  called per test in @BeforeMethod listener
 *   3. flushReports() called once in @AfterSuite listener
 */
public class ExtentReportManager {

    private static final Logger log = LogManager.getLogger(ExtentReportManager.class);

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /**
     * Initialises the ExtentReports instance and attaches a Spark (HTML) reporter.
     */
    public static void initReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(Constants.REPORT_FILE);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(Constants.REPORT_NAME);
        sparkReporter.config().setReportName(Constants.REPORT_DOCUMENT);
        sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System info shown in the report dashboard
        extent.setSystemInfo("OS",          System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Browser",     ConfigReader.getBrowser());
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester",      "QA Team");

        log.info("Extent Reports initialised. Report will be saved at: " + Constants.REPORT_FILE);
    }

    /**
     * Creates a new test entry in the report for the given test name.
     */
    public static void createTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
        log.debug("Extent test node created: " + testName);
    }

    /**
     * Creates a new test entry with description.
     */
    public static void createTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        extentTest.set(test);
    }

    /**
     * Returns the current thread's ExtentTest instance.
     */
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    /**
     * Flushes all logs and writes the final HTML report to disk.
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
            log.info("Extent Reports flushed. Open: " + Constants.REPORT_FILE);
        }
    }

    // Prevent instantiation
    private ExtentReportManager() {}
}
