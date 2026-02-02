package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class SwaglabLoginPage {


     Scenario scenario;
    AndroidDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("user open the swaglab app in android")
    public void user_open_the_swaglab_app_in_android() throws InterruptedException, MalformedURLException {
        UiAutomator2Options a1=new UiAutomator2Options();


        a1.setAppPackage("com.swaglabsmobileapp");//to get app package
        a1.setAppActivity("com.swaglabsmobileapp.MainActivity");// to get actvty name

        a1.setUdid("emulator-5554");// get the on which device you need to run
        a1.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2); // to authorize the adb to control the device

        a1.setCapability("ignoreHiddenApiPolicyError", true);

        // Connect to Appium 3.x server
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), a1);
        Thread.sleep(5000);

       scenario.log("brahmendra jayaraju passed tc 01 ");
    }
    @When("user enter the username {string}")
    public void user_enter_the_username(String username) throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup/android.widget.EditText[@content-desc='test-Username']")).sendKeys(username);

        Thread.sleep(5000);
    }
    @When("user enter the password {string}")
    public void user_enter_the_password(String password) throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='test-Password' and @text='Password']")).sendKeys(password);


        Thread.sleep(2000);
    }
    @When("user click on the login button")
    public void user_click_on_the_login_button() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN' or @content-desc='LOGIN']")).click();

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

        List<Map<String,String>> testdata=dataTable.asMaps(String.class,String.class);

        for(int i=0;i<testdata.size();i++) {
            Thread.sleep(3000);
            driver.findElement(AppiumBy.xpath("//android.view.ViewGroup/android.widget.EditText[@content-desc='test-Username']")).sendKeys(testdata.get(i).get("USERNAME"));
            driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='test-Password' and @text='Password']")).sendKeys(testdata.get(i).get("PASSWORD"));

        }

    }

    @Then("validate error message")
    public void validate_error_message() {
        WebElement error_message=driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Sorry, this user has been locked out.\"]"));
        Assert.assertTrue(error_message.isDisplayed());


    }

    @When("user enter the username {}")
    public void userEnterTheUsernameUSERNAME(String username) {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup/android.widget.EditText[@content-desc='test-Username']")).sendKeys(username);

    }

    @And("user enter the password {}")
    public void userEnterThePasswordPASSWORD(String password) {
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='test-Password' and @text='Password']")).sendKeys(password);

    }






    @AfterStep
    public  void afteral(Scenario scenario) {

        if (driver == null) {
            System.out.println("Driver is null, skipping screenshot");
            return;
        }
        System.out.println("test status:"+scenario.getStatus());
          this.scenario=scenario;
        try {

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png",scenario.getName());
        }
        catch (WebDriverException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
