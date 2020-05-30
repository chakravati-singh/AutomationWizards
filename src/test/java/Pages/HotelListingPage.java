package Pages;

import AppFramework.Locator;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashSet;
import java.util.List;

public class HotelListingPage extends BasePage {
    public HotelListingPage(IOSDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator sortFilterButton() {
        return new Locator(By.id("sort_filter"), "Sort Filter");
    }

    private Locator hotelName() {
        return new Locator(By.id("tvHotelName"), "Hotel Name");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnSortAndFilterButton() {
        click(sortFilterButton());
    }

    public String clickOnFifthHotel() {
        String hotelName = null;
        LinkedHashSet<String> hotels = new LinkedHashSet<>();
        while (hotels.size() < 5) {
            List<WebElement> elements = getWebElements(hotelName());
            for (WebElement element : elements) {
                hotels.add(getText(element));
                if (hotels.size() == 5) {
                    hotelName = getText(element);
                    click(element);
                    break;
                }
            }
            if (hotels.size() < 5) swipeDown();
        }
        return hotelName;
    }
}
