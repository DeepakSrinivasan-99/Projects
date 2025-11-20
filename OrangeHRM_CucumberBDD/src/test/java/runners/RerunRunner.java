package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "@target/failed_scenarios.txt",
    glue = {"stepDefinitions","utils"},
    plugin = {
        "pretty",
        "html:target/rerun-cucumber.html",
        "json:target/rerun-cucumber.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    }
)
public class RerunRunner extends AbstractTestNGCucumberTests {

}
