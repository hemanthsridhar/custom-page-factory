package org.test.selenium.actions.landingpage;


import org.assertj.core.api.SoftAssertions;

public interface LandingPageAssertions {

    SoftAssertions iShouldSeeTheLink(String hyperLinkIdentifier);

    LandingPageAssertions and();

    SoftAssertions iShouldSeeImagesLandingPage() throws InterruptedException;

    SoftAssertions iShouldSeeFooterLinks(String about, String advertising, String business, String howSearchWorks);

    SoftAssertions iShouldSeeShareButton();
}
