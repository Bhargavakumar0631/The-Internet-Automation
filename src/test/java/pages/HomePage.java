package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    String dynamicXpath = "//ul/li/a[text()='%s']";
    By homePageHeader = By.className("heading");

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void verifyHomePageHeader(){
        waitAndFind(homePageHeader);
    }

    /**
     * Navigates to a specific example page based on the link text provided.
     * @param exampleName The exact text of the link (e.g., "A/B Testing")
     */
    public void navigateToAvailableExamples(String exampleName) {
        String finalXpath = String.format(dynamicXpath, exampleName);
        try {
            waitAndFind(By.xpath(finalXpath)).click();
            System.out.println("Clicked example [" + exampleName + "]");
        } catch (TimeoutException e) {
            throw new RuntimeException("Could not find or click the example: [" + exampleName + "] within the timeout period.");
        }
    }
}
