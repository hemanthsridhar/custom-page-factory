package org.test.selenium.impl.client;

import org.openqa.selenium.WebDriver;
import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.commons.client.PropUiClient;
import org.test.selenium.impl.properties.landingpage.LandingPageActionsImpl;

public class PropertiesUiClientImpl implements PropUiClient {

    private final WebDriver driver;

    public PropertiesUiClientImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public LandingPageActions landingPageActions() {
        return new LandingPageActionsImpl(driver);
    }
}
