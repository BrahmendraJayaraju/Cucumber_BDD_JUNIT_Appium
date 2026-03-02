package stepdefinitions;
import io.appium.java_client.AppiumDriver;
import driver.DriverFactory;
import driver.DriverManager;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    AppiumDriverLocalService service;


    @Before
    public void setUp() throws Exception {


        service= AppiumDriverLocalService.buildDefaultService();

        service.start();

        System.out.println("Before Hook Started");

        DriverManager.setDriver(
                DriverFactory.createDriver()

        );

        System.out.println("Driver Created: " + DriverManager.getDriver());
    }


    @AfterStep
    public void afterStep(Scenario scenario) {

        AppiumDriver driver = DriverManager.getDriver();

        if (driver == null) {
            System.out.println("Driver is null, skipping screenshot");
            return;
        }

        try {


            String status = scenario.getStatus().toString();


            String methodName = Thread.currentThread()
                    .getStackTrace()[2]
                    .getMethodName();


            String info =
                    "Scenario: " + scenario.getName() +
                            "\nStatus: " + status +
                            "\nMethod: " + methodName +
                            "\nThread: " + Thread.currentThread().getName();


            scenario.attach(
                    info.getBytes(),
                    "text/plain",
                    "Step Info"
            );


            byte[] screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);


            scenario.attach(
                    screenshot,
                    "image/png",
                    "Screenshot - " + status
            );

        } catch (Exception e) {
            System.out.println("Error in AfterStep: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {

        System.out.println("After Hook Started");

        DriverManager.quitDriver();
        service.stop();

    }
}