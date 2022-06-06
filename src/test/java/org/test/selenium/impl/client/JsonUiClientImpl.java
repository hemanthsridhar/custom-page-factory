package org.test.selenium.impl.client;

import org.openqa.selenium.WebDriver;
import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.commons.client.JsonUiClient;
import org.test.selenium.impl.json.landingpage.LandingPageActionsImpl;

public class JsonUiClientImpl implements JsonUiClient {

    private final WebDriver driver;

    public JsonUiClientImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public LandingPageActions landingPageActions() {
        return new LandingPageActionsImpl(driver);
    }
}
