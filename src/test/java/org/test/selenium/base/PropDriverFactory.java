package org.test.selenium.base;

import org.junit.jupiter.api.BeforeEach;
import org.test.selenium.commons.client.PropUiClient;
import org.test.selenium.impl.client.UiClientImpl;

/**
 * @author hemanthsridhar
 *
 */
public class PropDriverFactory extends DriverFactory<PropUiClient> {

    private static PropUiClient uiClient;

    @BeforeEach
    public void initClient(){
        uiClient = new UiClientImpl(getDriver()).properties();
    }

    @Override
    public PropUiClient given(){
        return uiClient;
    }

}
