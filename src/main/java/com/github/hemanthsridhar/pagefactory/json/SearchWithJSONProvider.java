package com.github.hemanthsridhar.pagefactory.json;

import com.google.gson.*;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hemanthsridhar on 1/23/19.
 */
public class SearchWithJSONProvider {

    private static Map<String, SearchWithJSONProvider> providers = new ConcurrentHashMap<>();

    //this map is path -> (name -> locator))
    private Map<String, Map<String, By>> locators = new ConcurrentHashMap<>();

    private SearchWithJSONProvider(String locatorsFile) throws IllegalArgumentException {
        boolean isResource = false;
        if (locatorsFile.matches("\\{(.+)}")) {
            String propName = locatorsFile.substring(1, locatorsFile.length() - 1);
            locatorsFile = System.getProperty(propName);
            if (locatorsFile == null || locatorsFile.trim().isEmpty()) {
                throw new IllegalArgumentException("Undefined system property for locators file - " + propName);
            }
        }

        if (locatorsFile.matches("\\[(.+)]")) {
            isResource = true;
            locatorsFile = "/" + locatorsFile.substring(1, locatorsFile.length() - 1);
        }

        loadLocators(locatorsFile, isResource);
    }

    public static SearchWithJSONProvider getProvider(String locatorsFile) throws IllegalArgumentException {
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

    private void loadLocators(String locatorsFile, boolean isResource) throws IllegalArgumentException {
        try {
            Reader reader;

            if (isResource) {
                InputStream locStream = this.getClass().getResourceAsStream(locatorsFile);
                if (locStream == null) {
                    throw new FileNotFoundException("Locators file not found in resources: " + locatorsFile);
                }
                reader = new InputStreamReader(locStream);
            } else {
                reader = new FileReader(locatorsFile);
            }

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

                if (object.get("nameOfTheLocator") != null) {
                    name = object.get("nameOfTheLocator").getAsString();
                } else {
                    throw new IllegalArgumentException("Missing required property - nameOfTheLocator");
                }

                if (object.get("locateUsing") != null) {
                    type = object.get("locateUsing").getAsString();
                } else {
                    throw new IllegalArgumentException("Missing required property - locateUsing");
                }

                if (object.get("locator") != null) {
                    locator = object.get("locator").getAsString();
                } else {
                    throw new IllegalArgumentException("Missing required property - locator");
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