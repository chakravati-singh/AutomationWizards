package Pages;

import AppFramework.Locator;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class HotelDetailPage extends BasePage {
    public HotelDetailPage(IOSDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator selectRoomButton() {
        return new Locator(By.id("SelectRoom"), "Select Room Button");
    }

    private Locator continueButton() {
        return new Locator(By.id("CONTINUE"), "Continue Button");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnSelectRoomButton() {
        click(selectRoomButton());
    }

    public void clickOnContinueButton() {
        click(continueButton());
    }
}
