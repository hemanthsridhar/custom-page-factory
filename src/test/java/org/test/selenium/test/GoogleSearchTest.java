package org.test.selenium.test;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.test.selenium.base.DriverFactory;

/**
 * Created by hemanthsridhar on 12/12/21.
 */
public class GoogleSearchTest extends DriverFactory {

    @Test
    public void verifyIfGmailLinkIsDisplayed() {
        SoftAssertions softAssertions =
                given()
                .iAmOnLandingPage()
                        .then()
                        .iShouldSeeTheLink("Gmail");
        softAssertions.assertAll();
    }

    @Test
    public void verifyIfIAmAbleToSearch(){
        String searchText = "custom-page-factory";
        SoftAssertions softAssertions = given()
                .iSearchFor(searchText)
                .when()
                .iAmInSearchResultsPage(searchText)
                .then()
                .iShouldSeeTheSearchResults();
        softAssertions.assertAll();
    }

    @Test
    public void relativeLocatorsTest(){
        SoftAssertions softAssertions = given()
                .iClickOnImagesLink()
                .then()
                .iShouldSeeImagesLandingPage();
        softAssertions.assertAll();
    }
}