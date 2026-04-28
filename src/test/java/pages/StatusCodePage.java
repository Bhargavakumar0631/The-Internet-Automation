package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatusCodePage extends BasePage {

    String statusCodeLocator = "a[href*='%s']";
    By pageText = By.xpath("//p[contains(text(), 'status code')]");

    public StatusCodePage(WebDriver driver) {
        super(driver);
    }

    /** @param statusCode Ex: '200', '301', '404' */
    public void clickStatusCode(String statusCode){
        waitAndFind(By.cssSelector(String.format(statusCodeLocator, statusCode))).click();
        System.out.println("Clicked like for status code [" + statusCode + "]");
    }

    /** @param statusCode Ex: '200', '301', '404' */
    public boolean verifyStatusCode(String statusCode){
        String text = waitAndFind(pageText).getText();
        if(text != null && text.contains(statusCode)){
            return true;
        }
        return false;
    }
}
