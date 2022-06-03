package org.test.selenium.actions.searchresults;

import org.test.selenium.pages.IPage;

public interface SearchResultsPageActions extends IPage {
    SearchResultsPageActions iAmInSearchResultsPage(String title);

    SearchResultsPageAssertions then();

    SearchResultsPageActions and();

}
