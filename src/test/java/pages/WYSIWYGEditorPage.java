package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class WYSIWYGEditorPage extends BasePage {

    By editorIFrame = By.tagName("i-frame");
    By editor = By.id("tinymce");
    String buttonLocator = "button[title=%s]";
    By strongTags = By.cssSelector("#tinymce strong");
    Actions actions;

    public WYSIWYGEditorPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyIfEditable(){
        waitAndFindFrame(editorIFrame);
        return waitAndFind(editor).getAttribute("contenteditable").equals("true");
    }

    public void clearText(){
        waitAndFind(editor).clear();
        System.out.println("Cleared Text");
    }

    public void enterText(String text){
        waitAndFind(editor).sendKeys(text);
        System.out.println("Entered [" + text + "]");
    }

    /** @param buttonName Ex: 'Bold', 'Italic', 'Align center'*/
    public void clickButton(String buttonName){
        // perform select all
        actions.keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .build().perform();
        waitAndFind(By.cssSelector(String.format(buttonLocator, buttonName))).click();
        System.out.println("Clicked [" + buttonName + "] button");
    }

    public boolean verifyTextInStrongTags(){
        return !waitAndFind(strongTags).getText().isEmpty();
    }
}
