package com.github.hemanthsridhar.support; /**
 * Created by hemanthsridhar on 1/6/19.
 */

import com.github.hemanthsridhar.builder.CustomPageFactoryFinder;
import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;
import org.openqa.selenium.By;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@CustomPageFactoryFinder(value = SearchBy.FindByBuilder.class)
public @interface SearchBy {

    String locatorsFile() default "";

    String nameOfTheLocator() default "";

    class FindByBuilder extends AbstractCustomFindByBuilder {
        public By buildIt(Object annotation, Field field) {
            SearchBy findBy = (SearchBy) annotation;
            return buildByFromShortFindBy(findBy,field);
        }
    }
}