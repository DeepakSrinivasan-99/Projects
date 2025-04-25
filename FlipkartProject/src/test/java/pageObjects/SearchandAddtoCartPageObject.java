package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchandAddtoCartPageObject {

	public WebDriver driver;
	public Actions action;
	public WebDriverWait wait;
	
	By searchBox = By.xpath("//input[@placeholder='Search for Products, Brands and More']");
	//By searchIcon = By.

	
	public SearchandAddtoCartPageObject(WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Temporarily increase timeout

	}
	
	public void searchProduct(String productName)
	{
		driver.findElement(searchBox).sendKeys(productName);
	}
	
	
	public void clickSearchIcon()
	{
		
	}
	
}
