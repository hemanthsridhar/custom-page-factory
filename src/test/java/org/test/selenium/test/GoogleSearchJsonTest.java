package org.test.selenium.test;

import org.junit.jupiter.api.Test;
import org.test.selenium.base.JsonDriverFactory;

/**
 * @author hemanthsridhar
 *
 */
public class GoogleSearchJsonTest extends JsonDriverFactory {


    @Test
    public void verifyIfGmailLinkIsDisplayed() {
                given()
                        .inLandingPage()
                        .then()
                        .iShouldSeeTheLink("Gmail").assertAll();
    }

    @Test
    public void verifyIfFooterLinksExists() {
                given()
                        .inLandingPage()
                        .then()
                        .iShouldSeeFooterLinks("About","Advertising","Business","How Search works")
                .assertAll();
    }

    @Test
    public void verifyIfIAmAbleToSearch() {
        String searchText = "custom-page-factory";
         given()
                .inLandingPage()
                .iSearchFor(searchText)
                .when()
                .iAmInSearchResultsPage(searchText)
                .then()
                .iShouldSeeTheSearchResults().assertAll();
    }

    @Test
    public void findAllTest(){
        String searchText = "custom-page-factory";
        given()
                .inLandingPage()
                .iSearchFor(searchText)
                .when()
                .iAmInSearchResultsPage(searchText)
                .then()
                .iShouldSeeAllTheRightMenuItems(true).assertAll();
    }

    @Test
    public void findBysTest(){
        String searchText = "custom-page-factory";
        given()
                .inLandingPage()
                .iSearchFor(searchText)
                .when()
                .iAmInSearchResultsPage(searchText)
                .then()
                .iShouldSeeAllTheRightMenuItems(false).assertAll();
    }


    @Test
    public void relativeLocatorsTest() throws InterruptedException {
        given()
                .inLandingPage()
                .iClickOnImagesLink()
                .then()
                .iShouldSeeImagesLandingPage().assertAll();
    }
}