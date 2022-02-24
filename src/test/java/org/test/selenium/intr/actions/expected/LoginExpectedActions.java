package org.test.selenium.intr.actions.expected;

import org.test.selenium.intr.actions.pages.HomePageActions;
import org.test.selenium.intr.commons.Commons;

import java.util.List;

public interface LoginExpectedActions extends Commons {

    List<String> getErrorMessages();

    HomePageActions homePage();


}
