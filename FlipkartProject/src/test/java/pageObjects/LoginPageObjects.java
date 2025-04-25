package pageObjects;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPageObjects {
	public WebDriver driver;
	Actions action;
	WebDriverWait wait;

	By loginButton= By.xpath("//div[@class='H6-NpN _3N4_BX']/a[@class='_1TOQfO']");
    By mobileNumber= By.cssSelector("input[class='r4vIwl BV+Dqf']");
	By requestOTPButton=By.xpath("//button[normalize-space(text())='Request OTP']");
	By otpInputs = By.xpath("//div//input[@class='r4vIwl IX3CMV']");
	By verifyButton=By.cssSelector("button[class='QqFHMw llMuju M5XAsp']");
	
    //Constructor	
	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Temporarily increase timeout

	}

	public void verifyLoginLandingPage() {
		// TODO Auto-generated method stub
		
      if(driver.getCurrentUrl().equals("https://www.flipkart.com/"))
      {
    	  System.out.println("The driver is in Flipkart Landing page :"+driver.getCurrentUrl());
      }
		
	}

	public void enterLoginButton() {
		// TODO Auto-generated method stub
		WebElement hoverLoginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
		action.moveToElement(hoverLoginButton).click().build().perform();
		
	}

	public void enterMobileNumber(String MobileNumber) {
		// TODO Auto-generated method stub
		WebElement enterMobilenumber = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumber));
       ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid yellow'", enterMobilenumber);

		enterMobilenumber.sendKeys(MobileNumber);
		
		
		
	}

	public void clickRequestOTPButton() {
		// TODO Auto-generated method stub
		driver.findElement(requestOTPButton).click();
		
	}

	/*
	public void validateOTP() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait up to 30 sec

    while (true) {  
        List<WebElement> otpFields = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".otp-input"))); // Ensure OTP fields are visible
        boolean allFilled = true;

        for (WebElement otpField : otpFields) {
            String value = otpField.getDomAttribute("value"); // Get entered value

            if (value == null || value.length() != 1 || !value.matches("\\d")) { 
                allFilled = false;  // If any field is empty or not a number, wait again
                break; 
            }
        }

        if (allFilled) { // If all fields contain 1 digit
            WebElement verifyBtn = wait.until(ExpectedConditions.elementToBeClickable(verifyButton)); // Wait for Verify button to be clickable
            verifyBtn.click();
            System.out.println("✅ OTP entered correctly. Clicking Submit.");
            break; // Stop checking after clicking Submit
        } else {
            System.out.println("⏳ Waiting for OTP to be fully entered...");
        }

        wait.until(ExpectedConditions.textToBePresentInElementValue(otpFields.get(otpFields.size() - 1), "")); // Wait until last field gets a value.initially get(5) is empty
    }
}
 */
	    
	    
	    public void validateOTP() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        
	        // JavaScript to check OTP fields and click the Verify button
	        String script = "setInterval(() => { " +
	            "let inputs = document.querySelectorAll(\"div input.r4vIwl.IX3CMV\"); " + // Select OTP input fields
	            "let allFilled = [...inputs].every(input => input.value.length === 1 && !isNaN(input.value)); " + 
	            "if (allFilled) document.querySelector(\"button.QqFHMw.llMuju.M5XAsp\").click(); " + // Click Verify Button
	            "}, 1000);"; // Run every 1 second
	        
	        js.executeScript(script);
	        System.out.println("✅ OTP validation script started.");
	    }	    

	    
	    
	    
	    
	
        //setInterval(() => { ... }, 1000);  //Runs the JavaScript function every 1 second (1000ms).It repeatedly checks OTP inputs and clicks Verify when filled.

	    /*
	      Breakdown:
          ...inputs → Converts the NodeList (OTP inputs) into an array.
          .every(...) → Checks if every OTP field meets two conditions:
          input.value.length === 1 → The input must have exactly 1 character.
          
          !isNaN(input.value) → The input must be a number (not a letter or special character).
          When all OTP fields are valid, this finds and clicks the Verify button.
          document.querySelector(...) selects the button based on CSS class "QqFHMw llMuju M5XAsp".

	     */

}
