package Pages;

import AppFramework.AppActions;
import AppFramework.Locator;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

class BasePage extends AppActions {

    BasePage(IOSDriver driver) {
        this.driver = driver;
    }

    /************************************************************************** Methods ********************************************************************************/

    void swipeDown() {
        bringElementIntoViewDown(new Locator(By.id("dummy"), ""), 1);
    }
}
