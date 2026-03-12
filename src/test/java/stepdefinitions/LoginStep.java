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


public class LoginStep
{

    String configPath = System.getProperty("user.dir") + "/testenvironment.properties";

    String platform = System.getProperty("platform");

    private LoginPage login;
    private AppiumDriver driver;
    String username;
    String password;




    public static String objectRepologin = "./Locators/login.properties";

    public static String logindata = "./TestData/loginTestData.properties";
    public static String usernamexpathandroid = Utility.getValue( objectRepologin, "usernameandroid");
    public static String usernamexpathios = Utility.getValue( objectRepologin, "usernameios");

    public static String passwordxpathandroid = Utility.getValue( objectRepologin, "passwordandroid");
    public static String  passwordxpathios = Utility.getValue( objectRepologin, "passwordios");


    public static String  loginandroid= Utility.getValue( objectRepologin, "loginandroid");
    public static String  loginios = Utility.getValue( objectRepologin, "loginios");


    public static String  errorpathios= Utility.getValue( objectRepologin, "errorpath");

    public static String  errormessage = Utility.getValue( logindata , "lockouterror");



    public LoginStep()
    {

        driver = DriverManager.getDriver();
        login = new LoginPage(driver);
        if (platform == null || platform.isEmpty())
        {
            platform = Utility.getValue(configPath, "platform");
        }
    }



    @When("user enter the username and password")
    public void user_enter_the_username_and_password(DataTable dataTable) {

        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);

         username = data.get(0).get("USERNAME");
        password = data.get(0).get("PASSWORD");

        if (platform.equalsIgnoreCase("android"))
        {




            login.username(usernamexpathandroid,username);
            login.password(passwordxpathandroid,password);


        }
        else if(platform.equalsIgnoreCase("ios"))
        {
            login.username(usernamexpathios,username);
            login.password(passwordxpathios ,password);

        }

    }


    @When("user enter the username {string} and password {string}")
    public void user_enter_the_username_and_password_outline(String user, String pass) {

        System.out.println("Running for user: " + user);

        if (platform.equalsIgnoreCase("android")) {

            login.username(usernamexpathandroid,user);
            login.password(passwordxpathandroid,pass);

        } else if(platform.equalsIgnoreCase("ios")) {

            login.username(usernamexpathios,user);
            login.password(passwordxpathios ,pass);
        }
    }


    @And("user click on the login button")
    public void user_click_on_the_login_button() {

        if (platform.equalsIgnoreCase("android")) {
            login.clikonlogin(loginandroid);

        }
        else if (platform.equalsIgnoreCase("ios")) {


            login.clikonlogin(loginios);

        }

    }





    @Then("validate error message")
    public void validate_error_message() {

        if (platform.equalsIgnoreCase("android")) {

          //login.validateerror(,errormessage);
        }
        else if (platform.equalsIgnoreCase("ios")) {


            login.validateerror(errorpathios,errormessage);

        }


    }


    @Then("validate login result")
    public void validate_login_result() {


    }










}
