package utils;

import org.openqa.selenium.WebDriver;
import pageObjects.PageObjectManager;

import java.util.Map;

public class TestContextSetup {

    public PageObjectManager pageObjectManager;
    public TestBase testBase;
    public GenericUtils genericUtils;

    // New fields
    private String currentScenarioName;
    private Map<String, String> currentScenarioData;

    public TestContextSetup() {
        testBase = new TestBase();   // load properties only
    }

    public WebDriver getDriver() {
        return TestBase.WebDriverManager();
    }

    public void navigateToApplication() {
        String appUrl = testBase.getProperties().getProperty("url");
        WebDriver driver = getDriver();
        driver.get(appUrl);

        pageObjectManager = new PageObjectManager(driver);
        genericUtils = new GenericUtils(driver);
    }

    public void setCurrentScenarioName(String name) {
        this.currentScenarioName = name;
    }

    public String getCurrentScenarioName() {
        return currentScenarioName;
    }

    public void setCurrentScenarioData(Map<String, String> data) {
        this.currentScenarioData = data;
    }

    public Map<String, String> getCurrentScenarioData() {
        return currentScenarioData;
    }

    // helper convenience
    public String getData(String key) {
        if (currentScenarioData == null) return null;
        return currentScenarioData.getOrDefault(key, "").trim();
    }
}
