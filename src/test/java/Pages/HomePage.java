package Pages;

import AppFramework.Locator;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

/**
 * created by chakravati.singh on 2020-05-30
 */
public class HomePage extends BasePage {
    public HomePage(IOSDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator clickTab(String tabName) {
        return new Locator(By.xpath("//XCUIElementTypeStaticText[@name='"+tabName+"']"),tabName+" tab clicked");
    }

    private Locator skipButton() {
        return new Locator(By.xpath("//XCUIElementTypeButton[@name='SKIP']"), "skip button");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnRespectiveTab(String tabName) {
        click(clickTab(tabName));
    }

    public void clickOnSkipLoginFlowButton() {
        click(skipButton());
    }
}
