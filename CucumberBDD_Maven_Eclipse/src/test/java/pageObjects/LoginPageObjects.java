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

	// List of expected error messages for various scenarios
	public List<String> Invalid_number_or_email = Arrays.asList("We cannot find an account with that mobile number",
			"Invalid mobile number", "Invalid email address.",
			"Wrong or Invalid email address or mobile phone number. Please correct and try again.",
			"We cannot find an account with that email address");

	// CSS Selectors input.a-span12[id='captchacharacters'][placeholder='Type
	// characters']

	// input[@class='a-span12' or (@id='captchacharacters' and @placeholder='Type
	// characters')]

	By captchaInputLocator = By.cssSelector("input[placeholder='Type characters'][id='captchacharacters']");
	By HoverOnSignIn = By.cssSelector("a#nav-link-accountList");
	By SignIn = By.xpath(
			"//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner'][contains(normalize-space(text()), 'Sign in')]");
	By verifySignIn = By.xpath("//h1[contains(normalize-space(text()), 'Sign in')]");
	By enterEmailOrMobileNumber = By.xpath("//input[@type='email' and contains(@id, 'ap_email')]"); // By
																									// enterEmailOrMobileNumber
																									// =
																									// By.xpath("//input[@type='email'
																									// and
																									// (@id='ap_email_login'
																									// or
																									// @id='ap_email')]");

	By clickContinueButton = By.xpath("//input[@type='submit' and (@id='continue' or @class='a-button-input')]");
	By enterPassword = By.xpath("//input[@type='password' and @id='ap_password']");
	By signInSubmit = By.xpath("//input[@type='submit' and @id='signInSubmit']");
	By otpInputField = By.id("auth-mfa-otpcode");
	By otpSubmitButton = By.id("auth-signin-button");
	By verifyHomepage = By.xpath("//span[@id='nav-your-amazon-text']");

	By Invalid_number_or_EmailXpath = By.xpath("//div[@id='invalid-phone-alert']//div[@class='a-alert-content'] | "
			+ "//span[normalize-space(text())='We cannot find an account with that mobile number'] | "
			+ "//div[@id='invalid-email-alert']//div[@class='a-alert-content'] | "
			+ "//div[@id='auth-email-invalid-claim-alert']//div[@class='a-alert-content'] | "
			+ "//p[@class='a-spacing-none a-spacing-top-base'] [contains(normalize-space(text()), 'create an account using your mobile number')] | "
			+ "//span[@class='a-list-item' and normalize-space(text())='We cannot find an account with that email address']");

	By newEmailOrMobile = By
			.xpath("//span[normalize-spacce(text())='We cannot find an account with that mobile number']");

	// Constructor
	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Temporarily increase timeout

	}

	public void captchaBeforeHomePage() {
	    // JavaScript code to check captcha and click submit button
	    String jsCode = """
	            const checkCaptchaInterval = setInterval(() => {
	                const captchaInputbox = document.querySelector("input[placeholder='Type characters'][id='captchacharacters']");
	                if (captchaInputbox) {
	                    const captchaValue = captchaInputbox.value.trim();
	                    console.log("Captcha entered:", captchaValue);
	                    if (/^[a-zA-Z0-9]{6}$/.test(captchaValue)) {
	                        console.log('Valid Captcha entered:', captchaValue);
	                        const submitButton = document.querySelector("button[type='submit'].a-button-text");
	                        if (submitButton && !submitButton.disabled) {
	                            console.log('Submit button found, attempting to click...');
	                            submitButton.click();
	                            clearInterval(checkCaptchaInterval);
	                            console.log('Captcha submitted successfully.');
	                        }
	                    }
	                }
	            }, 1000); // Check every 1 second

	            setTimeout(() => {
	                clearInterval(checkCaptchaInterval);
	                console.log('Stopped checking captcha after 30 seconds.');
	            }, 30000); // Stop after 30 seconds
	        """;

	    try {
	        // Temporarily set implicit wait to 0
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

	        // Explicit wait with a timeout of 5 seconds for captcha input box
	        WebElement captchaInputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(captchaInputLocator));

	        if (captchaInputBox.isDisplayed()) {
	            System.out.println("Captcha Element is visible.");

	            // Execute the JavaScript code to monitor captcha input and click submit
	            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	            jsExecutor.executeScript(jsCode);
	        } else {
	            System.out.println("Captcha Element is not visible. Moving to the next step.");
	        }
	    } catch (TimeoutException te) {
	        // Handle the timeout case explicitly
	        System.out.println("Captcha Element not found within 5 seconds. Moving to the next step.");
	    } catch (Exception e) {
	        // Handle any other exceptions that might occur
	        System.out.println("An error occurred: " + e.getMessage());
	    } 
	    /*finally {
	        // Restore the implicit wait time
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Reset implicit wait back to 20 seconds
	    }*/
	}


	// Get Amazon Homepage Title
	public String getTitleAmazonHomepage() {
		return driver.getTitle();
	}

	// Hover over Sign-In Option
	public void hoverOnSignInOption() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		action.moveToElement(driver.findElement(HoverOnSignIn)).perform();
	}

	// Click Sign-In Button
	public void clickSignInButton() {
		driver.findElement(SignIn).click();
	}

	// Verify Sign-In Page
	public void verifySignInPage() {
		if (driver.findElement(verifySignIn).getText().contains("Sign in")) {
			System.out.println("Validation Passed: The element contains the text 'Sign in'.");
		} else {
			System.out.println("Validation Failed: Expected 'Sign in', but found '"
					+ driver.findElement(verifySignIn).getText() + "'.");
		}
	}

	// Authenticate Credentials
	public void enterUsername(String username) {
		driver.findElement(enterEmailOrMobileNumber).sendKeys(username);
	}

	// Add more error message lists as needed

	public void clickContinueButton() {
		// Click the Continue button
		driver.findElement(clickContinueButton).click();

	}

	public void displayAlertMessage() {

		try {
			// Temporarily set implicit wait to 0
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

			// Explicit wait for the error message element
			WebElement alertMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(Invalid_number_or_EmailXpath));

			// Capture and trim the actual error message
			String actualAlertMessage = alertMessage.getText().trim();
			System.out.println("Actual Error Message: " + actualAlertMessage);

			// Check if the actual error message is non-empty
			if (!actualAlertMessage.isEmpty()) {
				// Verify if the actual error message matches any of the expected messages
				boolean isMatch = false;
				for (String expectedMessage : Invalid_number_or_email) {
					if (actualAlertMessage.equals(expectedMessage.trim())) {
						System.out.println("Error message matches expected: " + expectedMessage);
						isMatch = true;
						break;
					}
				}

				if (!isMatch) {
					Assert.fail("Actual error message did not match any expected messages: " + actualAlertMessage);
				}
			} else {
				// Proceed to other steps if the error message is empty
				System.out.println("No error message displayed. Proceeding to the next steps...");
			}

		} catch (TimeoutException e) {
			System.out.println("Error message element not found within the timeout. Proceeding...");
		} finally {
			// Restore the implicit wait time
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
	}

	public void enterPassword(String Password) {
		driver.findElement(enterPassword).sendKeys(Password);
	}

	// Click Sign-In Submit Button

	// Handle OTP Input using JavaScript
	public void signInSubmit() {
		// JavaScript code to handle OTP input and submission
		driver.findElement(signInSubmit).click();

	}

	public void enterOTPSignin() {

		String jsCode = """
				    // Select the OTP input field and the submit button
				    const otpInputField = document.getElementById('auth-mfa-otpcode');
				    const submitButton = document.getElementById('auth-signin-button');

				    // Function to check if a 6-digit OTP is entered
				    function checkOTPAndSubmit() {
				        const otpValue = otpInputField.value.trim();

				        // Check if the OTP is exactly 6 digits
				        if (/^\\d{6}$/.test(otpValue)) {
				            console.log('Valid OTP entered:', otpValue);
				            submitButton.click(); // Simulate the click action
				            clearInterval(checkInterval); // Stop the interval after successful OTP entry
				        }
				    }

				    // Set up a 15-second interval
				    const checkInterval = setInterval(checkOTPAndSubmit, 1000); // Check 2 second

				    // Automatically stop checking after 15 seconds
				    setTimeout(() => {
				        clearInterval(checkInterval);
				        console.log('Stopped checking OTP after 15 seconds.');
				    }, 20000);
				""";

		// Execute the JavaScript code using Selenium
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript(jsCode);

	}

	public String verifyAmazonHomePage() {
		// TODO Auto-generated method stub
		// Locate the element and extract the text before the first space
		String op = driver.findElement(verifyHomepage).getText().split(" ")[0];
		System.out.println("The user " + op + " logged in successfully ..");
		return op;
	}

	public void handleAlertMessages() {
		try {
			// Temporarily set implicit wait to 0
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

			// Check for "Proceed to create an account" message
			String text = "";
			try {
				WebElement alertElement = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//p[contains(@class, 'a-spacing-none')]")));
				text = alertElement.getText().trim();

				if (!text.isEmpty()) {
					System.out.println("Actual Message: " + text);

					if (text.equals("Let's create an account using your mobile number")) {
						System.out.println(
								"Create account page navigation button is displayed successfully for creating an account.");
						return; // Exit the method as no further checks are needed
					}
				} else {
					System.out.println("No message displayed for 'Create Account'. Checking further...");
				}
			} catch (TimeoutException e) {
				System.out.println("'Create Account' message not found. Checking for other alerts...");
			}

			// Explicit wait for other error messages
			try {
				WebElement alertMessage = wait
						.until(ExpectedConditions.visibilityOfElementLocated(Invalid_number_or_EmailXpath));
				String actualAlertMessage = alertMessage.getText().trim();
				System.out.println("Actual Error Message: " + actualAlertMessage);

				// Verify if the actual error message matches any of the expected messages
				if (!actualAlertMessage.isEmpty()) {
					boolean isMatch = false;
					for (String expectedMessage : Invalid_number_or_email) {
						if (actualAlertMessage.equals(expectedMessage.trim())) {
							System.out.println("Error message matches expected: " + expectedMessage);
							isMatch = true;
							break;
						}
					}

					if (!isMatch) {
						Assert.fail("Actual error message did not match any expected messages: " + actualAlertMessage);
					}
				} else {
					System.out.println("No error message displayed. Proceeding to the next steps...");
				}
			} catch (TimeoutException e) {
				System.out.println("Error message element not found within the timeout. Proceeding...");
			}

		} finally {
			// Restore the implicit wait time
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
	}

}
