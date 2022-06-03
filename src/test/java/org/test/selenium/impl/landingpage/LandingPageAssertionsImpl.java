package org.test.selenium.impl.landingpage;

import com.github.hemanthsridhar.support.FilePath;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.test.selenium.actions.landingpage.LandingPageAssertions;
import org.test.selenium.constants.json.IPageObjects;
import org.test.selenium.pages.landingpage.LandingPageObjects;

import java.time.Duration;

@FilePath(value = IPageObjects.LANDING_PAGE)
public class LandingPageAssertionsImpl extends LandingPageObjects implements LandingPageAssertions {

    public LandingPageAssertionsImpl(WebDriver driver) {
        super(driver);
    }

    @Override
    public LandingPageAssertionsImpl and() {
        return this;
    }

    @Override
    public SoftAssertions iShouldSeeImagesLandingPage() {
     SoftAssertions softAssertions = new SoftAssertions();
     waitForVisibilityOfElement(imagesText, Duration.ofSeconds(10));
     softAssertions.assertThat(driver.getCurrentUrl()).contains("img");
     return softAssertions;
    }

    @Override
    public SoftAssertions iShouldSeeTheLink(String hyperLinkIdentifier) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isDisplayed(driver.findElement(By.xpath(String.format(hyperLinkElement, hyperLinkIdentifier)))))
                .withFailMessage("Hyper link "+hyperLinkIdentifier + " is not displayed")
                .isTrue();
        return softAssertions;
    }
}
