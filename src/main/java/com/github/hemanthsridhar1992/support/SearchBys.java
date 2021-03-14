package com.github.hemanthsridhar1992.support; /**
 * Created by hemanthsridhar on 1/6/19.
 */

import com.github.hemanthsridhar1992.builder.CustomPageFactoryFinder;
import com.github.hemanthsridhar1992.pagefactory.AbstractCustomFindByBuilder;
import io.appium.java_client.pagefactory.bys.builder.ByChained;
import org.openqa.selenium.By;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@CustomPageFactoryFinder(SearchBys.FindBysBuilder.class)
public @interface SearchBys {

    SearchBy[] value();

    class FindBysBuilder extends AbstractCustomFindByBuilder {
        @Override
        public By buildIt(Object annotation, Field field) {
            SearchBys findBys = (SearchBys) annotation;
            SearchBy[] findByArray = findBys.value();
            By[] byArray = new By[findByArray.length];
            for (int i = 0; i < findByArray.length; i++) {
                byArray[i] = buildByFromFindBy(findByArray[i]);
            }
            return new ByChained(byArray);
        }
    }
}