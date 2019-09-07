package com.github.hemanthsridhar.pagefactory; /**
 * Created by hemanthsridhar on 1/6/19.
 */

import com.github.hemanthsridhar.pagefactory.json.SearchAllWithJSON;
import com.github.hemanthsridhar.pagefactory.json.SearchBysWithJSON;
import com.github.hemanthsridhar.pagefactory.json.SearchWithJSON;
import com.github.hemanthsridhar.pagefactory.json.SearchWithJSONProvider;
import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

class CustomAnnotations extends AbstractAnnotations {
    private final Field field;

    CustomAnnotations(Field field) {
        this.field = field;
    }

    @Override
    public By buildBy() {

        SearchWithJSON searchWithJSON = field.getAnnotation(SearchWithJSON.class);
        SearchAllWithJSON searchAllWithJSON = field.getAnnotation(SearchAllWithJSON.class);
        SearchBysWithJSON searchBysWithJSON = field.getAnnotation(SearchBysWithJSON.class);

        if (searchWithJSON != null) {
            String elementName = searchWithJSON.nameOfTheLocator();
            Preconditions.checkArgument(isNotNullAndEmpty(elementName), "Element name is not found.");

            String locatorsFile = searchWithJSON.locatorsFile();
            Preconditions.checkArgument(isNotNullAndEmpty(locatorsFile), "Locators file name not provided");

            return new SearchWithJSONProvider(locatorsFile).getJSONProvider().getLocator(elementName);
        } else if (searchAllWithJSON != null) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                AbstractCustomFindByBuilder builder = null;
                try {
                    builder = annotation.annotationType()
                            .getAnnotation(CustomPageFactoryFinder.class).value()
                            .newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (builder != null) {
                    return builder.buildIt(annotation, field);
                }
            }
        } else if (searchBysWithJSON != null) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                AbstractCustomFindByBuilder builder = null;
                try {
                    builder = annotation.annotationType()
                            .getAnnotation(CustomPageFactoryFinder.class).value()
                            .newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (builder != null) {
                    return builder.buildIt(annotation, field);
                }
            }
        } else {
            return new Annotations(field).buildBy();
        }
        return new Annotations(field).buildBy();
    }

    @Override
    public boolean isLookupCached() {
        return (field.getAnnotation(CacheLookup.class) != null);
    }

    private boolean isNotNullAndEmpty(String arg) {
        return ((arg != null) && (!arg.trim().isEmpty()));
    }
}