package org.test.selenium.actions.searchresults;

import org.assertj.core.api.Java6BDDSoftAssertionsProvider;
import org.test.selenium.actions.commons.Commons;

public interface SearchResultsPageActions extends Commons {
    SearchResultsPageActions iAmInSearchResultsPage(String title);

    SearchResultsPageAssertions then();

    SearchResultsPageActions and();

}
