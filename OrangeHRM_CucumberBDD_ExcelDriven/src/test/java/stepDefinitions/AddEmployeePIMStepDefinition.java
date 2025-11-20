package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pageObjects.AddEmployeePIMPageObject;
import pageObjects.LoginPageObject;
import utils.ExcelUtils;
import utils.TestContextSetup;

import java.util.List;
import java.util.Map;

public class AddEmployeePIMStepDefinition {

    private final TestContextSetup context;
    private final LoginPageObject loginPage;
    private final AddEmployeePIMPageObject addEmployeePIMPage;

    Map<String, String> excelData;

    public AddEmployeePIMStepDefinition(TestContextSetup context) {
        this.context = context;
        this.loginPage = this.context.pageObjectManager.getLoginPage();
        this.addEmployeePIMPage = this.context.pageObjectManager.getAddEmployeePIMPage();
    }

    @Given("I am logged into OrangeHRM as an admin with Username {string} and Password {string}")
    public void i_am_logged_into_orange_hrm_as_an_admin(String username, String password) {

        excelData = ExcelUtils.getRowData("AddEmployee");

        username = excelData.get("Username");
        password = excelData.get("Password");

        loginPage.verifyOnLoginPage();
        loginPage.enterCredentials(username, password);
        loginPage.clearUsernameAndCheckRequired();
        loginPage.clearPasswordAndCheckRequired();
        loginPage.enterCredentials(username, password);
        loginPage.clickLogin();
    }

    @Given("I navigate to the PIM module")
    public void i_navigate_to_the_pim_module() {
        addEmployeePIMPage.clickPIMOption();
    }

    @When("I click on Add Employee")
    public void i_click_on_add_employee() {
        addEmployeePIMPage.clickAddEmployeeButton();
    }

    @When("I enter employee details")
    public void i_enter_employee_details(DataTable dataTable) {

        String fn = excelData.get("firstName");
        String mn = excelData.get("middleName");
        String ln = excelData.get("lastName");
        String id = excelData.get("empId");

        addEmployeePIMPage.clearFormFields();
        addEmployeePIMPage.enterEmployeeDetails(fn, mn, ln, id);
    }

    @When("I navigate to the Employee List")
    public void navigate_to_emp_list() {
        addEmployeePIMPage.clickEmployeeListTab();
    }

    @And("I search for employee with name {string}")
    public void search_for_employee_with_name(String employeeName) {

        Map<String, String> searchData = ExcelUtils.getRowData("SearchEmployee");
        employeeName = searchData.get("searchName");

        addEmployeePIMPage.searchForEmployee(employeeName);
    }

    @Then("the employee record should be displayed for {string}")
    public void emp_record_should_be_displayed(String createdEmployeeName) {

        Map<String, String> searchData = ExcelUtils.getRowData("SearchEmployee");
        createdEmployeeName = searchData.get("searchName");

        addEmployeePIMPage.verifySearchedEmployeeByName(createdEmployeeName);
    }

    @When("I click on Save")
    public void i_click_on_save() {
        addEmployeePIMPage.clickSaveEmployeeButton();
    }

    @Then("the employee should be added successfully")
    public void the_employee_should_be_added_successfully() {
        addEmployeePIMPage.verifySuccessMessage();
    }
}
