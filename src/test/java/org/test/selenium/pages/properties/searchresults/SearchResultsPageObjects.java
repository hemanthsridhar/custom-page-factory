package org.test.selenium.pages.properties.searchresults;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.commons.ICommons;

import java.util.List;

public class SearchResultsPageObjects implements ICommons {

    protected final WebDriver driver;

    @SearchBy
    protected List<WebElement> searchResults;

    @SearchBy
    protected WebElement searchResultsSection;


    public SearchResultsPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
    }
}
