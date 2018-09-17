import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    AppiumDriver driver;
    private static AppiumDriverLocalService service;

    @BeforeSuite
    public void setUp() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "An appium server node is not started!");
        }

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "deviceName");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "version");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "deviceUdid");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "appBundleId");

        driver = build(service, desiredCapabilities);
    }

    @AfterSuite
    public void stopServices() {
        if (driver != null) {
            driver.closeApp();
        }
        if (service != null) {
            service.stop();
        }
    }

    public AppiumDriver build(AppiumDriverLocalService service, DesiredCapabilities capabilities) throws Exception {

        String platform = System.getenv("PLATFORM");

        if (platform.equalsIgnoreCase("ios")) {
            return new IOSDriver<IOSElement>(service.getUrl(), capabilities);
        } else if (platform.equalsIgnoreCase("android")) {
            return new AndroidDriver<AndroidElement>(service.getUrl(), capabilities);
        } else {
            throw new Exception("Unable to read device platform.");
        }
    }
}
