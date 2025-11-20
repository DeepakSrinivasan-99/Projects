package pageObjects;

import org.openqa.selenium.WebDriver;


	
	
	// PageObjectManager.java (improved)
	public class PageObjectManager {
	    private final WebDriver driver;
	    private LoginPageObject loginPage;
	    private AddEmployeePIMPageObject addEmployeePIMPage; 
	    // add other pages as fields

	    public PageObjectManager(WebDriver driver) {
	        this.driver = driver;
	    }

	    public LoginPageObject getLoginPage() {
	        if (loginPage == null) {
	            loginPage = new LoginPageObject(driver);
	        }
	        return loginPage;
	    }

	    public AddEmployeePIMPageObject getAddEmployeePIMPage() {
	        if (addEmployeePIMPage == null) {
	            addEmployeePIMPage = new AddEmployeePIMPageObject(driver);
	        }
	        return addEmployeePIMPage;
	    }

	    
	
	}

	
	
	

