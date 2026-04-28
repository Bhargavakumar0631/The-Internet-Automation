package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploadPage extends BasePage {

    By fileUploadElement = By.id("file-upload");
    By uploadButton = By.id("file-submit");

    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    /** @param fileName Ex: 'test-image.png' */
    public void selectFile(String fileName){
        String filePath = System.getProperty("user.dir") + "/src/test/resources/" + fileName;
        waitAndFind(fileUploadElement).sendKeys(filePath);
        System.out.println("Select file [" + fileName + "]");
    }

    public void clickUpload(){
        waitAndFind(uploadButton).click();
        System.out.println("Clicked upload Button");
    }

    /** @param message Ex: 'File Uploaded!' */
    public boolean verifyUpload(String message){
        return driver.getPageSource().contains(message);
    }
}
