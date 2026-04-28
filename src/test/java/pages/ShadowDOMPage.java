package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDOMPage extends BasePage {

    By shadowGatewayParent = By.cssSelector("my-paragraph"); // this is the parent of #shadow-root, the shadow DOM element
    By shadowTextContainer = By.cssSelector("slot[name='my-text']");

    public ShadowDOMPage(WebDriver driver) {
        super(driver);
    }

    public SearchContext getShadowElement(){
        WebElement shadowHost = waitAndFindPresent(shadowGatewayParent);
        // shadowHost.getShadowRoot is of type SearchContext
        return shadowHost.getShadowRoot();
    }

    public boolean isShadowTextReadable(SearchContext shadowRoot){
        String shadowText = shadowRoot.findElement(shadowTextContainer).getText();
        System.out.println("Text found in Shadow DOM [" + shadowText + "]");
        return !shadowText.isEmpty();
    }
}
