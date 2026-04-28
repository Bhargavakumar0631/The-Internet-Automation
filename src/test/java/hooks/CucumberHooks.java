package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverFactory;
import java.util.Properties;

public class CucumberHooks {
    protected static Properties prop;
    protected static WebDriver driver;

    @Before
    public void setup() {
        prop = ConfigReader.initProperties();
        DriverFactory.initDriver(prop);
        driver = DriverFactory.getDriver();
    }

    @After
    public void teardown() {
        DriverFactory.quitDriver();
    }

    // This runs after EVERY single Gherkin step
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            if (driver != null) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "step_screenshot");
            }
        }
    }
}

