package org.test.selenium.intr.actions;


import org.test.selenium.intr.commons.Commons;

import java.util.List;

public interface LandingPageSteps extends Commons {

    LandingPageSteps enterUsername(String username);

    LandingPageSteps enterPassword(String password);

    LandingPageSteps clickOnLogin();

    List<String> getErrorMessages();

    String getLinkText(String text);
}
