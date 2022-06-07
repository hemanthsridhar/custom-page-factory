package org.test.selenium.pages.json;

import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.actions.searchresults.SearchResultsPageActions;
import org.test.selenium.base.DriverFactory;
import org.test.selenium.impl.json.landingpage.LandingPageActionsImpl;
import org.test.selenium.impl.json.searchresults.SearchResultsPageActionsImpl;

public interface IJsonPage {

    default LandingPageActions inLandingPage() {
        return new LandingPageActionsImpl(DriverFactory.getDriver());
    }

    default SearchResultsPageActions inSearchResultsPage() {
        return new SearchResultsPageActionsImpl(DriverFactory.getDriver());
    }
}
