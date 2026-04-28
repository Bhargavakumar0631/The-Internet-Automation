package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FramesPage extends BasePage {

    String frameLinkLocator = "//a[text()='%s']";

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    /** @param linkName This is the type of frame Ex: 'Nested Frame', 'iFrame'*/
    public void clickFramesLink(String linkName){
        WebElement frameLink = waitAndFind(By.xpath(String.format(frameLinkLocator, linkName)));
        frameLink.click();
        System.out.println("Clicked frame link [" + linkName + "]");
    }
}
