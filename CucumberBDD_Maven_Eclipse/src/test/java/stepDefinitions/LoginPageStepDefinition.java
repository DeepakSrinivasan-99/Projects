package stepDefinitions;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPageObjects;
import utils.GenericUtils;
import utils.TestContextSetup;

public class LoginPageStepDefinition {
	public TestContextSetup testContextSetup;
	public LoginPageObjects loginPage;
	public GenericUtils genericUtils;

	public LoginPageStepDefinition(TestContextSetup testContextSetup)
	{
		this.testContextSetup=testContextSetup;
		this.loginPage =testContextSetup.pageObjectManager.getLoginPage();
		this.genericUtils=testContextSetup.genericUtils;
				
	}

		@Given("User is on Amazon website")
		public void user_is_on_amazon_website() {
		    // Write code here that turns the phrase above into concrete actions
		   genericUtils.MaximizeCurrentWindow();
		   loginPage.captchaBeforeHomePage();
		   System.out.println("Actual Title: " + loginPage.getTitleAmazonHomepage());
		   Assert.assertTrue(loginPage.getTitleAmazonHomepage().endsWith("Amazon.in"), "Title does not end with 'Amazon.in'");		}
		
		
		@When("User hovers on Signin option")
		public void user_hovers_on_signin_option() {
		    loginPage.hoverOnSignInOption(); // Call the hover method
		}

		
		@Then("User click on Signin button")
		public void user_click_on_signin_button() {
		    // Write code here that turns the phrase above into concrete actions
			loginPage.clickSignInButton();
		    //throw new io.cucumber.java.PendingException();
		}
		@And("User should navigate to Signin page")
		public void user_should_navigate_to_signin_page() {
		    // Write code here that turns the phrase above into concrete actions
		    //throw new io.cucumber.java.PendingException();
			loginPage.verifySignInPage();
			
		}
		@When("User enters valid Mobile number {string}")
		public void user_enters_valid_Mobile_number(String MobileNumber) {
			
			loginPage.enterUsername(MobileNumber);
		    // Write code here that turns the phrase above into concrete actions
			
		    //throw new io.cucumber.java.PendingException();
		}
		
		@When("User enters invalid Mobile number {string}")
		public void user_enters_invalid_mobile_number(String MobileNumber)
		{
			loginPage.enterUsername(MobileNumber);

		}
		
		
		
	    @When("User enters valid EmailID {string}")
	    public void user_enters_valid_emailid(String EmailId)
	    {
			loginPage.enterUsername(EmailId);

	    }
	    
	    @When("User enters invalid Email Id {string}")
		public void user_enters_invalid_email_id(String EmailId)
		{
			loginPage.enterUsername(EmailId);

		}

	    
	    @When("User enters valid new Mobile or EmailID {string}")
	    public void user_enters_valid_new_mobile_or_emailid(String mobileOrEmailID)
	    {
	    	loginPage.enterUsername(mobileOrEmailID);
	    }
		
		
	    @Then("User clicks on the Continue button")
	    public void user_clicks_on_the_Continue_button()
	    {
	    	loginPage.clickContinueButton();
	    }

	    @And("alert message should be displayed")
	    public void alert_message_should_be_displayed()
	    {
	    	loginPage.displayAlertMessage();
	    }
	    
	    @And("alert message or create account should be displayed")
	    public void alert_message_or_create_account_page_displayed()
	    {
	    	loginPage.handleAlertMessages();
	    }
	    
	    @And("User should enter valid Password {string}")
	    public void user_should_enter_valid_password(String password)
	    {
	    	loginPage.enterPassword(password);
	    }
	   

		
		@When("User clicks on the Signin button")
		public void user_clicks_on_the_signin_button() {
		    // Write code here that turns the phrase above into concrete actions
		    //throw new io.cucumber.java.PendingException();
			loginPage.signInSubmit();
		}
		
		@Then("User should enter OTP and click Signin")
		public void user_should_enter_otp_and_click_Signin()
		{
			loginPage.enterOTPSignin();
			
		}
		
		@Then("User should be logged into the Amazon Homepage")
		public void user_should_be_logged_into_the_amazon_homepage() {
		    // Write code here that turns the phrase above into concrete actions
		    //throw new io.cucumber.java.PendingException();
			Assert.assertTrue(loginPage.verifyAmazonHomePage().contains("Deepak's"));
		}

}
