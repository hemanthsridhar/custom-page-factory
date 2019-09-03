package org.test.selenium.factory; /**
 * Created by hemanthsridhar on 1/6/19.
 */
import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Iterator;

class CustomAnnotations extends AbstractAnnotations {
    private final Field field;

    CustomAnnotations(Field field) {
        this.field = field;
    }

    @Override
    public By buildBy() {

        SearchWith search = field.getAnnotation(SearchWith.class);

        if (search != null) {
            String pageName = search.inPage();
            Preconditions.checkArgument(isNotNullAndEmpty(pageName), "Page name is missing.");

            String elementName = search.nameOfTheLocator();
            Preconditions.checkArgument(isNotNullAndEmpty(elementName), "Element name is not found.");

            String locatorsFile = search.locatorsFile();
            Preconditions.checkArgument(isNotNullAndEmpty(locatorsFile), "Locators file name not provided");

            return SearchWithProvider.getProvider(locatorsFile).getLocator(pageName, elementName);
        } else {
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