package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChallengingDOMPage extends BasePage {

    By blueButton = By.cssSelector(".button:not(.alert):not(.success)");
    By redButton = By.cssSelector(".button.alert");
    By greenButton = By.cssSelector(".button.success");
    By canvas = By.id("canvas");

    public ChallengingDOMPage(WebDriver driver) {
        super(driver);
    }

    // Run this function to get a Map of Ids of all three buttons
    public Map<String, String> getIds(){
        Map<String, String> idMap = new HashMap<>();
        idMap.put("blue", waitAndFind(blueButton).getAttribute("id"));
        idMap.put("red", waitAndFind(redButton).getAttribute("id"));
        idMap.put("green", waitAndFind(greenButton).getAttribute("id"));
        return idMap;
    }

    public void clickBlueButton(){
        waitAndFind(blueButton).click();
        System.out.println("Clicked Blue Button");
    }

    /* This returns a byte[] array. It takes screenshot of just the canvas
    * then return it in form of an array of 0s and 1s */
    public byte[] getCanvas(){
        return waitAndFind(canvas).getScreenshotAs(OutputType.BYTES);
    }

    /** @param mapOfIdsBeforeRefresh This can be obtained from above function getIds()
     * Then this compares map of old Ids with new */
    public boolean verifyIdAfterRefresh(Map<String, String> mapOfIdsBeforeRefresh){
        Map<String, String> mapOfIdsAfterRefresh = getIds();
        return !mapOfIdsAfterRefresh.equals(mapOfIdsBeforeRefresh);
    }

    /** @param screenshotBeforeRefresh This can be obtained from above function getCanvas()
     * Then this compares byte array of old canvas element with new */
    public boolean verifyCanvasAfterRefresh(byte[] screenshotBeforeRefresh){
        byte[] screenshotAfterRefresh = getCanvas();
        return !Arrays.equals(screenshotAfterRefresh, screenshotBeforeRefresh);
    }

}
