package org.test.selenium.actions.searchresults;


import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;

public interface SearchResultsPageActions {
    SearchResultsPageActions iAmInSearchResultsPage(String title);

    SearchResultsPageAssertions then();

    SearchResultsPageActions and();

}
