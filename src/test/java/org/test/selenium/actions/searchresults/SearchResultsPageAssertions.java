package org.test.selenium.actions.searchresults;

import org.assertj.core.api.SoftAssertions;
import org.test.selenium.actions.commons.Commons;

public interface SearchResultsPageAssertions extends Commons {
    SoftAssertions iShouldSeeTheSearchResults();
}
