package org.test.selenium.actions.landingpage;


import org.assertj.core.api.SoftAssertions;
import org.test.selenium.actions.commons.Commons;
import org.test.selenium.impl.landingpage.LandingPageAssertionsImpl;

public interface LandingPageAssertions extends Commons{

    SoftAssertions iShouldSeeTheLink(String hyperLinkIdentifier);

    LandingPageAssertionsImpl and();

    SoftAssertions iShouldSeeImagesLandingPage();
}
