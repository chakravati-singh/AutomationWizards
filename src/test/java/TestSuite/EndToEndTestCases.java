package TestSuite;


import AppFramework.RetryAnalyzer;
import Pages.*;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EndToEndTestCases extends BaseTestSuite {

    private String hotelCategory;
    private String cityName;
    private int NumberOfAdults;
    private int NumberOfChildren;
    private int NumberOfRooms;

    private void loadTestData() throws IOException {
        FileReader fileReader = new FileReader("src/test/resources/testData.properties");
        Properties p = new Properties();
        p.load(fileReader);
        hotelCategory = p.getProperty("hotelCategory");
        cityName = p.getProperty("cityName");
        NumberOfAdults = Integer.parseInt(p.getProperty("NumberOfAdults"));
        NumberOfChildren = Integer.parseInt(p.getProperty("NumberOfChildren"));
        NumberOfRooms = Integer.parseInt(p.getProperty("NumberOfRooms"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Test_End_To_End_Flow() throws IOException {
        loadTestData();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSkipLoginFlowButton();
        homePage.clickOnRespectiveTab(hotelCategory);
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.searchACityAndSelectIt(cityName);
        hotelSearchPage.clickOnCheckIn();
        hotelSearchPage.clickOnCheckOut();
        String totalGuestCount = hotelSearchPage.enterAdultsAndChildrenCountInEachRoom(NumberOfAdults, NumberOfChildren, NumberOfRooms);
        hotelSearchPage.clickOnBusinessTypeTrip();
        hotelSearchPage.clickOnSearchButton();
        HotelListingPage hotelListingPage = new HotelListingPage(driver);
        hotelListingPage.clickOnSortAndFilterButton();
        SortAndFilterPage sortAndFilterPage = new SortAndFilterPage(driver);
        sortAndFilterPage.clickOnPriceFilter();
        sortAndFilterPage.clickOnApplyFilter();
        String hotelName = hotelListingPage.clickOnFifthHotel();
        HotelDetailPage hotelDetailPage = new HotelDetailPage(driver);
        hotelDetailPage.clickOnSelectRoomButton();
        hotelDetailPage.clickOnContinueButton();
        BookingSummaryPage bookingSummaryPage = new BookingSummaryPage(driver);
        customSoftAssert.assertEquals(bookingSummaryPage.getHotelName(), hotelName);
        customSoftAssert.assertTrue(bookingSummaryPage.getRoomCount().contains(String.valueOf(NumberOfRooms)));
        customSoftAssert.assertTrue(bookingSummaryPage.getNumberOfGuests().contains(String.valueOf(Integer.parseInt(totalGuestCount))));
        bookingSummaryPage.fillGuestDetails();
        bookingSummaryPage.makeSpecialRequest();
        bookingSummaryPage.uncheckDonateCheckBox();
        bookingSummaryPage.clickOnContinueButton();
    }
}
