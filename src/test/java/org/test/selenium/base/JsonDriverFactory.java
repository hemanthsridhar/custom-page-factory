package org.test.selenium.base;

import org.junit.jupiter.api.BeforeEach;
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
    public JsonUiClient given(){
        return uiClient;
    }

}
