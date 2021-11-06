package org.test.selenium.pages;

import com.github.hemanthsridhar.pagefactory.FileBasedElementLocatorFactory;
import com.github.hemanthsridhar.pagefactory.SearchWithFieldDecorator;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.test.selenium.constants.PageObjectsConfig;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
@FilePath(value = PageObjectsConfig.INVALID_PATH_PAGE)
public class InvalidFilePathPage extends PageInitializer {
    private final WebDriver driver;

    @SearchBy
    private WebElement userName;

    public InvalidFilePathPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new SearchWithFieldDecorator(new FileBasedElementLocatorFactory(driver, this)), this);
    }

    public void enterUserName(String username) {
        userName.sendKeys(username);
    }
}
