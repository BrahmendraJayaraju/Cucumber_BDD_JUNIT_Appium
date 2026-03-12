package WebUtility;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.Assert;
import org.openqa.selenium.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.net.URL;
import java.time.Duration;
import io.appium.java_client.AppiumBy;


import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;

import java.util.*;




public class Utility {

    public AppiumDriver driver;
    public WebDriverWait wait;

    public Utility(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));


    }

    public void waitForElement(String locatorType, String value) {
        try {
            switch (locatorType.toLowerCase()) {
                case "id":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(value)));
                    break;

                case "accessibilityid":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(value)));
                    break;

                case "xpath":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(value)));
                    break;

                case "classname":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.className(value)));
                    break;

                case "androiduiautomator":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator(value)));
                    break;

                case "iospredicate":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.iOSNsPredicateString(value)));
                    break;

                case "iosclasschain":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.iOSClassChain(value)));
                    break;

                default:
                    System.out.println("Invalid locator type: " + locatorType);
            }
        } catch (Exception e) {
            System.out.println("Wait failed for element: " + value);
        }
    }



    public void clickElement(String locatorType, String value) {

        waitForElement(locatorType, value);
        try {
            switch (locatorType.toLowerCase()) {
                case "xpath":


                    driver.findElement(AppiumBy.xpath(value)).click();
                    break;

                case "id":

                    driver.findElement(AppiumBy.id(value)).click();
                    break;

                case "accessibilityid":

                    driver.findElement(AppiumBy.accessibilityId(value)).click();
                    break;

                case "classname":

                    driver.findElement(AppiumBy.className(value)).click();
                    break;

                case "androiduiautomator":

                    driver.findElement(AppiumBy.androidUIAutomator(value)).click();
                    break;

                case "iospredicate":

                    driver.findElement(AppiumBy.iOSNsPredicateString(value)).click();
                    break;

                case "iosclasschain":

                    driver.findElement(AppiumBy.iOSClassChain(value)).click();
                    break;

                default:
                    System.out.println("Invalid locator type: " + locatorType);
            }
        } catch (Exception e) {
            System.out.println("Unable to click element: " + e.getMessage());
        }
    }



    public void enterText(String locatorType, String value, String text) {

        try {
            waitForElement(locatorType, value);
            switch (locatorType.toLowerCase()) {
                case "xpath":

                    driver.findElement(AppiumBy.xpath(value)).sendKeys(text);
                    break;

                case "id":

                    driver.findElement(AppiumBy.id(value)).sendKeys(text);
                    break;

                case "accessibilityid":

                    driver.findElement(AppiumBy.accessibilityId(value)).sendKeys(text);
                    break;

                case "classname":

                    driver.findElement(AppiumBy.className(value)).sendKeys(text);
                    break;

                case "androiduiautomator":

                    driver.findElement(AppiumBy.androidUIAutomator(value)).sendKeys(text);
                    break;

                case "iospredicate":

                    driver.findElement(AppiumBy.iOSNsPredicateString(value)).sendKeys(text);
                    break;

                case "iosclasschain":

                    driver.findElement(AppiumBy.iOSClassChain(value)).sendKeys(text);
                    break;

                default:
                    System.out.println("Invalid locator type: " + locatorType);
            }
        } catch (Exception e) {
            System.out.println("Unable to enter text: " + e.getMessage());
        }
    }

    //done a ios
    public boolean isElementDisplayed(String locatorType, String value) {

        try {
            waitForElement(locatorType, value);
            switch (locatorType.toLowerCase()) {
                case "id":

                    return driver.findElement(AppiumBy.id(value)).isDisplayed();

                case "accessibilityid":

                    return driver.findElement(AppiumBy.accessibilityId(value)).isDisplayed();

                case "xpath":

                    return driver.findElement(AppiumBy.xpath(value)).isDisplayed();

                case "classname":

                    return driver.findElement(AppiumBy.className(value)).isDisplayed();

                case "androiduiautomator":

                    return driver.findElement(AppiumBy.androidUIAutomator(value)).isDisplayed();

                case "iospredicate":

                    return driver.findElement(AppiumBy.iOSNsPredicateString(value)).isDisplayed();

                case "iosclasschain":

                    return driver.findElement(AppiumBy.iOSClassChain(value)).isDisplayed();

                default:
                    System.out.println("Invalid locator type: " + locatorType);
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //done a ios
    public String getAttribute(String locatorType, String value, String attribute) {
        try {
            waitForElement(locatorType, value);

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    return driver.findElement(AppiumBy.xpath(value)).getAttribute(attribute);

                case "id":
                    return driver.findElement(AppiumBy.id(value)).getAttribute(attribute);

                case "accessibilityid":
                    return driver.findElement(AppiumBy.accessibilityId(value)).getAttribute(attribute);

                case "classname":
                    return driver.findElement(AppiumBy.className(value)).getAttribute(attribute);

                case "androiduiautomator":
                    return driver.findElement(AppiumBy.androidUIAutomator(value)).getAttribute(attribute);

                case "iospredicate":
                    return driver.findElement(AppiumBy.iOSNsPredicateString(value)).getAttribute(attribute);

                case "iosclasschain":
                    return driver.findElement(AppiumBy.iOSClassChain(value)).getAttribute(attribute);

                default:
                    System.out.println("Invalid locator type: " + locatorType);
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    //done a ios
    public void singleTap(String locatorType, String value, int sequenceNumber, int pauseTime) {
        try {
            waitForElement(locatorType, value);

            WebElement element = null;

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    element = driver.findElement(AppiumBy.xpath(value));
                    break;

                case "id":
                    element = driver.findElement(AppiumBy.id(value));
                    break;

                case "accessibilityid":
                    element = driver.findElement(AppiumBy.accessibilityId(value));
                    break;

                case "classname":
                    element = driver.findElement(AppiumBy.className(value));
                    break;

                case "androiduiautomator":
                    element = driver.findElement(AppiumBy.androidUIAutomator(value));
                    break;

                case "iospredicate":
                    element = driver.findElement(AppiumBy.iOSNsPredicateString(value));
                    break;

                case "iosclasschain":
                    element = driver.findElement(AppiumBy.iOSClassChain(value));
                    break;

                default:
                    System.out.println("Invalid locator type: " + locatorType);
                    return;
            }

            Point location = element.getLocation();
            Dimension size = element.getSize();

            Point center = getCenterElement(location, size);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "FingerTouch");

            Sequence seq = new Sequence(finger, sequenceNumber);

            seq.addAction(finger.createPointerMove(Duration.ZERO,
                            PointerInput.Origin.viewport(), center))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofMillis(pauseTime)))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(seq));
        } catch (Exception e) {
            System.out.println("Single tap failed: " + e.getMessage());
        }
    }


    //done a ios
    public void clearText(String locatorType, String value) {
        try {
            waitForElement(locatorType, value);

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    driver.findElement(AppiumBy.xpath(value)).clear();
                    break;

                case "id":
                    driver.findElement(AppiumBy.id(value)).clear();
                    break;

                case "accessibilityid":
                    driver.findElement(AppiumBy.accessibilityId(value)).clear();
                    break;

                case "classname":
                    driver.findElement(AppiumBy.className(value)).clear();
                    break;

                case "androiduiautomator":
                    driver.findElement(AppiumBy.androidUIAutomator(value)).clear();
                    break;

                case "iospredicate":
                    driver.findElement(AppiumBy.iOSNsPredicateString(value)).clear();
                    break;

                case "iosclasschain":
                    driver.findElement(AppiumBy.iOSClassChain(value)).clear();
                    break;

                default:
                    System.out.println("Invalid locator type: " + locatorType);
            }
        } catch (Exception e) {
            System.out.println("Unable to clear text: " + e.getMessage());
        }
    }



    public String getText(String locatorType, String value)
    {
        try {
            waitForElement(locatorType, value);

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    return driver.findElement(AppiumBy.xpath(value)).getText();

                case "id":
                    return driver.findElement(AppiumBy.id(value)).getText();

                case "accessibilityid":

                    System.out.println("camehere");

                    return driver.findElement(AppiumBy.accessibilityId(value)).getText();

                case "classname":
                    return driver.findElement(AppiumBy.className(value)).getText();

                case "androiduiautomator":
                    return driver.findElement(AppiumBy.androidUIAutomator(value)).getText();

                case "iospredicate":
                    return driver.findElement(AppiumBy.iOSNsPredicateString(value)).getText();

                case "iosclasschain":
                    return driver.findElement(AppiumBy.iOSClassChain(value)).getText();

                default:
                    System.out.println("Invalid locator type: " + locatorType);
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    public void clickUsingActions(String locatorType, String value) {
        try {
            waitForElement(locatorType, value);

            WebElement element = null;

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    element = driver.findElement(AppiumBy.xpath(value));
                    break;

                case "id":
                    element = driver.findElement(AppiumBy.id(value));
                    break;

                case "accessibilityid":
                    element = driver.findElement(AppiumBy.accessibilityId(value));
                    break;

                case "classname":
                    element = driver.findElement(AppiumBy.className(value));
                    break;

                case "androiduiautomator":
                    element = driver.findElement(AppiumBy.androidUIAutomator(value));
                    break;

                case "iospredicate":
                    element = driver.findElement(AppiumBy.iOSNsPredicateString(value));
                    break;

                case "iosclasschain":
                    element = driver.findElement(AppiumBy.iOSClassChain(value));
                    break;

                default:
                    System.out.println("Invalid locator type: " + locatorType);
                    return;
            }

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();

        } catch (Exception e) {
            System.out.println("Actions click failed: " + e.getMessage());
        }
    }


    //done a ios
    public void doubleTap(String locatorType, String value, int sequenceNumber, int pauseTime) {
        try {
            waitForElement(locatorType, value);

            WebElement element = null;

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    element = driver.findElement(AppiumBy.xpath(value));
                    break;

                case "id":
                    element = driver.findElement(AppiumBy.id(value));
                    break;

                case "accessibilityid":
                    element = driver.findElement(AppiumBy.accessibilityId(value));
                    break;

                case "classname":
                    element = driver.findElement(AppiumBy.className(value));
                    break;

                case "androiduiautomator":
                    element = driver.findElement(AppiumBy.androidUIAutomator(value));
                    break;

                case "iospredicate":
                    element = driver.findElement(AppiumBy.iOSNsPredicateString(value));
                    break;

                case "iosclasschain":
                    element = driver.findElement(AppiumBy.iOSClassChain(value));
                    break;

                default:
                    System.out.println("Invalid locator type: " + locatorType);
                    return;
            }

            Point location = element.getLocation();
            Dimension size = element.getSize();

            Point center = getCenterElement(location, size);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "FingerTouch");

            Sequence seq = new Sequence(finger, sequenceNumber);

            seq.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofMillis(pauseTime)))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))

                    .addAction(new Pause(finger, Duration.ofMillis(pauseTime)))

                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofMillis(pauseTime)))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(seq));

            System.out.println("Double tap completed");

        } catch (Exception e) {
            System.out.println("Double tap failed: " + e.getMessage());
        }
    }

    //done a  left and right images
    public void swipeElement(String locatorType,
                             String value,
                             String direction,
                             double startFraction,
                             double endFraction,
                             int holdTime,
                             int moveTime) {
        try {
            waitForElement(locatorType, value);

            WebElement element = null;

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    element = driver.findElement(AppiumBy.xpath(value));
                    break;

                case "id":
                    element = driver.findElement(AppiumBy.id(value));
                    break;

                case "accessibilityid":
                    element = driver.findElement(AppiumBy.accessibilityId(value));
                    break;

                case "classname":
                    element = driver.findElement(AppiumBy.className(value));
                    break;

                case "androiduiautomator":
                    element = driver.findElement(AppiumBy.androidUIAutomator(value));
                    break;

                case "iospredicate":
                    element = driver.findElement(AppiumBy.iOSNsPredicateString(value));
                    break;

                case "iosclasschain":
                    element = driver.findElement(AppiumBy.iOSClassChain(value));
                    break;

                default:
                    System.out.println("Invalid locator type");
                    return;
            }

            int elementX = element.getRect().x;
            int elementY = element.getRect().y;
            int width = element.getSize().width;
            int height = element.getSize().height;

            int startx = 0;
            int endx = 0;

            if (direction.equalsIgnoreCase("left")) {
                startx = elementX + (int) (width * startFraction);
                endx = elementX + (int) (width * endFraction);
            } else if (direction.equalsIgnoreCase("right")) {
                startx = elementX + (int) (width * endFraction);
                endx = elementX + (int) (width * startFraction);
            }

            int starty = elementY + height / 2;
            int endy = starty;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            Sequence seq = new Sequence(finger, 1);

            seq.addAction(finger.createPointerMove(Duration.ZERO,
                            PointerInput.Origin.viewport(), startx, starty))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofMillis(holdTime)))
                    .addAction(finger.createPointerMove(Duration.ofMillis(moveTime),
                            PointerInput.Origin.viewport(), endx, endy))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(seq));

        } catch (Exception e) {
            System.out.println("Swipe failed: " + e.getMessage());
        }
    }


    //done a  ios   vertical  up and down using element
    public void swipeVertical(String locatorType,
                              String value,
                              String direction,
                              double startFraction,
                              double endFraction,
                              int holdTime,
                              int moveTime) {


        try {


            waitForElement(locatorType, value);

            WebElement element = null;

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    element = driver.findElement(AppiumBy.xpath(value));
                    break;

                case "id":
                    element = driver.findElement(AppiumBy.id(value));
                    break;

                case "accessibilityid":
                    element = driver.findElement(AppiumBy.accessibilityId(value));
                    break;

                case "classname":
                    element = driver.findElement(AppiumBy.className(value));
                    break;

                case "androiduiautomator":
                    element = driver.findElement(AppiumBy.androidUIAutomator(value));
                    break;

                case "iospredicate":
                    element = driver.findElement(AppiumBy.iOSNsPredicateString(value));
                    break;

                case "iosclasschain":
                    element = driver.findElement(AppiumBy.iOSClassChain(value));
                    break;

                default:
                    System.out.println("Invalid locator type");
                    return;
            }

            int elementX = element.getRect().x;
            int elementY = element.getRect().y;
            int width = element.getSize().width;
            int height = element.getSize().height;

            int startx = elementX + (width / 2);
            int endx = startx;

            int starty;
            int endy;

            if (direction.equalsIgnoreCase("down")) {
                starty = elementY + (int) (height * startFraction);
                endy = elementY + (int) (height * endFraction);
            } else if (direction.equalsIgnoreCase("up")) {
                starty = elementY + (int) (height * endFraction);
                endy = elementY + (int) (height * startFraction);
            } else {
                System.out.println("Invalid direction");
                return;
            }

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            Sequence seq = new Sequence(finger, 1);

            seq.addAction(finger.createPointerMove(Duration.ZERO,
                            PointerInput.Origin.viewport(), startx, starty))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofMillis(holdTime)))
                    .addAction(finger.createPointerMove(Duration.ofMillis(moveTime),
                            PointerInput.Origin.viewport(), endx, endy))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(seq));
        } catch (Exception e) {
            System.out.println("Vertical swipe failed: " + e.getMessage());
        }
    }


    //DONE A ios
