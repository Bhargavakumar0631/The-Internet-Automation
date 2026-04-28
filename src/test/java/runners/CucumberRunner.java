package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "hooks"},// Points to your step definition AND your CucumberHooks
        // tags = "@iframe or @wysiwyg_editor",  multiple tag can be given with 'or'
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "html:target/cucumber-reports/report.html", // cucumber report
                "testng:target/cucumber-testng-report.xml" // testng report
        }
)

/*The Scenarios Provider: It contains a hidden TestNG @Test method that scans your features
folder and turns every scenario into a TestNG test.
The Bridge: It allows you to run Cucumber through the TestNG XML file,
use TestNG listeners, and run scenarios in parallel.*/
public class CucumberRunner extends AbstractTestNGCucumberTests {
    // Leave empty. It inherits everything from AbstractTestNGCucumberTests
}