package com.qa.utils;

import com.qa.constants.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils.java
 * ---------------------
 * Utility class for capturing screenshots.
 * Called from Listeners on test failure, or manually from test methods.
 */
public class ScreenshotUtils {

    private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);

    /**
     * Takes a screenshot and saves it to the screenshots/ folder.
     *
     * @param driver   Active WebDriver instance
     * @param testName Name used in the screenshot filename
     * @return Absolute path of the saved screenshot (used in Extent Reports)
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        // Build timestamped filename: TestName_2024-01-15_10-30-45.png
        String timestamp  = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String fileName   = testName + "_" + timestamp + Constants.SCREENSHOT_EXT;
        String folderPath = Constants.SCREENSHOT_DIR;
        String fullPath   = folderPath + fileName;

        try {
            // Ensure screenshots directory exists
            File screenshotDir = new File(folderPath);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Cast driver to TakesScreenshot and capture
            File srcFile  = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fullPath);
            FileUtils.copyFile(srcFile, destFile);

            log.info("Screenshot captured: " + fullPath);

        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during screenshot capture: " + e.getMessage());
        }

        return fullPath;
    }

    // Prevent instantiation
    private ScreenshotUtils() {}
}
