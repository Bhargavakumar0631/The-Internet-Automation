package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ABTestingPage extends BasePage {

    By header = By.tagName("h3");

    public ABTestingPage(WebDriver driver) {
        super(driver);
    }

    /** @param headerText Ex: 'A/B Test Variation 1' , 'A/B Test Control' */
    public boolean verifyHeader(String headerText){
        return isHeaderCorrect(header, headerText);
    }
}
