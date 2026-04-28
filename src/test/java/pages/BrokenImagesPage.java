package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BrokenImagesPage extends BasePage{

    By allImages = By.tagName("img");

    public BrokenImagesPage(WebDriver driver) {
        super(driver);
    }

    /* This function check the broken images using restAssured
    * and returns a list of src and status code of those broken images */
    public List<String> verifyAllImagesOnPage() {
        List<WebElement> images = driver.findElements(allImages);
        List<String> brokenImages = new ArrayList<>();

        // 3. Loop and Validate using RestAssured
        for (WebElement img : images) {
            String src = img.getAttribute("src");
            if (src != null && !src.isEmpty()) {
                try {
                    int statusCode = given().when().head(src).getStatusCode();
                    if (statusCode != 200) { // Ok status code is 200
                        brokenImages.add("URL: " + src + " | Status: " + statusCode);
                    }
                } catch (Exception e) {
                    brokenImages.add("URL: " + src + " | Error: Connection Failed");
                }
            }
        }
        System.out.println(brokenImages);
        return brokenImages;
    }
}
