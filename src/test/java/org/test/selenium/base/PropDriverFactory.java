package org.test.selenium.base;

import org.junit.jupiter.api.BeforeEach;
import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.commons.client.PropUiClient;
import org.test.selenium.impl.client.UiClientImpl;

/**
 * @author hemanthsridhar
 * @Date 06/06/22
 */
public class PropDriverFactory extends DriverFactory<PropUiClient> {

    private static PropUiClient uiClient;

    @BeforeEach
    public void initClient(){
        uiClient = new UiClientImpl(getDriver()).properties();
    }

    @Override
    public PropUiClient uiClient(){
        return uiClient;
    }

    public LandingPageActions given() {
        return uiClient.landingPageActions();
    }
}
