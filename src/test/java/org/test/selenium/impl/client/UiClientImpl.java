package org.test.selenium.impl.client;

import org.openqa.selenium.WebDriver;
import org.test.selenium.commons.client.IUiClient;
import org.test.selenium.commons.client.JsonUiClient;
import org.test.selenium.commons.client.PropUiClient;

public class UiClientImpl implements IUiClient {

    private final WebDriver driver;

    public UiClientImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public JsonUiClient json() {
        return new JsonUiClientImpl(driver);
    }

    @Override
    public PropUiClient properties() {
        return new PropertiesUiClientImpl(driver);
    }
}
