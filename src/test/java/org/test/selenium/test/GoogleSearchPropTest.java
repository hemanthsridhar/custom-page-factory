package org.test.selenium.test;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.test.selenium.base.PropDriverFactory;

/**
 * @author hemanthsridhar
 * @Date 06/06/22
 */
public class GoogleSearchPropTest extends PropDriverFactory {


    @Test
    public void verifyIfGmailLinkIsDisplayed() {
                given()
                        .iAmOnLandingPage()
                        .then()
                        .iShouldSeeTheLink("Gmail").assertAll();
        uiClient().landingPageActions().then().iShouldSeeShareButton().assertAll();
    }

    @Test
    public void verifyIfFooterLinksExists() {
                given()
                        .iAmOnLandingPage()
                        .then()
                        .iShouldSeeFooterLinks("About","Advertising","Business","How Search works")
                        .assertAll();
    }

    @Test
    public void verifyIfIAmAbleToSearch() {
        String searchText = "custom-page-factory";
        given()
                .iSearchFor(searchText)
                .when()
                .iAmInSearchResultsPage(searchText)
                .then()
                .iShouldSeeTheSearchResults().assertAll();
    }

    @Test
    public void relativeLocatorsTest() throws InterruptedException {
        given()
                .iClickOnImagesLink()
                .then()
                .iShouldSeeImagesLandingPage()
                .assertAll();
    }
}