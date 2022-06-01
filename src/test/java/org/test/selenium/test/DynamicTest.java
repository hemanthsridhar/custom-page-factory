package org.test.selenium.test;

import org.junit.jupiter.api.Test;
import org.test.selenium.base.DriverFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicTest extends DriverFactory {

    @Test
    public void verifyIfGmailTextIsDisplayed() throws Exception {
        assertEquals(artClient.getLinkText("Gmail"),"Gmail");
    }
}
