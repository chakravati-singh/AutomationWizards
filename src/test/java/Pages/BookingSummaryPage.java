package Pages;

import AppFramework.Locator;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

public class BookingSummaryPage extends BasePage {
    public BookingSummaryPage(IOSDriver driver) {
        super(driver);
    }

    /******************************************************************* Locators ****************************************************************/

    private Locator hotelName() {
        return new Locator(By.id("HotelNameOnReview"), "Hotel Name");
    }

    private Locator roomsCount() { return new Locator(By.xpath("//XCUIElementTypeStaticText[@name='3 Room']"), "Rooms"); }

    private Locator numberOfGuest() { return new Locator(By.xpath("//XCUIElementTypeStaticText[@name='8 Guests']"), "No. Of Guest"); }

    private Locator checkInDate() {
        return new Locator(By.id("CheckInDate"), "Check In Date");
    }

    private Locator checkOutDate() {
        return new Locator(By.id("CheckOutDate"), "Check Out Date");
    }

    private Locator firstName() {
        return new Locator(By.xpath("(//XCUIElementTypeTextField[@name='FirstName'])[1]"), "First Name field");
    }

    private Locator lastName() {
        return new Locator(By.id("LastName"), "Last Name field");
    }

    private Locator Email() {
        return new Locator(By.id("Email"), "Email field");
    }

    private Locator phoneNumber() {
        return new Locator(By.id("PhoneNumber"), "Phone Number field");
    }

    private Locator specialRequest() {
        return new Locator(By.id("Make Special Request"), "Make Special Request button");
    }

    private Locator smokingRoom() {
        return new Locator(By.id("Smoking room"), "Smoking Room Checkbox");
    }

    private Locator earlyCheckIn() {
        return new Locator(By.id("Early check-in"), "Early Check-in Checkbox");
    }

    private Locator doneButton() {
        return new Locator(By.id("DONE"), "DONE Button");
    }

    private Locator donateCheckBox() {
        return new Locator(By.xpath("(//XCUIElementTypeButton[@name='checkbox unchecked'])[2]"), "Donate Checkbox");
    }

    private Locator continueButton() {
        return new Locator(By.id("CONTINUE"), "Continue Button");
    }

    /***************************************************************** Methods *******************************************************************/

    public String getHotelName() {
        return getText(hotelName());
    }

    public String getRoomCount() {
        return getText(roomsCount());
    }

    public String getNumberOfGuests() {
        return getText(numberOfGuest());
    }

    public String getCheckInDate() {
        return getText(checkInDate()).split(",")[0];
    }

    public String getCheckOutDate() {
        return getText(checkOutDate()).split(",")[0];
    }

    public void fillGuestDetails() {
        EnterValue(firstName(),"Test");
        EnterValue(lastName(),"User");
        EnterValue(Email(),"test@gmail.com");
        EnterValue(lastName(),"User");
        EnterValue(phoneNumber(),"7760486769");
    }

    public void makeSpecialRequest() {
        bringElementIntoViewDown(specialRequest(),3);
        click(specialRequest());
        click(smokingRoom());
        click(earlyCheckIn());
        click(doneButton());
    }

    public void uncheckDonateCheckBox() {
        bringElementIntoViewDown(donateCheckBox(),3);
        click(donateCheckBox());
    }

    public void clickOnContinueButton() {
        click(continueButton());
    }
}
