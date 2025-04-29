package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	private static WebDriver driver;
	private final Properties properties = new Properties();

	public TestBase() {
		loadProperties();
	}

	private void loadProperties() {
		try (FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/global.properties")) {
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load global.properties", e);
		}
	}

	/**
	 * Returns a singleton WebDriver instance, creating it on first call. Browser
	 * name is taken from: 1) System.getProperty("browser") if present, 2) else from
	 * global.properties key "browser", 3) else defaults to "chrome".
	 */
	public WebDriver WebDriverManager() {
		if (driver == null) {
			String browser = System.getProperty("browser", properties.getProperty("browser", "chrome")).toLowerCase();
			initializeDriver(browser);
		}
		return driver;
	}

	/** Expose loaded properties if needed elsewhere */
	public Properties getProperties() {
		return properties;
	}

        
        private void initializeDriver(String browser) {
    if (browser.equals("edge")) {
        //  make sure you have the matching msedgedriver.exe on the PATH
        EdgeOptions opts = new EdgeOptions();
        opts.addArguments("--headless=new");      // headless mode
        opts.addArguments("--disable-gpu");       // disable GPU acceleration
        opts.addArguments("--window-size=1920,1080");
        // if you see security restrictions, you may need:
        // opts.addArguments("--remote-allow-origins=*");

        driver = new EdgeDriver(opts);
    }
    else if (browser.equals("chrome")) {
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
        driver = new ChromeDriver(opts);
    }

	 else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			}

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
    driver.get(properties.getProperty("QAUrl"));

}

	/** Close just the current browser tab */
	public void closeCurrentTab() {
		if (driver != null) {
			driver.close();
		}
	}

	/** Quit the entire browser session and reset driver to null */
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
