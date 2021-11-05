package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public abstract class AbstractCustomFindByBuilder {

    public abstract By buildIt(Object annotation, Field field);

    protected By buildByFromFindBy(SearchBy findBy, Field field) {
        return this.buildByFromShortFindBy(findBy, field);
    }

    protected By buildByFromShortFindBy(SearchBy findBy, Field field) {
        String locatorName = findBy.nameOfTheLocator().trim().isEmpty() ? field.getName() : findBy.nameOfTheLocator().trim();
        SearchWithJSONProvider searchWithJSON = new SearchWithJSONProvider(findBy.locatorsFile());
        return searchWithJSON.getJSONProvider().getLocator(locatorName);
    }
}
