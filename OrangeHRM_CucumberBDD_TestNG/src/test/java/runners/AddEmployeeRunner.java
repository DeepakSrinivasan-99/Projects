

//src/test/java/cucumberOptions/AddEmployeeRunner.java
package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
features = "src/test/java/features/PIM.feature",
glue     = "stepDefinitions",monochrome=true,
tags     = "@AddMultipleEmployee",
plugin = {
		"pretty",  
		"html:target/cucumber.html", "json:target/cucumber.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"rerun:target/failed_scenarios.txt" 
		})


public class AddEmployeeRunner extends AbstractTestNGCucumberTests {}
