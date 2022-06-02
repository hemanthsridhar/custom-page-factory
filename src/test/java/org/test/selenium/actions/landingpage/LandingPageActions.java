package org.test.selenium.actions.landingpage;


import org.test.selenium.actions.commons.Commons;
import org.test.selenium.actions.searchresults.SearchResultsPageActions;

public interface LandingPageActions extends Commons {

    LandingPageActions iSearchFor(String searchText);

    SearchResultsPageActions when();

    LandingPageAssertions then();

    LandingPageActions and();

    LandingPageActions iAmOnLandingPage();

    LandingPageActions iClickOnImagesLink();
}
