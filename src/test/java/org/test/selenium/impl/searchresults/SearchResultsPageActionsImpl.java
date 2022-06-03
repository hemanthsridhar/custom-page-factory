package org.test.selenium.impl.searchresults;
import com.github.hemanthsridhar.support.FilePath;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.test.selenium.actions.searchresults.SearchResultsPageActions;
import org.test.selenium.actions.searchresults.SearchResultsPageAssertions;
import org.test.selenium.constants.json.IPageObjects;
import org.test.selenium.pages.searchresults.SearchResultsPageObjects;

@FilePath(value = IPageObjects.SEARCH_RESULTS_PAGE)
public class SearchResultsPageActionsImpl extends SearchResultsPageObjects implements SearchResultsPageActions {

    public SearchResultsPageActionsImpl(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchResultsPageActions iAmInSearchResultsPage(String title) {
        String expectedTitle = title + " - Google Search";
        Assertions.assertThat(driver.getTitle().trim())
                .withFailMessage("Title is not "+expectedTitle)
                .isEqualTo(expectedTitle);
        return this;
    }

    @Override
    public SearchResultsPageAssertions then() {
        return new SearchResultsPageAssertionsImpl(driver);
    }

    @Override
    public SearchResultsPageActions and() {
        return this;
    }

}
