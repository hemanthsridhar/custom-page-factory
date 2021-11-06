package org.test.selenium.pages;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchAll;
import com.github.hemanthsridhar.support.SearchBy;
import com.github.hemanthsridhar.support.SearchBys;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.constants.PageObjectsConfig;

import java.util.List;

/**
 * Created by hemanthsridhar on 1/6/19.
 */

@FilePath(value = PageObjectsConfig.LANDING_PAGE)
public class LandingPage extends PageInitializer {

    private final WebDriver driver;
    @SearchBy
    private WebElement password;
    @SearchBy
    private WebElement loginButton;
    @SearchBy
    private WebElement userName;

    @SearchAll(value = {
            @SearchBy(nameOfTheLocator = "allTextboxes"),
            @SearchBy(nameOfTheLocator = "something1")})
    private List<WebElement> allTextboxes;

    @SearchBys(value = {
            @SearchBy(nameOfTheLocator = "allTextboxes"),
            @SearchBy(nameOfTheLocator = "something1")})
    private List<WebElement> allTextboxesBys;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
    }

    public LandingPage enterUserName(String userName) {
        this.userName.sendKeys(userName);
        return this;
    }

    public LandingPage enterPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public ErrorPopupPage clickOnLogin() {
        loginButton.click();
        return invalidPasswordPage();
    }

    public LandingPage verifyNumberOfTextboxesForSearchAllAndSearchBys() {
        Assert.assertTrue("All text boxes from find all is 0. Please check the locator or the implementation of SearchAll",
                allTextboxes.size() > 0);
        Assert
                .assertEquals("All text boxes from find bys is not 0. Please check the locator or the implementation of SearchBys",
                        0, allTextboxesBys.size());
        return this;
    }
}
