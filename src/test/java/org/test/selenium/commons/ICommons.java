package org.test.selenium.commons;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public interface ICommons extends IExplicitWaits {

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
