package org.test.selenium.pages.landingpage;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.PageFactoryLoader;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.constants.json.PageObjectsConfig;

public class LandingPageObjects {

    protected final WebDriver driver;

    public ByLocatorsPageObjects byLocators;

    @SearchBy
    protected WebElement searchTextBox;

    protected String hyperLinkElement = "//a[text()='%s']";

    @SearchBy
    protected WebElement imagesText;


    public LandingPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
        byLocators = PageFactoryLoader.newInstance().initElements(ByLocatorsPageObjects.class);
    }

      public LandingPageObjects enterSearchText(String searchText) {
        searchTextBox.click();
        searchTextBox.sendKeys(searchText);
        searchTextBox.sendKeys(Keys.ENTER);
        return this;
    }

    @FilePath(value = PageObjectsConfig.LANDING_PAGE)
    public interface ByLocatorsPageObjects {
        @SearchBy
        By gmailLink();

        @SearchBy
        By imagesLink();
    }
}