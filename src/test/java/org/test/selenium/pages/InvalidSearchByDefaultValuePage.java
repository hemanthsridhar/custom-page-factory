package org.test.selenium.pages;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchAll;
import com.github.hemanthsridhar.support.SearchBy;
import com.github.hemanthsridhar.support.SearchBys;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.constants.PageObjectsConfig;

import java.util.List;

/**
 * Created by hemanthsridhar on 1/6/19.
 */

@FilePath(value = PageObjectsConfig.LANDING_PAGE)
public class InvalidSearchByDefaultValuePage extends PageInitializer {

    private final WebDriver driver;

    @SearchBy
    private WebElement doesNotExistInLandingPageObjectsJsonFile;


    public InvalidSearchByDefaultValuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
    }

    public void searchByInvalidDefault() {
        Assert.assertTrue("does not exist in search by", doesNotExistInLandingPageObjectsJsonFile.isDisplayed());
    }


}
