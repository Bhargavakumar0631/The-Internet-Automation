package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class MultipleWindowsPage extends BasePage {

    String linkTextLocator = "//a[text()='%s']";
    String parentHandle;

    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
    }

    /** @param linkText Example linkText could be "Click Here"*/
    public void clickLinkWithText(String linkText){
        // getting parent window handle before clicking on link
        parentHandle = driver.getWindowHandle();
        waitAndFind(By.xpath(String.format(linkTextLocator, linkText))).click();
        System.out.println("Clicked link with text [" + linkText + "]");
    }

    public void switchWindow(){
        driver.findElement(By.linkText("Click Here")).click();
        // using set because getWIndowHandles returns a set<>
        Set<String> allHandles = driver.getWindowHandles();
        // 4. Switch to the new handle
        for (String handle : allHandles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    /** @param text this is text present on the new window Ex: "New Window"*/
    public boolean verifyNewWindowText(String text){
        return driver.getPageSource().contains(text);
    }

}
