package utils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import io.qameta.allure.Allure;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class Hooks {

    private final TestContextSetup context;
    private final Logger log = LoggerFactory.getLogger(Hooks.class);

    public Hooks(TestContextSetup context) {
        this.context = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("▶ Starting Scenario: {}", scenario.getName());
        context.navigateToApplication();   // launch browser
    }

    @AfterStep
    public void afterStep(Scenario scenario) {

        try {
            WebDriver driver = context.getDriver();
            if (driver == null) return;

            // Wait until page JS is ready
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd)
                            .executeScript("return document.readyState")
                            .equals("complete")
            );

            Thread.sleep(200); // Stabilize UI

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // ONLY Allure attachment
            Allure.addAttachment("Step Screenshot", new ByteArrayInputStream(screenshot));

        } catch (Exception e) {
            log.error("⚠ Error capturing screenshot: {}", e.getMessage());
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        log.info("⏹ Finished Scenario: {}", scenario.getName());
        context.testBase.quitDriver();
    }
}
