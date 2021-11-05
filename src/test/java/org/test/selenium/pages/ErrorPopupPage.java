package org.test.selenium.pages;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchBy;
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
@FilePath(value = PageObjectsConfig.ERROR_MSG_PAGE)
public class ErrorPopupPage extends PageInitializer {
    private WebDriver driver;

    @SearchBy
    private WebElement isThisYourAccount;

    public ErrorPopupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
    }

    public ErrorPopupPage verifyIsThisYourAccountText() throws Exception {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(isThisYourAccount));
        Assert.assertEquals("Is this your account text is not correct",
                "Is this your account?", isThisYourAccount.getText().trim());
        return this;
    }
}
