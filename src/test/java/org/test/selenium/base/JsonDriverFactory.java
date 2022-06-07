package org.test.selenium.base;

import org.test.selenium.commons.client.IUiClient;
import org.test.selenium.commons.client.JsonUiClient;

/**
 * @author hemanthsridhar
 *
 */
public class JsonDriverFactory extends DriverFactory<IUiClient> {

    @Override
    public JsonUiClient given(){
        return new JsonUiClient();
    }
}
