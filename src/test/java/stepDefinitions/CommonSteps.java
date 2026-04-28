package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.ConfigReader;
import utils.DriverFactory;

import java.util.Properties;

public class CommonSteps {
    Properties prop;
    WebDriver driver;
    HomePage home;
    By loginMessage;

    @Given("the user navigates to the homepage")
    public void the_user_navigates_to_the_homepage() {
        driver = DriverFactory.getDriver();
        home = new HomePage(driver);
        loginMessage = By.xpath("//div[@class='example']/p");
        prop = ConfigReader.getProperties();

        // navigating to homepage
        driver.get(prop.getProperty("url"));
        home.verifyHomePageHeader();
    }

    @Given("the user navigates to {string}")
    public void the_user_navigates_to(String exampleName) {
        home.navigateToAvailableExamples(exampleName);
    }
}
