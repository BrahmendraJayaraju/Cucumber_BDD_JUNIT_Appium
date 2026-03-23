package stepdefinitions;

import Pages.LoginPage;
import WebUtility.Utility;
import driver.DriverManager;

import io.appium.java_client.AppiumDriver;

import io.cucumber.datatable.DataTable;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;


public class LoginStep {


    private LoginPage login;
    private AppiumDriver driver;

    String username;
    String password;


    public LoginStep() {

        driver = DriverManager.getDriver();
        login = new LoginPage(driver);

    }


    @When("user enter the username and password")
    public void user_enter_the_username_and_password(DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        username = data.get(0).get("USERNAME");
        password = data.get(0).get("PASSWORD");


        login.username(username);
        login.password(password);


    }


    @When("user enter the username {string} and password {string}")
    public void user_enter_the_username_and_password_outline(String user, String pass) {


        login.username(user);
        login.password(pass);


    }


    @And("user click on the login button")
    public void user_click_on_the_login_button() {


        login.clikonlogin();


    }


    @Then("validate error message")
    public void validate_error_message() {


        login.validateerror();


    }


    @Then("validate login result")
    public void validate_login_result() {


    }


}
