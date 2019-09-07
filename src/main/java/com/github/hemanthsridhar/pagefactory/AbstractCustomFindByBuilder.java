package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.pagefactory.json.SearchWithJSON;
import com.github.hemanthsridhar.pagefactory.json.SearchWithJSONProvider;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

public abstract class AbstractCustomFindByBuilder {

    public abstract By buildIt(Object annotation, Field field);

    protected By buildByFromFindBy(SearchWithJSON findBy) {
        By ans = this.buildByFromShortFindBy(findBy);
        return ans;
    }

    protected By buildByFromShortFindBy(SearchWithJSON findBy) {
        SearchWithJSONProvider searchWithJSON = new SearchWithJSONProvider(findBy.locatorsFile());
        return searchWithJSON.getJSONProvider().getLocator(findBy.nameOfTheLocator());
    }
}
