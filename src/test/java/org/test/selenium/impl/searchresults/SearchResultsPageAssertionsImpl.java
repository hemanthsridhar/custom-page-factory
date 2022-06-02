package org.test.selenium.impl.searchresults;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.FilePath;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.actions.commons.ExplicitWaits;
import org.test.selenium.actions.searchresults.SearchResultsPageAssertions;
import org.test.selenium.constants.json.PageObjectsConfig;
import org.test.selenium.pages.searchresults.SearchResultsPageObjects;

import java.time.Duration;
import java.util.List;

@FilePath(value = PageObjectsConfig.SEARCH_RESULTS_PAGE)
public class SearchResultsPageAssertionsImpl extends SearchResultsPageObjects implements SearchResultsPageAssertions {

    protected final WebDriver driver;

    public SearchResultsPageAssertionsImpl(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
    }

    public SearchResultsPageAssertions and(){
        return this;
    }

    @Override
    public SoftAssertions iShouldSeeTheSearchResults() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(getSearchResults().size())
                .withFailMessage("Search Results are not displayed")
                .isGreaterThan(0);
        return softAssertions;
    }

    private List<WebElement> getSearchResults() {
        waitForVisibilityOfElement(searchResultsSection,Duration.ofSeconds(10));
        return searchResults;
    }
}
