package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IFramePage extends FramesPage{

    By iframe = By.tagName("iframe");
    By editor = By.id("tinymce");

    public IFramePage(WebDriver driver) {
        super(driver);
    }

    public void switchToIFrame(){
        waitAndFindFrame(iframe);
        System.out.println("Switched to IFrame context");
    }

    // we can verify this by first switching the iframe then using the editor locator to get an
    // attribute called contenteditable
    public boolean verifyIfEditable(){
        return waitAndFind(editor).getAttribute("contenteditable").equals("true");
    }
}
