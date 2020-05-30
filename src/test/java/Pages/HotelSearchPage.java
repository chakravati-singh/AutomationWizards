package Pages;

import AppFramework.Locator;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class HotelSearchPage extends BasePage {
    public HotelSearchPage(IOSDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator searchBox() { return new Locator(By.xpath("//XCUIElementTypeButton[@name='searchCity']"), "City/Area/Hotel search box"); }

    private Locator searchBar() { return new Locator(By.id("Enter City/Area/Hotel Name"), "City/Area/Hotel search field"); }

    private Locator searchResult() {
        return new Locator(By.xpath("//XCUIElementTypeApplication[@name='MakeMyTrip']/XCUIElementTypeTable[2]/XCUIElementTypeCell[1]"), "Search Result");
    }

    private Locator checkInDate() { return new Locator(By.id("CHECK-IN"), "Check-In Date"); }

    private Locator checkInMonthYear() {
        return new Locator(By.id("CheckInMonth"), "Check-In Month");
    }

    private Locator checkOutDate() { return new Locator(By.id("CHECK-OUT"), "Check-Out Date"); }

    private Locator checkOutMonthYear() {
        return new Locator(By.id("CheckOutMonth"), "Check-Out Month");
    }

    private Locator doneButton() {
        return new Locator(By.id("DONE"), "Done Button");
    }

    private Locator guestsButton() { return new Locator(By.id("Guests"), "Guests Button"); }

    private Locator guestCount() { return new Locator(By.id("AdultCount"), "Guests Count"); }

    private Locator plusAdultButton() { return new Locator(By.xpath("//XCUIElementTypeButton[@name='AddAdultInRoom']"), "Plus Adult Button"); }

    private Locator plusChildButton() { return new Locator(By.xpath("//XCUIElementTypeButton[@name='AddChildInRoom']"), "PLus Child Button"); }

    private Locator guestDoneButton() { return new Locator(By.id("DONE"), "Done Button"); }

    private Locator addRoomButton() {
        return new Locator(By.id("+ ADD ANOTHER ROOM"), "Add Room Button");
    }

    private Locator adultCount() { return new Locator(By.xpath("//XCUIElementTypeStaticText[@name='AdultCountInRoom']"), "Adult Count"); }

    private Locator childCount() { return new Locator(By.xpath("//XCUIElementTypeStaticText[@name='ChildCountInRoom']"), "Child Count"); }

    private Locator businessTypeTrip(String tripType) { return new Locator(By.id("'"+tripType+"'"), "Trip Type Button"); }

    private Locator searchButton() {
        return new Locator(By.id("SEARCH"), "Search Button");
    }


    /***************************************************************** Methods *******************************************************************/

    private void clickOnCity() {
        click(searchBox());
    }

    public void searchACityAndSelectIt(String cityName) {
        clickOnCity();
        EnterValue(searchBar(), cityName);
        click(searchResult());
    }

    public String clickOnCheckIn() {
        click(checkInDate());
        click(doneButton());
        return getText(checkInDate()) + " " + getText(checkInMonthYear());
    }

    public String clickOnCheckOut() {
        click(checkOutDate());
        click(doneButton());
        return getText(checkOutDate()) + " " + getText(checkOutMonthYear());
    }

    public String enterAdultsAndChildrenCountInEachRoom(int adults, int children, int rooms) {
        click(guestsButton());
        for (int k = 0; k < rooms; k++) {
            int existingAdultCount = Integer.parseInt(getText(adultCount()));
            for (int i = 0; i < adults - existingAdultCount; i++) click(plusAdultButton());
            int existingChildCount = Integer.parseInt(getText(childCount()));
            for (int j = 0; j < children - existingChildCount; j++) click(plusChildButton());
            if (k != rooms - 1) click(addRoomButton());
        }
        click(guestDoneButton());
        return getText(guestCount());
    }

    public void clickOnSearchButton() {
        click(searchButton());
    }

    public void clickOnBusinessTypeTrip(String tripType) {
        click(businessTypeTrip(tripType));
    }


}
