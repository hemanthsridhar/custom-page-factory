package org.test.selenium.commons.client;

import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.pages.properties.IPropPage;

/**
 * @author hemanthsridhar
 * @Date 06/06/22
 */
public interface PropUiClient extends IPropPage {
    LandingPageActions landingPageActions();
}
