package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HorizontalSliderPage extends BasePage {

    By slider = By.cssSelector(".sliderContainer input"); // this is the actual slider
    By rangeDisplay = By.cssSelector(".sliderContainer #range"); // this is current value of slider

    public HorizontalSliderPage(WebDriver driver) {
        super(driver);
    }

    /** @param range This could be anything between 0 and 5 and also only in multiple of 0.5
     * This function checks for above given conditions because, the slider only goes from 0 to 5
     * and only increases 0.5 values at once
     * Strategy is to press te right arrow range/0.5 times to range
     * Ex- range = 3.5, range/0.5 = 7, so we press right arrow key 7 times to move to 3.5*/
    public void moveSlider(String range){
        try {
            double value = Double.parseDouble(range);
            int rightArrowCount = (int)(value/0.5);
            if (value >= 0 && value <= 5 && value % 0.5 == 0) {
                for (int i = 0; i < rightArrowCount; i++){
                    waitAndFind(slider).sendKeys(Keys.ARROW_RIGHT);
                }
                System.out.println("Moved slider to [" + range + "]");
            } else {
                System.out.println(value + " Entered is not correct");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }

    /** @param range  could be any whole or decimal number*/
    public boolean verifyRange(String range){
        return waitAndFind(rangeDisplay).getText().equals(range);
    }
}
