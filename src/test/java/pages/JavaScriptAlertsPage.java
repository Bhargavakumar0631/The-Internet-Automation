package pages;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaScriptAlertsPage extends BasePage {

    String buttonLocator = "//button[text()='%s']";
    By result = By.id("result");

    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    /** @param buttonName valid button names are 'Click for JS Alert', 'Click for JS Confirm', 'Click for JS Prompt' */
    public void clickButton(String buttonName){
        By button = By.xpath(String.format(buttonLocator, buttonName));
        waitAndFind(button).click();
        System.out.println("Click button with name [" + buttonName + "]");
    }

    /** @param action valid actions could be 'accept', 'dismiss'
     * @param text could be anything, If no input is to be filled pass an empty string
     * but the gherkin will pass that automatically if scenario outline example table is left blank
     * for the text field*/
    public void handleAlert(String action, String text){
        Alert alert = isAlertPresent();
        if (action.equals("accept")) {
            if (!text.isEmpty()) {
                alert.sendKeys(text);
                System.out.println("entered text [" + text + "]");
            }
            alert.accept();
            System.out.println("Accepted the alert");
        } else {
            alert.dismiss();
            System.out.println("Dismissed the alert");
        }
    }

    /** @param resultText valid button names are 'You successfully clicked an alert',
     * 'You clicked: Cancel', 'You entered: Hello QA' */
    public boolean verifyResultText(String resultText){
        return waitAndFind(result).getText().equals(resultText);
    }
}
