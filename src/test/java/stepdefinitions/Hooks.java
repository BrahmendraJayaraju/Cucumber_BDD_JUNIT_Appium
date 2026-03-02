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

    private static final String configPath =
            System.getProperty("user.dir") + "/testenvironment.properties";


    private static boolean firstRun = true;





    // ================= BEFORE =================
    @Before
    public void setUp() throws Exception {
        System.out.println("Before Hook Running");
        String execution = Utility.getValue(configPath, "execution");

        if (firstRun & execution.equalsIgnoreCase("local") ){
            System.out.println("🔥 GLOBAL SETUP FROM HOOK 🔥");
            MobileLifecycleManager.uninstallAppBasedOnConfig();
            firstRun = false;
        }

        if (execution.equalsIgnoreCase("local")) {
            ServerManager.startServer();
        }

        DriverManager.setDriver(DriverFactory.createDriver());



        System.out.println("Driver Created: " + DriverManager.getDriver());
    }

    // ================= AFTER STEP =================
    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("After Hook Running");
        AppiumDriver driver = DriverManager.getDriver();

        if (driver == null) return;

        byte[] screenshot =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

        scenario.attach(screenshot, "image/png",
                "Screenshot - " + scenario.getStatus());
    }

    // ================= AFTER =================
    @After
    public void tearDown() {

        AppiumDriver driver = DriverManager.getDriver();
        System.out.println("Driver Quit outside");

        if (driver != null) {
            try {

                String configPath = System.getProperty("user.dir") + "/testenvironment.properties";

                String appPackage = Utility.getValue(configPath, "apppackage");
                String bundleId  = Utility.getValue(configPath, "bundleid");

                // -------- ANDROID --------
                if (driver instanceof AndroidDriver android) {

                    if (android.isAppInstalled(appPackage)) {
                        android.terminateApp(appPackage);
                        System.out.println("Android App Terminated Successfully");
                    }

                }

                // -------- IOS --------
                else if (driver instanceof IOSDriver ios) {

                    ios.terminateApp(bundleId);
                    System.out.println("iOS App Terminated Successfully");
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
    public static void cleanUp() {

        String execution = Utility.getValue(configPath, "execution");

        if (execution.equalsIgnoreCase("local")) {

            try {
                MobileLifecycleManager.uninstallAppBasedOnConfig() ;
            } catch (Exception ignored) {}

            ServerManager.stopServer();
        }
    }
}