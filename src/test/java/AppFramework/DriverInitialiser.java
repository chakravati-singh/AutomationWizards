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
    private String appPackage;
    private String appActivity;

    public void loadConfigProp() throws IOException {
        FileReader reader = new FileReader("src/test/resources/capabilities.properties");
        Properties p = new Properties();
        p.load(reader);
        newCommandTimeout = p.getProperty("newCommandTimeout");
        automationName = p.getProperty("automationName");
        appPackage = p.getProperty("appPackage");
        appActivity = p.getProperty("appActivity");
    }


    public IOSDriver<IOSElement> getIOSDriver(AppiumDriverLocalService appiumDriverLocalService, String DeviceName) throws MalformedURLException {
        String appiumServiceUrl = appiumDriverLocalService.getUrl().toString();
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "phonepeqa#3");
        desiredCapabilities.setCapability("udid", DeviceName);
        desiredCapabilities.setCapability("newCommandTimeout", 45000);
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("useNewWDA", "true");
        desiredCapabilities.setCapability("app", "/Users/chakravati.singh/Desktop/MakeMyTrip.ipa");
        desiredCapabilities.setCapability("platformVersion", "13.4.1");
        desiredCapabilities.setCapability("autoAcceptAlerts",true);

//        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//        desiredCapabilities.setCapability(MobileCapabilityType.UDID, DeviceName);
//        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, newCommandTimeout);
//        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
//        desiredCapabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, String.valueOf(true));
//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        IOSDriver driver = new IOSDriver(new URL(appiumServiceUrl), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    public AppiumDriverLocalService getAppiumDriverLocalService() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort().withArgument(LOG_LEVEL, "error");
        AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        //AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildDefaultService();
        appiumService.start();
        return appiumService;

    }


    public String getAttachedDevices() {

        List<String> DeviceList = new ArrayList<String>();
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            Pattern pattern = Pattern.compile("^([a-zA-Z0-9:.\\-]+)(\\s+)(device)");
            Matcher matcher = null;
            while ((line = in.readLine()) != null) {
                if (line.matches(pattern.pattern())) {
                    matcher = pattern.matcher(line);
                    if (matcher.find())
                        System.out.println(matcher.group(1));
                    DeviceList.add(matcher.group(1));
                }
            }
            return matcher.group(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] captureScreenShot(WebDriver browser) {
        return ((TakesScreenshot) browser).getScreenshotAs(OutputType.BYTES);
    }
}
