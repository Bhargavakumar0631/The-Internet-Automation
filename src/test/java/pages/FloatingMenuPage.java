package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class FloatingMenuPage extends BasePage {

    By floatingMenu = By.id("menu");
    By pageFooter = By.id("page-footer");

    public FloatingMenuPage(WebDriver driver) {
        super(driver);
    }

    public void scrollToBotton(){
        Actions actions = new Actions(driver);
        actions.scrollToElement(waitAndFindPresent(pageFooter)).perform();
        System.out.println("Scrolled to bottom of the page");
    }

    public boolean isFloatingMenuVisible(){
        return waitAndFind(floatingMenu).isDisplayed();
    }
}
