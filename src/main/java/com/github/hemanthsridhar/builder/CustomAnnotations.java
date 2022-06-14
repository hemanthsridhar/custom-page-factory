package com.github.hemanthsridhar.builder;

import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchAll;
import com.github.hemanthsridhar.support.SearchBy;
import com.github.hemanthsridhar.support.SearchBys;
import org.openqa.selenium.By;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author hemanthsridhar
 */

public class CustomAnnotations extends AbstractAnnotations {
    private final Field field;
    private final Object page;
    private String filePath;

    public CustomAnnotations(Field field, Object page) {
        this.field = field;
        this.page = page;
        setFilePath(page);
    }

    private String getFilePath() {
        return filePath;
    }

    private void setFilePath(Object page) {
        String filePath;
        Class<?> clazz = page.getClass();
        if (!clazz.isAnnotationPresent(FilePath.class)) {
            filePath = null;
        } else {
            filePath = clazz.getAnnotation(FilePath.class).value();
        }
        this.filePath = filePath;
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
                    return builder.buildIt(annotation, field, getFilePath());
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
