package com.qa.utils;

import com.qa.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * WaitUtils.java
 * ---------------
 * Provides Explicit Wait and Fluent Wait helper methods.
 * Eliminates Thread.sleep() usage across the framework.
 */
public class WaitUtils {

    private static final Logger log = LogManager.getLogger(WaitUtils.class);

    // ===================== EXPLICIT WAIT METHODS =====================

    /**
     * Waits until an element is visible on the page.
     */
    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
        log.debug("Waiting for element visibility: " + element);
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until an element located by locator is visible.
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        log.debug("Waiting for visibility of locator: " + locator);
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until an element is clickable.
     */
    public static WebElement waitForClickability(WebDriver driver, WebElement element) {
        log.debug("Waiting for element to be clickable: " + element);
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until an element is clickable by locator.
     */
    public static WebElement waitForClickability(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until a specific text appears in an element.
     */
    public static boolean waitForTextInElement(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * Waits until the URL contains a specific keyword.
     */
    public static boolean waitForUrlContains(WebDriver driver, String urlKeyword) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.urlContains(urlKeyword));
    }

    /**
     * Waits until an element's presence in DOM (not necessarily visible).
     */
    public static WebElement waitForPresence(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ===================== FLUENT WAIT METHOD =====================

    /**
     * Fluent Wait: polls every 500ms, ignores NoSuchElementException.
     * Useful for dynamically loading elements.
     *
     * @param driver      WebDriver instance
     * @param locator     By locator for the element
     * @param timeoutSecs Total wait time in seconds
     * @param pollMillis  Polling frequency in milliseconds
     */
    public static WebElement fluentWait(WebDriver driver, By locator,
                                        int timeoutSecs, int pollMillis) {
        log.debug("Fluent wait for: " + locator
                + " | timeout=" + timeoutSecs + "s | poll=" + pollMillis + "ms");

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSecs))
                .pollingEvery(Duration.ofMillis(pollMillis))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Prevent instantiation
    private WaitUtils() {}
}
