package org.test.selenium.actions.searchresults;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;

public interface SearchResultsPageAssertions {
    SoftAssertions iShouldSeeTheSearchResults();

    SoftAssertions iShouldSeeAllTheRightMenuItems(boolean isFindAll);
}
