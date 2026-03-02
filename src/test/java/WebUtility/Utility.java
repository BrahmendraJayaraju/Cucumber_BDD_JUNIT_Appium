package WebUtility;


import java.io.FileInputStream;
import java.util.Properties;


public class Utility {


    public static String getValue(String filePath, String key) {

        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(filePath);
            prop.load(fis);
            fis.close();

            return prop.getProperty(key);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
