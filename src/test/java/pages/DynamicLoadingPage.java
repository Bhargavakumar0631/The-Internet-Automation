package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicLoadingPage extends BasePage {

    String exampleLinkSelector = "//div[@class='example']/a[%s]";
    By startButton = By.cssSelector("#start button");
    By loadingIndicator = By.id("loading");
    /* This element @dynamicElement loads dynamically, sometimes it is already present on the Page,
        sometimes it may render after you click on start
        In Example 1 If you try to search the DOM for #finish before clicking start you will find it,
        but in Example 2 it won't be found before you click start, only after the click
    */
    By dynamicElement = By.id("finish");
    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    /**.
     * @param index ex: Example 1 has index 1 and vice versa
     */
    public void clickExampleLink(String index){
        driver.findElement(By.xpath(String.format(exampleLinkSelector, index))).click();
        System.out.println("Clicked Example " + index);
    }

    public void clickStart(){
        waitAndFind(startButton).click();
        System.out.println("Clicked Start Button");
    }

    public boolean verifyLoadingBar(){
       return waitForInvisibility(loadingIndicator);
    }

    /**.
     * @param text ex: Hello World!
     * This waits until the dynamic element inside which the text resides is both present AND visible
     */
    public boolean verifyText(String text) {
        WebElement element = waitAndFind(dynamicElement);
        return element.getText().equals(text);
    }
}
