package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LargeAndDeepDOMPage extends BasePage {

    String siblingStringCss = "#siblings [id$='%s']";
    By siblingElement;//using ends Id ends with

    public LargeAndDeepDOMPage(WebDriver driver) {
        super(driver);
    }

    /** @param elementName Ex: '50.3' this function collects elements from the DOM with given CSS
     *  and checks size of the findElements list, it should not be empty
     *  .isDisplayed() can also be done to check if the element */
    public boolean isSiblingElementPresent(String elementName) {
        siblingElement = By.cssSelector(String.format(siblingStringCss, elementName));
        return !driver.findElements(siblingElement).isEmpty();
    }

    /** @param text Ex: '50.3'*/
    public boolean verifyText(String text){
        return waitAndFind(siblingElement).getText().equals(text);
    }


}
