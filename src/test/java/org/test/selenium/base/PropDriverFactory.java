package org.test.selenium.base;

import org.test.selenium.commons.client.IUiClient;
import org.test.selenium.commons.client.PropUiClient;

/**
 * @author hemanthsridhar
 */
public class PropDriverFactory extends DriverFactory<IUiClient> {

    @Override
    public PropUiClient given() {
        return new PropUiClient();
    }

}
