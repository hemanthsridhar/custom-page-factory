package org.test.selenium.pages;

import org.test.selenium.base.DriverFactory;
import org.test.selenium.impl.steps.HomePageStepsImpl;
import org.test.selenium.impl.steps.LandingPageStepsImpl;
import org.test.selenium.intr.actions.HomePageSteps;
import org.test.selenium.intr.actions.LandingPageSteps;

public interface Pages {

    default LandingPageSteps landingPageActions(){
        return new LandingPageStepsImpl(DriverFactory.getDriver());
    }

    default HomePageSteps homePageActions(){
        return new HomePageStepsImpl(DriverFactory.getDriver());
    }
}
