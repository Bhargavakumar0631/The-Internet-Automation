package pages;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ContextMenuPage extends BasePage {

    By hotSpot = By.id("hot-spot");
    Actions actions;

    public ContextMenuPage(WebDriver driver) {
        super(driver);
    }

    public void rightClickHotSpot(){
        actions = new Actions(driver);
        actions.contextClick(waitAndFind(hotSpot)).build().perform(); // can do just .perform
        System.out.println("Right Clicked the hot-spot");
    }

    /** @param alertMessage a valid message could be ""You selected a context menu*/
    public boolean verifyAlertMessage(String alertMessage){
        String actualMessage = driver.switchTo().alert().getText();
        return actualMessage.equals(alertMessage);
    }

    public void acceptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
        System.out.println("Accepted the alert");
    }
}
