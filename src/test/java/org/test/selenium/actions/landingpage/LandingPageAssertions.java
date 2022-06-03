package org.test.selenium.actions.landingpage;


import org.assertj.core.api.SoftAssertions;
import org.test.selenium.impl.landingpage.LandingPageAssertionsImpl;
import org.test.selenium.pages.IPage;

public interface LandingPageAssertions extends IPage {

    SoftAssertions iShouldSeeTheLink(String hyperLinkIdentifier);

    LandingPageAssertionsImpl and();

    SoftAssertions iShouldSeeImagesLandingPage();
}
