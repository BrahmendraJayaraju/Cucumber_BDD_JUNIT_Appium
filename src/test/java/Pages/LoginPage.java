package Pages;

import WebUtility.Utility;
import io.appium.java_client.AppiumDriver;



public class LoginPage extends Utility {
    public static String objectRepologin = "./Locators/login.properties";

    public static String logindata = "./TestData/loginTestData.properties";

    String configPath = System.getProperty("user.dir") + "/testenvironment.properties";

    String platform = Utility.getValue(configPath, "platform");

    public static String usernamexpathandroid = Utility.getValue(objectRepologin, "usernameandroid");
    public static String usernamexpathios = Utility.getValue(objectRepologin, "usernameios");

    public static String passwordxpathandroid = Utility.getValue(objectRepologin, "passwordandroid");
    public static String passwordxpathios = Utility.getValue(objectRepologin, "passwordios");


    public static String loginandroid = Utility.getValue(objectRepologin, "loginandroid");
    public static String loginios = Utility.getValue(objectRepologin, "loginios");


    public static String errorpathios = Utility.getValue(objectRepologin, "errorpath");
    public static String errorpathandroid = Utility.getValue(objectRepologin, "errorpathandroid");

    public static String errormessage = Utility.getValue(logindata, "lockouterror");


    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage username(String text) {


        if (platform.equalsIgnoreCase("android")) {


            this.enterText("xpath", usernamexpathandroid, text);


        } else if (platform.equalsIgnoreCase("ios")) {
            this.enterText("xpath", usernamexpathios, text);

        }


        return this;
    }


    public LoginPage password(String text) {


        if (platform.equalsIgnoreCase("android")) {


            this.enterText("xpath", passwordxpathandroid, text);


        } else if (platform.equalsIgnoreCase("ios")) {
            this.enterText("xpath", passwordxpathios, text);

        }

        return this;
    }


    public LoginPage clikonlogin() {


        if (platform.equalsIgnoreCase("android")) {


            this.clickElement("xpath", loginandroid);


        } else if (platform.equalsIgnoreCase("ios")) {
            this.clickElement("xpath", loginios);

        }


        return this;
    }

    public LoginPage validateerror() {





        if (platform.equalsIgnoreCase("android")) {

            String actualvalue = this.getText("xpath", errorpathandroid);


            this.compare(actualvalue, errormessage);


        } else if (platform.equalsIgnoreCase("ios")) {

            String actualvalue = this.getText("xpath", errorpathios);


            this.compare(actualvalue, errormessage);


        }


        return this;
    }


}

