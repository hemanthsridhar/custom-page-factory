package org.test.selenium.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.test.selenium.pages.LandingPage;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class GoogleTest {

    private WebDriver driver;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://www.facebook.com");
    }

    @Test
    public void test1() throws Exception {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.printAllTextboxes().enterUserName("hemanth@gmail.com").enterPassword("test@123").clickOnLogin(driver).clickOnCreate();
    }

    @After
    public void after() {
        driver.quit();
    }

}
