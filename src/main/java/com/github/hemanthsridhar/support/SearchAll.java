package com.github.hemanthsridhar.support; /**
 * Created by hemanthsridhar on 1/6/19.
 */

import com.github.hemanthsridhar.builder.CustomPageFactoryFinder;
import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;
import io.appium.java_client.pagefactory.bys.builder.ByAll;
import org.openqa.selenium.By;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@CustomPageFactoryFinder(value = SearchAll.FindByAllBuilder.class)
public @interface SearchAll {

    SearchBy[] value();

    class FindByAllBuilder extends AbstractCustomFindByBuilder {
        @Override
        public By buildIt(Object annotation, Field field, String filePath) {
            SearchAll findBys = (SearchAll) annotation;
            SearchBy[] findByArray = findBys.value();
            By[] byArray = new By[findByArray.length];
            for (int i = 0; i < findByArray.length; i++) {
                byArray[i] = buildByFromFindBy(findByArray[i],field, filePath);
            }
            return new ByAll(byArray);
        }
    }
}