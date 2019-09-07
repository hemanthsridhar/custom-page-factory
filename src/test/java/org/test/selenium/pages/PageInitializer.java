package org.test.selenium.pages;

import org.test.selenium.base.DriverFactory;

public class PageInitializer extends DriverFactory {

    public LandingPage landingPage(){
        return new LandingPage(getDriver());
    }

    public InvalidPasswordPage homePage(){
        return new InvalidPasswordPage(getDriver());
    }
}
