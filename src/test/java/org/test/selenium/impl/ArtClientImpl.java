package org.test.selenium.impl;

import org.openqa.selenium.WebDriver;
import org.test.selenium.impl.actions.LandingPageActionsImpl;
import org.test.selenium.intr.ArtUiClient;
import org.test.selenium.intr.actions.pages.LandingPageActions;

public class ArtClientImpl implements ArtUiClient {

    private final WebDriver driver;

    public ArtClientImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public LandingPageActions landingPageActions() {
        return new LandingPageActionsImpl(driver);
    }
}
