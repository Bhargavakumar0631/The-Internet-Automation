package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicContentPage extends BasePage {
    By pageContent = By.xpath("//div[@id='content']/div[@class='row']");
    By images = By.xpath(".//img");//. is for searching inside current div or node and // is for looking for all img node inside it
    By pageHeader = By.xpath("//div[@class='example']/h3");

    public DynamicContentPage(WebDriver driver){
        super(driver);
    }

    public Map<String, List<String>> getPageData() {
        Map<String, List<String>> originalData = new HashMap<>();
        List<String> originalTexts = new ArrayList<>();
        List<String> originalImages = new ArrayList<>();
        waitAndFind(pageContent);
        List<WebElement> originalRows = driver.findElements(pageContent);
        for (WebElement row : originalRows) {
            originalTexts.add(row.getText());
            originalImages.add(row.findElement(images).getAttribute("src"));
        }
        originalData.put("texts", originalTexts);
        originalData.put("images", originalImages);
        return originalData;
    }

    public void refreshPage(){
        driver.navigate().refresh();
        isHeaderCorrect(pageHeader, "Dynamic Content");
    }

    /**.
     * @param originalData Data in form of Map<String, List<String>>
     * which can be Obtained using above function getPagedata(), which then compares
     * original data to refreshed data
     */
    public boolean verifyPageData(Map<String, List<String>> originalData) {
        Map<String, List<String>> refreshedData = getPageData();
        // Java Lists have a built-in .equals() that compares all items in order!
        boolean textChanged = !refreshedData.get("texts").equals(originalData.get("texts"));
        boolean imageChanged = !refreshedData.get("images").equals(originalData.get("images"));

        if (textChanged || imageChanged) {
            System.out.println("Content successfully changed!");
            return true;
        } else {
            System.out.println("Content remained the same.");
            return false;
        }
    }
}
