package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

/**
 * Created by hemanthsridhar on 12/12/21.
 */
public abstract class AbstractCustomFindByBuilder {

    public abstract By buildIt(Object annotation, Field field, String filePath);

    protected By buildByFromFindBy(SearchBy findBy, Field field, String filePath) {
        return this.buildByFromShortFindBy(findBy, field, filePath);
    }

    protected By buildByFromShortFindBy(SearchBy findBy, Field field, String filePath) {
        String locatorName = findBy.nameOfTheLocator().trim().isEmpty() ? field.getName() : findBy.nameOfTheLocator().trim();
        if(filePath.endsWith(".properties")) {
            SearchWithPropertiesProvider searchWithPropertiesProvider = new SearchWithPropertiesProvider(filePath);
            return searchWithPropertiesProvider.getPropertiesProvider().getLocator(locatorName);
        }
        else {
            SearchWithJSONProvider searchWithJSON = new SearchWithJSONProvider(filePath);
            return searchWithJSON.getJSONProvider().getLocator(locatorName);
        }
    }
}