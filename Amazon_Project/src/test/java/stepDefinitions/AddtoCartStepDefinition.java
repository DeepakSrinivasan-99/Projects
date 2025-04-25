package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddtoCartPageObject;
import pageObjects.LoginPageObjects;
import utils.GenericUtils;
import utils.TestContextSetup;

public class AddtoCartStepDefinition {
	
	public TestContextSetup testContextSetup;
	public AddtoCartPageObject addtoCartPage;
	public GenericUtils genericUtils;
	public LoginPageObjects loginPage;
	
	public AddtoCartStepDefinition(TestContextSetup testContextSetup)
	{
		this.testContextSetup=testContextSetup;
		this.addtoCartPage= testContextSetup.pageObjectManager.getAddtoCartPage();
		this.loginPage =testContextSetup.pageObjectManager.getLoginPage();
		this.genericUtils=testContextSetup.genericUtils;

		
	}

	@Given("the User is already logged in")
	public void the_user_is_already_logged_in() {
		//Assert.assertTrue(loginPage.verifyAmazonHomePage().contains("Deepak's"));
	    
	}
	@When("User enters the product {string} in Searchbox")
	public void user_enters_the_product_in_searchbox(String product) {
		
		addtoCartPage.searchProduct(product);
		
	}
	@When("clicks on the {string} displaying below Searchbox")
	public void clicks_on_the_displaying_below_searchbox(String SearchSuggestion) {
	    // Write code here that turns the phrase above into concrete actions
       addtoCartPage.selectSearchSuggestion(SearchSuggestion);
	}
	@Then("Search results should be displayed")
	public void search_results_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    addtoCartPage.verifySearchedProductsPage();
	}
	@When("user clicks on the specific product")
	public void user_clicks_on_the_specific_product() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		addtoCartPage.clickOneProduct();
	}
	
	@When("product landing page should be displayed")
	public void product_landing_page_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		addtoCartPage.verifyLoadedProductName();
	}

	@When("User clicks on the AddToCart button")
	public void clicks_addtocart()
	{
	addtoCartPage.clickAddtoCartButton();
	}
	
    @Then("product will be added to the cart")
    public void added_to_cart()

     {
    	
    	addtoCartPage.verifyProductAdded();
    	}
}
    
