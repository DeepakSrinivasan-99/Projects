package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class TestBase {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final static Properties properties = new Properties();

    public static final String TIMESTAMP =
            new SimpleDateFormat("d-MMM-yy HH-mm-ss").format(new Date());

    private static final String SCREENSHOT_DIR =
            System.getProperty("user.dir") + File.separator +
            "SparkReport" + File.separator + "screenshots" + File.separator;

    public TestBase() {
        loadProperties();
        new File(SCREENSHOT_DIR).mkdirs();
    }

    // ------------------ Load Properties ------------------
    private void loadProperties() {
        String path = System.getProperty("user.dir") + "/src/test/resources/global.properties";
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load global.properties from: " + path, e);
        }
    }

    // ------------------ Driver Management ------------------
    public static WebDriver WebDriverManager() {
        if (driver.get() == null) {
            String browser = System.getenv().getOrDefault("BROWSER",
                    System.getProperty("browser",
                            properties.getProperty("browser", "chrome"))).toLowerCase();

            initializeDriver(browser);
        }
        return driver.get();
    }

    private static void initializeDriver(String browser) {
        try {
            switch (browser) {
                case "firefox":
                case "ff":
                    FirefoxOptions ffOpts = new FirefoxOptions();
                    if (Boolean.getBoolean("ci") || System.getenv("CI") != null) {
                        ffOpts.addArguments("--headless");
                    }
                    driver.set(new FirefoxDriver(ffOpts));
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (Boolean.getBoolean("ci") || System.getenv("CI") != null) {
                        edgeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                    }
                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (Boolean.getBoolean("ci") || System.getenv("CI") != null) {
                        chromeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
                        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                    }
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
            }

            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.get().manage().window().maximize();

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to initialize WebDriver for browser: " + browser, e);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void quitDriver() {
        WebDriver drv = driver.get();
        if (drv != null) {
            try {
                // close all windows gracefully
                try {
                    for (String handle : drv.getWindowHandles()) {
                        try {
                            drv.switchTo().window(handle);
                            drv.close();
                        } catch (Exception ignored) {}
                    }
                } catch (Exception ignored) {}

                // final quit
                try { drv.quit(); } catch (Exception ignored) {}
            } catch (Exception e) {
                System.err.println("⚠️ Error while quitting driver: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }

    


    public Properties getProperties() {
        return properties;
    }
}
