package driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class ServerManager {

    private static AppiumDriverLocalService service;

    public static synchronized void startServer() {

        if (service == null || !service.isRunning()) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
            System.out.println("Appium Server Started");
        }
    }

    public static synchronized void stopServer() {

        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium Server Stopped");
        }
    }
}