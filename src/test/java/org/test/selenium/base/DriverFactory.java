package org.test.selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.config.UiConfig;
import org.test.selenium.impl.UiClientImpl;

public class DriverFactory {

    private static WebDriver driver;
    private static LandingPageActions uiClient;

    public synchronized static WebDriver getDriver() {
        return driver;
    }

    private static void setDriver(WebDriver driver) {
        DriverFactory.driver = driver;
    }

    public UiConfig uiConfig;

    @BeforeEach
    void beforeEach() {
        uiConfig = ConfigFactory.create(UiConfig.class);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        setDriver(driver);
        driver.get(uiConfig.baseUrl());
        driver.manage().window().maximize();
        uiClient = new UiClientImpl(getDriver()).landingPageActions();
    }

    public LandingPageActions given(){
        return uiClient;
    }

    @AfterEach
    void afterEach() {
        getDriver().quit();
    }
}
