package org.test.selenium.pages;

import org.test.selenium.base.DriverFactory;

public class PageInitializer extends DriverFactory {

    public LandingPage landingPage() {
        return new LandingPage(getDriver());
    }

    public ErrorPopupPage errorPopupPage() {
        return new ErrorPopupPage(getDriver());
    }

    public InvalidFilePathPage invalidFilePathPage() {
        return new InvalidFilePathPage(getDriver());
    }

    public InvalidSearchByDefaultValuePage invalidSearchByPage() {
        return new InvalidSearchByDefaultValuePage(getDriver());
    }

    public InvalidSearchByNameOfLocatorPage invalidSearchByNameOfLocatorPage() {
        return new InvalidSearchByNameOfLocatorPage(getDriver());
    }
}
