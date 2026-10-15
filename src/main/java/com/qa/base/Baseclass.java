package com.qa.base;

import com.qa.constants.Constants;
import com.qa.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class Baseclass {

	private static final Logger log = LogManager.getLogger(Baseclass.class);

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	private static ThreadLocal<String> tlBrowser = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static String getBrowserName() {
		return tlBrowser.get();
	}

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("") String xmlBrowser) {

		String browser = xmlBrowser;

		// Priority 1 - Maven (-Dbrowser=chrome)
		if (browser == null || browser.trim().isEmpty()) {
			browser = System.getProperty("browser");
		}

		// Priority 2 - config.properties
		if (browser == null || browser.trim().isEmpty()) {
			browser = ConfigReader.getBrowser();
		}

		browser = browser.toLowerCase();
		tlBrowser.set(browser);

		boolean headless = ConfigReader.isHeadless();

		log.info("Launching Browser : " + browser + " | Headless : " + headless);

		WebDriver driver = initDriver(browser, headless);

		tlDriver.set(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));

		driver.manage().window().maximize();

		driver.get(ConfigReader.getBaseUrl());

		log.info("Navigated to : " + ConfigReader.getBaseUrl());
	}

	private WebDriver initDriver(String browser, boolean headless) {

		switch (browser) {

		case Constants.CHROME:

			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();

			if (headless) {
				chromeOptions.addArguments("--headless=new");
			}

			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--start-maximized");

			log.info("Chrome Driver Started");

			return new ChromeDriver(chromeOptions);

		case Constants.FIREFOX:

			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (headless) {
				firefoxOptions.addArguments("--headless=new");
			}

			firefoxOptions.addArguments("--disable-notifications");
			firefoxOptions.addArguments("--start-maximized");

			log.info("Firefox Driver Started");

			// return new FirefoxDriver(firefoxOptions);
			try {
				return new FirefoxDriver(firefoxOptions);
			} catch (Exception e) {
				log.error("Failed to start Chrome driver: " + e.getMessage());
				throw new RuntimeException("Driver initialization failed", e);
			}

		case Constants.EDGE:

			WebDriverManager.edgedriver().setup();

			EdgeOptions edgeOptions = new EdgeOptions();

			if (headless) {
				edgeOptions.addArguments("--headless=new");
			}
			edgeOptions.addArguments("--disable-notifications");
			edgeOptions.addArguments("--start-maximized");

			log.info("Edge Driver Started");

			return new EdgeDriver(edgeOptions);

		default:

			log.warn("Invalid browser '" + browser + "' supplied. Defaulting to Chrome.");

			WebDriverManager.chromedriver().setup();

			return new ChromeDriver();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		WebDriver driver = getDriver();

		if (driver != null) {

			log.info("Closing Browser");

			driver.quit();

			tlDriver.remove();
			tlBrowser.remove();
		}
	}
}