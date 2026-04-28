package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class JQueryUIMenusPage extends BasePage {

    String element = "//a[text()='%s']";
    WebElement elementSelector;
    Actions actions;


    public JQueryUIMenusPage(WebDriver driver) {
        super(driver);
    }

    /** @param elementName could be 'Enabled', 'Disabled', 'Downloads' etc */
    public void hoverToElement(String elementName){
        elementSelector = waitAndFind(By.xpath(String.format(element, elementName)));
        actions = new Actions(driver);
        actions.moveToElement(elementSelector).perform();
        System.out.println("Hovered over element with name [" + elementName + "]");
    }

    /** @param fileExtension could be 'PDF', 'CSV', 'Excel' etc */
    public void clickFileExtension(String fileExtension){
        elementSelector = waitAndFind(By.xpath(String.format(element, fileExtension)));
        actions.moveToElement(elementSelector).perform();
        elementSelector.click();
        System.out.println("Clicked [" + fileExtension + "]");
    }

    /** @param fileNameWithExtension ex: 'menu.pdf' etc */
    public boolean verifyFileDownload(String fileNameWithExtension) {
        return isFileDownloaded(fileNameWithExtension);
    }
}
