package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HoversPage extends BasePage {

    String relativePath = "//div[@class='figure'][1]";
    By firstImage = By.xpath(relativePath + "/img");
    By nameLocator = By.xpath(relativePath + "//h5");
    By linkTextLocator = By.xpath(relativePath + "//a");
    Actions actions;

    public HoversPage(WebDriver driver) {
        super(driver);
    }

    public void hoverOverImage(){
        actions = new Actions(driver);
        actions.moveToElement(waitAndFind(firstImage)).build().perform(); // can use just .perform
        System.out.println("Hovered Over the Image");
    }

    /** @param name valid example could be "name: user1"
     * @param linkText valid example could be "View profile" */
    public boolean verifyTextAndLink(String name, String linkText){
        return waitAndFind(nameLocator).getText().equals(name) && waitAndFind(linkTextLocator).getText().equals(linkText);
    }

    /** @param link valid example could be "View profile" */
    public void clickLink(String link){
        waitAndFind(linkTextLocator).click();
        System.out.println("Clicked Link [" + link + "]");
        // when clicked on view profile, a page opens with text Not Found
        driver.getPageSource().contains("not Found");
    }
}
