package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	public LoginPageObjects loginPage;
	public CreateAccountPageObjects createAccountPage;
	public AddtoCartPageObject addtoCartPage;
	public DeleteProductFromCartPageObject deleteProduct;
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
	
	public CreateAccountPageObjects getCreateAccountPage()
	{
		this.createAccountPage=new CreateAccountPageObjects(driver);
		return createAccountPage;
	}
	
	public AddtoCartPageObject getAddtoCartPage()
	{
		this.addtoCartPage=new AddtoCartPageObject(driver);
		return addtoCartPage;
	}



	public DeleteProductFromCartPageObject getDeleteProductPage() {
		// TODO Auto-generated method stub
		this.deleteProduct=new DeleteProductFromCartPageObject(driver);
		return deleteProduct;
	}
}
