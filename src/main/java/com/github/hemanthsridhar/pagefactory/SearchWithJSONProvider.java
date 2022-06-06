package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.utils.LocatorStrategies;
import com.google.gson.*;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.openqa.selenium.By;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hemanthsridhar
 * @Date 06/06/22
 */

public class SearchWithJSONProvider {

    private static final Map<String, SearchWithJSONProvider> providers = new ConcurrentHashMap<>();

    //this map is file_path -> (name -> locator))
    private final Map<String, Map<String, By>> locators = new ConcurrentHashMap<>();
    private final String locatorsFile;
    private LocatorStrategies locatorStrategies;

    public SearchWithJSONProvider(String locatorsFile) throws IllegalArgumentException {
        this.locatorsFile = locatorsFile;
        loadLocators();
    }

    public SearchWithJSONProvider(String locatorsFile, boolean isLoadLocators) throws IllegalArgumentException {
        this.locatorsFile = locatorsFile;
        if (isLoadLocators) {
            loadLocators();
        }
    }

    public SearchWithJSONProvider getJSONProvider() throws IllegalArgumentException {
        SearchWithJSONProvider provider;

        if (!providers.containsKey(locatorsFile)) {
            provider = new SearchWithJSONProvider(locatorsFile, true);
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

    private void loadLocators() throws IllegalArgumentException {
        try {

            locatorStrategies = new LocatorStrategies();

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

                by = locatorStrategies.getLocator(type, locator);
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

    public By getLocatorAsBy(String locatorName, Object... args) {

        By by = null;

        try {
            locatorStrategies = new LocatorStrategies();

            File file = new File(locatorsFile);

            String type;
            String locator;


            type = ((JSONArray) JsonPath.parse(file).read("$..[?(@.name=='" + locatorName + "')].type")).get(0).toString();

            locator = ((JSONArray) JsonPath.parse(file).read("$..[?(@.name=='" + locatorName + "')].value")).get(0).toString();


            try {
                if (args.length > 0) {
                    locator = String.format(locator, args);
                }
            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
            }

            by = locatorStrategies.getLocator(type, locator);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (by == null) {
            throw new IllegalArgumentException("By is null. Please check locator type in your json file.");
        }
        return by;
    }
}
