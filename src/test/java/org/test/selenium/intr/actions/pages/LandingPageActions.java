package org.test.selenium.intr.actions.pages;


import org.test.selenium.intr.actions.expected.LoginExpectedActions;
import org.test.selenium.intr.commons.Commons;

public interface LandingPageActions extends Commons {

    LandingPageActions enterUsername(String username);

    LandingPageActions enterPassword(String password);

    LoginExpectedActions clickOnLogin();

}
