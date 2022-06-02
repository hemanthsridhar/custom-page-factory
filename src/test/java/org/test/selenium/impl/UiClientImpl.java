package org.test.selenium.impl;

import org.openqa.selenium.WebDriver;
import org.test.selenium.impl.landingpage.LandingPageActionsImpl;
import org.test.selenium.actions.commons.UiClient;
import org.test.selenium.actions.landingpage.LandingPageActions;

public class UiClientImpl implements UiClient {

    private final WebDriver driver;

    public UiClientImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public LandingPageActions landingPageActions() {
        return new LandingPageActionsImpl(driver);
    }
}
