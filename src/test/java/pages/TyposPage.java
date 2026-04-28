package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TyposPage extends BasePage {

    String paragraphLocator = "//p[contains(text(), \"%s\")]";
    int repetition = Integer.parseInt(prop.getProperty("repetition"));

    public TyposPage(WebDriver driver) {
        super(driver);
    }

    /** @param correctText Ex: "don't"
     * @param incorrectText Ex: "don,t" */
    public boolean verifyTypo(String correctText, String incorrectText){
        boolean isCorrectPresent = false;
        boolean isIncorrectPresent = false;
        for(int i = 0; i < repetition; i++) {
            List<WebElement> correctTextElements = driver.findElements(By.xpath(String.format(paragraphLocator, correctText)));
            List<WebElement> incorrectTextElements = driver.findElements(By.xpath(String.format(paragraphLocator, incorrectText)));
            if (!correctTextElements.isEmpty() && correctTextElements.getFirst().isDisplayed()) {
                isCorrectPresent = true;
            }
            if (!incorrectTextElements.isEmpty() && incorrectTextElements.getFirst().isDisplayed()) {
                isIncorrectPresent = true;
            }
            if(isCorrectPresent && isIncorrectPresent){
                break;
            }
            driver.navigate().refresh();
        }
        return isCorrectPresent && isIncorrectPresent;
    }
}
