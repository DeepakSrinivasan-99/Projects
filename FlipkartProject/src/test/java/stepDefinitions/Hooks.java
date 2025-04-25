package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestContextSetup;
import utils.VideoRecordingHelper;

public class Hooks {

    private TestContextSetup testContextSetup;
    private VideoRecordingHelper recordingHelper;
    private Properties properties;
    
    public WebDriverWait wait;
    public WebDriver driver; 

    
    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.recordingHelper = new VideoRecordingHelper();
        this.properties = new Properties();
        loadProperties();  // Load the properties file
        this.driver= testContextSetup.testBase.WebDriverManager();
       
    }

    // Method to load the global.properties file
    public void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/global.properties");  // Correct path to your properties file
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@Before
    public void BeforeScenario() throws InterruptedException {
        // Fetch the video directory path from the global properties
        String baseFolderPath = properties.getProperty("video.dir", "test-output/VideoReports/");
        
        // Generate a unique file name for the video
        String videoFilePath = baseFolderPath + "test_video_" + System.currentTimeMillis() + ".mp4";
        
        // Start the video recording
        try {
            recordingHelper.startRecording(videoFilePath);
            System.out.println("Video recording started...");
            
            // Wait for the page to load or test setup to be complete
            WebDriverWait wait = new WebDriverWait(testContextSetup.testBase.WebDriverManager(), Duration.ofSeconds(10));
            wait.until(webDriver -> true); // Dummy condition to mimic delay

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@After
    public void AfterScenario() throws IOException {
        // Stop the video recording after the scenario completes
        recordingHelper.stopRecording();
        System.out.println("Video recording stopped...");

        // Optionally, quit WebDriver if needed
        // testContextSetup.testBase.WebDriverManager().quit();
    }

    //@AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        WebDriver driver = testContextSetup.testBase.WebDriverManager();
        if (scenario.isFailed()) {
            // Take screenshot if the scenario fails
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");
        }
    }
    
    
    //Takes screenshot for every step
    //@AfterStep
    public void addScreenshot(Scenario scenario) throws IOException {
        // Take a screenshot for every step
        File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
        scenario.attach(fileContent, "image/png", "Screenshot for step: " + scenario.getName());
    }
    
    @AfterStep
    public void addScreenshot1(Scenario scenario) {
        try {
            // Wait for the page to be fully loaded before taking a screenshot
        	
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
            System.out.println("Screenshot taken successfully.");
        } catch (TimeoutException e) {
            System.out.println("Timeout while waiting for page load before taking a screenshot.");
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }


    @After
    public void AfterScenari() {
        //testContextSetup.testBase.quitDriver();  // Correct way to quit the driver
    }

    

}
