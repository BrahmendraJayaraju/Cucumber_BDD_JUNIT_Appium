package Pages;

import WebUtility.Utility;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;


public class LoginPage extends Utility
{


    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage username(String element, String text) {

        this.enterText("xpath", element, text);
        return this;
    }


    public LoginPage password(String element, String text) {

        this.enterText("xpath", element, text);
        return this;
    }


    public LoginPage clikonlogin(String element) {

        this.clickElement("xpath", element);
        return this;
    }

    public LoginPage validateerror(String element,String value) {

String actualvalue=this.getText("accessibilityid",element);
System.out.println("das:"+actualvalue);
 this.compare(actualvalue,value);


        return this;
    }






}

