package org.test.selenium.impl.steps;
import com.github.hemanthsridhar.support.FilePath;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.test.selenium.constants.json.PageObjectsConfig;
import org.test.selenium.intr.actions.LandingPageSteps;
import org.test.selenium.pages.LandingPageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@FilePath(value = PageObjectsConfig.LANDING_PAGE)
public class LandingPageStepsImpl extends LandingPageObjects implements LandingPageSteps {


    public LandingPageStepsImpl(WebDriver driver) {
        super(driver);
    }

    @Override
    public LandingPageSteps enterUsername(String username) {
        waitForVisibilityOfElement(this.username, Duration.ofSeconds(30));
        this.username.sendKeys(username);
        return this;
    }

    @Override
    public LandingPageSteps enterPassword(String password) {
        waitForVisibilityOfElement(this.password, Duration.ofSeconds(30));
        this.password.sendKeys(password);
        return this;
    }

    @Override
    public LandingPageSteps clickOnLogin() {
        waitForVisibilityOfElement(this.loginButton, Duration.ofSeconds(30));
        waitForElementToBeClickable(this.loginButton,Duration.ofSeconds(30));
        try {
            this.loginButton.click();
        }
        catch (ElementClickInterceptedException e){
            ((JavascriptExecutor)(driver)).executeScript("arguments[0].click();",this.loginButton);
        }
        return this;
    }

    public List<String> getErrorMessages() {
        List<String> actualErrorMessages = new ArrayList<>();
        waitForVisibilityOfElements(errorMsgs, Duration.ofSeconds(90));
        for (WebElement errorMsg : errorMsgs) {
            actualErrorMessages.add(errorMsg.getText().trim());
        }
        return actualErrorMessages;
    }
}
