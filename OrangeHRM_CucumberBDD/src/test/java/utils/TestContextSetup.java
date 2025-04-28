package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pageObjects.PageObjectManager;

public class TestContextSetup {

	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public GenericUtils genericUtils;

	
	public TestContextSetup() throws IOException
	{
		testBase = new TestBase();
		pageObjectManager = new PageObjectManager(testBase.WebDriverManager());
		genericUtils = new GenericUtils(testBase.WebDriverManager());
		
		
		

	}
	
}


/*
 
 Below are the steps and key points explaining how the TestContextSetup class works:

The TestContextSetup class acts as a central container for shared resources used throughout your test execution.
 When an instance of this class is created, its constructor initializes the key components needed by your tests. 

Initialization of TestBase:

The constructor of TestContextSetup first creates a new instance of TestBase.
TestBase is responsible for initializing the WebDriver (setting up the browser, timeouts, etc.).
Creating the WebDriver Instance:

The method testBase.WebDriverManager() is called to obtain a WebDriver instance.
This ensures that all further actions use the same browser session.
Initializing the PageObjectManager:

A new instance of PageObjectManager is created by passing the WebDriver instance from TestBase.
PageObjectManager manages the creation and retrieval of various page objects (representing different pages of the application), so they all use the same WebDriver.
Initializing GenericUtils:

Similarly, GenericUtils is instantiated with the same WebDriver.
GenericUtils typically contains common utility methods (e.g., for handling waits, alerts, or common actions).
Shared Test Context:

By initializing these components in the constructor of TestContextSetup, you create a shared context.
This shared context allows different parts of your test framework (such as step definitions in Cucumber) to access the same WebDriver and helper objects.
Benefits:

Consistency: All tests and page objects work on the same browser instance.
Reusability: Common utilities and page objects are centralized.
Maintainability: Changes to WebDriver initialization or utilities are made in one place.
This setup ensures that every test has access to a common context (WebDriver, PageObjectManager, and GenericUtils), making your framework more organized and easier to maintain.


 
 */

