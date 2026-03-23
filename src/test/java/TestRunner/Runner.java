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
                "pretty", //clean readable output in console
                "html:target/cucumber-report.html",  //Generates HTML report
                "json:target/cucumber.json" //JSON report
        }
)
public class Runner {


}