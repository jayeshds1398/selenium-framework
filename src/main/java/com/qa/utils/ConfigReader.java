package com.qa.utils;

import com.qa.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader.java ------------------ Reads key-value pairs from
 * config.properties file. Singleton pattern ensures the file is loaded only
 * once.
 */
public class ConfigReader {

	private static final Logger log = LogManager.getLogger(ConfigReader.class);
	private static Properties properties;

	// Static block: loads config once when class is first used
	static {
		loadProperties();
	}

	private static void loadProperties() {
		properties = new Properties();
		try (FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH)) {
			properties.load(fis);
			log.info("config.properties loaded successfully.");
		} catch (IOException e) {
			log.error("Failed to load config.properties: " + e.getMessage());
			throw new RuntimeException("Could not load config.properties. Check path: " + Constants.CONFIG_FILE_PATH);
		}
	}

	/**
	 * Returns a property value by key. Throws RuntimeException if key is missing.
	 */
	public static String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null || value.isEmpty()) {
			log.error("Property key not found: " + key);
			throw new RuntimeException("Missing property in config.properties: " + key);
		}
		return value.trim();
	}

	// ======================== Convenience Methods ========================

	public static String getBaseUrl() {
		return getProperty("base.url");
	}

	public static String getLoginPageUrl() {
		return getProperty("login.page.url");
	}

	public static String getForgotPasswordUrl() {
		return getProperty("forgot.password.url");
	}

	public static String getBrowser() {
		return getProperty("browser");
	}

	public static boolean isHeadless() {
		return Boolean.parseBoolean(getProperty("headless"));
	}

	public static int getImplicitWait() {
		return Integer.parseInt(getProperty("implicit.wait"));
	}

	public static int getExplicitWait() {
		return Integer.parseInt(getProperty("explicit.wait"));
	}

	public static int getPageLoadTimeout() {
		return Integer.parseInt(getProperty("page.load.timeout"));
	}

	public static String getValidUsername() {
		return getProperty("valid.username");
	}

	public static String getValidPassword() {
		return getProperty("valid.password");
	}

	// Prevent instantiation
	private ConfigReader() {
	}
}
