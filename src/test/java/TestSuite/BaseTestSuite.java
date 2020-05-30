package TestSuite;

import AppFramework.CustomSoftAssert;
import AppFramework.DriverInitialiser;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTestSuite {

    private AppiumDriverLocalService appiumServices;
    protected IOSDriver driver;
    CustomSoftAssert customSoftAssert;
    private DriverInitialiser driverInitialiser = new DriverInitialiser();

    @BeforeMethod
    protected void onTestStarted() throws IOException {
        driverInitialiser.loadConfigProp();
        String device = /*driverInitialiser.getAttachedDevices()*/"6cadf5b22905d945328f7b70447f34409dd370be";
        System.out.println("Device NAME **************** " + device);
        appiumServices = driverInitialiser.getAppiumDriverLocalService();
        driver = driverInitialiser.getIOSDriver(appiumServices, device);
        customSoftAssert = new CustomSoftAssert(driver);
    }

    @AfterMethod
    protected void onTestFinished(ITestResult testResult) {
        customSoftAssert.assertAll();
        if (testResult != null && testResult.getStatus() == ITestResult.FAILURE) {
            driverInitialiser.captureScreenShot(driver);
            Reporter.log(testResult.getThrowable().toString(), true);
        }
        if (driver != null) driver.quit();
        if (appiumServices != null) appiumServices.stop();
    }
}
