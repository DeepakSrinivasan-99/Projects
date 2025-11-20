

package runners;

import org.testng.IRetryAnalyzer;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.RetryAnalyzer;

@CucumberOptions(
  features = "src/test/resources/features",
  glue = {"stepDefinitions","utils"},
  tags = "@Sanity", // Run only sanity tests
  plugin = {
		    "pretty",
		    "html:target/cucumber.html",
		    "json:target/cucumber.json",
		    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		    "rerun:target/failed_scenarios.txt"
		  }
)
public class SanityRunner extends AbstractTestNGCucumberTests {
	
	@Override
    @DataProvider(parallel = true)   //Executes scenario in scenario threads
    public Object[][] scenarios() {
        return super.scenarios();
    }
	
	/*
	public IRetryAnalyzer getRetryAnalyzer() {
	    return new RetryAnalyzer();
	}
*/
	
}
