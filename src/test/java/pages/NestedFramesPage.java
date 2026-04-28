package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage extends FramesPage {

    String frameLocator = "frame-%s";
    By content = By.id("content");

    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }

    /** @param frameName give name of the frame such as left, top, middle etc*/
    public void switchToFrame(String frameName){
        waitAndFindFrame(By.name(String.format(frameLocator, frameName)));
        System.out.println("Switched to[" + frameName + "] frame");
    }

    /** @param text Ex: 'MIDDLE' */
    public boolean verifyMiddleFrameContent(String text){
        return driver.findElement(content).getText().equals(text);
    }
}
