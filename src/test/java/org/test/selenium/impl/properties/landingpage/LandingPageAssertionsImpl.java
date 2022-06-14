package org.test.selenium.impl.properties.landingpage;

import com.github.hemanthsridhar.support.FilePath;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.test.selenium.actions.landingpage.LandingPageAssertions;
import org.test.selenium.constants.properties.IPageObjects;
import org.test.selenium.pages.properties.landingpage.LandingPageObjects;

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
        waitForVisibilityOfElement(driver.findElement(byLocators.elementByText("images")), Duration.ofSeconds(10));
        softAssertions.assertThat(driver.getCurrentUrl()).contains("img");
        return softAssertions;
    }

    @Override
    public SoftAssertions iShouldSeeFooterLinks(String about, String advertising, String business, String howSearchWorks) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat
                (isDisplayed(driver.findElement(byLocators.multipleParamsLink(about, advertising, business, howSearchWorks))))
                .isTrue();
        return softAssertions;
    }

    @Override
    public SoftAssertions iShouldSeeShareButton() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isDisplayed(driver.findElement(byLocators.shareButton()))).isTrue();
        return softAssertions;
    }

    @Override
    public SoftAssertions iShouldSeeTheLink(String hyperLinkIdentifier) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(isDisplayed(driver.findElement(byLocators.linkByText(hyperLinkIdentifier))))
                .withFailMessage("Hyper link " + hyperLinkIdentifier + " is not displayed")
                .isTrue();
        softAssertions.assertThat(true).isTrue();
        return softAssertions;
    }
}
