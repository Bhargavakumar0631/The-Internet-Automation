package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

import java.util.Map;

public class DOMAndFramesTest {

    WebDriver driver = DriverFactory.getDriver();
    NestedFramesPage nestedFrames = new NestedFramesPage(driver);
    IFramePage iFrames = new IFramePage(driver);
    ChallengingDOMPage challengingDOM = new ChallengingDOMPage(driver);
    LargeAndDeepDOMPage largeAndDeepDOM = new LargeAndDeepDOMPage(driver);
    ShadowDOMPage shadow = new ShadowDOMPage(driver);
    ShiftingContentMenuElementPage shiftingMenuElement = new ShiftingContentMenuElementPage(driver);
    Map<String, String> buttonIds;
    byte[] canvasElement;
    SearchContext shadowRoot;
    int menuItemLocation;

    @And("clicks {string}")
    public void clicks_button(String frameLink) {
        nestedFrames.clickFramesLink(frameLink);
    }

    @And("the user switches to the {string} frame, then the {string} frame")
    public void the_user_switches_frame(String frame1, String frame2) {
        nestedFrames.switchToFrame(frame1);
        nestedFrames.switchToFrame(frame2);
    }

    @Then("the body text should be {string}")
    public void the_body_text_should_be_verified(String bodyText) {
        Assert.assertTrue(nestedFrames.verifyMiddleFrameContent(bodyText),
                "The text [" + bodyText + "] was not found.");
    }

    @Then("the user switches to the iframe context")
    public void the_user_switches_to_the_iframe_context() {
        iFrames.switchToIFrame();
    }

    @When("the text editor should be interactable")
    public void the_text_editor_should_be_interactable(){
        Assert.assertTrue(iFrames.verifyIfEditable(),
                "The text editor is not Interactable");
    }

    @And("the user clicks the blue button")
    public void the_user_clicks_the_blue_button() {
        buttonIds = challengingDOM.getIds();
        canvasElement = challengingDOM.getCanvas();
        challengingDOM.clickBlueButton();
    }

    @Then("the IDs of all three buttons should change")
    public void the_IDs_of_all_three_buttons_should_change() {
        Assert.assertTrue(challengingDOM.verifyIdAfterRefresh(buttonIds),
                "Ids of Buttons did not change");
    }

    @When("the canvas element should render a different numeric value")
    public void the_canvas_element_should_render_a_different_numeric_value() {
        Assert.assertTrue(challengingDOM.verifyCanvasAfterRefresh(canvasElement),
                "The Numeric value of the Canvas did not change");
    }

    @Then("the user queries the DOM for sibling element {string}")
    public void the_user_queries_the_DOM_for_sibling_element(String element) {
        Assert.assertTrue(largeAndDeepDOM.isSiblingElementPresent(element),
                "The Element was not found in Siblings");
    }

    @Then("the element should exist and contain the text {string}")
    public void the_element_should_exist_and_contain_the_text(String text) {
        Assert.assertTrue(largeAndDeepDOM.verifyText(text),
                "The Text in the Sibling element was not as expected");
    }

    @Then("the user inspects the shadow host")
    public void the_user_inspects_the_shadow_host() {
        shadowRoot = shadow.getShadowElement();
    }

    @Then("the text inside the shadow root should be accessible and verifiable")
    public void the_text_inside_the_shadow_root_should_be_accessible_and_verifiable() {
        Assert.assertTrue(shadow.isShadowTextReadable(shadowRoot),
                "The Text in the Shadow element is not accessible");
    }

    @Then("clicks on {string} and checks pixel Location of {string}")
    public void clicks_on_Element(String linkName, String menuItem) {
        shiftingMenuElement.clickExampleLink(linkName);
        menuItemLocation = shiftingMenuElement.getPixelLocationAlongXAxis(menuItem);
    }

    @Then("the user refreshes the page to check changed position")
    public void the_user_refreshes_the_page() {
        shiftingMenuElement.refreshPage();
    }

    @Then("the pixel location of the {string} menu item should shift")
    public void the_pixel_location_of_menu_item_should_shift(String item) {
        Assert.assertTrue(shiftingMenuElement.verifyPixelLocationAfterRefresh(menuItemLocation, item),
                "The Menu Item [" + item + "] did not Shift");
    }
}
