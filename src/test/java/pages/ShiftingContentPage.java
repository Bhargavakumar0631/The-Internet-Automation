package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShiftingContentPage extends BasePage {

    String linkTextLocator = "//a[contains(text(), '%s')]";

    public ShiftingContentPage(WebDriver driver) {
        super(driver);
    }

    /** @param exampleName Ex: 'Menu Element', 'List', 'An Image'*/
    public void clickExampleLink(String exampleName){
        waitAndFind(By.xpath(String.format(linkTextLocator, exampleName))).click();
        System.out.println("Clicked Link for [" + exampleName + "]");
    }
}
