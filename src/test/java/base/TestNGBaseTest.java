package base;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.ConfigReader;
import utils.DriverFactory;

import java.util.*;

// Only to be used if running a pure TestNg test, more tags and code can be added here.
public class TestNGBaseTest {
    Properties prop;


    @BeforeMethod
    public void setup() {
        prop = ConfigReader.initProperties();
         DriverFactory.initDriver(prop);
         DriverFactory.getDriver().get(prop.getProperty("url"));
    }

    @Test
    public void testBench(){
    }

    @AfterMethod
    public void teardown() {
        DriverFactory.quitDriver();
    }
}

