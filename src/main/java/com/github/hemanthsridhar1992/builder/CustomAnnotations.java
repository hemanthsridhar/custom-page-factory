package com.github.hemanthsridhar1992.builder;

import com.github.hemanthsridhar1992.pagefactory.AbstractCustomFindByBuilder;
import com.github.hemanthsridhar1992.support.SearchAll;
import com.github.hemanthsridhar1992.support.SearchBy;
import com.github.hemanthsridhar1992.support.SearchBys;
import org.openqa.selenium.By;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
public class CustomAnnotations extends AbstractAnnotations {
    private final Field field;

    public CustomAnnotations(Field field) {
        this.field = field;
    }

    @Override
    public By buildBy() {

        SearchBy searchBy = field.getAnnotation(SearchBy.class);
        SearchAll searchAll = field.getAnnotation(SearchAll.class);
        SearchBys searchBys = field.getAnnotation(SearchBys.class);

        if (searchBy != null || searchAll != null || searchBys != null) {
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
            return new Annotations(field).buildBy();
        } else {
            return new Annotations(field).buildBy();
        }
    }

    @Override
    public boolean isLookupCached() {
        return (field.getAnnotation(CacheLookup.class) != null);
    }
}
