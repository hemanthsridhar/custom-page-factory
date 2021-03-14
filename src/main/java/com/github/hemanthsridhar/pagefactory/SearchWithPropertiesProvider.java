package com.github.hemanthsridhar.pagefactory;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.hemanthsridhar.constants.MobileLocators.*;
import static com.github.hemanthsridhar.constants.WebLocators.*;

/**
 * Created by hemanthsridhar on 1/23/19.
 */
public class SearchWithPropertiesProvider {

    private static final Map<String, SearchWithPropertiesProvider> providers = new ConcurrentHashMap<>();

    //this map is file_path -> (name -> locator))
    private final Map<String, Map<String, By>> locators = new ConcurrentHashMap<>();
    private final String locatorsFile;

    public SearchWithPropertiesProvider(String locatorsFile) throws IllegalArgumentException {
        this.locatorsFile = locatorsFile;
        loadLocators(locatorsFile);
    }

    public SearchWithPropertiesProvider getPropertiesProvider() throws IllegalArgumentException {
        SearchWithPropertiesProvider provider;

        if (!providers.containsKey(locatorsFile)) {
            provider = new SearchWithPropertiesProvider(locatorsFile);
            providers.put(locatorsFile, provider);
        } else {
            provider = providers.get(locatorsFile);
        }

        return provider;
    }

    public By getLocator(String name) {
        Map<String, By> pageLocators = locators.get(name);
        By locator = pageLocators == null ? null : pageLocators.get(name);
        if (locator == null) {
            throw new IllegalArgumentException("Locator " + name + " does not exist.");
        }
        return locator;
    }

    private void loadLocators(String locatorsFile) throws IllegalArgumentException {
        try {

            Properties properties = new Properties();
            Map<String, By> pageLocators;
            By by;
            properties.load(new FileInputStream(locatorsFile));

            for (String key : properties.stringPropertyNames()) {
                String locator = properties.getProperty(key);
                int lastOccurenceOfUnderscore = key.lastIndexOf("_");
                String name = key.substring(0, lastOccurenceOfUnderscore); // TODO: user_name_xpath, name = user_name
                String type = key.substring(lastOccurenceOfUnderscore + 1);

                pageLocators = locators.get(name);
                if (pageLocators == null) {
                    pageLocators = new ConcurrentHashMap<>();
                    locators.put(name, pageLocators);
                }

                switch (type.toLowerCase()) {
                    case ID:
                        by = By.id(locator);
                        break;
                    case CSS_SELECTOR:
                    case CSS:
                        by = By.cssSelector(locator);
                        break;
                    case CLASS_NAME:
                        by = By.className(locator);
                        break;
                    case XPATH:
                        by = By.xpath(locator);
                        break;
                    case LINK_TEXT:
                        by = By.linkText(locator);
                        break;
                    case PARTIAL_LINK_TEXT:
                        by = By.partialLinkText(locator);
                        break;
                    case NAME:
                        by = By.name(locator);
                        break;
                    case TAG_NAME:
                        by = By.tagName(locator);
                        break;
                    case ACCESSIBILITY_ID:
                        by = MobileBy.AccessibilityId(locator);
                        break;
                    case UI_AUTOMATOR:
                    case ANDROID_UI_AUTOMATOR:
                        by = MobileBy.AndroidUIAutomator(locator);
                        break;
                    case IOS_CLASS_CHAIN:
                        by = MobileBy.iOSClassChain(locator);
                        break;
                    case ANDROID_VIEW_TAG:
                        by = MobileBy.AndroidViewTag(locator);
                        break;
                    case IOSN_S_PREDICATE_STRING:
                        by = MobileBy.iOSNsPredicateString(locator);
                        break;
                    case IMAGE:
                        by = MobileBy.image(locator);
                        break;
                    case WINDOWS_AUTOMATION:
                        by = MobileBy.windowsAutomation(locator);
                        break;
                    case ANDROID_DATA_MATCHER:
                        by = MobileBy.androidDataMatcher(locator);
                        break;
                    case ANDROID_VIEW_MATCHER:
                        by = MobileBy.androidViewMatcher(locator);
                        break;
                    case CUSTOM:
                        by = MobileBy.custom(locator);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported locator type - " + type);
                }
                pageLocators.put(name, by);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to find locators file " + locatorsFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading locators file " + locatorsFile);
        }
    }
}
