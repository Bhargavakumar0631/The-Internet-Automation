package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

public class NetworkAndStatusTest {

    WebDriver driver = DriverFactory.getDriver();
    BrokenImagesPage brokenImages = new BrokenImagesPage(driver);
    RedirectLinkPage redirect = new RedirectLinkPage(driver);
    StatusCodePage status = new StatusCodePage(driver);
    SlowResourcesPage slowResources = new SlowResourcesPage(driver);
    Response response;

    @Then("2 out of the 3 images on the page should fail to load")
    public void images_on_the_page_should_fail_to_load() {
        Assert.assertFalse(brokenImages.verifyAllImagesOnPage().isEmpty(),
                "There were no Broken Images on the Page");
    }

    @And("the user clicks ‘here’ to trigger a redirect")
    public void the_user_clicks_to_trigger_a_redirect() {
        response = redirect.clickHere();
    }

    @Then("the browser URL should change to the Status Codes page")
    public void the_browser_URL_should_change_to_the_Status_Codes_page() {
        Assert.assertTrue(redirect.verifyRedirect(response),
                "The Redirect was not successful");
    }

    @When("the user clicks on the status code {string}")
    public void the_user_clicks_on_the_status_code(String statusCode) {
        status.clickStatusCode(statusCode);
    }

    @Then("the page text should indicate that the server returned a {string} status")
    public void the_page_text_should_indicate_that_the_server_returned_a_status(String statusCode){
        Assert.assertTrue(status.verifyStatusCode(statusCode),
                "The Status code [" + statusCode + "] was not found");
    }

    @Then("rogue GET request that takes 30 seconds to complete")
    public void rogue_get_request_tha_takes_30_seconds_to_complete(){
        Assert.assertTrue(slowResources.verifyStatusAndLatency(),
                "The status code was not 200 or the page loaded too early");
    }
}
