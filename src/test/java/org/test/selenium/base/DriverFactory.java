package org.test.selenium.base;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.test.selenium.config.UiConfig;
import org.test.selenium.impl.ArtClientImpl;
import org.test.selenium.intr.actions.pages.LandingPageActions;

public class DriverFactory {

    public static LandingPageActions artClient;
    private static WebDriver driver;
    public UiConfig uiConfig;

    public synchronized static WebDriver getDriver() {
        return driver;
    }

    private static void setDriver(WebDriver driver) {
        DriverFactory.driver = driver;
    }

    @BeforeEach
    void beforeEach() {
        uiConfig = ConfigFactory.create(UiConfig.class);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        setDriver(driver);
        driver.get(uiConfig.baseUrl());
        artClient = new ArtClientImpl(getDriver()).landingPageActions();
    }

    @AfterEach
    void afterEach() {
        getDriver().quit();
    }
}
