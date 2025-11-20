package utils;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hooks {

    TestContextSetup context;

    public Hooks(TestContextSetup context) {
        this.context = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("▶ Starting: " + scenario.getName());
        context.navigateToApplication();   // OPEN browser ONLY here
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        try {
            WebDriver driver = context.getDriver();

            if (driver != null) {

                // WAIT UNTIL PAGE IS FULLY LOADED
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

                Thread.sleep(300);  // small buffer so UI settles

                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
             // ❌ REMOVE Cucumber attachment to avoid duplication
                // scenario.attach(screenshot, "image/png", "Screenshot");

                // ✅ KEEP only Allure attachment
                Allure.addAttachment("Step Screenshot", new ByteArrayInputStream(screenshot));
            }

        } catch (Exception e) {
            System.out.println("⚠ Screenshot failed: " + e.getMessage());
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("⏹ Finished: " + scenario.getName());
        context.testBase.quitDriver();   // Proper ThreadLocal quit
    }
}
