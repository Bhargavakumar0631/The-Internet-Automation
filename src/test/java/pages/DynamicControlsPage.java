package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DynamicControlsPage extends BasePage {

    By checkboxLocator = By.cssSelector("#checkbox input");
    By removeButton = By.xpath("//button[@onclick='swapCheckbox()']");
    By textbox = By.xpath("//input[@type='text']");
    By enableButton = By.xpath("//button[@onclick='swapInput()']");
    By loadingIndicator = By.id("loading");
    String messageContainer = "//p[text()=\"%s\"]";
    public DynamicControlsPage(WebDriver driver){
        super(driver);
    }

    public boolean isCheckboxAvailable(){
        /* using List because when checkbox has not only disappeared from the page but the
        DOM too, isDisplayed() will throw NoSuchElementException
        isDisplayed would have been false it the tag was <input type="checkbox" style="display:none">*/
        List<WebElement> checkbox = driver.findElements(checkboxLocator);
        return checkbox.isEmpty();
    }

    public void clickRemove(){
        waitAndFind(removeButton).click();
        System.out.println("Clicked Remove Button");
    }

    /**.
     * @param message expected message after clicking remove or enable buttons
     */
    public boolean verifyMessageAndLoadingIndicator(String message){
        try {
            List<WebElement> loadingElements = driver.findElements(loadingIndicator);
            Boolean isLoadingIndicatorVisible = !loadingElements.isEmpty();
            Boolean isMessageSelectorVisible = waitAndFind(By.xpath(String.format(messageContainer, message))).isDisplayed();
            return isLoadingIndicatorVisible && isMessageSelectorVisible;
        } catch(TimeoutException e){
            e.getStackTrace();
            return false;
        }
    }

    public void clickEnable(){
        waitAndFind(enableButton).click();
        System.out.println("Clicked Enable Button");
    }

    public boolean isTextboxEnabled(){
        return waitAndFind(textbox).isEnabled();
    }
}
