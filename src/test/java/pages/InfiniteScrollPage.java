package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class InfiniteScrollPage extends BasePage{

    By scrollPara = By.cssSelector(".jscroll-inner > div"); // CSS of every new para that appears after scrolling to the bottom
    By pageFooter = By.id("page-footer");
    public InfiniteScrollPage(WebDriver driver) {
        super(driver);
    }

    public int getParagraphCount(){
        List<WebElement> allParagraphs = driver.findElements(scrollPara);
        return allParagraphs.size();
    }

    public void scrollToBotton(){
        Actions actions = new Actions(driver);
        actions.scrollToElement(waitAndFindPresent(pageFooter)).perform();
        System.out.println("Scrolled to bottom of the page");
    }

    /** @param totalParasBeforeScroll this is length of DOM paras before scrolling to the
     * bottom, this can be obtained by above function getParagraphCount() */
    public boolean verifyDOMParaUponScroll(int totalParasBeforeScroll){
        List<WebElement> newParagraphs = driver.findElements(scrollPara);
        int totalParasAfterScroll = newParagraphs.size();
        return totalParasAfterScroll > totalParasBeforeScroll;
    }
}
