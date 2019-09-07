package org.test.selenium.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithJSON;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;

import java.util.List;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class LandingPage {

    public LandingPage(WebDriver driver){
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver)), this);
    }

    private static final String PAGE = "LandingPage";

    @SearchWithJSON(inPage = LandingPage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "email")
    private WebElement userName;

    @SearchWithJSON(inPage = LandingPage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "password")
    private WebElement password;

    @SearchWithJSON(inPage = LandingPage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "loginButton")
    private WebElement loginButton;

    @SearchWithJSON(inPage = LandingPage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "allTextboxes")
    private List<WebElement> allTextboxes;

    public LandingPage enterUserName(String userName) {
        this.userName.sendKeys(userName);
        return this;
    }

    public LandingPage enterPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public HomePage clickOnLogin(WebDriver driver) {
        loginButton.click();
        return new HomePage(driver);
    }

    public LandingPage printAllTextboxes(){
        System.out.println(allTextboxes.size());
        return this;
    }
}
