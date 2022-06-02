package org.test.selenium.pages;

import org.test.selenium.actions.searchresults.SearchResultsPageActions;
import org.test.selenium.base.DriverFactory;
import org.test.selenium.impl.landingpage.LandingPageActionsImpl;
import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.impl.searchresults.SearchResultsPageActionsImpl;

public interface Pages {

    default LandingPageActions landingPageActions(){
        return new LandingPageActionsImpl(DriverFactory.getDriver());
    }

    default SearchResultsPageActions searchResultsPageActions(){
        return new SearchResultsPageActionsImpl(DriverFactory.getDriver());
    }
}
