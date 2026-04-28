package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShiftingContentMenuElementPage extends ShiftingContentPage{

    String menuElement= "//a[text()='%s']";
    By pageHeader = By.xpath("//div[@class='example']/h3");

    public ShiftingContentMenuElementPage(WebDriver driver) {
        super(driver);
    }

    /** @param menuItemName Ex: 'Gallery', 'Home', 'About'*/
    public int getPixelLocationAlongXAxis(String menuItemName){
        WebElement menuItem = waitAndFind(By.xpath(String.format(menuElement, menuItemName)));
        int xInitial = menuItem.getLocation().getX();
        System.out.println("location of Element is:" + xInitial);
        return xInitial;
    }

    public void refreshPage(){
        driver.navigate().refresh();
        isHeaderCorrect(pageHeader, "Shifting Content: Menu Element");
    }

    /** @param xAxisLocationBeforeRefresh is is of type int, this location of menu item before Refresh,
     *  can be obtained from above function getPixelLocationAlongXAxis()
     * @param itemName Ex: 'Gallery', 'Home', 'About'*/
    public boolean verifyPixelLocationAfterRefresh(int xAxisLocationBeforeRefresh, String itemName){
        int xAxisLocationAfterRefresh = getPixelLocationAlongXAxis(itemName);
        return !(xAxisLocationAfterRefresh == xAxisLocationBeforeRefresh);
    }
}
