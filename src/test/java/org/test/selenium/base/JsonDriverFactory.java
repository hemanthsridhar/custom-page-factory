package org.test.selenium.base;

import org.junit.jupiter.api.BeforeEach;
import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.commons.client.JsonUiClient;
import org.test.selenium.impl.client.UiClientImpl;

/**
 * @author hemanthsridhar
 *
 */
public class JsonDriverFactory extends DriverFactory<JsonUiClient> {

    private static JsonUiClient uiClient;

    @BeforeEach
    public void initClient(){
        uiClient = new UiClientImpl(getDriver()).json();
    }

    @Override
    public JsonUiClient uiClient(){
        return uiClient;
    }


    public LandingPageActions given() {
        return uiClient.landingPageActions();
    }

}
