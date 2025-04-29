package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class LoginPageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    // simple locators
    private By usernameInput = By.cssSelector("input[name='username']");
    private By passwordInput = By.cssSelector("input[name='password']");
    private By loginButton   = By.cssSelector("button[type='submit']");
    // all error spans; username will be index 0, password index 1
    private By errorSpans    = By.cssSelector(".oxd-input-field-error-message");
    
    private By dashboardVerify= By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6");
    
    private By invalidError = By.xpath("//div[contains(@class,'oxd-alert-content--error')]/p[text()='Invalid credentials']");
    
    String invalidErrorText;
    
    

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

   
    public void verifyOnLoginPage() {
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
    }

    public void enterCredentials(String user, String pass) {
        WebElement u = wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        u.sendKeys(user);

        WebElement p = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        p.sendKeys(pass);
    }


    
     // Clears the given input by Ctrl+A, Delete, then TAB 
     // to trigger the Required message 
     
    private void clearAndTab(WebElement input) {
        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE, Keys.TAB);
        
    }

    public void clearUsernameAndCheckRequired() {
        WebElement u = wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        clearAndTab(u);

        List<WebElement> errs = driver.findElements(errorSpans);
        String msg = errs.get(0).getText();              // first span to username
        Assert.assertEquals(msg, "Required");
        
        
    }

    public void clearPasswordAndCheckRequired() {
        WebElement p = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        clearAndTab(p);

        List<WebElement> errs = driver.findElements(errorSpans);
        String msg = errs.get(1).getText();              // second span to password
        Assert.assertEquals(msg, "Required");
    }
    
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    
    public void verifyDashboardPageAfterLogin()
    {
    	WebElement d = wait.until(ExpectedConditions.elementToBeClickable(dashboardVerify));
    	Assert.assertEquals(d.getText(), "Dashboard");
    }


	


    public String viewInvalidError() {
        try {
    

            // Wait until the exact message is visible and get the text
            invalidErrorText = wait.until(ExpectedConditions
                .presenceOfElementLocated(invalidError))
                .getText();

            System.out.println("Invalid Error: " + invalidErrorText);

            // Assert the actual error message
            Assert.assertEquals(invalidErrorText, "Invalid credentials");

        } catch (TimeoutException e) {
            throw new AssertionError("Invalid credentials message not found on time.", e);
        }

        return invalidErrorText;
    }

	
	public void logoutPage() {
		// TODO Auto-generated method stub
		
		// Click the user icon to open the dropdown (if needed)
		WebElement userDropdown = driver.findElement(By.className("oxd-userdropdown-name"));
		userDropdown.click();

		// Wait until the dropdown is visible
		WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//a[text()='Logout' and contains(@class, 'oxd-userdropdown-link')]")
		));

		// Click on the Logout link
		logoutLink.click();

		
	}
	
}
