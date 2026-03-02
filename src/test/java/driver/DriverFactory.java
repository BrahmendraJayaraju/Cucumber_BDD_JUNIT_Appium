package driver;

import WebUtility.Utility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class DriverFactory {

    public static AppiumDriver createDriver() throws Exception {

        String configPath = System.getProperty("user.dir") + "/testenvironment.properties";

        String execution = Utility.getValue(configPath, "execution");
        String platform = Utility.getValue(configPath, "platform");

        String localurl = Utility.getValue(configPath, "localurl");
        String platformname = Utility.getValue(configPath, "platformname");
        String deviceid = Utility.getValue(configPath, "deviceid");
        String apppackage = Utility.getValue(configPath, "apppackage");
        String appactivity = Utility.getValue(configPath, "appactivity");
        String bundleid = Utility.getValue(configPath, "bundleid");
        String sauceurl = Utility.getValue(configPath, "sauceurl");
        String sauceusername = Utility.getValue(configPath, "sauce.username");
        String sauceaccesskey = Utility.getValue(configPath, "sauce.accesskey");

        System.out.println("Execution: " + execution);
        System.out.println("Platform: " + platform);


        // ---------- LOCAL ----------
        if (execution.equalsIgnoreCase("local")) {

            if (platform.equalsIgnoreCase("android")) {

                UiAutomator2Options options = new UiAutomator2Options();
                options.setPlatformName(platformname);
                options.setApp(System.getProperty("user.dir")
                        + "/Applications/Android/com.swaglabsmobileapp--12.apk");

                options.setAppPackage(apppackage);
                options.setAppActivity(appactivity);
                options.setUdid(deviceid);
                options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

                options.setCapability("ignoreHiddenApiPolicyError", true);

                options.setFullReset(true);

                options.setAppWaitDuration(Duration.ofSeconds(60));
                options.setNewCommandTimeout(Duration.ofSeconds(40));

                return new AndroidDriver(new URL(localurl), options);
            } else if (platform.equalsIgnoreCase("ios")) {

                XCUITestOptions options = new XCUITestOptions();

                options.setPlatformName("ios");
                options.setBundleId(bundleid);
                options.setUdid(deviceid);
                options.setAutomationName(AutomationName.IOS_XCUI_TEST);


                String appPath = System.getProperty("user.dir") +
                        "/Applications/ios/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip";
                options.setApp(appPath);

                options.setFullReset(true);


                return new IOSDriver(new URL(localurl), options);
            }
        }


        // ---------- BROWSERSTACK ----------
        else if (execution.equalsIgnoreCase("browserstack")) {

            String bsUrl = "https://hub.browserstack.com/wd/hub";

            if (platform.equalsIgnoreCase("android")) {

                UiAutomator2Options options = new UiAutomator2Options();

                options.setPlatformName("Android");
                options.setDeviceName("Samsung Galaxy S23");
                options.setPlatformVersion("13.0");

                // Upload app to BrowserStack and use returned app_url
                options.setCapability("appium:app", "bs://f78b9e4edb24407257622e54586bdce3dc9fe6ff");

                options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

                // SAME as local behavior
                options.setFullReset(true);     // uninstall + reinstall
                options.setNoReset(false);
                options.setAppWaitDuration(Duration.ofSeconds(60));
                options.setNewCommandTimeout(Duration.ofSeconds(40));

                options.setCapability("bstack:options", Map.of(
                        "userName", "brahmendrajayara_zI9n7K",
                        "accessKey", "9zCBA4RPLh4qsN9USceq",
                        "buildName", "Mobile-Build-1",
                        "sessionName", "Android Test"
                ));

                return new AndroidDriver(new URL(bsUrl), options);
            } else if (platform.equalsIgnoreCase("ios")) {

                XCUITestOptions options = new XCUITestOptions();

                options.setPlatformName("iOS");
                options.setDeviceName("iPhone 15");
                options.setPlatformVersion("17");

                options.setCapability("appium:app", "bs://<your-app-id>");

                options.setAutomationName(AutomationName.IOS_XCUI_TEST);

                // SAME as local behavior
                options.setFullReset(true);
                options.setNoReset(false);
                options.setNewCommandTimeout(Duration.ofSeconds(40));

                options.setCapability("bstack:options", Map.of(
                        "userName", "brahmendrajayara_zI9n7K",
                        "accessKey", "9zCBA4RPLh4qsN9USceq",
                        "buildName", "Mobile-Build-1",
                        "sessionName", "iOS Test"
                ));

                return new IOSDriver(new URL(bsUrl), options);
            }
        }
        throw new RuntimeException("Invalid execution or platform value!");
    }
}
