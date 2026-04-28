package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class SortableDataTablesPage extends BasePage {

    By lastNameColumns = By.xpath("//table[@id='table1']/tbody/tr/td[1]"); // css: table#table1 tbody tr td:nth-child(1)
    String lastNameHeader = "//span[text()='%s']";

    public SortableDataTablesPage(WebDriver driver) {
        super(driver);
    }

    // This function iterates over all the lastName cells in all rows
    public List<String> getLastNames(){
        List<WebElement> allCells = driver.findElements(lastNameColumns);
        List<String> lastNames = new ArrayList<>();
        for (WebElement cell: allCells){
            lastNames.add(cell.getText());
        }
        // 3. Sort the list alphabetically
        // can't return Collections.sort() directly because it is a void method, just sorts doesn't return anything
        Collections.sort(lastNames);
        return lastNames;
    }

    /** @param lastName give the name of column header like Last Name, First Name*/
    public void clickHeaderToSort(String lastName){
        waitAndFind(By.xpath(String.format(lastNameHeader, lastName))).click();
        System.out.println("Clicked Header to sort");
    }

    /** @param lastNamesList this could be obtained from above function getLastNames()*/
    public boolean verifySortedLastNames(List<String> lastNamesList){
        List<String> lastNamesAfterSorting = getLastNames();
        return lastNamesAfterSorting.equals(lastNamesList);
    }

}
