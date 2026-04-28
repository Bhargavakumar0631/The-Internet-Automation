package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

import java.util.List;
import java.util.Map;

public class DynamicBehaviorTest {

    WebDriver driver =DriverFactory.getDriver();
    DynamicContentPage dynamicContent = new DynamicContentPage(driver);
    DynamicControlsPage dynamicControl = new DynamicControlsPage(driver);
    DynamicLoadingPage dynamicLoading = new DynamicLoadingPage(driver);
    DisappearingElementsPage disappearingElement = new DisappearingElementsPage(driver);
    Map<String, List<String>> pageData;
    Boolean disappearingElementResult;

    @When("the user refreshes the page to see the change")
    public void the_user_refreshes_the_page() {
        pageData = dynamicContent.getPageData();
        dynamicContent.refreshPage();
    }

    @Then("the text and images in the content blocks should change")
    public void the_text_and_images_in_the_content_blocks_should_change() {
        Assert.assertTrue(dynamicContent.verifyPageData(pageData),
                "The Page Content remained unchanged");
    }

    @When("the user clicks the Remove button")
    public void the_user_clicks_the_Remove_button() {
        dynamicControl.clickRemove();
    }

    @Then("the loading indicator appears and the message {string} is displayed and checkbox has disappeared")
    public void the_loading_indicator_appears_and_the_message_is_displayed_and_checkbox_has_disappeared(String message) {
        Assert.assertTrue(dynamicControl.verifyMessageAndLoadingIndicator(message),
                "The Message and Loading indicator were not displayed");
        Assert.assertTrue(dynamicControl.isCheckboxAvailable(),
                "The checkbox remained visible");
    }

    @When("the user clicks the Enable button")
    public void the_user_clicks_the_Enable_button() {
        dynamicControl.clickEnable();
    }

    @Then("the loading indicator appears with text {string} and the input field becomes enabled")
    public void the_loading_indicator_appears_and_the_input_field_becomes_enabled(String message) {
        Assert.assertTrue(dynamicControl.verifyMessageAndLoadingIndicator(message),
                "The Message and Loading indicator was not displayed");
        Assert.assertTrue(dynamicControl.isTextboxEnabled(),
                "The Textbox remained disabled");
    }

    @When("the user clicks the link for Example {string}")
    public void the_user_clicks_the_link_for(String example) {
        dynamicLoading.clickExampleLink(example);
    }

    @And("the user clicks the Start button")
    public void the_user_clicks_the_Start_button() {
        dynamicLoading.clickStart();
    }

    @Then("the loading bar appears and disappears")
    public void the_loading_bar_appears_and_disappears() {
        Assert.assertTrue(dynamicLoading.verifyLoadingBar(),
                "The Loading Bar appeared bu did not disappear");
    }

    @And("the text {string} is displayed")
    public void the_text_is_displayed(String text) {
        Assert.assertTrue(dynamicLoading.verifyText(text),
                "The text [" + text + "] was not displayed");
    }

    @When("the user refreshes the page multiple times until state of {string} button changes")
    public void the_user_refreshes_the_page_multiple_times(String button) {
        disappearingElementResult = disappearingElement.refreshPageUntilStateChange(button);
    }

    @Then("the {string} button should randomly appear and disappear from the DOM")
    public void the_button_should_randomly_appear_and_disappear_from_the_dom(String button) {
        Assert.assertTrue(disappearingElementResult,
                "The [" + button + "] did not appear and disappear randomly");
    }
}
