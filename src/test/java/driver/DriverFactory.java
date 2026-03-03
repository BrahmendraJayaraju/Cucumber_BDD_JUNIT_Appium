package driver;

import WebUtility.Utility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;

import java.net.URL;
import java.util.Map;

public class DriverFactory {

    public static AppiumDriver createDriver() throws Exception {

        String configPath = System.getProperty("user.dir") + "/testenvironment.properties";


        String execution = System.getProperty("execution");

        if (execution == null || execution.isEmpty()) {
            execution = Utility.getValue(configPath, "execution");
        }
        String platform = System.getProperty("platform");

        if (platform == null || platform.isEmpty()) {
            platform = Utility.getValue(configPath, "platform");
        }
        String localurl = Utility.getValue(configPath, "localurl");
        String deviceid = Utility.getValue(configPath, "deviceid");


        if (execution.equalsIgnoreCase("local")) {



            // =========================
            // LOCAL ANDROID
            // =========================
            if (platform.equalsIgnoreCase("android")) {

                UiAutomator2Options options = new UiAutomator2Options();

                options.setPlatformName("Android");
                options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
                options.setUdid(deviceid);



                // Always fresh install
                options.setApp(System.getProperty("user.dir")
                        +"/Applications/Android/com.swaglabsmobileapp--12.apk");

                options.setNoReset(true);
                options.setFullReset(false);
                options.setAppWaitActivity("*");


                options.setCapability("appium:forceAppLaunch", true);


                return new AndroidDriver(new URL(localurl), options);
            }
            // =========================
            // LOCAL IOS
            // =========================
            else if (platform.equalsIgnoreCase("ios")) {

                XCUITestOptions options = new XCUITestOptions();

                options.setPlatformName("iOS");
                options.setAutomationName(AutomationName.IOS_XCUI_TEST);
                options.setUdid(deviceid);


                // Always fresh install
                options.setApp(System.getProperty("user.dir")
                        +"/Applications/ios/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.zip");

                options.setNoReset(true);
                options.setFullReset(false);


                return new IOSDriver(new URL(localurl), options);
            }
        }

        // =========================
        // BROWSERSTACK
        // =========================
        else if (execution.equalsIgnoreCase("browserstack")) {

            String browserstackbaseurl= System.getProperty("browserstackbaseurl");

            String bsUrl = System.getProperty(browserstackbaseurl);

            String bsUsername = System.getenv("BROWSERSTACK_USERNAME");
            String bsAccessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            String bsApp = Utility.getValue(configPath, "bs.app");
            String bsDevice = Utility.getValue(configPath, "bs.device");
            String bsVersion = Utility.getValue(configPath, "bs.osVersion");

            String buildName = "Mobile-Build-" + System.currentTimeMillis();
            String sessionName = "Thread-" + Thread.currentThread().getId();

            if (platform.equalsIgnoreCase("android")) {

                UiAutomator2Options options = new UiAutomator2Options();

                options.setPlatformName("Android");
                options.setDeviceName(bsDevice);
                options.setPlatformVersion(bsVersion);
                options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

                options.setCapability("appium:app", bsApp);

                options.setCapability("bstack:options", Map.of(
                        "userName", bsUsername,
                        "accessKey", bsAccessKey,
                        "projectName", "SwagLabs Automation",
                        "buildName", buildName,
                        "sessionName", sessionName,
                        "debug", true,
                        "networkLogs", true,
                        "deviceLogs", true
                ));

                return new AndroidDriver(new URL(bsUrl), options);
            }

            else if (platform.equalsIgnoreCase("ios")) {

                XCUITestOptions options = new XCUITestOptions();

                options.setPlatformName("iOS");
                options.setDeviceName(bsDevice);
                options.setPlatformVersion(bsVersion);
                options.setAutomationName(AutomationName.IOS_XCUI_TEST);

                options.setCapability("appium:app", bsApp);

                options.setCapability("bstack:options", Map.of(
                        "userName", bsUsername,
                        "accessKey", bsAccessKey,
                        "projectName", "SwagLabs Automation",
                        "buildName", buildName,
                        "sessionName", sessionName,
                        "debug", true,
                        "networkLogs", true,
                        "deviceLogs", true
                ));

                return new IOSDriver(new URL(bsUrl), options);
            }
        }

        throw new RuntimeException("Invalid execution or platform value!");
    }




}