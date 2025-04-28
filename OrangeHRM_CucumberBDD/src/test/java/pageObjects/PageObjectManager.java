package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	
	public WebDriver driver;
	
	public LoginPageObject loginPage;
	public AddEmployeePIMPageObject addEmployeePIMPage;
	
	public PageObjectManager(WebDriver driver)
	{
		this.driver = driver;
	}

	public LoginPageObject getLoginPage() {
		// TODO Auto-generated method stub
this.loginPage=new LoginPageObject(driver);
return loginPage;
}
	
	public AddEmployeePIMPageObject getAddEmployeePIMPage() {
		this.addEmployeePIMPage= new AddEmployeePIMPageObject(driver);
		return addEmployeePIMPage;
	}

	
	
	
}
