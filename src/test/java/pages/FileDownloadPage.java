package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FileDownloadPage extends BasePage {

    By fileLinks = By.cssSelector("#content a");

    public FileDownloadPage(WebDriver driver) {
        super(driver);
    }

    /** @param extension the file links on secure download page gets refreshed everytime you visit the page,
     * so there are no definite file names to test positive scenarios, we could use extensions like .pdf, .txt. png etc.
     * to test secure download*/
    public String clickFileLink(String extension) {
        List<WebElement> allLinks = driver.findElements(fileLinks);
        for (WebElement link : allLinks) {
            if (link.getText().endsWith(extension)) {
                String fullName = link.getText();
                link.click();
                System.out.println("Clicked file with name [" + fullName + "]");
                return fullName; // Returning the name to know what to look for in Downloads
            }
        }
        throw new RuntimeException("No " + extension + " file found on page!");
    }

    /** @param fileName can be obtained from the clickFileByExtension() method or provided by ourselves */
    public boolean verifyFileDownload(String fileName) {
        return isFileDownloaded(fileName);
    }
}
