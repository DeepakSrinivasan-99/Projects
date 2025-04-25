package stepDefinitions;

import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CreateAccountPageObjects;
import utils.GenericUtils;
import utils.TestContextSetup;

public class CreateAccountPageStepDefinition {

	public TestContextSetup testContextSetup;
	public CreateAccountPageObjects createAccountPage;
	public GenericUtils genericUtils;
	
	
	public CreateAccountPageStepDefinition(TestContextSetup testContextSetup)
	{
		this.testContextSetup=testContextSetup;
		this.createAccountPage= testContextSetup.pageObjectManager.getCreateAccountPage();
		this.genericUtils=testContextSetup.genericUtils;

		
	}
	

		@When("clicks on the start here link")
		public void clicks_on_the_start_here_link() {
		    // Write code here that turns the phrase above into concrete actions
			
			createAccountPage.clickStartHereLink();
			
		}
		
		@Then("Create account page should be displayed")
		public void create_account_page_should_be_displayed() {
		    // Write code here that turns the phrase above into concrete actions
			createAccountPage.verifyCreateAccountPage();
			
		}
		
	    @And("Enter the required details in the page")
	    public void enter_the_required_details_in_the_page(List<String> Details) {
	    	
	    	createAccountPage.enterDetails(Details);
	    }

	    @When("User clicks on the verify Mobile number button")
	    public void user_clicks_on_the_verify_mobile_number_button() {
	        // Write code here that turns the phrase above into concrete actions
         createAccountPage.clickVerifyButton();
	    }
	    @Then("Alert message should be displayed")
	    public void alert_message_should_be_displayed() {
	        // Write code here that turns the phrase above into concrete actions
          createAccountPage.displayExistingAccountInfo();
	    }


		
		
	}


