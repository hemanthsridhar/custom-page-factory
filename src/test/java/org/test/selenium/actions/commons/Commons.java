package org.test.selenium.actions.commons;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.test.selenium.pages.Pages;

import java.time.Duration;

public interface Commons extends ExplicitWaits, Pages {

    default boolean isDisplayed(WebElement element, Duration... duration) {
        try{
            try{
                waitForVisibilityOfElement(element,duration[0]);
                element.isDisplayed();
            }
            catch (Exception e){
                element.isDisplayed();
            }
            return true;
        }
        catch (NoSuchElementException | TimeoutException e){
            return false;
        }
    }
}
