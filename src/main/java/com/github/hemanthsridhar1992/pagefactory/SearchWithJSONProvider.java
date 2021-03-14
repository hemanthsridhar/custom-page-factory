package com.github.hemanthsridhar1992.pagefactory;

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

/**
 * Created by hemanthsridhar on 1/23/19.
 */
public class SearchWithJSONProvider {

    private static Map<String, SearchWithJSONProvider> providers = new ConcurrentHashMap<>();

    //this map is file_path -> (name -> locator))
    private Map<String, Map<String, By>> locators = new ConcurrentHashMap<>();
    private String locatorsFile;

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
        return pageLocators == null ? null : pageLocators.get(name);
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

                switch (type) {
                    case "id":
                        by = By.id(locator);
                        break;
                    case "css":
                        by = By.cssSelector(locator);
                        break;
                    case "className":
                        by = By.className(locator);
                        break;
                    case "xpath":
                        by = By.xpath(locator);
                        break;
                    case "linkText":
                        by = By.linkText(locator);
                        break;
                    case "partialLinkText":
                        by = By.partialLinkText(locator);
                        break;
                    case "name":
                        by = By.name(locator);
                        break;
                    case "tagName":
                        by = By.tagName(locator);
                        break;
                    case "accessibilityId":
                        by = MobileBy.AccessibilityId(locator);
                        break;
                    case "uiautomator":
                        by = MobileBy.AndroidUIAutomator(locator);
                        break;
                    case "IosUIautomation":
                        by = MobileBy.IosUIAutomation(locator);
                        break;
                    case "iosClassChain":
                        by = MobileBy.iOSClassChain(locator);
                        break;
                    case "androidViewTag":
                        by = MobileBy.AndroidViewTag(locator);
                        break;
                    case "iOSNsPredicateString":
                        by = MobileBy.iOSNsPredicateString(locator);
                        break;
                    case "image":
                        by = MobileBy.image(locator);
                        break;
                    case "windowsAutomation":
                        by = MobileBy.windowsAutomation(locator);
                        break;
                    case "custom":
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
