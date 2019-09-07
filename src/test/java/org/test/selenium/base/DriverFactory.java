package org.test.selenium.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static WebDriver driver;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        setDriver(driver);
        driver.get("http://www.facebook.com");
    }

    public synchronized static WebDriver getDriver(){
        return driver;
    }

    private void setDriver(WebDriver driver){
        DriverFactory.driver = driver;
    }

    @After
    public void after() {
        getDriver().quit();
    }
}
