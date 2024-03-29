package org.test.selenium.impl.properties.searchresults;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.FilePath;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.actions.searchresults.SearchResultsPageAssertions;
import org.test.selenium.pages.properties.searchresults.SearchResultsPageObjects;

import java.time.Duration;
import java.util.List;

@FilePath(value = "classpath:page_objects/properties/search_results_page_objects.properties")
public class SearchResultsPageAssertionsImpl extends SearchResultsPageObjects implements SearchResultsPageAssertions {

    protected final WebDriver driver;

    public SearchResultsPageAssertionsImpl(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
    }

    public SearchResultsPageAssertions and() {
        return this;
    }

    @Override
    public SoftAssertions iShouldSeeTheSearchResults() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(getSearchResults().size())
                .withFailMessage("Search Results are not displayed")
                .isGreaterThan(0);
        return softAssertions;
    }

    @Override
    public SoftAssertions iShouldSeeAllTheRightMenuItems(boolean isFindAll) {
        SoftAssertions softAssertions = new SoftAssertions();
        if (isFindAll) {
            softAssertions.assertThat(isDisplayed(rightMenuItemsFindAll)).isTrue();
            softAssertions.assertThat(isDisplayed(driver.findElement(byLocators.rightMenuItemsFindAll()))).isTrue();
        } else {
            softAssertions.assertThat(isDisplayed(rightMenuItemsFindBys)).isTrue();
            softAssertions.assertThat(isDisplayed(driver.findElement(byLocators.rightMenuItemsFindBys()))).isTrue();
        }
        return softAssertions;
    }

    private List<WebElement> getSearchResults() {
        waitForVisibilityOfElement(searchResultsSection, Duration.ofSeconds(10));
        return searchResults;
    }
}
