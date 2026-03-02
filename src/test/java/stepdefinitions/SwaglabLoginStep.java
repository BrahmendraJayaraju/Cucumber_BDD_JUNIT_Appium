package stepdefinitions;

import driver.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import io.cucumber.datatable.DataTable;



import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

import org.openqa.selenium.WebElement;


public class SwaglabLoginStep {

    private AppiumDriver getDriver() {
        return DriverManager.getDriver();
    }


    @When("user enter the username {string}")
    public void user_enter_the_username(String username) throws InterruptedException {
        getDriver().findElement(AppiumBy.xpath("//android.view.ViewGroup/android.widget.EditText[@content-desc='test-Username']")).sendKeys(username);

        Thread.sleep(5000);
    }

    @When("user enter the password {string}")
    public void user_enter_the_password(String password) throws InterruptedException {
        getDriver().findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='test-Password' and @text='Password']")).sendKeys(password);


        Thread.sleep(2000);
    }

    @When("user click on the login button")
    public void user_click_on_the_login_button() throws InterruptedException {
        getDriver().findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN' or @content-desc='LOGIN']")).click();

        Thread.sleep(2000);
    }


    @Then("user can see the dashboard")
    public void user_can_see_the_dashboard() throws InterruptedException {


    }

    @Then("user close the app")
    public void user_close_the_app() {

    }


    @When("user enter the username  and password")
    public void user_enter_the_username_and_password(DataTable dataTable) throws InterruptedException {


    }

    @Then("validate error message")
    public void validate_error_message() {
        WebElement error_message = getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Sorry, this user has been locked out.\"]"));
        Assert.assertTrue(error_message.isDisplayed());


    }


    @When("user clicks on tab")
    public void user_clicks_on_tab() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(10000);
        getDriver().findElement(
                AppiumBy.xpath("//XCUIElementTypeButton[@name=\"tab bar option cart\"]")
        ).click();
    }



}
