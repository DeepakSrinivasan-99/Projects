package stepDefinitions;

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
	
	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
	    // Write code here that turns the phrase above into concrete actions
		loginPage.verifyLoginLandingPage();
	}
	@When("the user click on the Login button")
	public void the_user_click_on_Login_button() {
	   loginPage.enterLoginButton();
	}
	@And("User enters the Mobile number {string}")
	public void clicks_on_the_login_button(String MobileNumber) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		loginPage.enterMobileNumber(MobileNumber);
	}
	@Then("the user clicks the Request OTP button")
	public void the_user_clicks_requestOTP_button() {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		loginPage.clickRequestOTPButton();
	}

	@When("User enters OTP and click verify button")
	public void user_enters_otp_and_click_verify_button()
	{
		loginPage.validateOTP();
	}
	
	
}
