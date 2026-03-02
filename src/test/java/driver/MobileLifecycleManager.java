package driver;

import WebUtility.Utility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;

public class MobileLifecycleManager {



    // ================= UNINSTALL APP =================
    public static void uninstallAppBasedOnConfig() {

        try {

            String configPath = System.getProperty("user.dir") + "/testenvironment.properties";

            String platform = Utility.getValue(configPath, "platform");
            String deviceId = Utility.getValue(configPath, "deviceid");
            String localUrl = Utility.getValue(configPath, "localurl");

            if (platform.equalsIgnoreCase("android"))
            {
                try {


                    if (platform.equalsIgnoreCase("android")) {

                        String appPackage = Utility.getValue(configPath, "apppackage");

                        Process process = Runtime.getRuntime().exec(
                                "adb -s " + deviceId + " uninstall " + appPackage
                        );

                        process.waitFor();

                        System.out.println("Android App Uninstalled (if installed)");
                    }

                } catch (Exception e) {
                    System.out.println("Uninstall skipped: " + e.getMessage());
                }
            }



            else if (platform.equalsIgnoreCase("ios")) {

                String bundleId = Utility.getValue(configPath, "bundleid");

                // 🚀 Fastest way (No temp driver, no WDA restart)
                Process process = Runtime.getRuntime().exec(
                        "xcrun simctl uninstall " + deviceId + " " + bundleId
                );

                process.waitFor();

                System.out.println("iOS App Uninstalled");
            }

        } catch (Exception e) {

            System.out.println("Uninstall failed: " + e.getMessage());
        }
    }
}