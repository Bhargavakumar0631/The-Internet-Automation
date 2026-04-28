package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntryAdPage extends BasePage {

    By modalWindow = By.className("modal");
    By close = By.xpath("//p[text()='Close']"); // CSS: .modal :nth-child(odd) p

    public EntryAdPage(WebDriver driver) {
        super(driver);
    }

    public boolean isModalWindowVisible(){
        boolean isVisible = waitAndFind(modalWindow).isDisplayed();
        if(isVisible){
            System.out.println("Modal window was visible");
        }
        return isVisible;
    }

    public void clickClose(){
        waitToBeClickable(close).click();
        System.out.println("clicked Close");
    }

    public boolean verifyModalWindowHasDisappeared(){
        return waitForInvisibility(modalWindow);
    }
}
