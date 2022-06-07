package org.test.selenium.actions.landingpage;

import org.test.selenium.actions.searchresults.SearchResultsPageActions;

public interface LandingPageActions {

    LandingPageActions iSearchFor(String searchText);

    SearchResultsPageActions when();

    LandingPageAssertions then();

    LandingPageActions and();

    LandingPageActions iClickOnImagesLink();

}
