package org.test.selenium.actions.searchresults;


public interface SearchResultsPageActions {
    SearchResultsPageActions iAmInSearchResultsPage(String title);

    SearchResultsPageAssertions then();

    SearchResultsPageActions and();

}
