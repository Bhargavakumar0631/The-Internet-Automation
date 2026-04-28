package pages;

import base.BasePage;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class SlowResourcesPage extends BasePage {

    public SlowResourcesPage(WebDriver driver) {
        super(driver);
    }

    // the slow resource is: https://the-internet.herokuapp.com/slow_external
    public boolean verifyStatusAndLatency() {
        String currentUrl = driver.getCurrentUrl() + "_external";
        Response response = given()
                .when()
                .get(currentUrl);

        long responseTime = response.getTimeIn(TimeUnit.SECONDS); //response.getStatusCode() for statusCode
        System.out.println("Response Time: " + responseTime + " seconds");
        return responseTime >= 30;
    }
}
