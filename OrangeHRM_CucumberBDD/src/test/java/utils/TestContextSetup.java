package utils;

import org.openqa.selenium.WebDriver;
import pageObjects.PageObjectManager;

public class TestContextSetup {

    public PageObjectManager pageObjectManager;
    public TestBase testBase;
    public GenericUtils genericUtils;

    public TestContextSetup() {
        testBase = new TestBase();   // Only load properties
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
}
