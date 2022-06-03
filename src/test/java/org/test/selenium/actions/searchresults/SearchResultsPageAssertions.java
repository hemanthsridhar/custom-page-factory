package org.test.selenium.actions.searchresults;

import org.assertj.core.api.SoftAssertions;
import org.test.selenium.pages.IPage;

public interface SearchResultsPageAssertions extends IPage {
    SoftAssertions iShouldSeeTheSearchResults();
}
