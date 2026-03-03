package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/swaglabs/swaglabloginTest.feature",
        glue = {"stepdefinitions"},
        monochrome = true,
        dryRun = false,
        tags="@REGRES",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        }
)
public class Runner {


}