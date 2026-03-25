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

    public static AppiumDriver createDriver() throws Exception
    {

        String configPath = System.getProperty("user.dir") + "/testenvironment.properties";

        String execution = Utility.getValue(configPath, "execution");
        String platform = Utility.getValue(configPath, "platform");


        String apppackage= Utility.getValue(configPath, "apppackage");

        String  appactivity=Utility.getValue(configPath, "appactivity");
        String bundleid=Utility.getValue(configPath, "bundleid");
        String localurl = Utility.getValue(configPath, "localurl");
        String deviceid = Utility.getValue(configPath, "deviceid");











        if (execution.equalsIgnoreCase("local")) {




            if (platform.equalsIgnoreCase("android")) {

                UiAutomator2Options options = new UiAutomator2Options();

                options.setApp(System.getProperty("user.dir")+"/Applications/Android/com.swaglabsmobileapp--12.apk");
                options.setPlatformName("Android");
                options.setAppPackage(apppackage);
                options.setAppActivity(appactivity);
                options.setAppWaitActivity("*");
                options.setUdid(deviceid);
                options.setAutoGrantPermissions(true);
                options.setCapability("appium:forceAppLaunch", true);
                options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
                options.setNoReset(false);


                return new AndroidDriver(new URL(localurl), options);
            }


            else if (platform.equalsIgnoreCase("ios")) {

                XCUITestOptions options = new XCUITestOptions();
                options.setApp(System.getProperty("user.dir") +"/Applications/ios/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.zip");
                options.setPlatformName("iOS");
                options.setUdid(deviceid);

                options.setBundleId(bundleid);
                options.setAutoAcceptAlerts(true);
                options.setAutomationName(AutomationName.IOS_XCUI_TEST);
                //ios working opposite
                options.setNoReset(true);
                options.setCapability("useNewWDA", false);











                return new IOSDriver(new URL(localurl), options);
            }
        }


        else if (execution.equalsIgnoreCase("browserstack")) {


            String bsUrl = Utility.getValue(configPath, "browserstackbaseurl");

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
                options.setAutoGrantPermissions(true);


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
                options.setAutoAcceptAlerts(true);


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