package TestSuite;

import AppFramework.CustomSoftAssert;
import AppFramework.DriverInitialiser;
import io.appium.java_client.ios.IOSDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTestSuite {

    protected IOSDriver driver;
    CustomSoftAssert customSoftAssert;
    private DriverInitialiser driverInitialiser = new DriverInitialiser();

    @BeforeMethod
    protected void onTestStarted() throws IOException {
        driverInitialiser.loadConfigProp();
        driver = driverInitialiser.getIOSDriver();
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
    }
}
