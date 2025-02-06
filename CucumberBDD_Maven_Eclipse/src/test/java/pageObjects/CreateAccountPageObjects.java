package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPageObjects {

		// TODO Auto-generated method stub

		By clickStartHereLink= By.xpath("//a[@class='nav-a' and normalize-space(text())='Start here.']");
		By verifyCreateAccount= By.xpath("//h1[normalize-space(text())='Create Account']");
		By enterName = By.xpath("//input[@id='ap_customer_name']");
		By enterMobileNumber = By.xpath("//input[@id='ap_phone_number']");
		By enterPassword = By.xpath("//input[@id='ap_password']"); 
		By verifyButton= By.xpath("//span[@class='a-button-inner']/input[@id='continue']");
		By displayExistingAccountInfo=By.xpath("//span[@class='a-list-item' and contains(normalize-space(text()),'You indicated you are a new customer, but an account already exists with the mobile phone number')]");

		public WebDriver driver;
		
		public CreateAccountPageObjects(WebDriver driver)
		{
			this.driver=driver;
		}
		
		public void clickStartHereLink()
		{
			driver.findElement(clickStartHereLink).click();
		}

		
		public void verifyCreateAccountPage()
		{
			if(driver.findElement(verifyCreateAccount).getText().equalsIgnoreCase("Create Account"))
			{
				System.out.println("Create account page displayed successfully");
			}
		}
		
		public void enterDetails(List<String> details)
		{
			driver.findElement(enterName).sendKeys(details.get(0));
			driver.findElement(By.xpath("//body")).click(); 
			driver.findElement(enterMobileNumber).sendKeys(details.get(1));
			
			driver.findElement(enterPassword).sendKeys(details.get(2));
			
		}
		
		public void clickVerifyButton()
		{
			driver.findElement(verifyButton).click();

		}
		
		public void displayExistingAccountInfo()
		{
			if(driver.findElement(displayExistingAccountInfo).getText().trim().contains("You indicated you are a new customer, but an account already exists with the mobile phone number"))
			{
				System.out.println("Existing account mobile number used for Create account "+ driver.findElement(displayExistingAccountInfo).getText().trim());
			}
		}
			
		
		
}


