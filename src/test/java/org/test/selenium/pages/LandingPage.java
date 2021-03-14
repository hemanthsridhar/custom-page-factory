package org.test.selenium.pages;

import com.github.hemanthsridhar1992.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar1992.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar1992.support.SearchAll;
import com.github.hemanthsridhar1992.support.SearchBy;
import com.github.hemanthsridhar1992.support.SearchBys;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.constants.PageObjectsConfig;

import java.util.List;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class LandingPage extends PageInitializer {

    private WebDriver driver;
    @SearchBy(locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "password")
    private WebElement password;
    @SearchBy(locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "loginButton")
    private WebElement loginButton;
    @SearchBy(locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "email")
    private WebElement userName;

    @SearchAll(value = {
            @SearchBy(locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "allTextboxes"),
            @SearchBy(locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "something1")})
    private List<WebElement> allTextboxes;

    @SearchBys(value = {
            @SearchBy(locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "allTextboxes"),
            @SearchBy(locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "something1")})
    private List<WebElement> allTextboxesBys;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver)), this);
    }

    public LandingPage enterUserName(String userName) {
        this.userName.sendKeys(userName);
        return this;
    }

    public LandingPage enterPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public InvalidPasswordPage clickOnLogin() {
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
