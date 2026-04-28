package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotificationMessagesPage extends BasePage {

    String linkLocator = "//a[text()='%s']";
    By flashMessage = By.cssSelector("#flash-messages > div");

    public NotificationMessagesPage(WebDriver driver) {
        super(driver);
    }

    /** @param linkName Ex: 'Click here'*/
    public void clickLink(String linkName){
        waitAndFind(By.xpath(String.format(linkLocator, linkName))).click();
        System.out.println("Clicked link with name [" + linkName + "]");
    }

    /* Ex: messages: "Action successful", "Action unsuccesful, please try again"
    * unsuccessful is spelled wrong on the webpage itself*/
    public String getFlashMessage(){
        // .trim removes all white spaces and replaceAll("[^a-zA-Z ]", "") replaces all chars except alphabets
        return (waitAndFind(flashMessage).getText()).split(",")[0].trim().replaceAll("[^a-zA-Z ]", "");
    }
}
