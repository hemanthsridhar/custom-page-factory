package org.test.selenium.test;

import org.junit.Test;
import org.test.selenium.pages.PageInitializer;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class FBTest extends PageInitializer {

    @Test
    public void test1() throws Exception {
        landingPage()
                .verifyNumberOfTextboxesForSearchAllAndSearchBys()
                .enterUserName("bla@gmail.com").enterPassword("bla")
                .clickOnLogin();
                //.verifyIfForgotPasswordLinkIsDisplayed();
    }
}
