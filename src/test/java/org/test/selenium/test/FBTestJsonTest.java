package org.test.selenium.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.test.selenium.pages.json.PageInitializer;

/**
 * Created by hemanthsridhar on 12/12/21.
 */
public class FBTestJsonTest extends PageInitializer {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void happyFlow() throws Exception {
        landingPage()
                .verifyNumberOfTextboxesForSearchAllAndSearchBys()
                .enterUserName("bla@gmail.com").enterPassword("bla")
                .clickOnLogin()
                .verifyIsThisYourAccountText();
    }

    @Test
    public void whenSearchByDoesNotExist() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Locator doesNotExistInLandingPageObjectsJsonFile does not exist.");
        invalidSearchByPage().searchByInvalidDefault();
    }

    @Test
    public void nameOfTheLocatorIsWrongInSearchBy() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Locator doesNotExist does not exist.");
        invalidSearchByNameOfLocatorPage().searchByInvalidNameOfLocator();
    }

    @Test
    public void whenFilePathIsWrong() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Unable to find locators file src/test/resources/page_objects/json/does_not_exist.json");
        invalidFilePathPage().enterUserName("test");
    }


}
