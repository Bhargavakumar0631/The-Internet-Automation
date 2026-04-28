package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class CheckboxesPage extends BasePage {

    String checkboxLocator = "//form[@id='checkboxes']/input[%d]";
    public CheckboxesPage(WebDriver driver) {
        super(driver);
    }

    /** @param checkboxName it could be Checkbox 1, Checkbox 2*/
    public By getCheckbox(String checkboxName){
        int index = Integer.parseInt(checkboxName.replaceAll("[^0-9]", ""));
        System.out.println("pointing to [" + checkboxName + "]");
        return By.xpath(String.format(checkboxLocator, index));
    }

    /** @param checkbox the By locator for checkbox can be obtained from function getCheckbox()*/
    public void clickCheckbox(By checkbox){
        waitAndFind(checkbox).click();
        System.out.println("Clicked Checkbox");
    }

    /** @param checkbox the By locator for checkbox can be obtained from function getCheckbox()*/
    public boolean verifyCheckboxStatus(By checkbox){
        return waitAndFind(checkbox).isSelected();
    }
}
