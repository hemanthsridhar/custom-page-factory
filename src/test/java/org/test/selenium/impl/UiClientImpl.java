package org.test.selenium.impl;

import org.openqa.selenium.WebDriver;
import org.test.selenium.commons.client.IUiClient;
import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.impl.landingpage.LandingPageActionsImpl;

public class UiClientImpl implements IUiClient {

    private final WebDriver driver;

    public UiClientImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public LandingPageActions landingPageActions() {
        return new LandingPageActionsImpl(driver);
    }
}
