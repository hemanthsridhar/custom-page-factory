package org.test.selenium.impl.actions;
import com.github.hemanthsridhar.support.FilePath;
import org.openqa.selenium.WebDriver;
import org.test.selenium.constants.json.PageObjectsConfig;
import org.test.selenium.intr.actions.pages.LandingPageActions;
import org.test.selenium.intr.actions.expected.LoginExpectedActions;
import org.test.selenium.pages.LandingPage;

import java.time.Duration;

@FilePath(value = PageObjectsConfig.LANDING_PAGE)
public class LandingPageActionsImpl extends LandingPage implements LandingPageActions {


    public LandingPageActionsImpl(WebDriver driver) {
        super(driver);
    }

    @Override
    public LandingPageActions enterUsername(String username) {
        waitForVisibilityOfElement(this.username, Duration.ofSeconds(30));
        this.username.sendKeys(username);
        return this;
    }

    @Override
    public LandingPageActions enterPassword(String password) {
        waitForVisibilityOfElement(this.password, Duration.ofSeconds(30));
        this.password.sendKeys(password);
        return this;
    }

    @Override
    public LoginExpectedActions clickOnLogin() {
        waitForVisibilityOfElement(this.loginButton, Duration.ofSeconds(30));
        waitForElementToBeClickable(this.loginButton,Duration.ofSeconds(30));
        loginButton.click();
        return new LoginExpectedActionsImpl(driver);
    }
}
