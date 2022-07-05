package org.test.selenium.pages.properties.landingpage;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.pagefactory.dynamic.PageFactoryLoader;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.commons.ICommons;

public class LandingPageObjects implements ICommons {

    protected final WebDriver driver;

    protected ByLocators byLocators;

    @SearchBy
    protected WebElement searchTextBox;


    public LandingPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
        byLocators = PageFactoryLoader.newInstance().initElements(ByLocators.class);
    }

    public LandingPageObjects enterSearchText(String searchText) {
        searchTextBox.click();
        searchTextBox.sendKeys(searchText);
        searchTextBox.sendKeys(Keys.ENTER);
        return this;
    }

    @FilePath(value = "classpath:page_objects/properties/landing_page_objects.properties")
    protected interface ByLocators {

        @SearchBy
        By linkByText(String text);

        @SearchBy
        By elementByText(String text);

        @SearchBy
        By multipleParamsLink(String about, String advertising, String business, String howSearchWorks);

        @SearchBy
        By shareButton();
    }
}
