package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DragAndDropPage extends BasePage {

    String columnId = "column-%s";
    String columnHeader = "#column-%s header";
    Actions actions;

    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    /** @param column1 valid entries could include "A"
     * @param column2 valid entries could include "B" */
    public List<String> dragAndDrop(String column1, String column2){
        By column1Selector = By.id(String.format(columnId, column1.toLowerCase()));
        By column2Selector = By.id(String.format(columnId, column2.toLowerCase()));
        List<String> headers = new ArrayList<>(Arrays.asList(column1, column2));
        actions = new Actions(driver);
        actions.dragAndDrop(waitAndFind(column1Selector), waitAndFind(column2Selector)).build().perform();
        System.out.println("Dragged and Dropped column " + column1 + "over " + column2);
        return headers;
    }

    /** @param headerList this is original List of Headers in order
     * This can be obtained from above function dragAndDrop()*/
    public boolean verifySwappedHeaders(List<String> headerList){
        By column1Header = By.cssSelector(String.format(columnHeader,headerList.getFirst().toLowerCase()));
        By column2Header = By.cssSelector(String.format(columnHeader,headerList.getLast().toLowerCase()));
        // Header of column A should become B and vice versa
        return waitAndFind(column1Header).getText().equals(headerList.getLast()) && waitAndFind(column2Header).getText().equals(headerList.getFirst());
    }
}
