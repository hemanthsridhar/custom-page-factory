package org.test.selenium.intr.commons;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.selenium.base.DriverFactory;

import java.time.Duration;
import java.util.List;

public interface ExplicitWaits {

    default void waitForVisibilityOfElement(WebElement element, Duration duration) throws TimeoutException {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOf(element));
    }

    default void waitForVisibilityOfElements(List<WebElement> elements, Duration duration) throws TimeoutException {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    default void waitForElementToBeClickable(WebElement element, Duration duration) throws TimeoutException {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(element));
    }

    default void waitForElementTextToBePresentInElement(WebElement element, Duration duration, String text) throws TimeoutException {
        WebDriver driver = DriverFactory.getDriver();
        new WebDriverWait(driver, duration).until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}
