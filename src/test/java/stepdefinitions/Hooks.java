package stepdefinitions;

import WebUtility.Utility;
import driver.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private static final String CONFIG_PATH =
            System.getProperty("user.dir") + "/testenvironment.properties";



    @BeforeAll
    public static void globalSetUp() {

        System.out.println("===== BEFORE ALL =====");

        String execution = Utility.getValue(CONFIG_PATH, "execution");

        if (execution.equalsIgnoreCase("local")) {
            try {
                // Start Appium Server
                ServerManager.startServer();

                // Uninstall app
                MobileLifecycleManager.uninstallAppBasedOnConfig();

                System.out.println("Server Started + App Uninstalled");

            } catch (Exception e) {
                System.out.println("Global setup failed: " + e.getMessage());
            }
        }
    }



    @Before
    public void setUp() throws Exception {

        System.out.println("===== BEFORE HOOK =====");

        // Create driver
        AppiumDriver driver = DriverFactory.createDriver();

        if (driver == null) {
            throw new RuntimeException("Driver creation failed");
        }

        DriverManager.setDriver(driver);

        System.out.println("Driver Created: " + driver);
    }



    @AfterStep
    public void afterStep(Scenario scenario) {

        AppiumDriver driver = DriverManager.getDriver();

        if (driver != null) {
            try {
                if (driver.getSessionId() != null) {

                    byte[] screenshot = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);

                    scenario.attach(
                            screenshot,
                            "image/png",
                            "Step Screenshot - " + scenario.getName()
                    );
                }

            } catch (Exception e) {
                System.out.println("Screenshot skipped: " + e.getMessage());
            }
        }
    }



    @After
    public void tearDown() {

        System.out.println("===== AFTER HOOK =====");

        AppiumDriver driver = DriverManager.getDriver();

        if (driver != null) {
            try {

                String appPackage = Utility.getValue(CONFIG_PATH, "apppackage");
                String bundleId  = Utility.getValue(CONFIG_PATH, "bundleid");

                // -------- ANDROID --------
                if (driver instanceof AndroidDriver android)
                {

                    if (android.isAppInstalled(appPackage))
                    {
                        android.terminateApp(appPackage);
                        System.out.println("Android App Terminated");
                    }

                }

                // -------- IOS --------
                else if (driver instanceof IOSDriver ios)
                {

                    ios.terminateApp(bundleId);
                    System.out.println("iOS App Terminated");
                }

            } catch (Exception e) {
                System.out.println("App Termination Failed: " + e.getMessage());
            }

            DriverManager.quitDriver();
            System.out.println("Driver Quit Successfully");
        }
    }


    // ================= AFTER ALL =================
    @AfterAll
    public static void cleanUp()
    {

        System.out.println("===== AFTER ALL =====");

        String execution = Utility.getValue(CONFIG_PATH, "execution");

        if (execution.equalsIgnoreCase("local")) {

            try {
                ServerManager.stopServer();
                System.out.println("Appium Server Stopped");
            } catch (Exception e) {
                System.out.println("Server Stop Failed: " + e.getMessage());
            }
        }
    }
}