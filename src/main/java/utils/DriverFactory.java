package utils;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* In a multithreaded environment (where you might run 5 tests at once), your ThreadLocal<WebDriver> driver object acts like a locker room.
    tlDriver.set(driver): This puts a specific browser into a "locker" labeled with the current Thread ID.
    return driver.get(): This method reaches into that locker and pulls out the exact browser that belongs to the test currently running. */
public class DriverFactory {
    // ThreadLocal ensures thread-safety for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser").toLowerCase();

        if (browserName.equals("chrome")) {
            // Use chromeOptions if need be
            ChromeOptions options = getChromeOptions();
            driver.set(new ChromeDriver(options));
        } else if (browserName.equals("firefox")) {
            driver.set(new FirefoxDriver());
        } else {
            throw new RuntimeException("Browser type not supported: " + browserName);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("timeout"))));
        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    private static @NonNull ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Setup ChromeOptions to handle popups and other properties
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "target" + File.separator + "downloads");
        // adding below lines to avoid credentials pop-ups like 'save password' etc.
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection");
        return options;
    }
}