package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.DriverFactory;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

// this class is for all the miscellaneous functions applicable throughout the Project
public class BasePage {
    // making this protected makes it accessible to all classes that extend it
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static Properties prop = ConfigReader.initProperties();

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("timeout"))));
    }

    /* this method waits and finds the visible locator and returns the WebElement using Explicit wait
    If it does not find the element visible it will throw a TimeoutException
    Because it returns a WebElement, you can chain any standard Selenium method to it:
    waitAndFind(locator).click(); (Wait then click)
    waitAndFind(locator).sendKeys("Hello"); (Wait then type)
    String text = waitAndFind(locator).getText(); (Wait then grab text)
    boolean isSelected = waitAndFind(locator).isSelected(); */
    /** @param locator could be any by locator -- by.id(''), by.xpath('')
     *  This method waits for the element to be visible on the Page
     * */
    public WebElement waitAndFind(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitToBeClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /** @param locator This method waits for the element to be present in the DOM.
     * Useful for elements rendered dynamically. Sometimes the element is present in DOm
     * but not visible on the page, it's because of hidden attribute
     */
    public WebElement waitAndFindPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // This waits for an element to disappear
    public boolean waitForInvisibility(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch(TimeoutException e){
            return false;
        }
    }

    // This method is to verify Header whenever on a new page
    /** @param locator could be any by locator -- by.id(''), by.xpath('')
     * @param expectedText could be any kind of page header or title -- 'Forgot Password', 'Login Page'*/
    public boolean isHeaderCorrect(By locator, String expectedText) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /** this function tells if a new window has opened or not */
    public boolean isNewWindowPresent() {
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            System.out.println("New window was opened");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Alert isAlertPresent() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    /** @param fileName give full file name with extension ex: sample.pdf */
    public boolean isFileDownloaded(String fileName) {
        String downloadPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "downloads";

        // 1. Ensure the directory exists
        File directory = new File(downloadPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            System.out.println("Target download directory created: " + created);
        }

        File file = new File(downloadPath + File.separator + fileName);
        boolean isFound = false;

        // 1. Wait and Check
        for (int i = 0; i < Integer.parseInt(prop.getProperty("timeout")); i++) {
            if (file.exists() && file.length() > 0) {
                isFound = true;
                break; // Exit loop immediately once found
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Test was interrupted");
            }
        }

        if (isFound) {
            boolean deleted = file.delete();
            System.out.println("File found and deleted: " + deleted);
        }

        return isFound;
    }

    /** @param locator give any By locator such as By.id, by.classNme, By.xpath
     * This function waits and switches to the desired frame using it's By locator. */
    public void waitAndFindFrame(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }
}
