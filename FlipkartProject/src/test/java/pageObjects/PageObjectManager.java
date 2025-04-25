package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	public LoginPageObjects loginPage;
	public SearchandAddtoCartPageObject searchAddtoCartPage;
	
	public WebDriver driver;
	
	public PageObjectManager(WebDriver driver)
	{
		this.driver = driver;
	}

	
	
	public LoginPageObjects getLoginPage()
	{
	
	 this.loginPage= new LoginPageObjects(driver);
	 return loginPage;
	}
	
	public SearchandAddtoCartPageObject getSearchandAddtoCartPage()
	{
	
	 this.searchAddtoCartPage= new SearchandAddtoCartPageObject(driver);
	 return searchAddtoCartPage;
	}
	
	
}
