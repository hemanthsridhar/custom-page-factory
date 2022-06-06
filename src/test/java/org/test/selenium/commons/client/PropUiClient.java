package org.test.selenium.commons.client;

import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.pages.properties.IPropPage;

/**
 * @author hemanthsridhar
 *
 */
public interface PropUiClient extends IPropPage {
    LandingPageActions landingPageActions();
}
