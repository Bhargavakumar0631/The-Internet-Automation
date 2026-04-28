package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeolocationPage extends BasePage {

    String buttonLocator = "//button[text()='%s']";
    By latitudeValue = By.id("lat-value");
    By longitudeValue = By.id("long-value");

    public GeolocationPage(WebDriver driver) {
        super(driver);
    }

    /** @param buttonName Ex:'Where am I?'*/
    public void clickButton(String buttonName){
        waitAndFind(By.xpath(String.format(buttonLocator, buttonName))).click();
        System.out.println("clicked [" + buttonName + "] button");
    }

    public boolean verifyCoordinatesAreDisplayed(){
        WebElement latitude = waitAndFind(latitudeValue);
        WebElement longitude = waitAndFind(longitudeValue);
        if(latitude.isDisplayed() && longitude.isDisplayed()) {
            System.out.println("Latitude Value: [" + latitude.getText() + "]" +
                    "\n Longitude Value: [" + longitude.getText() + "]");
            return true;
        }
        return false;
    }

    // Using JavascriptExecutor to set location
    public void setMockGeolocation() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Delhi Coordinates hardcoded for testing
        double latitude = 28.6129;
        double longitude = 77.2295;

        String script = "window.navigator.geolocation.getCurrentPosition = function(success) {" +
                "    var position = {" +
                "        coords: {" +
                "            latitude: " + latitude + "," +
                "            longitude: " + longitude + "," +
                "            accuracy: 100" +
                "        }" +
                "    };" +
                "    success(position);" +
                "};";

        js.executeScript(script);
    }
}
