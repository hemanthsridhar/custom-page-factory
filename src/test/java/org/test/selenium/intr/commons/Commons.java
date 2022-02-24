package org.test.selenium.intr.commons;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public interface Commons extends ExplicitWaits {

    default boolean isDisplayed(WebElement element) {
        try{
            element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    default boolean isDisplayed(WebElement element, Duration duration) {
        try{
            waitForVisibilityOfElement(element,duration);
            return isDisplayed(element);
        }
        catch (TimeoutException e){
            return false;
        }
    }
}
