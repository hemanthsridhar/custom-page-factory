package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.utils.LocatorStrategies;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hemanthsridhar
 *
 */

public class SearchWithPropertiesProvider {

    private static final Map<String, SearchWithPropertiesProvider> providers = new ConcurrentHashMap<>();

    //this map is file_path -> (name -> locator))
    private final Map<String, Map<String, By>> locators = new ConcurrentHashMap<>();
    private final String locatorsFile;
    private LocatorStrategies locatorStrategies;

    public SearchWithPropertiesProvider(String locatorsFile) throws IllegalArgumentException {
        this.locatorsFile = locatorsFile;
        loadLocators();
    }

    public SearchWithPropertiesProvider(String locatorsFile, boolean isLoadLocators) throws IllegalArgumentException {
        this.locatorsFile = locatorsFile;
        if (isLoadLocators) {
            loadLocators();
        }
    }

    public SearchWithPropertiesProvider getPropertiesProvider() throws IllegalArgumentException {
        SearchWithPropertiesProvider provider;

        if (!providers.containsKey(locatorsFile)) {
            provider = new SearchWithPropertiesProvider(locatorsFile, true);
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
            Properties properties = new Properties();
            Map<String, By> pageLocators;
            By by;
            properties.load(new FileInputStream(locatorsFile));

            for (String key : properties.stringPropertyNames()) {
                String locator = properties.getProperty(key);
                int lastOccurenceOfUnderscore = key.lastIndexOf("_");
                String name = key.substring(0, lastOccurenceOfUnderscore);
                String type = key.substring(lastOccurenceOfUnderscore + 1);

                pageLocators = locators.get(name);
                if (pageLocators == null) {
                    pageLocators = new ConcurrentHashMap<>();
                    locators.put(name, pageLocators);
                }

                by = locatorStrategies.getLocator(type, locator);
                pageLocators.put(name, by);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to find locators file " + locatorsFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading locators file " + locatorsFile);
        }
    }

    public By getLocatorAsBy(String locatorName, Object... args) {

        try {
            locatorStrategies = new LocatorStrategies();

            Properties properties = new Properties();
            By by = null;
            try {
                properties.load(new FileInputStream(locatorsFile));
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (String key : properties.stringPropertyNames()) {
                String locator = properties.getProperty(key);
                int lastOccurenceOfUnderscore = key.lastIndexOf("_");
                String name = key.substring(0, lastOccurenceOfUnderscore);
                String type = key.substring(lastOccurenceOfUnderscore + 1);

                if (locatorName.equals(name)) {
                    try {
                        if (args.length > 0) {
                            locator = String.format(locator, args);
                        }
                    } catch (ArrayIndexOutOfBoundsException|NullPointerException ignored) {
                    }
                    by = locatorStrategies.getLocator(type, locator);
                    break;
                }
            }
            return by;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported locator type please check your property type");
        }
    }
}
