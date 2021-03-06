package AppFramework;


import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssert extends SoftAssert {


    IOSDriver driver;

    public CustomSoftAssert(IOSDriver driver) {
        this.driver = driver;
    }

    @Override
    @Step("Test Case Passed")
    public void onAssertSuccess(IAssert<?> iAssert) {
        Reporter.log("<p><font size=\"3\" color=\"green\">--Passed\"</font></p>", true);
    }

    @Override
    @Step("Test Case Failed")
    public void onAssertFailure(IAssert<?> iAssert, AssertionError assertionError) {
        Reporter.log("<p><font size=\"3\" color=\"red\">--Failed\"</font></p>", true);
    }
}
