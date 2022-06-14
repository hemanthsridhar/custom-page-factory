package org.test.selenium.pages.json.searchresults;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.pagefactory.dynamic.PageFactoryLoader;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchAll;
import com.github.hemanthsridhar.support.SearchBy;
import com.github.hemanthsridhar.support.SearchBys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.commons.ICommons;
import org.test.selenium.constants.json.IPageObjects;

import java.util.List;

public class SearchResultsPageObjects implements ICommons {

    protected final WebDriver driver;

    @SearchBy
    protected List<WebElement> searchResults;

    @SearchBy
    protected WebElement searchResultsSection;

    @SearchAll(value = {@SearchBy(nameOfTheLocator = "settingsBtn"), @SearchBy(nameOfTheLocator = "signInBtn"), @SearchBy(nameOfTheLocator = "invalidLocator")})
    protected WebElement rightMenuItemsFindAll;

    @SearchBys(value = {@SearchBy(nameOfTheLocator = "settingsBtn"), @SearchBy(nameOfTheLocator = "signInBtn"), @SearchBy(nameOfTheLocator = "googleApps")})
    protected WebElement rightMenuItemsFindBys;

    protected ByLocators byLocators;


    public SearchResultsPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
        byLocators = PageFactoryLoader.newInstance().initElements(ByLocators.class);
    }

    @FilePath(value = IPageObjects.SEARCH_RESULTS_PAGE)
    protected interface ByLocators {

        @SearchAll(value = {@SearchBy(nameOfTheLocator = "settingsBtn"), @SearchBy(nameOfTheLocator = "signInBtn"), @SearchBy(nameOfTheLocator = "invalidLocator")})
        By rightMenuItemsFindAll();

        @SearchBys(value = {@SearchBy(nameOfTheLocator = "settingsBtn"), @SearchBy(nameOfTheLocator = "signInBtn"), @SearchBy(nameOfTheLocator = "googleApps")})
        By rightMenuItemsFindBys();
    }
}
