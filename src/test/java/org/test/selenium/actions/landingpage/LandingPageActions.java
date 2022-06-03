package org.test.selenium.actions.landingpage;
import org.test.selenium.actions.searchresults.SearchResultsPageActions;
import org.test.selenium.pages.IPage;

public interface LandingPageActions extends IPage {

    LandingPageActions iSearchFor(String searchText);

    SearchResultsPageActions when();

    LandingPageAssertions then();

    LandingPageActions and();

    LandingPageActions iAmOnLandingPage();

    LandingPageActions iClickOnImagesLink();
}
