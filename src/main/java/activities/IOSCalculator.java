package activities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class IOSCalculator implements Calculator {

    protected AppiumDriver<MobileElement> driver;

    public IOSCalculator(AppiumDriver driver) {
        this.driver = driver;
    }

    public MobileElement locateButton2() {
        return driver.findElementByAccessibilityId("2");
    }

    public MobileElement locateButton3() {
        return driver.findElementByAccessibilityId("3");
    }

    public MobileElement locateButtonEquls() {
        return driver.findElementByAccessibilityId("=");
    }

    public MobileElement locateButtonMultiply() {
        return driver.findElementByAccessibilityId("x");
    }

    public MobileElement locateResultsField() {
        return driver.findElementByXPath("//XCUIElementTypeStaticText|//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]");
    }
}
