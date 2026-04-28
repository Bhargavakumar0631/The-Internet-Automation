package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class JavaScriptOnloadEventErrorPage extends BasePage {

    public JavaScriptOnloadEventErrorPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyOnLoadError() {
        // Fetch all browser logs
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);

        boolean errorFound = false;
        for (LogEntry entry : logEntries) {
            // Look for SEVERE level logs (standard for JS crashes)
            if (entry.getLevel().getName().equals("SEVERE")) {
                System.out.println("Detected JS Error: " + entry.getMessage());

                // Check if the message contains the specific text from your image
                if (entry.getMessage().contains("Failed to load resource")) {
                    errorFound = true;
                    break;
                }
            }
        }
        return errorFound;
    }
}
