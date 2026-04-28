package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class KeyPressesPage extends BasePage {

    By result = By.id("result");

    public KeyPressesPage(WebDriver driver) {
        super(driver);
    }

    /** @param key Enter name of any key Ex: 'Enter', 'space'*/
    public void pressKey(String key){
        Actions actions = new Actions(driver);
        Keys keyToPress = Keys.valueOf(key.toUpperCase());
        actions.sendKeys(keyToPress).perform();
        System.out.println("[" + key + "] has been pressed");
    }

    /** @param resultText Ex: 'You entered: ENTER'*/
    public boolean verifyResult(String resultText){
        return waitAndFind(result).getText().equals(resultText);
    }
}
