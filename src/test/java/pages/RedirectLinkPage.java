package pages;

import base.BasePage;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;

public class RedirectLinkPage extends BasePage {

    By hereLink = By.id("redirect");
    String targetUrl = "/status_codes";

    public RedirectLinkPage(WebDriver driver) {
        super(driver);
    }

    public Response clickHere() {
        // Capture response after clicking here link or redirect link
        return  given()
                .config(RestAssuredConfig.config()
                        .redirect(RedirectConfig.redirectConfig().followRedirects(false)))
                .when().
                get(waitAndFind(hereLink).getAttribute("href"));
    }

    /** @param response this is a Response you get after clicking here link
     * It contains an object of 4 things StatusLine(statusCode & message),
     * Headers(Location, serverType, date), cookies and Body(HTML/JSON)*/
    public boolean verifyRedirect(Response response){
        int statusCode = response.getStatusCode();
        String locationHeader = response.getHeader("Location");

        if (statusCode == 302 && locationHeader != null && locationHeader.contains(targetUrl)) {
            return true;
        } else {
            System.out.println("Redirect check failed. Status: " + statusCode + ", Location: " + locationHeader);
            return false;
        }
    }
}
