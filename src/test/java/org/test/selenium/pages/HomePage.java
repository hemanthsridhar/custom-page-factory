package org.test.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.test.selenium.factory.FileBasedElementLocatorFactory;
import org.test.selenium.factory.SearchWith;
import org.test.selenium.factory.SearchWithFieldDecorator;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class HomePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver)), this);
    }

    private static final String PAGE = "HomePage";

    @SearchWith(inPage = HomePage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "createButton")
    private WebElement createButton;

    public HomePage clickOnCreate() throws Exception{

        Thread.sleep(3000);
        createButton.click();
        Thread.sleep(3000);
        return this;
    }
}
