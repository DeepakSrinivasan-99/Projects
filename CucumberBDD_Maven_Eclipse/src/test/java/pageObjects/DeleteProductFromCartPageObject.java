package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.WebElement;
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
	
	
	public void deleteAllProducts(){
		// Locate all delete buttons in the cart
		List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[contains(@aria-label, 'Delete')]//span[@data-a-selector='decrement-icon']"));

		// Loop through each delete button and click it
		for (WebElement deleteButton : deleteButtons) {
		    try {
		        deleteButton.click(); // Click the delete button
		        Thread.sleep(2000); // Wait for the product to be removed (use explicit wait if needed)
		        System.out.println("Deleted a product from the cart.");
		    } catch (Exception e) {
		        System.out.println("Failed to delete a product: " + e.getMessage());
		    }
		}

	}
	
}

