package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features/Login.feature" },
		glue = "stepDefinitions", monochrome = true, 
		tags = "@LoginPage", 
		plugin = {
				"pretty",  
				"html:target/cucumber.html", "json:target/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/failed_scenarios.txt" 
				})


public class LoginRunner extends AbstractTestNGCucumberTests {
}
