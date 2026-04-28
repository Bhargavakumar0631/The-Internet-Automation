package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

import java.util.List;

public class StandardUIElementsTest {

    WebDriver driver = DriverFactory.getDriver();
    AddOrRemoveElementPage addOrRemoveElements = new AddOrRemoveElementPage(driver);
    CheckboxesPage checkboxPage = new CheckboxesPage(driver);
    DropdownPage dropdown = new DropdownPage(driver);
    InputsPage input = new InputsPage(driver);
    HorizontalSliderPage slide = new HorizontalSliderPage(driver);
    SortableDataTablesPage dataTable = new SortableDataTablesPage(driver);
    By checkboxLocator;
    List<String> lastNamesList;

    @When("the user clicks Add Element 3 times")
    public void the_user_click_element_3_times() {
        for(int i = 0; i < 3; i++){
            addOrRemoveElements.clickAddElement();
        }
    }

    @Then("3 Delete buttons should be visible")
    public void Three_Delete_buttons_should_be_visible() {
        Assert.assertEquals(addOrRemoveElements.getDeleteButtonCount(), 3, "3 delete buttons were not found");
    }

    @When("the user clicks a Delete button")
    public void the_user_clicks_a_Delete_button() {
        addOrRemoveElements.clickDeleteButtonByIndex(1);
    }

    @When("2 Delete buttons should remain visible")
    public void Two_Delete_buttons_should_remain_visible() {
        Assert.assertEquals(addOrRemoveElements.getDeleteButtonCount(), 2, "3 delete buttons were not found");
    }

    @Then("the user clicks checkbox {string}")
    public void the_user_clicks_checkbox(String checkbox) {
        checkboxLocator = checkboxPage.getCheckbox(checkbox);
        checkboxPage.clickCheckbox(checkboxLocator);
    }

    @When("{string} should be checked")
    public void checkbox_should_be_checked(String checkbox) {
        checkboxLocator = checkboxPage.getCheckbox(checkbox);
        Assert.assertTrue(checkboxPage.verifyCheckboxStatus(checkboxLocator),
                "Checkbox [" + checkbox + "] was unchecked");
    }

    @When("{string} should be unchecked")
    public void checkbox_should_be_unchecked(String checkbox) {
        checkboxLocator = checkboxPage.getCheckbox(checkbox);
        Assert.assertFalse(checkboxPage.verifyCheckboxStatus(checkboxLocator),
                "Checkbox [" + checkbox + "] was checked");
    }

    @Then("the user selects {string} from the dropdown")
    public void the_user_selects_option_from_the_dropdown(String option) {
        dropdown.selectDropdownOption(option);
    }

    @When("the dropdown value should be {string}")
    public void the_dropdown_value_should_be_optionValue(String optionValue) {
        Assert.assertTrue(dropdown.verifyOption(optionValue),
                "The Selected value is not equal to [" + optionValue + "]");
    }

    @And("the user types {string} into the input field")
    public void the_user_types_number_into_the_input_field(String value) {
        input.enterInput(value);
    }

    @Then("presses the up arrow key")
    public void presses_the_up_arrow_key() {
        input.pressUpArrowKey();
    }

    @And("the input value should be {string}")
    public void the_input_value_should_be_increased(String value) {
        Assert.assertTrue(input.verifyTextboxValue(value),
                "The Value in the Input field is not equal to [" + value + "]");
    }

    @When("the user moves the slider to {string}")
    public void the_user_moves_the_slider_to_a_value(String value) {
        slide.moveSlider(value);
    }

    @Then("the slider value text should display {string}")
    public void the_slider_value_text_should_display_certain_value(String value) {
        Assert.assertTrue(slide.verifyRange(value),
                "The slider was not found in the correct position");
    }

    @Then("the user clicks the {string} column header in Example 1")
    public void the_user_clicks_the_Last_Name_column_header_in_Example_1(String lastName) {
        lastNamesList = dataTable.getLastNames();
        dataTable.clickHeaderToSort(lastName);
    }

    @Then("the table rows should be sorted alphabetically by Last Name")
    public void the_table_rows_should_be_sorted_alphabetically_by_Last_Name() {
        Assert.assertTrue(dataTable.verifySortedLastNames(lastNamesList),
                "The Last Name column was not sorted");
    }
}

