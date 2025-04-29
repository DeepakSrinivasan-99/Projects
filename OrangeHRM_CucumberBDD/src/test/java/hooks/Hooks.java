package hooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utils.TestContextSetup;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Hooks {
 
 
    private final TestContextSetup ctx;
    private final WebDriver driver;
    private static String mainWindow;       // the original blank tab
    private String scenarioWindow;          // the tab for the current scenario

    public Hooks(TestContextSetup ctx) {
        this.ctx    = ctx;
        this.driver = ctx.testBase.WebDriverManager();
    }

    @Before
    public void setUp() {
        // 1) on very first @Before ever, record the handle of the main window
        if (mainWindow == null) {
            mainWindow = driver.getWindowHandle();
        }

        // 2) open a brand‐new tab for this scenario
        scenarioWindow = driver.switchTo()
                               .newWindow(WindowType.TAB)
                               .getWindowHandle();

        // 3) go to the login page
        driver.get(ctx.testBase.getProperties().getProperty("QAUrl"));
        ctx.genericUtils.MaximizeCurrentWindow();
    }

    //@After
    public void tearDown(Scenario scenario) {
        // 4) close only the scenario’s tab
        driver.switchTo().window(scenarioWindow).close();
        // 5) switch back to the main (blank) tab so it's ready for the next scenario
        driver.switchTo().window(mainWindow);
    }

    
    

    @AfterStep
    public void takeScreenshotAfterEveryStep(Scenario scenario) {
        // 1) Pick which element to wait for
        By waitFor;
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("/auth/login")) {
            // on Login page wait for username input
            waitFor = By.name("username");
        } else {
            // after login  wait for Dashboard header
            waitFor = By.xpath("//h6[text()='Dashboard']");
        }

        // 2) Wait up to 5 seconds for it to be visible
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(waitFor));
        } catch (TimeoutException e) {
            // if it never appears, we’ll still grab whatever is on screen
        }

        // 3) Capture screenshot bytes
        byte[] screenshot = ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);

        // 4) Label it PASS or FAIL
        String label = scenario.isFailed() ? "FAIL" : "PASS";

        // 5) Attach with scenario name and label
        String screenshotName = scenario.getName() + " [" + label + "]";
        scenario.attach(screenshot, "image/png", screenshotName);
    }
}
