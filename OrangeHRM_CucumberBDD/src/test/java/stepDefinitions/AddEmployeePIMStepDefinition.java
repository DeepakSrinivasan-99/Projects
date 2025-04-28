package stepDefinitions;

import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddEmployeePIMPageObject;
import pageObjects.LoginPageObject;
import utils.TestContextSetup;

public class AddEmployeePIMStepDefinition {
	
    private LoginPageObject loginPage;
	public AddEmployeePIMPageObject addEmployeePIMPage;

	
	
	public AddEmployeePIMStepDefinition(TestContextSetup testContextSetup)
	{
     this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
	 this.addEmployeePIMPage=testContextSetup.pageObjectManager.getAddEmployeePIMPage();
	}
	

@Given("I am logged into OrangeHRM as an admin with Username {string} and Password {string}")
public void i_am_logged_into_orange_hrm_as_an_admin(String username,String password) {
    // Write code here that turns the phrase above into concrete actions
	loginPage.verifyOnLoginPage();
	
	loginPage.enterCredentials(username,password);

	loginPage.clearUsernameAndCheckRequired();
	loginPage.clearPasswordAndCheckRequired();

	loginPage.enterCredentials(username,password);
	
	loginPage.clickLogin();

}

@Given("I navigate to the PIM module")
public void i_navigate_to_the_pim_module() throws Exception {
    // Write code here that turns the phrase above into concrete actions
	addEmployeePIMPage.clickPIMOption();
}

@When("I click on Add Employee")
public void i_click_on_add_employee() {
  addEmployeePIMPage.clickAddEmployeeButton();
}


@When("I enter employee details")
public void i_enter_employee_details(DataTable dataTable) {
	    List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
	    String firstName = data.get(0).get("firstName");
	    String middleName = data.get(0).get("middleName");
	    String lastName = data.get(0).get("lastName");
	    String empId = data.get(0).get("empId");
	    
	    addEmployeePIMPage.enterEmployeeDetails(firstName, middleName, lastName, empId);
	    
	    addEmployeePIMPage.clearFormFields();
	    
	    addEmployeePIMPage.enterEmployeeDetails(firstName, middleName, lastName, empId);

	    
	    
	    
}

@When("I enter employees detail then click on Add and Save")
public void i_enter_multiple_employee_details(DataTable dataTable) throws InterruptedException {
    List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
    int temp=0;
    for (Map<String, String> record : data) {
        String firstName = record.get("firstName");
        String middleName = record.get("middleName");
        String lastName = record.get("lastName");
        String empId = record.get("empId");
        if(temp==0) { 
        	i_click_on_add_employee(); // Click "Add" before each entry
        }
        else {
        	addEmployeePIMPage.clickAddEmployeeButton1();
        }
        temp++;
        addEmployeePIMPage.enterEmployeeDetails(firstName, middleName, lastName, empId);

        i_click_on_save(); // Save the employee

        // Optional: Add wait or navigation step to go back to PIM page before adding next
    }

}
	    

	 

	


@When("I click on Save")
public void i_click_on_save() {
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.java.PendingException();
	addEmployeePIMPage.clickSaveEmployeeButton();
}

@Then("the employee should be added successfully")
public void the_employee_should_be_added_successfully() {
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.java.PendingException();
	addEmployeePIMPage.verifySuccessMessage();
}



@When("I navigate to the Employee List")
public void navigate_to_emp_list() {
	addEmployeePIMPage.clickEmployeeList();
}


@And("I search for employee with name {string}")
public void search_for_employee_with_name(String employeeName)
{
	addEmployeePIMPage.SearchEmployee(employeeName);
	addEmployeePIMPage.clickSearchButton();
	emp_record_should_be_displayed(employeeName);
}


@Then("the employee record should be displayed for {string}")
public void emp_record_should_be_displayed(String createdEmployeeName) {
   addEmployeePIMPage.verifySearchedEmployeeName(createdEmployeeName);
}


}
