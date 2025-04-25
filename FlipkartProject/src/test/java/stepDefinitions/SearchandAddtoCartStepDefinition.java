package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.LoginPageObjects;
import pageObjects.SearchandAddtoCartPageObject;
import utils.GenericUtils;
import utils.TestContextSetup;

public class SearchandAddtoCartStepDefinition {
	
	
   public TestContextSetup testContextSetup;
   public LoginPageObjects loginPage;
   public SearchandAddtoCartPageObject searchandAddtoCartPage;
   public GenericUtils genericUtils;
   


public SearchandAddtoCartStepDefinition(TestContextSetup testContextSetup) {
		// TODO Auto-generated constructor stub
	   this.testContextSetup=testContextSetup;
		this.loginPage =testContextSetup.pageObjectManager.getLoginPage();
		this.genericUtils=testContextSetup.genericUtils;
	}
	{
		
				
	}
	
	
	@Given("User is already logged in the Flipkart website")
	public void user_already_logged_in()
	{
		loginPage.verifyLoginLandingPage();

	}
	
	
    @When("User enters the {string} in searchbox")
    public void user_enters_in_searchbox(String productName)
    {
    	searchandAddtoCartPage.searchProduct(productName);
    }

	
	
	

}
