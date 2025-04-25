package pageObjects;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class AddEmployeePIMPageObject {
	
	public WebDriverWait wait;
	public WebDriver driver;
	
	By PIM = By.xpath("//a[contains(@href,'pim/viewPimModule')]");
	By addEmployeeButton= By.xpath("//button[contains(@class,'oxd-button--secondary') and normalize-space()='Add']");
	By enterFirstName = By.xpath("//input[@name='firstName']");
	By enterMiddleName = By.xpath("//input[@name='middleName']");
	By enterLastName = By.xpath("//input[@name='lastName']");
	By enterEmployeeID = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	                              
	By clickSaveButton = By.xpath("//div[@class='oxd-form-actions']/button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	
	By verifyEmployeeNameAfterAdded = By.xpath("//div[@class='orangehrm-edit-employee-name']");
	
	
	By addEmployeeButton1 = By.xpath("//ul/li/a[text()='Add Employee']");
	
	By viewSuccessMessage= By.xpath("//p[normalize-space()='Success']");

	By clickEmployeeList = By.xpath("//ul/li/a[text()='Employee List']");
	
	By searchEmployee = By.xpath("//div/input[@placeholder='Type for hints...']");
	
	By clickSearchButton = By.xpath("//button[@type='submit' and contains(normalize-space(),'Search')]");

	
   int i=1;
	
	public AddEmployeePIMPageObject(WebDriver driver) {
		this.driver=driver;
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
	}


	public void clickPIMOption() {
		
		 List<WebElement> menuItems = driver.findElements(By.xpath("//a[@class='oxd-main-menu-item']"));
		    for (WebElement item : menuItems) {
		        System.out.println(item.getText());
		    }
		    
	    try {
	        Thread.sleep(2000); // Debug only, remove this after testing
	        WebElement PIMOption = wait.until(ExpectedConditions.elementToBeClickable(PIM));
	        PIMOption.click();
	    } catch (Exception e) {
	        System.out.println("Error clicking PIM: " + e.getMessage());
	    }
	}
	    
	   public void clickAddEmployeeButton()
	   {
		   WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton));
		   addButton.click();
		   
	   }
	   
	   public void clickAddEmployeeButton1() {
		   wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton1)).click();
	   }

	
	public void enterEmployeeDetails(String firstName, String middleName, String lastName, String empId) {
		
	    driver.findElement(enterFirstName).sendKeys(firstName);
	    driver.findElement(enterMiddleName).sendKeys(middleName);
	    driver.findElement(enterLastName).sendKeys(lastName);
	 
	    WebElement idInput = wait.until(ExpectedConditions.elementToBeClickable(enterEmployeeID));
        // clear any pre‑filled text
        idInput.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        idInput.sendKeys(empId);
	}
	
	
public void enterMultipleEmployeeDetails(String firstName, String middleName, String lastName, String empId) {
		
	    driver.findElement(enterFirstName).sendKeys(firstName);
	    driver.findElement(enterMiddleName).sendKeys(middleName);
	    driver.findElement(enterLastName).sendKeys(lastName);
	 
	    WebElement idInput = wait.until(ExpectedConditions.elementToBeClickable(enterEmployeeID));
        // clear any pre‑filled text
        idInput.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        idInput.sendKeys(empId);
	}

	public void clearFormFields() {
	    List<By> inputFields = Arrays.asList(
	        enterFirstName,
	        enterMiddleName,
	        enterLastName,
	       enterEmployeeID);

	    for (By locator : inputFields) {
	        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        input.sendKeys(Keys.CONTROL + "a", Keys.DELETE, Keys.TAB);
	    }
	}

	public void clickSaveEmployeeButton() {
		// TODO Auto-generated method stub
		driver.findElement(clickSaveButton).click();
		
	}
	
	public void verifySuccessMessage() {
		String successMessage= wait.until(ExpectedConditions.presenceOfElementLocated(viewSuccessMessage)).getText();
		System.out.println("Emp "+i+" added and Success Message :  "+successMessage); i++;
	}
	
	public void clickEmployeeList()
	{
	   wait.until(ExpectedConditions.elementToBeClickable(clickEmployeeList)).click();
	}
	
	public void SearchEmployee(String employeeName)
	{
		wait.until(ExpectedConditions.elementToBeClickable(searchEmployee)).sendKeys(employeeName);
	}
	
	public void clickSearchButton() {
		wait.until(ExpectedConditions.elementToBeClickable(clickSearchButton)).click();
	}
	
	public void verifySearchedEmployeeName(String createdEmployeeName) {
		By searchedEmployeeName = By.xpath(
		        "//div[@class='oxd-table-cell oxd-padding-cell']/div[normalize-space()='"+ createdEmployeeName + "']"
		            );
		String searchedEmpName= wait.until(ExpectedConditions.visibilityOfElementLocated(searchedEmployeeName)).getText();
		Assert.assertEquals(searchedEmpName,createdEmployeeName);
	}
}
