package pageObjects;

import org.openqa.selenium.*;
import org.testng.Assert;
import java.util.Arrays;
import java.util.List;

public class AddEmployeePIMPageObject extends BasePage {

    private final By PIM = By.xpath("//a[contains(@href,'pim/viewPimModule')]");
    private final By addEmployeeButton = By.xpath("//button[contains(@class,'oxd-button--secondary') and normalize-space()='Add']");
    private final By sidebarAddEmployeeButton = By.xpath("//ul/li/a[text()='Add Employee']");

    private final By firstNameInput = By.xpath("//input[@name='firstName']");
    private final By middleNameInput = By.xpath("//input[@name='middleName']");
    private final By lastNameInput = By.xpath("//input[@name='lastName']");
    private final By empIdInput = By.xpath("//label[text()='Employee Id']/following::input[1]");

    private final By saveButton = By.xpath("//div[@class='oxd-form-actions']/button[contains(@class,'orangehrm-left-space')]");
    private final By viewSuccessMessage = By.cssSelector(".oxd-toast-content");

    private final By employeeListTab = By.xpath("//ul/li/a[text()='Employee List']");
    private final By searchInput = By.xpath("//div/input[@placeholder='Type for hints...']");
    private final By searchButton = By.xpath("//button[@type='submit' and contains(normalize-space(),'Search')]");

    public AddEmployeePIMPageObject(WebDriver driver) {
        super(driver);
    }

    public void clickPIMOption() {
        click(PIM);
    }

    public void clickAddEmployeeButton() {
        click(addEmployeeButton);
    }

    public void clickSidebarAddEmployeeButton() {
        click(sidebarAddEmployeeButton);
    }

    public void enterEmployeeDetails(String firstName, String middleName, String lastName, String empId) {
        type(firstNameInput, firstName);
        type(middleNameInput, middleName);
        type(lastNameInput, lastName);

        if (empId != null && !empId.isEmpty()) {
            type(empIdInput, empId);
        }
    }

    public void clearFormFields() {
        List<By> fields = Arrays.asList(firstNameInput, middleNameInput, lastNameInput, empIdInput);
        fields.forEach(this::clearField);
    }

    public void clickSaveEmployeeButton() {
        click(saveButton);
    }

    public void verifySuccessMessage() {
        if (!isVisible(viewSuccessMessage)) {
            throw new AssertionError("Success message not visible!");
        }
    }

    public void clickEmployeeListTab() {
        click(employeeListTab);
    }

    public void searchForEmployee(String empName) {
        type(searchInput, empName);
        click(searchButton);
    }

    public void verifySearchedEmployeeByName(String name) {
        String xpath = "//div[@class='oxd-table-cell oxd-padding-cell']//div[normalize-space()='" + name + "']";
        Assert.assertEquals(getText(By.xpath(xpath)), name);
    }
}
