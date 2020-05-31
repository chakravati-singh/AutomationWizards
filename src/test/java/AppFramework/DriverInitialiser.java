package AppFramework;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;

public class DriverInitialiser {

    private String newCommandTimeout;
    private String automationName;
    private String deviceName;
    private String appiumUrl;

    public void loadConfigProp() throws IOException {
        FileReader reader = new FileReader("src/test/resources/capabilities.properties");
        Properties p = new Properties();
        p.load(reader);
        newCommandTimeout = p.getProperty("newCommandTimeout");
        automationName = p.getProperty("automationName");
        deviceName = System.getProperty("deviceName");
        appiumUrl = System.getProperty("appiumUrl");
    }


    public IOSDriver<IOSElement> getIOSDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName",deviceName);
        desiredCapabilities.setCapability("newCommandTimeout", newCommandTimeout);
        desiredCapabilities.setCapability("automationName",automationName);
        desiredCapabilities.setCapability("autoAcceptAlerts",true);

        IOSDriver driver = new IOSDriver(new URL(appiumUrl), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    public byte[] captureScreenShot(WebDriver browser) {
        return ((TakesScreenshot) browser).getScreenshotAs(OutputType.BYTES);
    }
}
