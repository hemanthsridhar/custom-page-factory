package org.test.selenium.impl;

import org.openqa.selenium.WebDriver;
import org.test.selenium.impl.steps.LandingPageStepsImpl;
import org.test.selenium.intr.ArtUiClient;
import org.test.selenium.intr.actions.LandingPageSteps;

public class ArtClientImpl implements ArtUiClient {

    private final WebDriver driver;

    public ArtClientImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public LandingPageSteps landingPageActions() {
        return new LandingPageStepsImpl(driver);
    }
}
