package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

public class FileManagementTest {

    WebDriver driver = DriverFactory.getDriver();
    FileDownloadPage fileDownload = new FileDownloadPage(driver);
    FileUploadPage fileUpload = new FileUploadPage(driver);
    String fileFullName;

    @When("the user clicks on a file link ending with extension {string}")
    public void the_user_clicks_on_a_file_link(String extensionName) {
        fileFullName = fileDownload.clickFileLink(extensionName);
    }

    @And("the file should be successfully downloaded to the local directory")
    public void the_file_should_be_successfully_downloaded_to_the_local_directory() {
        Assert.assertTrue(fileDownload.verifyFileDownload(fileFullName),
                "File with name [" + fileFullName + "] was not found in the directory");
    }

    @Then("the user selects the file {string}")
    public void the_user_selects_a_file(String fileName) {
        fileUpload.selectFile(fileName);
    }

    @When("clicks the Upload button")
    public void the_user_click_a_button() {
        fileUpload.clickUpload();
    }

    @Then("the {string} success message should appear")
    public void the_success_message_Should_appear(String message){
        Assert.assertTrue(fileUpload.verifyUpload(message),
                "File Upload was not successful");
    }
}
