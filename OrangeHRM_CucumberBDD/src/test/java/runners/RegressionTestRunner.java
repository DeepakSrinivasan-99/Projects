

package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
  features = "src/test/resources/features",
  glue = {"stepDefinitions","utils"},
  tags = "@Regression", // Run only Regression tests
  plugin = {
		    "pretty",
		    "html:target/cucumber.html",
		    "json:target/cucumber.json",
		    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		    "rerun:target/failed_scenarios.txt"
		  }
)
public class RegressionTestRunner extends AbstractTestNGCucumberTests {
	
	@Override
    @DataProvider(parallel = true)   //Executes scenario in scenario threads
    public Object[][] scenarios() {
	    System.setProperty("cucumber.parallellimit", "2");
        return super.scenarios();
    }
	
}
