package pages;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormAuthPage extends BasePage {
    // Making all Private for encapsulation
    By usernameField = By.id("username");
    By passwordField = By.id("password");
    By loginButton = By.xpath("//button[@type='submit']");
    By flashMessage = By.id("flash");

    public FormAuthPage(WebDriver driver){
        super(driver);
    }

    /** @param username  Enter tomsmith for the username */
    public void fillUsername(String username){
        waitAndFind(usernameField).sendKeys(username);
        System.out.println("filled Username " + username);
    }

    /** @param password  Enter SuperSecretPassword! for the password */
    public void fillPassword(String password){
        waitAndFind(passwordField).sendKeys(password);
        // below line converts password into asterisks
        System.out.println("filled Password " + String.format("%" + password.length() + "s", "").replace(" ", "*"));
    }

    public void clickLogin(){
        waitAndFind(loginButton).click();
        System.out.println("Clicked Login Button");
    }

    /** @param message, valid messages could be:
     * You logged into a secure area!, Your username is invalid!, Your password is invalid!, You logged out of the secure area!
     */
    public boolean verifyFlashMessage(String message){
        return isHeaderCorrect(flashMessage, message);
    }
}
