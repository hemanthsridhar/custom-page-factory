package com.github.hemanthsridhar.utils;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import static com.github.hemanthsridhar.constants.MobileLocators.*;
import static com.github.hemanthsridhar.constants.WebLocators.*;

/**
 * @author hemanthsridhar
 */

public class LocatorStrategies {

    public By getLocator(String type, String locator) {
        By by;
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
        return by;
    }
}
