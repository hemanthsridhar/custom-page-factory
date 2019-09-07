package org.test.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithJSON;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class HomePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver)), this);
    }

    private static final String PAGE = "HomePage";

    @SearchWithJSON(inPage = HomePage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "createButton")
    private WebElement createButton;

    public HomePage clickOnCreate() throws Exception{

        Thread.sleep(3000);
        createButton.click();
        Thread.sleep(3000);
        return this;
    }
}
