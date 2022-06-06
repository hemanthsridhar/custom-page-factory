package com.github.hemanthsridhar.support;

import com.github.hemanthsridhar.builder.CustomPageFactoryFinder;
import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;
import io.appium.java_client.pagefactory.bys.builder.ByChained;
import org.openqa.selenium.By;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @author hemanthsridhar
 * @Date 06/06/22
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@CustomPageFactoryFinder(value = SearchBys.FindBysBuilder.class)
public @interface SearchBys {

    SearchBy[] value();

    class FindBysBuilder extends AbstractCustomFindByBuilder {
        @Override
        public By buildIt(Object annotation, Field field, String filePath) {
            SearchBys findBys = (SearchBys) annotation;
            SearchBy[] findByArray = findBys.value();
            By[] byArray = new By[findByArray.length];
            for (int i = 0; i < findByArray.length; i++) {
                byArray[i] = buildByFromFindBy(findByArray[i], field, filePath);
            }
            return new ByChained(byArray);
        }
    }
}