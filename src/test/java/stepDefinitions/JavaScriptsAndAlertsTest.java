package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

public class JavaScriptsAndAlertsTest {

    WebDriver driver = DriverFactory.getDriver();
    JavaScriptAlertsPage alerts = new JavaScriptAlertsPage(driver);
    JavaScriptOnloadEventErrorPage onLoad = new JavaScriptOnloadEventErrorPage(driver);
    JQueryUIMenusPage jQueryUIMenu = new JQueryUIMenusPage(driver);
    KeyPressesPage keyPress = new KeyPressesPage(driver);

    @When("the user clicks {string}")
    public void the_user_clicks_button(String buttonName) {
        alerts.clickButton(buttonName);
    }

    @And("handles the alert by {string} with text {string}")
    public void handles_the_alert_by_action_with_given_text(String action, String inputText) {
        alerts.handleAlert(action, inputText);
    }

    @Then("the result text should show {string}")
    public void the_user_hovers_over_the_first_avatar(String expectedResult) {
        Assert.assertTrue(alerts.verifyResultText(expectedResult),
                "The Result text [" + expectedResult + "] was not as expected");
    }

    @Then("a JavaScript error should be present in the browser console")
    public void a_JavaScript_error_should_be_present_in_the_browser_console() {
        Assert.assertTrue(onLoad.verifyOnLoadError(),
                "JavaScript on load error was not found");
    }

    @When("the user hovers over {string}")
    public void the_user_hovers_over(String element){
        jQueryUIMenu.hoverToElement(element);
    }

    @And("the user clicks extension {string}")
    public void the_user_clicks_click_here(String fileExtension) {
        jQueryUIMenu.clickFileExtension(fileExtension);
    }

    @Then("a file named {string} should download")
    public void a_file_should_be_downloaded(String fileName) {
        Assert.assertTrue(jQueryUIMenu.verifyFileDownload(fileName),
                "File with name [" + fileName + "] was not downloaded");
    }

    @When("the user presses the {string} key")
    public void the_user_presses_Enter_Key(String key) {
        keyPress.pressKey(key);
    }

    @Then("the result text should say {string}")
    public void the_result_should_give_a_message(String message) {
        Assert.assertTrue(keyPress.verifyResult(message),
                "The Obtained result message was not correct");
    }
}
