package org.test.selenium.test;

import org.junit.jupiter.api.Test;
import org.test.selenium.base.DriverFactory;
import org.test.selenium.intr.actions.HomePageSteps;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by hemanthsridhar on 12/12/21.
 */
public class ArtifactoryLoginTest extends DriverFactory {

    @Test
    public void verifyIfUnAndPwdErrorIsDisplayed() throws Exception {
        List<String> actualErrorMsgs = artClient
                .clickOnLogin()
                .getErrorMessages();

        List<String> expectedErrorMsgs = new ArrayList<>();
        expectedErrorMsgs.add("Username is required");
        expectedErrorMsgs.add("Password is required");

        assertEquals(
                expectedErrorMsgs, actualErrorMsgs,"Error msgs are not displayed");
    }

    @Test
    public void verifyIfPwdErrorIsDisplayed() throws Exception {
        List<String> actualErrorMsgs = artClient
                .enterUsername(uiConfig.username())
                .clickOnLogin()
                .getErrorMessages();

        List<String> expectedErrorMsgs = new ArrayList<>();
        expectedErrorMsgs.add("Password is required");

        assertEquals(expectedErrorMsgs,actualErrorMsgs,"Password is required is not displayed");
    }

    @Test
    public void verifyIfUNameErrorIsDisplayed() throws Exception {
        List<String> actualErrorMsgs = artClient
                .enterPassword(uiConfig.username())
                .clickOnLogin()
                .getErrorMessages();

        List<String> expectedErrorMsgs = new ArrayList<>();
        expectedErrorMsgs.add("Username is required");

        assertEquals(expectedErrorMsgs,actualErrorMsgs,"Username is required is not displayed");
    }

    @Test
    public void verifyIfWelcomeMsgIsDisplayed() throws Exception {
        String userName = uiConfig.username();
        HomePageSteps homePage = artClient
                .enterUsername(userName)
                .enterPassword(uiConfig.password())
                .clickOnLogin().homePageActions();
        assertEquals("Welcome, "+userName,homePage.getWelcomeMessage(),"Welcome msg is not correct");
    }

    @Test
    public void verifyIfUpgradeButtonIsDisplayed() throws Exception {
        HomePageSteps homePage = artClient
                .enterUsername(uiConfig.username())
                .enterPassword(uiConfig.password())
                .clickOnLogin().homePageActions();
        assertTrue(homePage.isUpgradeButtonDisplayed(),"Upgrade button is not displayed.");
    }
}