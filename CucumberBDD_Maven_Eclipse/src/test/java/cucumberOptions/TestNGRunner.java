package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {
        "src/test/java/features/1_Login.feature",
        "src/test/java/features/3_DeleteProductFromCart.feature"
    },
    glue = "stepDefinitions",
    monochrome = true,
    tags = "@LoginPage and @DeleteFromCart",
    plugin = {
        "html:target/cucumber.html", 
        "json:target/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "rerun:target/failed_scenarios.txt"
    }
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
