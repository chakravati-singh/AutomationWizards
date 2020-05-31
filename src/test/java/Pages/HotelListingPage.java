package Pages;

import AppFramework.Locator;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class HotelListingPage extends BasePage {
    public HotelListingPage(IOSDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator sortFilterButton() {
        return new Locator(By.id("Sort & Filter"), "Sort Filter");
    }

    private Locator hotelName() {
        return new Locator(By.xpath("(//XCUIElementTypeStaticText[@name='HotelNameOnListing'])[5]"), "Hotel Name");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnSortAndFilterButton() {
        clickOnSpecificPoint(100,100);
        click(sortFilterButton());
    }

    public String clickOnFifthHotel() {
        bringElementIntoViewDown(hotelName(),10);
        String hotelName = getText(hotelName());
        click(hotelName());
        return hotelName;
    }
}
