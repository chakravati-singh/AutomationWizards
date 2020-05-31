package Pages;


import AppFramework.Locator;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class SortAndFilterPage extends BasePage {
    public SortAndFilterPage(IOSDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator fourAndAboveRating() {
        return new Locator(By.xpath("//XCUIElementTypeStaticText[`name BEGINSWITH '4.0 & Above'`]"), "4.0 & Above Rating");
    }

    private Locator applyFilters() {
        return new Locator(By.id("APPLY FILTERS"), "Apply Filter");
    }

    private Locator priceSeekBar() {
        String sliderPathQuery = "//XCUIElementTypeApplication[@name='MakeMyTrip']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[4]/XCUIElementTypeOther[1]";
        return new Locator(By.xpath(sliderPathQuery), "Price Seek Bar");
    }

    /***************************************************************** Methods *******************************************************************/

    public void clickOnPriceFilter() {
        waitUntilDisplayed(priceSeekBar(), 15);
        int X = convertToWebElement(priceSeekBar()).getLocation().getX();
        int Y = convertToWebElement(priceSeekBar()).getLocation().getY();
        int ds = convertToWebElement(priceSeekBar()).getSize().getWidth();
        int dsw = convertToWebElement(priceSeekBar()).getSize().getHeight();
        clickOnSpecificPoint((int) (X + (ds * 0.08)), Y + dsw / 2);
        System.out.println(X);
        System.out.println(Y);
    }

    public void clickOnFourAndAboveRating() {
        bringElementIntoViewDown(fourAndAboveRating(), 5);
        click(fourAndAboveRating());
    }

    public void clickOnApplyFilter() {
        click(applyFilters());
    }
}
