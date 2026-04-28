package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {
    By emailTextBox = By.id("email");
    By retrievePasswordButton = By.xpath("//button[@id='form_submit']/i");
    public ForgotPasswordPage(WebDriver driver){
        super(driver);
    }

    /** @param email  correct email: test@example.com or anything string be given */
    public void fillEmail(String email){
        waitAndFind(emailTextBox).sendKeys(email);
        System.out.println("filled Email " + email);
    }

    public void clickRetrievePassword(){
        waitAndFind(retrievePasswordButton).click();
        System.out.println("Click Retrieve Password Button");
    }
}
