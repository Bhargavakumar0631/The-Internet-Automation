package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.ConfigReader;
import utils.DriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PageNavigationAndEventsTest {

    Properties prop = ConfigReader.getProperties();
    WebDriver driver = DriverFactory.getDriver();
    HomePage home = new HomePage(driver);
    EntryAdPage entryAd = new EntryAdPage(driver);
    ExitIntentPage exitIntent = new ExitIntentPage(driver);
    FloatingMenuPage floatingMenu = new FloatingMenuPage(driver);
    InfiniteScrollPage infiniteScroll = new InfiniteScrollPage(driver);
    NotificationMessagesPage notificationMessages = new NotificationMessagesPage(driver);
    GeolocationPage geo = new GeolocationPage(driver);
    int paraCount;
    By header = By.tagName("h3");
    List<String> flashMessages = new ArrayList<>();

    @When("the modal window appears")
    public void the_modal_window_appears() {
        Assert.assertTrue(entryAd.isModalWindowVisible(),
                "The Modal Window did not appear");
    }

    @And("the user clicks Close")
    public void the_user_clicks_Close() {
        entryAd.clickClose();
    }

    @Then("the modal window should disappear")
    public void the_modal_window_should_disappear() {
        Assert.assertTrue(entryAd.verifyModalWindowHasDisappeared(),
                "The Modal Window did not disappear");
    }

    @When("the user moves the mouse cursor outside the browser viewport")
    public void the_user_moves_the_mouse_cursor_outside_the_browser_viewport() {
        exitIntent.performMouseLeaveAction();
    }

    @Then("the exit intent modal should appear")
    public void the_exit_intent_modal_should_appear(){
        Assert.assertTrue(exitIntent.isModalWindowVisible(),
                "The Exit Intent Modal Window did not appear");
    }

    @When("the user scrolls to the bottom of the page")
    public void the_user_scrolls_to_the_bottom_of_the_page() {
        if(home.isHeaderCorrect(header, "Floating Menu")) {
            floatingMenu.scrollToBotton();
        } else {
            paraCount = infiniteScroll.getParagraphCount();
            infiniteScroll.scrollToBotton();
        }
    }

    @Then("the floating menu should remain visible in the viewport")
    public void the_floating_menu_should_remain_visible_in_the_viewport() {
        Assert.assertTrue(floatingMenu.isFloatingMenuVisible(),
                "Floating menu is not Visible");
    }

    @Then("new DOM paragraphs should be appended to the page dynamically")
    public void new_DOM_paragraphs_should_be_appended_to_the_page_dynamically() {
        Assert.assertTrue(infiniteScroll.verifyDOMParaUponScroll(paraCount),
                "The DOM Paragraphs did not increase after scrolling to the bottom of the page");
    }

    @When("the user clicks {string} multiple times")
    public void the_user_clicks_multiple_times(String linkName) {
        int repetition = Integer.parseInt(prop.getProperty("repetition"));
        for(int i = 0; i < repetition ; i++) {
            notificationMessages.clickLink(linkName);
            flashMessages.add(notificationMessages.getFlashMessage());
        }
    }

    @Then("the flash message should toggle between {string} and {string}")
    public void the_flash_message_should_toggle_between_actions(String message1, String message2) {
        Assert.assertTrue(flashMessages.contains(message1) && flashMessages.contains(message2),
                "Toggle between [" + message1 + "] & [" + message2 + "did not happen");
    }

    @When("the user grants location permissions")
    public void the_user_grants_location_permissions() {
        geo.setMockGeolocation();
    }

    @And("clicks the {string} button")
    public void the_user_clicks_button(String button) {
        geo.clickButton(button);
    }

    @Then("the simulated latitude and longitude coordinates should be displayed")
    public void the_simulated_latitude_and_longitude_coordinates_should_be_displayed() {
        Assert.assertTrue(geo.verifyCoordinatesAreDisplayed(),
                "The coordinates were not displayed");
    }
}
