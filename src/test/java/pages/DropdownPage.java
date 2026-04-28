package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    By dropdownLocator = By.id("dropdown");
    Select dropdown;
    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    /** @param option valid options are Option 1, Option 2*/
    public void selectDropdownOption(String option){
        dropdown = new Select(waitAndFind(dropdownLocator));
        dropdown.selectByVisibleText(option);
        System.out.println("Selected option [" + option + "]");
    }

    /** @param option valid options are Option 1, Option 2*/
    public boolean verifyOption(String option){
        return dropdown.getFirstSelectedOption().getAttribute("value").equals(option);
    }
}
