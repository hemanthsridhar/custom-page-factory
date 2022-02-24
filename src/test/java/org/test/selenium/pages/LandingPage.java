package org.test.selenium.pages;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPage {

    protected final WebDriver driver;
    @SearchBy
    public List<WebElement> errorMsgs;
    @SearchBy
    protected WebElement password;
    @SearchBy
    protected WebElement loginButton;
    @SearchBy
    protected WebElement username;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
    }
}
