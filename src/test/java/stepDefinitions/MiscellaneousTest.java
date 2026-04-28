package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

public class MiscellaneousTest {

    WebDriver driver = DriverFactory.getDriver();
    ABTestingPage abTesting = new ABTestingPage(driver);
    TyposPage typo = new TyposPage(driver);
    WYSIWYGEditorPage editor = new WYSIWYGEditorPage(driver);

    @Then("the header should display either {string} or {string}")
    public void The_header_should_display_given_text(String text1, String text2){
        Assert.assertTrue(abTesting.verifyHeader(text1) || abTesting.verifyHeader(text2),
                "Neither [" + text1 + "] nor [" + text2 + "was present");
    }

    @Then("the paragraph text should contain known spelling variations {string} vs {string}")
    public void the_paragraph_text_should_contain_known_spelling_variation(String correctText, String incorrectText){
        Assert.assertTrue(typo.verifyTypo(correctText, incorrectText),
                "Typo was not found");
    }
    @When("the WYSIWYG Editor is editable")
    public void the_WYSIWYG_Editor_is_editable(){
        Assert.assertTrue(editor.verifyIfEditable(),
                "The Editor was not Editable");
    }

    @And("the user clears the default text")
    public void the_user_clears_the_default_text() {
        editor.clearText();
    }

    @And("the user types {string}")
    public void the_user_types(String text) {
        editor.enterText(text);
    }

    @And("the user clicks the {string} format button")
    public void the_user_clicks_the_Bold_format_button(String button){
        editor.clickButton(button);
    }

    @Then("the text inside the editor should be wrapped in strong tags")
    public void the_text_inside_the_editor_should_be_wrapped_in_strong_tags(){
        Assert.assertTrue(editor.verifyIfEditable(),
                "No text was found inside strong tags");
    }
}
