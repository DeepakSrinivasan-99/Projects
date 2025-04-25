package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DeleteProductFromCartPageObject;
import pageObjects.LoginPageObjects;
import utils.GenericUtils;
import utils.TestContextSetup;

public class DeleteProductFromCartStepDefinition {
	
	public TestContextSetup testContextSetup;
	public DeleteProductFromCartPageObject deleteProduct;
	public GenericUtils genericUtils;
	public LoginPageObjects loginPage;
	
	public DeleteProductFromCartStepDefinition(TestContextSetup testContextSetup)
	{
		this.testContextSetup=testContextSetup;
		this.deleteProduct= testContextSetup.pageObjectManager.getDeleteProductPage();
		this.loginPage =testContextSetup.pageObjectManager.getLoginPage();
		this.genericUtils=testContextSetup.genericUtils;

		
	}


	
	

	@When("User clicks on the Cart button")
	public void user_clicks_on_the_cart_button()
	{
		deleteProduct.clickCartIcon();
	}
	
    @Then("Cart page should be displayed")
    public void cart_page_be_displayed()
    {
    	deleteProduct.verifyCartPage();
    }
    
    @And("Delete all products from the cart")
    public void delete_all_products_from_cart()
    {
    	deleteProduct.deleteAllProducts();
    }
}
