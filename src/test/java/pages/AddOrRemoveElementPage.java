package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AddOrRemoveElementPage extends BasePage {

    By addElement = By.xpath("//button[@onclick='addElement()']");
    By elements = By.cssSelector("#elements button"); // this is the element inside which all delete buttons live
    String deleteButtonSelector = "//div[@id='elements']/child::button[%d]";

    public AddOrRemoveElementPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddElement(){
        waitAndFind(addElement).click();
        System.out.println("Clicked add Element");
    }

    /**@param index could be 1, 2, any number n*/
    public void clickDeleteButtonByIndex(int index){
        By deleteButton = By.xpath(String.format(deleteButtonSelector, index));
        try {
            waitAndFind(deleteButton).click();
            System.out.println("Clicked delete Button");
        } catch(TimeoutException e){
            e.getStackTrace();
        }
    }

    public int getDeleteButtonCount(){
        List<WebElement> deleteButtonList = driver.findElements(elements);
        return deleteButtonList.size();
    }
}
