package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ForgotPasswordPage;
import pages.FormAuthPage;
import pages.HomePage;
import pages.SecureFileDownloaderPage;
import utils.DriverFactory;

public class AuthenticationTest{

    WebDriver driver = DriverFactory.getDriver();
    HomePage home = new HomePage(driver);
    FormAuthPage formAuth = new FormAuthPage(driver);
    SecureFileDownloaderPage secureDownload = new SecureFileDownloaderPage(driver);
    ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
    By loginMessage = By.xpath("//div[@class='example']/p");
    String fileName;

    @When("the user navigates to {string} with credentials {string} and {string} in the URL")
    public void the_user_navigates_to_with_credentials_and_in_the_url(String exampleName, String userName, String password) {
        /* can use
         ((HasAuthentication) driver).register(UsernameAndPassword.of("admin", "admin"));
         driver.get(prop.getProperty("url"));
        or */

        driver.get("https://" + userName + ":" + password + "@the-internet.herokuapp.com/" + exampleName);
    }

    @Then("the page text should contain {string}")
    public void the_page_text_should_contain(String message) {
        Assert.assertTrue(home.isHeaderCorrect(loginMessage, message),
                "Verification Failed: Expected page text to contain [" + message + "] but it did not.");
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        formAuth.fillUsername(username);
        formAuth.fillPassword(password);
        formAuth.clickLogin();
    }

    @Then("a flash message should contain {string}")
    public void a_flash_message_should_contain(String message) {
        Assert.assertTrue(formAuth.verifyFlashMessage(message),
                "The flash message did not contain the expected text: " + message);
    }

    @And("the user clicks on any secure file link with extension {string}")
    public void the_user_clicks_on_a_secure_file_link_with_extension(String extension) {
        fileName = secureDownload.clickFileByExtension(extension);
    }

    @Then("the file should be downloaded successfully")
    public void the_file_should_be_downloaded_successfully() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Download wait timer was interrupted.", e);
        }
        Assert.assertTrue(secureDownload.verifyFileDownload(fileName),
                "The file [" + fileName + "] was not found in the Downloads folder!");
    }

    @When("the user enters the email {string}")
    public void the_user_enters_the_email(String email) {
        forgotPassword.fillEmail(email);
    }

    @And("clicks the Retrieve Password button")
    public void clicks_Retrieve_Password_button() {
        forgotPassword.clickRetrievePassword();
    }

    @Then("a server error {string} is expected by design")
    public void a_server_error_is_expected_by_design(String message) {
        Assert.assertTrue(driver.getPageSource().contains(message),
                "Verification Failed: Expected page text to contain [" + message + "] but it did not.");
    }
}
