package org.test.selenium.pages;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.PageFactoryLoader;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.constants.json.PageObjectsConfig;

import java.util.List;

public class LandingPageObjects {

    protected final WebDriver driver;
    @SearchBy
    public List<WebElement> errorMsgs;
    public DynamicPageObjects homePageDynamicPageObjects;
    @SearchBy
    protected WebElement password;
    @SearchBy
    protected WebElement loginButton;
    @SearchBy
    protected WebElement username;


    public LandingPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
        homePageDynamicPageObjects = PageFactoryLoader.newInstance().initElements(DynamicPageObjects.class);
    }

    @FilePath(value = PageObjectsConfig.LANDING_PAGE)
    public interface DynamicPageObjects {

        @SearchBy
        By getHyperLinkText();

    }
}
