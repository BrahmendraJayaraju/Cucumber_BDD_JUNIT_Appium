package driver;

import WebUtility.Utility;


public class MobileLifecycleManager {

    public static void uninstallAppBasedOnConfig()
    {

        try {

            String configPath = System.getProperty("user.dir") + "/testenvironment.properties";

            String platform = Utility.getValue(configPath, "platform");



            String deviceId = Utility.getValue(configPath, "deviceid");
            String bundleId = Utility.getValue(configPath, "bundleid");


            if (platform.equalsIgnoreCase("android"))
            {
                try {


                    if (platform.equalsIgnoreCase("android")) {

                        String appPackage = Utility.getValue(configPath, "apppackage");

                        Process process = Runtime.getRuntime().exec(
                                "adb -s " + deviceId + " uninstall " + appPackage
                        );
//wait until app is uninstalled from cmd
                        process.waitFor();

                        System.out.println("Android App Uninstalled (if installed)");
                    }

                } catch (Exception e) {
                    System.out.println("Uninstall skipped: " + e.getMessage());
                }
            }



            else if (platform.equalsIgnoreCase("ios")) {




                Process process = Runtime.getRuntime().exec(
                        "xcrun simctl uninstall " + deviceId + " " + bundleId
                );
//wait until app is uninstalled from cmd
                process.waitFor();

                System.out.println("iOS App Uninstalled");
            }

        } catch (Exception e) {

            System.out.println("Uninstall failed: " + e.getMessage());
        }
    }
}