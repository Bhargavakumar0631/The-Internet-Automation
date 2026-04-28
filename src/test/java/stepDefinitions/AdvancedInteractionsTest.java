package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

import java.util.List;

public class AdvancedInteractionsTest {

    WebDriver driver = DriverFactory.getDriver();
    HomePage home = new HomePage(driver);
    DragAndDropPage dragAndDrop = new DragAndDropPage(driver);
    HoversPage hovers = new HoversPage(driver);
    ContextMenuPage contextMenu = new ContextMenuPage(driver);
    MultipleWindowsPage multipleWindow = new MultipleWindowsPage(driver);
    List<String> headers;

    @When("the user drags column {string} over column {string}")
    public void the_user_drags_column_A_over_B(String column1, String column2) {
        headers = dragAndDrop.dragAndDrop(column1, column2);
    }

    @Then("the headers of the columns should swap places")
    public void the_headers_of_the_columns_should_swap_places() {
        Assert.assertTrue(dragAndDrop.verifySwappedHeaders(headers),
                "The Headers wer not Swapped");
    }

    @When("the user hovers over the first avatar")
    public void the_user_hovers_over_the_first_avatar() {
        hovers.hoverOverImage();
    }

    @And("the text {string} and a {string} link should appear")
    public void the_text_name_and_view_profile_link_should_appear(String name, String link) {
        Assert.assertTrue(hovers.verifyTextAndLink(name, link),
                "Name [" + name + "] and [" + link +"] were not found");
    }

    @Then("the users should click on {string} link and next page with heading Not found")
    public void user_clicks_view_profile_link(String link){
        hovers.clickLink(link);
    }

    @Then("the user right-clicks on the designated hot spot")
    public void the_user_right_clicks_on_the_designated_hot_spot() {
        contextMenu.rightClickHotSpot();
    }

    @When("a JavaScript alert saying {string} should appear")
    public void a_JavaScript_alert_should_appear(String alertMessage) {
        Assert.assertTrue(contextMenu.verifyAlertMessage(alertMessage),
                "The alert message [" + alertMessage + "] was not found");
    }

    @When("the user accepts the alert")
    public void the_user_accepts_the_alert() {
        contextMenu.acceptAlert();
    }

    @Then("the user clicks link {string}")
    public void the_user_clicks_click_here(String linkText) {
        multipleWindow.clickLinkWithText(linkText);
    }

    @When("a new window opens")
    public void a_new_window_opens() {
        Assert.assertTrue(home.isNewWindowPresent(),
                "New window was not present");
    }

    @And("the user switches focus to the new window")
    public void the_user_switches_focus_to_the_new_window() {
        multipleWindow.switchWindow();
    }

    @Then("the page text should be {string}")
    public void the_page_text_should_be_New_Window(String text) {
        Assert.assertTrue(multipleWindow.verifyNewWindowText(text),
                "[" + text + "] was not found on new window");
    }
}
