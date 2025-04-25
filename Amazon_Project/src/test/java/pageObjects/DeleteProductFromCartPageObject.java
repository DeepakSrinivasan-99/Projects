package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.GenericUtils;

public class DeleteProductFromCartPageObject {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public GenericUtils genericUtils;
	
	public DeleteProductFromCartPageObject(WebDriver driver)
	{
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		this.genericUtils=new GenericUtils(driver);
		
	}

	public void clickCartIcon()
	{
		By cartIcon =By.xpath("//a[@id='nav-cart']");
		
		driver.findElement(cartIcon).click();
	}

	public void verifyCartPage() {
		// TODO Auto-generated method stub
		By verifyCartPage =By.xpath("//h2[@id='sc-active-items-header']");
		
		if(driver.findElement(verifyCartPage).getText().trim().equalsIgnoreCase("Shopping Cart"))
		{
			System.out.println("The user landed in Cart Page successfully");
		}
		
	}
	
	public void deleteAllProducts() {
	    int maxRetries = 5; // Maximum number of retries for handling stale elements
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Wait for elements to be clickable
	    By deleteButtonLocator = By.xpath("//button[contains(@aria-label, 'Delete')]//span[@data-a-selector='decrement-icon']"); // Locator for delete buttons

	    while (true) {
	        // Step 1: Fetch all delete buttons in the cart
	        List<WebElement> deleteButtons = driver.findElements(deleteButtonLocator);

	        // Step 2: Check if the cart is empty
	        if (deleteButtons.isEmpty()) {
	            System.out.println("All products deleted successfully!");
	            break; // Exit the loop if no delete buttons are found
	        }

	        try {
	            // Step 3: Refetch the first delete button dynamically and click it
	            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator));
	            deleteButton.click();

	            // Step 4: Wait for the delete button to become stale (i.e., the product is removed from the DOM)
	            wait.until(ExpectedConditions.stalenessOf(deleteButton));

	            // Step 5: Reset retry counter after a successful deletion
	            System.out.println("Product deleted successfully.");
	            maxRetries = 5;

	        } catch (StaleElementReferenceException e) {
	            // Step 6: Handle StaleElementReferenceException
	            if (maxRetries-- <= 0) {
	                System.out.println("Max retries reached. Exiting.");
	                break; // Exit if max retries are exhausted
	            }
	            System.out.println("Stale element. Retries left: " + maxRetries);
	        }
	    }
	    System.out.println("All products deleted.");
	}


	}


	


