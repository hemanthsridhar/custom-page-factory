package org.test.selenium.commons.client;

import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.pages.IPage;

public interface IUiClient extends IPage {

    LandingPageActions landingPageActions();

}
