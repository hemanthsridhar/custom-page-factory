package org.test.selenium.impl.actions;

import com.github.hemanthsridhar.support.FilePath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.test.selenium.constants.json.PageObjectsConfig;
import org.test.selenium.intr.actions.pages.HomePageActions;
import org.test.selenium.intr.actions.expected.LoginExpectedActions;
import org.test.selenium.pages.LandingPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@FilePath(value = PageObjectsConfig.LANDING_PAGE)
public class LoginExpectedActionsImpl extends LandingPage implements LoginExpectedActions {

    public LoginExpectedActionsImpl(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<String> getErrorMessages() {
        List<String> actualErrorMessages = new ArrayList<>();
        waitForVisibilityOfElements(errorMsgs, Duration.ofSeconds(90));
        for (WebElement errorMsg : errorMsgs) {
            actualErrorMessages.add(errorMsg.getText().trim());
        }
        return actualErrorMessages;
    }

    @Override
    public HomePageActions homePage() {
        return new HomePageActionsImpl(driver);
    }
}
