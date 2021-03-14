package org.test.selenium.pages;

import com.github.hemanthsridhar1992.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar1992.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar1992.support.SearchBy;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.selenium.constants.PageObjectsConfig;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class InvalidPasswordPage extends PageInitializer {
    private WebDriver driver;
    @SearchBy(locatorsFile = PageObjectsConfig.ERROR_MSG_PAGE, nameOfTheLocator = "forgottenPasswordLinkInAlert")
    private WebElement forgottenPasswordLinkInAlert;

    public InvalidPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver)), this);
    }

    public InvalidPasswordPage verifyIfForgotPasswordLinkIsDisplayed() throws Exception {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(forgottenPasswordLinkInAlert));
        Assert.assertEquals("Forgotten password link's text is not correct",
                "Forgotten password?", forgottenPasswordLinkInAlert.getText().trim());
        return this;
    }
}
