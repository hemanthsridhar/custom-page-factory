package com.github.hemanthsridhar.pagefactory.json; /**
 * Created by hemanthsridhar on 1/6/19.
 */

import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;
import com.github.hemanthsridhar.pagefactory.CustomPageFactoryFinder;
import io.appium.java_client.pagefactory.bys.builder.ByAll;
import io.appium.java_client.pagefactory.bys.builder.ByChained;
import org.openqa.selenium.By;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@CustomPageFactoryFinder(SearchBysWithJSON.FindByBuilder.class)
public @interface SearchBysWithJSON {

    SearchWithJSON[] value();

    class FindByBuilder extends AbstractCustomFindByBuilder {
        @Override
        public By buildIt(Object annotation, Field field) {
            SearchBysWithJSON findBys = (SearchBysWithJSON) annotation;
            SearchWithJSON[] findByArray = findBys.value();
            By[] byArray = new By[findByArray.length];
            for (int i = 0; i < findByArray.length; i++) {
                byArray[i] = buildByFromFindBy(findByArray[i]);
            }
            return new ByChained(byArray);
        }
    }
}