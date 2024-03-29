package com.github.hemanthsridhar.support;

import com.github.hemanthsridhar.builder.CustomPageFactoryFinder;
import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;
import org.openqa.selenium.By;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @author hemanthsridhar
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@CustomPageFactoryFinder(value = SearchBy.FindByBuilder.class)
public @interface SearchBy {

    String nameOfTheLocator() default "";

    class FindByBuilder extends AbstractCustomFindByBuilder {
        public By buildIt(Object annotation, Field field, String filePath) {
            SearchBy findBy = (SearchBy) annotation;
            return buildByFromShortFindBy(findBy, field, filePath);
        }
    }
}