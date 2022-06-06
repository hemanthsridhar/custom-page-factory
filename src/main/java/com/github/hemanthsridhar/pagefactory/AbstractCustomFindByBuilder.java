package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

/**
 * @author hemanthsridhar
 * @Date 06/06/22
 */

public abstract class AbstractCustomFindByBuilder {

    public abstract By buildIt(Object annotation, Field field, String filePath);

    protected By buildByFromFindBy(SearchBy findBy, Field field, String filePath) {
        return this.buildByFromShortFindBy(findBy, field, filePath);
    }

    protected By buildByFromShortFindBy(SearchBy findBy, Field field, String filePath) {
        String locatorName = findBy.nameOfTheLocator().trim().isEmpty() ? field.getName() : findBy.nameOfTheLocator().trim();
        if (filePath.endsWith(".properties")) {
            SearchWithPropertiesProvider searchWithPropertiesProvider = new SearchWithPropertiesProvider(filePath);
            return searchWithPropertiesProvider.getPropertiesProvider().getLocator(locatorName);
        } else {
            SearchWithJSONProvider searchWithJSON = new SearchWithJSONProvider(filePath);
            return searchWithJSON.getJSONProvider().getLocator(locatorName);
        }
    }

    protected By buildByFromShortFindBy(SearchBy findBy, String field, String filePath, Object... args) {
        String locatorName = findBy.nameOfTheLocator().trim().isEmpty() ? field : findBy.nameOfTheLocator().trim();
        if (filePath.endsWith(".properties")) {
            SearchWithPropertiesProvider searchWithPropertiesProvider
                    = new SearchWithPropertiesProvider(filePath, false);
            return searchWithPropertiesProvider.getLocatorAsBy(locatorName, args);
        } else {
            SearchWithJSONProvider searchWithJSON = new SearchWithJSONProvider(filePath, false);
            return searchWithJSON.getLocatorAsBy(locatorName, args);
        }
    }
}