package com.qa.utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.base.Baseclass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener.java
 * ------------------
 * Implements TestNG's ITestListener to:
 *  1. Hook into @BeforeSuite / @AfterSuite to init and flush Extent Reports
 *  2. Log PASS / FAIL / SKIP to the Extent Report
 *  3. Auto-capture screenshots on test failure
 *
 * Registered in testng.xml via <listeners> tag.
 */
public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    // ===================== Suite Level =====================

    @Override
    public void onStart(ITestContext context) {
        log.info("===== TEST SUITE STARTED: " + context.getName() + " =====");
        ExtentReportManager.initReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("===== TEST SUITE FINISHED: " + context.getName() + " =====");
        log.info("Passed: "  + context.getPassedTests().size()
               + " | Failed: " + context.getFailedTests().size()
               + " | Skipped: " + context.getSkippedTests().size());
        ExtentReportManager.flushReports();
    }

    // ===================== Test Level =====================

    @Override
    public void onTestStart(ITestResult result) {
        String browser = Baseclass.getBrowserName();

        String testName =
                "[" + browser.toUpperCase() + "] "
                + result.getTestClass().getRealClass().getSimpleName()
                + " :: "
                + result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        log.info(">>> TEST STARTED: " + testName);

        // Create a new node in the Extent Report
        if (description != null && !description.isEmpty()) {
            ExtentReportManager.createTest(testName, description);
        } else {
            ExtentReportManager.createTest(testName);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.info("✓ TEST PASSED: " + testName);

        ExtentReportManager.getTest().log(
            Status.PASS,
            MarkupHelper.createLabel("TEST PASSED: " + testName, ExtentColor.GREEN)
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.error("✗ TEST FAILED: " + testName);
        log.error("Failure reason: " + result.getThrowable().getMessage());

        // Log the failure reason in the report
        ExtentReportManager.getTest().log(
            Status.FAIL,
            MarkupHelper.createLabel("TEST FAILED: " + testName, ExtentColor.RED)
        );
        ExtentReportManager.getTest().log(
            Status.FAIL,
            "Exception: " + result.getThrowable().getMessage()
        );

        // Auto screenshot on failure
        WebDriver driver = Baseclass.getDriver();       
        if (driver != null) 
        {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, testName);
            try {
                ExtentReportManager.getTest().addScreenCaptureFromPath(
                    screenshotPath, "Failure Screenshot"
                );
                log.info("Screenshot attached to report: " + screenshotPath);
            } catch (Exception e) {
                log.error("Could not attach screenshot to report: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.warn("⚠ TEST SKIPPED: " + testName);

        ExtentReportManager.getTest().log(
            Status.SKIP,
            MarkupHelper.createLabel("TEST SKIPPED: " + testName, ExtentColor.YELLOW)
        );
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn("TEST FAILED WITHIN SUCCESS PERCENTAGE: " + result.getMethod().getMethodName());
    }
}
