package com.github.hemanthsridhar.pagefactory;

import com.google.gson.*;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.hemanthsridhar.constants.MobileLocators.*;
import static com.github.hemanthsridhar.constants.WebLocators.*;

/**
 * Created by hemanthsridhar on 1/23/19.
 */
public class SearchWithJSONProvider {

    private static final Map<String, SearchWithJSONProvider> providers = new ConcurrentHashMap<>();

    //this map is file_path -> (name -> locator))
    private final Map<String, Map<String, By>> locators = new ConcurrentHashMap<>();
    private final String locatorsFile;

    public SearchWithJSONProvider(String locatorsFile) throws IllegalArgumentException {
        this.locatorsFile = locatorsFile;
        loadLocators(locatorsFile);
    }

    public SearchWithJSONProvider getJSONProvider() throws IllegalArgumentException {
        SearchWithJSONProvider provider;

        if (!providers.containsKey(locatorsFile)) {
            provider = new SearchWithJSONProvider(locatorsFile);
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

            Reader reader = new FileReader(locatorsFile);

            JsonArray array = new JsonParser().parse(reader).getAsJsonArray();

            reader.close();
            Iterator<JsonElement> iterator = array.iterator();

            Map<String, By> pageLocators;
            String name;
            String type;
            String locator;
            By by;

            while (iterator.hasNext()) {
                JsonObject object = iterator.next().getAsJsonObject();

                if (object.get("name") != null) {
                    name = object.get("name").getAsString();
                } else {
                    throw new IllegalArgumentException("Missing required property - name");
                }

                if (object.get("type") != null) {
                    type = object.get("type").getAsString();
                } else {
                    throw new IllegalArgumentException("Missing required property - type");
                }

                if (object.get("value") != null) {
                    locator = object.get("value").getAsString();
                } else {
                    throw new IllegalArgumentException("Missing required property - value");
                }

                pageLocators = locators.get(name);
                if (pageLocators == null) {
                    pageLocators = new ConcurrentHashMap<>();
                    locators.put(name, pageLocators);
                }

                switch (type.toLowerCase()) {
                    case ID:
                        by = By.id(locator);
                        break;
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
                    case IOS_UI_AUTOMATION:
                        by = MobileBy.IosUIAutomation(locator);
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
                    case CUSTOM:
                        by = MobileBy.custom(locator);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported locator type - " + type);
                }
                pageLocators.put(name, by);
            }
        } catch (JsonIOException | JsonSyntaxException e) {
            throw new IllegalArgumentException("Error parsing locators file " + locatorsFile, e);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to find locators file " + locatorsFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading locators file " + locatorsFile);
        }
    }
}
