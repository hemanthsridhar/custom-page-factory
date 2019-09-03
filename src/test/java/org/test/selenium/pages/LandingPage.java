package org.test.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.test.selenium.factory.FileBasedElementLocatorFactory;
import org.test.selenium.factory.SearchWith;
import org.test.selenium.factory.SearchWithFieldDecorator;
import org.test.selenium.test.PropertyFileReader;

import java.util.List;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class LandingPage {

    public LandingPage(WebDriver driver){
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver)), this);
    }

    private static final String PAGE = "LandingPage";

    @SearchWith(inPage = LandingPage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "email")
    private WebElement userName;

    @SearchWith(inPage = LandingPage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "password")
    private WebElement password;

    @SearchWith(inPage = LandingPage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "loginButton")
    private WebElement loginButton;

    @SearchWith(inPage = LandingPage.PAGE, locatorsFile = "src/test/resources/pageobjects.json", nameOfTheLocator = "allTextboxes")
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
