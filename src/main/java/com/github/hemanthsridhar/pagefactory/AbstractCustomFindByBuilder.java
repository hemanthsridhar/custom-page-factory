package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.support.SearchBy;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public abstract class AbstractCustomFindByBuilder {

    public abstract By buildIt(Object annotation, Field field);

    protected By buildByFromFindBy(SearchBy findBy) {
        return this.buildByFromShortFindBy(findBy);
    }

    protected By buildByFromShortFindBy(SearchBy findBy) {
        SearchWithJSONProvider searchWithJSON = new SearchWithJSONProvider(findBy.locatorsFile());
        return searchWithJSON.getJSONProvider().getLocator(findBy.nameOfTheLocator());
    }
}
