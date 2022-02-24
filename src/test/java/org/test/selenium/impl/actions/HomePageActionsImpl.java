package org.test.selenium.impl.actions;

import com.github.hemanthsridhar.support.FilePath;
import org.openqa.selenium.WebDriver;
import org.test.selenium.constants.json.PageObjectsConfig;
import org.test.selenium.intr.actions.pages.HomePageActions;
import org.test.selenium.pages.HomePage;

import java.time.Duration;

@FilePath(value = PageObjectsConfig.HOME_PAGE)
public class HomePageActionsImpl extends HomePage implements HomePageActions {

    public HomePageActionsImpl(WebDriver driver) {
        super(driver);
    }


    @Override
    public String getWelcomeMessage() {
        waitForVisibilityOfElement(welcomeMessage, Duration.ofSeconds(30));
        return welcomeMessage.getText().trim();
    }

    @Override
    public boolean isUpgradeButtonDisplayed() {
        return isDisplayed(upgradeButton, Duration.ofSeconds(60));
    }
}
