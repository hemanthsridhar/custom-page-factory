package org.test.selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.test.selenium.actions.landingpage.LandingPageActions;
import org.test.selenium.commons.client.JsonUiClient;
import org.test.selenium.commons.client.PropUiClient;
import org.test.selenium.config.UiConfig;
import org.test.selenium.impl.client.UiClientImpl;

public abstract class DriverFactory<T> {

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
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        setDriver(driver);
        driver.get(uiConfig.baseUrl());
        driver.manage().window().maximize();
    }

    @AfterEach
    void afterEach() {
        getDriver().quit();
    }

    abstract T uiClient();

}
