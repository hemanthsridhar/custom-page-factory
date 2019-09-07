package org.test.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithJSON;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import org.test.selenium.constants.PageObjectsConfig;

import java.util.List;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class LandingPage extends PageInitializer{

    private WebDriver driver;
    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver)), this);
    }

    @SearchWithJSON( locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "email")
    private WebElement userName;

    @SearchWithJSON( locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "password")
    private WebElement password;

    @SearchWithJSON( locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "loginButton")
    private WebElement loginButton;

    @SearchWithJSON( locatorsFile = PageObjectsConfig.LANDING_PAGE, nameOfTheLocator = "allTextboxes")
    private List<WebElement> allTextboxes;

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
        return homePage();
    }

    public LandingPage printAllTextboxes(){
        System.out.println(allTextboxes.size());
        return this;
    }
}
