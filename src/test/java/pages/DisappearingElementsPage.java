package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;

import java.util.List;
import java.util.Properties;

public class DisappearingElementsPage extends BasePage {

    String buttonLocator = "//a[text()='%s']";
    int repetition = Integer.parseInt(prop.getProperty("repetition")); // can directly do prop because prop is defined in BasePage

    public DisappearingElementsPage(WebDriver driver) {
        super(driver);
    }

    public boolean refreshPageUntilStateChange(String buttonName) {
        boolean visible = false;
        boolean invisible = false;
        for (int i = 0; i <= repetition; i++) {
            // Using a list because if the button is not found, it will throw NoSuchElementException
            List<WebElement> elements = driver.findElements(By.xpath(String.format(buttonLocator, buttonName)));
            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                visible = true;
            } else {
                invisible = true;
            }

            if (visible && invisible) {
                System.out.println("The Element" + buttonName + "is appearing and disappearing randomly");
                return true;
            }
            driver.navigate().refresh();
        }
        return false;
    }
}
