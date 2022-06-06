package org.test.selenium.commons.client;

import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.pages.json.IJsonPage;

/**
 * @author hemanthsridhar
 *
 */
public interface JsonUiClient extends IJsonPage {
    LandingPageActions landingPageActions();
}
