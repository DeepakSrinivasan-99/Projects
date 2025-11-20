package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPageObject;
import utils.GenericUtils;
import utils.TestContextSetup;

public class LoginPageStepDefinition {

    private final LoginPageObject loginPage;
    private final TestContextSetup context;
    private final GenericUtils genericUtils;
    private final WebDriver driver;

    public LoginPageStepDefinition(TestContextSetup context) {
        this.context = context;
        this.genericUtils = context.genericUtils;
        this.driver = context.getDriver();
        this.loginPage = context.pageObjectManager.getLoginPage();
    }

    @Given("Admin user is in Login Page")
    public void admin_user_is_in_login_page() {
        loginPage.verifyOnLoginPage();
    }

    @When("User enters the Valid username {string} and password {string}")
    public void user_enters_the_valid_username_and_password(String username, String password) {

        loginPage.enterCredentials(username, password);
        loginPage.clearUsernameAndCheckRequired();
        loginPage.clearPasswordAndCheckRequired();

        loginPage.enterCredentials(username, password);
    }

    @When("click the Login button")
    public void click_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("User should be navigated to the Dashboard page")
    public void user_should_be_navigated_to_the_dashboard_page() {
        loginPage.verifyDashboardPageAfterLogin();
    }

    @When("User enters the Invalid username {string} and password {string}")
    public void user_enters_the_invalid_username_and_password(String username, String password) {

        loginPage.enterCredentials(username, password);
        loginPage.clearUsernameAndCheckRequired();
        loginPage.clearPasswordAndCheckRequired();
        loginPage.enterCredentials(username, password);
    }

    @Then("an invalid login error message should be displayed")
    public void an_invalid_login_error_message_should_be_displayed() {
        System.out.println("Invalid message: " + loginPage.viewInvalidError());
    }

    @And("User should be logged out")
    public void user_should_be_logged_out() {
        loginPage.logoutPage();
    }
}