//without pointing to any element up and down vertical scroll
    public void swipeScreen(String direction,
                            double startXFraction,
                            double startYFraction,
                            double endXFraction,
                            double endYFraction,
                            int holdTime,
                            int moveTime,
                            int sequenceNumber) {
        try {
            int screenWidth = driver.manage().window().getSize().width;
            int screenHeight = driver.manage().window().getSize().height;

            int startx = (int) (screenWidth * startXFraction);
            int starty = (int) (screenHeight * startYFraction);

            int endx = (int) (screenWidth * endXFraction);
            int endy = (int) (screenHeight * endYFraction);

            if (direction.equalsIgnoreCase("down")) {
                System.out.println("Swiping Down");
            } else if (direction.equalsIgnoreCase("up")) {
                int tempX = startx;
                int tempY = starty;

                startx = endx;
                starty = endy;

                endx = tempX;
                endy = tempY;

                System.out.println("Swiping Up");
            } else if (direction.equalsIgnoreCase("right")) {
                int tempX = startx;
                int tempY = starty;

                startx = endx;
                starty = endy;

                endx = tempX;
                endy = tempY;

                System.out.println("Swiping Right");
            } else if (direction.equalsIgnoreCase("left")) {
                System.out.println("Swiping Left");
            } else {
                System.out.println("Invalid direction");
                return;
            }

            System.out.println("Starting coordinates: " + startx + "," + starty);
            System.out.println("Ending coordinates: " + endx + "," + endy);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "FingerTouch");

            Sequence seq = new Sequence(finger, sequenceNumber);

            seq.addAction(finger.createPointerMove(Duration.ZERO,
                            PointerInput.Origin.viewport(), startx, starty))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofMillis(holdTime)))
                    .addAction(finger.createPointerMove(Duration.ofMillis(moveTime),
                            PointerInput.Origin.viewport(), endx, endy))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(seq));
        } catch (Exception e) {
            System.out.println("Screen swipe failed: " + e.getMessage());
        }
    }


    // scrolling android ios
    public void scrollUntilElementVisible(String locatorType,
                                          String value,
                                          String direction,
                                          int maxSwipe) {
        try {
            for (int i = 0; i < maxSwipe; i++) {
                List<WebElement> elements;

                switch (locatorType.toLowerCase()) {
                    case "xpath":
                        elements = driver.findElements(AppiumBy.xpath(value));
                        break;

                    case "id":
                        elements = driver.findElements(AppiumBy.id(value));
                        break;

                    case "accessibilityid":
                        elements = driver.findElements(AppiumBy.accessibilityId(value));
                        break;

                    case "classname":
                        elements = driver.findElements(AppiumBy.className(value));
                        break;

                    case "androiduiautomator":
                        elements = driver.findElements(AppiumBy.androidUIAutomator(value));
                        break;

                    case "iospredicate":
                        elements = driver.findElements(AppiumBy.iOSNsPredicateString(value));
                        break;

                    case "iosclasschain":
                        elements = driver.findElements(AppiumBy.iOSClassChain(value));
                        break;

                    default:
                        System.out.println("Invalid locator type");
                        return;
                }

                if (!elements.isEmpty()) {
                    System.out.println("Element found after swipe: " + i);
                    return;
                }

                System.out.println("Swipe attempt: " + (i + 1));

                if (direction.equalsIgnoreCase("down")) {
                    swipeScreen("down", 0.5, 0.8, 0.5, 0.2, 200, 600, 1);
                } else if (direction.equalsIgnoreCase("up")) {
                    // swapped coordinates
                    swipeScreen("up", 0.5, 0.8, 0.5, 0.2, 200, 600, 1);
                } else {
                    System.out.println("Invalid direction");
                    return;
                }

                Thread.sleep(700);
            }

            System.out.println("Element not found after max swipe attempts");
        } catch (Exception e) {
            System.out.println("Scroll failed: " + e.getMessage());
        }
    }


    public static Point getCenterElement(Point location, Dimension size) {
        return new Point(
                location.getX() + size.getWidth() / 2,
                location.getY() + size.getHeight() / 2
        );
    }


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


    //done a ios
    public void openNotificationPanel() {
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).openNotifications();
                System.out.println("Android notification panel opened");
            } else if (driver instanceof IOSDriver) {


                Dimension size = driver.manage().window().getSize();

                int x = size.width / 2;
                int startY = 0;
                int endY = size.height / 2;

                HashMap<String, Object> params = new HashMap<>();
                params.put("duration", 1.0);
                params.put("fromX", x);
                params.put("fromY", startY);
                params.put("toX", x);
                params.put("toY", endY);

                driver.executeScript("mobile: dragFromToForDuration", params);

                System.out.println("Opening notification center in iOS");
            } else {
                System.out.println("Unsupported platform");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // done a
    public void pressBack() {
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
                System.out.println("Android back pressed");
            } else if (driver instanceof IOSDriver) {
                System.out.println("iOS does not support hardware back button");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void pressHome() {
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
                System.out.println("Android home pressed");
            } else if (driver instanceof IOSDriver) {
                driver.executeScript("mobile: pressButton", Map.of("name", "home"));
                System.out.println("iOS home button pressed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void lockDevice() {
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).lockDevice();
                System.out.println("Android device locked");
            } else if (driver instanceof IOSDriver) {
                ((IOSDriver) driver).lockDevice();
                System.out.println("iOS device locked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void unlockDevice() {
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).unlockDevice();
                System.out.println("Android device unlocked");
            } else if (driver instanceof IOSDriver) {
                ((IOSDriver) driver).unlockDevice();
                System.out.println("iOS device unlocked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void rotateLandscape() {
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
                System.out.println("Android rotated to landscape");
            } else if (driver instanceof IOSDriver) {
                System.out.println("iOS rotation works only if app supports landscape");
                ((IOSDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
            }
        } catch (Exception e) {
            System.out.println("Rotation not supported by the application");
        }
    }


    public void rotatePortrait() {
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
                System.out.println("Android rotated to Portrait");
            } else if (driver instanceof IOSDriver) {
                ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
                System.out.println("iOS rotated to Portrait");
            } else {
                System.out.println("Rotation not supported on this driver");
            }
        } catch (Exception e) {
            System.out.println("Unable to rotate device");
            e.printStackTrace();
        }
    }


    public void openKeyboard(String locatorType, String value) {
        try {
            WebElement element = null;

            switch (locatorType.toLowerCase()) {
                case "id":
                    element = driver.findElement(AppiumBy.id(value));
                    break;

                case "accessibilityid":
                    element = driver.findElement(AppiumBy.accessibilityId(value));
                    break;

                case "xpath":
                    element = driver.findElement(AppiumBy.xpath(value));
                    break;

                case "classname":
                    element = driver.findElement(AppiumBy.className(value));
                    break;

                case "androiduiautomator":
                    element = driver.findElement(AppiumBy.androidUIAutomator(value));
                    break;

                case "iospredicate":
                    element = driver.findElement(AppiumBy.iOSNsPredicateString(value));
                    break;

                case "iosclasschain":
                    element = driver.findElement(AppiumBy.iOSClassChain(value));
                    break;

                default:
                    System.out.println("Invalid locator type");
                    return;
            }

            element.click();

            if (driver instanceof AndroidDriver) {
                System.out.println("Android keyboard opened");
            } else if (driver instanceof IOSDriver) {
                System.out.println("iOS keyboard opened");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void hideKeyboard() {
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).hideKeyboard();
                System.out.println("Android keyboard hidden");
            } else if (driver instanceof IOSDriver) {
                ((IOSDriver) driver).hideKeyboard();
                System.out.println("iOS keyboard hidden");
            }
        } catch (Exception e) {
            System.out.println("Keyboard not present");
        }
    }


    public void clearNotifications(String locatorType, String value) {
        try {
            By locator = null;
            waitForElement(locatorType, value);

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    locator = AppiumBy.xpath(value);
                    break;

                case "id":
                    locator = AppiumBy.id(value);
                    break;

                case "accessibilityid":
                    locator = AppiumBy.accessibilityId(value);
                    break;

                case "classname":
                    locator = AppiumBy.className(value);
                    break;

                case "androiduiautomator":
                    locator = AppiumBy.androidUIAutomator(value);
                    break;

                case "iospredicate":
                    locator = AppiumBy.iOSNsPredicateString(value);
                    break;

                case "iosclasschain":
                    locator = AppiumBy.iOSClassChain(value);
                    break;

                default:
                    System.out.println("Invalid locator type");
                    return;
            }

            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;


                Thread.sleep(2000);

                androidDriver.findElement(locator).click();

                System.out.println("Android notifications cleared");
            } else if (driver instanceof IOSDriver) {
                IOSDriver iosDriver = (IOSDriver) driver;


                Thread.sleep(2000);

                iosDriver.findElement(locator).click();

                System.out.println("iOS notifications cleared");
            } else {
                System.out.println("Unsupported platform");
            }

        } catch (Exception e) {
            System.out.println("No clear notification button visible");
        }
    }



    public void pressKeyboardKeys(String text) {
        try {
            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;

                for (char ch : text.toCharArray()) {
                    if (Character.isLowerCase(ch)) {
                        ch = Character.toUpperCase(ch);
                    }

                    if (Character.isDigit(ch)) {
                        int digit = Character.getNumericValue(ch);

                        switch (digit) {
                            case 0:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_0));
                                break;
                            case 1:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
                                break;
                            case 2:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
                                break;
                            case 3:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
                                break;
                            case 4:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_4));
                                break;
                            case 5:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
                                break;
                            case 6:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_6));
                                break;
                            case 7:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_7));
                                break;
                            case 8:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_8));
                                break;
                            case 9:
                                androidDriver.pressKey(new KeyEvent(AndroidKey.DIGIT_9));
                                break;
                        }
                    } else if (Character.isLetter(ch)) {
                        androidDriver.pressKey(
                                new KeyEvent(AndroidKey.valueOf("KEYCODE_" + ch))
                        );
                    }
                }
            } else if (driver instanceof IOSDriver) {
                // send text to currently focused element
                driver.switchTo().activeElement().sendKeys(text);
            } else {
                System.out.println("Unsupported platform");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void longPress(String locatorType, String value, int holdTime) {
        try {
            WebElement element = null;

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    element = driver.findElement(AppiumBy.xpath(value));
                    break;

                case "id":
                    element = driver.findElement(AppiumBy.id(value));
                    break;

                case "accessibilityid":
                    element = driver.findElement(AppiumBy.accessibilityId(value));
                    break;

                case "classname":
                    element = driver.findElement(AppiumBy.className(value));
                    break;

                case "androiduiautomator":
                    element = driver.findElement(AppiumBy.androidUIAutomator(value));
                    break;

                case "iospredicate":
                    element = driver.findElement(AppiumBy.iOSNsPredicateString(value));
                    break;

                case "iosclasschain":
                    element = driver.findElement(AppiumBy.iOSClassChain(value));
                    break;

                default:
                    System.out.println("Invalid locator type");
                    return;
            }

            int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
            int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            Sequence seq = new Sequence(finger, 1);

            seq.addAction(finger.createPointerMove(
                            Duration.ZERO,
                            PointerInput.Origin.viewport(),
                            centerX,
                            centerY))

                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))

                    .addAction(new Pause(finger, Duration.ofMillis(holdTime)))

                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(seq));

            System.out.println("Long press done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // send as parameter
    public void openBrowser(String browser, String url) {

        AppiumDriverLocalService service = null;

        try {

            service = new AppiumServiceBuilder()
                    .usingPort(4723)
                    .withArgument(() -> "--relaxed-security")
                    .withArgument(() -> "--allow-insecure", "chromedriver_autodownload")
                    .build();

            service.start();

            if (browser.equalsIgnoreCase("chrome")) {

                UiAutomator2Options options = new UiAutomator2Options();

                options.setPlatformName("Android");
                options.setAutomationName("UIAutomator2");
                options.setUdid("emulator-5554");
                options.withBrowserName("Chrome");

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            } else if (browser.equalsIgnoreCase("safari")) {

                XCUITestOptions options = new XCUITestOptions();

                options.setPlatformName("iOS");
                options.setAutomationName("XCUITest");
                options.setUdid("061B0E0A-29A2-4855-95DE-AC45E71634F0");
                options.withBrowserName("Safari");

                driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
            }

            driver.get(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void putAppInBackground(int seconds) {
        {

            try {

                System.out.println("App sent to background for " + seconds + " seconds");

                if (driver instanceof AndroidDriver) {

                    ((AndroidDriver) driver).runAppInBackground(Duration.ofSeconds(seconds));

                } else if (driver instanceof IOSDriver) {

                    ((IOSDriver) driver).runAppInBackground(Duration.ofSeconds(seconds));

                } else {

                    System.out.println("Unsupported driver type");

                }

            } catch (Exception e) {

                System.out.println("App not sent to background");
                e.printStackTrace();
            }
        }
    }


    public void zoomIn() {

        Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int centerY = size.height / 2;

        if (driver instanceof AndroidDriver) {

            Map<String, Object> zoomIn = new HashMap<>();
            zoomIn.put("left", centerX - 200);
            zoomIn.put("top", centerY - 200);
            zoomIn.put("width", 400);
            zoomIn.put("height", 400);
            zoomIn.put("percent", 0.75);
            zoomIn.put("speed", 2000);

            driver.executeScript("mobile: pinchOpenGesture", zoomIn);

        } else if (driver instanceof IOSDriver) {

            Map<String, Object> zoomIn = new HashMap<>();
            zoomIn.put("scale", 2.0);
            zoomIn.put("velocity", 1.0);

            driver.executeScript("mobile: pinch", zoomIn);
        }
    }


    public void zoomOut() {

        Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int centerY = size.height / 2;

        if (driver instanceof AndroidDriver) {

            Map<String, Object> zoomOut = new HashMap<>();
            zoomOut.put("left", centerX - 200);
            zoomOut.put("top", centerY - 200);
            zoomOut.put("width", 400);
            zoomOut.put("height", 400);
            zoomOut.put("percent", 0.75);
            zoomOut.put("speed", 2000);

            driver.executeScript("mobile: pinchCloseGesture", zoomOut);

        } else if (driver instanceof IOSDriver) {


            Map<String, Object> zoomOut = new HashMap<>();
            zoomOut.put("scale", 0.3);
            zoomOut.put("velocity", -1.0);

            driver.executeScript("mobile: pinch", zoomOut);
            System.out.println("zoomout worked");
        }
    }


    public void closeapp() {
        if (driver instanceof AndroidDriver) {

            driver.quit();

        } else if (driver instanceof IOSDriver) {

            driver.quit();

            System.out.println("closeapp worked");
        }


    }


    public void switchtootherapp(String value) {


        if (driver instanceof AndroidDriver) {

            ((AndroidDriver) driver).activateApp(value);

        } else if (driver instanceof IOSDriver) {

            ((IOSDriver) driver).activateApp(value);


        }

    }


    public void switchbackapp(String value) {
        if (driver instanceof AndroidDriver) {

            ((AndroidDriver) driver).activateApp(value);

        } else if (driver instanceof IOSDriver) {

            ((IOSDriver) driver).activateApp(value);


        }
    }



    public void terminatespecificapp(String value) {
        if (driver instanceof AndroidDriver) {


            ((AndroidDriver) driver).terminateApp(value);

            System.out.println("it terminates app but in recent still it shows you can check with commend adb shell pidof com.flipkart.android");

        } else if (driver instanceof IOSDriver) {


            ((IOSDriver) driver).terminateApp(value);
            System.out.println("ios terminated");


        }

    }



    // after switching we need to write normal methods and normal browser xpaths to handle
// application chrome version is also important if you are  using your local chrome
    // incase if it is not working we can launch chrome from cmd and handle webview
    //ios is diffrent need to check
    public void switchtowebview(String value) {

        if (driver instanceof AndroidDriver) {

            ((AndroidDriver) driver).context(value);

        } else if (driver instanceof IOSDriver) {

            ((IOSDriver) driver).context(value);


        }

    }

    public void navigatebacktonativeview() {

        driver.navigate().back();

    }

    public Set<String> getAllContexts()
    {
        Set<String> contexts = null;

        if (driver instanceof AndroidDriver) {

            contexts = ((AndroidDriver) driver).getContextHandles();

        } else if (driver instanceof IOSDriver) {

            contexts = ((IOSDriver) driver).getContextHandles();

        }

        return contexts;
    }






        public void compare(String actual, String expected)
        {
            Assert.assertEquals("Error message mismatch", expected, actual);
        }


}



