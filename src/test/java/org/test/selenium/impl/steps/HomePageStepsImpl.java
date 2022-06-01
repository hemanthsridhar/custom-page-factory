package org.test.selenium.impl.steps;

import com.github.hemanthsridhar.support.FilePath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.test.selenium.constants.json.PageObjectsConfig;
import org.test.selenium.intr.actions.HomePageSteps;
import org.test.selenium.pages.HomePageObjects;

import java.time.Duration;

@FilePath(value = PageObjectsConfig.HOME_PAGE)
public class HomePageStepsImpl extends HomePageObjects implements HomePageSteps{


    public HomePageStepsImpl(WebDriver driver) {
        super(driver);
    }

    private String data;

    public void setData(String data){
        this.data = data;
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
