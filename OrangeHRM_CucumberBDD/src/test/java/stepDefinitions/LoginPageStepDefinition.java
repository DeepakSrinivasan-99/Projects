package stepDefinitions;

import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.LoginPageObject;
import utils.GenericUtils;
import utils.TestContextSetup;

public class LoginPageStepDefinition {
    private LoginPageObject loginPage;
    private TestContextSetup testContextSetup;
    private GenericUtils genericUtils;
    private WebDriver driver;


    public LoginPageStepDefinition(TestContextSetup context) {
        this.loginPage = context.pageObjectManager.getLoginPage();
        this.testContextSetup=context;
        this.genericUtils = context.genericUtils; //  Add this line

    }

    @Given("Admin user is in Login Page")
    public void admin_user_is_in_login_page() {
        
        loginPage.verifyOnLoginPage();


    }

    @When("User enters the Valid username {string} and password {string}")
    public void user_enters_the_valid_username_and_password(String username, String password) {
       
    	
    	// 2) Enter both fields with valid creds
    	loginPage.enterCredentials(username,password);

    	// 3) Now clear each and assert its Required shows
    	loginPage.clearUsernameAndCheckRequired();
    	loginPage.clearPasswordAndCheckRequired();

    	// (4) Finally, if you still want to log in:)
    	loginPage.enterCredentials(username,password);
    	// then verify Dashboard, etc.

    }

    @When("click the Login button")
    public void click_the_login_button() {
    	loginPage.clickLogin();
    	
    }

    @Then("User should be navigated to the Dashboard page")
    public void user_should_be_navigated_to_the_dashboard_page() {
        // whatever your verifyDashboardPage() is
    	loginPage.verifyDashboardPageAfterLogin();
    }
    
    
    @When("User enters the Invalid username {string} and password {string}")
    public void user_enters_the_invalid_username_and_password(String username, String password) {
        // Write code here that turns the phrase above into concrete actions
    	
    	
    	// 2) Enter both fields with valid creds
    	loginPage.enterCredentials(username,password);

    	// 3) Now clear each and assert its Required shows
    	loginPage.clearUsernameAndCheckRequired();
    	loginPage.clearPasswordAndCheckRequired();

    	// (4) Finally, if you still want to log in:)
    	loginPage.enterCredentials(username,password);
    	//  then verify Dashboard, etc.

    }
    @Then("an invalid login error message should be displayed")
    public void an_invalid_login_error_message_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
    	String invalidErroMessage=loginPage.viewInvalidError();
    	System.out.println("Invalid Error message displayed :"+invalidErroMessage);
        //throw new io.cucumber.java.PendingException();
    }

    @And("User should be logged out")
    public void user_should_be_logged_out() {
    	loginPage.logoutPage();

    }
    
 
    
}
