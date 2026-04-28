package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class InputsPage extends BasePage {

    By input = By.xpath("//input[@type='number']");

    public InputsPage(WebDriver driver) {
        super(driver);
    }

    /** @param num could be any number whole or decimal*/
    public void enterInput(String num){
        waitAndFind(input).sendKeys(num);
        System.out.println("Entered [" + num + "] into input textbox");
    }

    public void pressUpArrowKey(){
        waitAndFind(input).sendKeys(Keys.ARROW_UP);
        System.out.println("Clicked Up Arrow Key");
    }

    /** @param num could be any number whole or decimal*/
    public boolean verifyTextboxValue(String num){
        return waitAndFind(input).getAttribute("value").equals(num);
    }
}
