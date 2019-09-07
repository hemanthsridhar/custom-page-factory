package com.github.hemanthsridhar.pagefactory; /**
 * Created by hemanthsridhar on 1/6/19.
 */
import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;


import java.lang.reflect.Field;

class CustomAnnotations extends AbstractAnnotations {
    private final Field field;

    CustomAnnotations(Field field) {
        this.field = field;
    }

    @Override
    public By buildBy() {

        SearchWithJSON searchWithJSON = field.getAnnotation(SearchWithJSON.class);

        SearchWithProperty searchWithProperty = field.getAnnotation(SearchWithProperty.class);

        if (searchWithJSON != null) {
            String pageName = searchWithJSON.inPage();
            Preconditions.checkArgument(isNotNullAndEmpty(pageName), "Page name is missing.");

            String elementName = searchWithJSON.nameOfTheLocator();
            Preconditions.checkArgument(isNotNullAndEmpty(elementName), "Element name is not found.");

            String locatorsFile = searchWithJSON.locatorsFile();
            Preconditions.checkArgument(isNotNullAndEmpty(locatorsFile), "Locators file name not provided");

            return SearchWithJSONProvider.getProvider(locatorsFile).getLocator(pageName, elementName);
        }
        else if(searchWithProperty !=null){

            String elementName = searchWithProperty.nameOfTheLocator();
            Preconditions.checkArgument(isNotNullAndEmpty(elementName), "Element name is not found.");

            String locatorsFile = searchWithProperty.locatorsFile();
            Preconditions.checkArgument(isNotNullAndEmpty(locatorsFile), "Locators file name not provided");

            return SearchWithPropertyProvider.getProvider(locatorsFile).getLocator(elementName);
        }
        else {
            return new Annotations(field).buildBy();
        }
    }

    @Override
    public boolean isLookupCached() {
        return (field.getAnnotation(CacheLookup.class) != null);
    }

    private boolean isNotNullAndEmpty(String arg) {
        return ((arg != null) && (!arg.trim().isEmpty()));
    }
}