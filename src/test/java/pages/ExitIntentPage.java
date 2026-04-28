package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ExitIntentPage extends BasePage {

    By modalWindow = By.className("modal");

    public ExitIntentPage(WebDriver driver) {
        super(driver);
    }

    /* This is the only was action of mouse leaving the viewport is doable
    * Selenium cannot move the physical mouse cursor outside the browser's viewport */

    public void performMouseLeaveAction(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.documentElement.dispatchEvent(new MouseEvent('mouseleave'));");
        System.out.println("Mouse has left the viewport.");
    }

    public boolean isModalWindowVisible(){
        boolean isVisible = waitAndFind(modalWindow).isDisplayed();
        if(isVisible){
            System.out.println("Exit Intent Modal window was visible");
        }
        return isVisible;
    }
}
