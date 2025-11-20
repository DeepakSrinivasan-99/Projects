package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void click(By locator) {
        try {
            log.info("Click → {}", locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            log.error("❌ Click failed → {}", locator);
            throw e;
        }
    }

    protected void type(By locator, String value) {
        try {
            log.info("Type '{}' → {}", value, locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            log.error("❌ Typing failed → {}", locator);
            throw e;
        }
    }

    protected String getText(By locator) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
            log.info("GetText '{}' ← {}", text, locator);
            return text;
        } catch (Exception e) {
            log.error("❌ getText failed → {}", locator);
            throw e;
        }
    }

    protected boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            log.info("Visible → {}", locator);
            return true;
        } catch (Exception e) {
            log.warn("Not Visible → {}", locator);
            return false;
        }
    }

    protected void waitForVisible(By locator) {
        log.info("Wait for visible → {}", locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void clearField(By locator) {
        try {
            log.info("Clear field → {}", locator);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
        } catch (Exception e) {
            log.error("❌ Clear field failed → {}", locator);
            throw e;
        }
    }
}
