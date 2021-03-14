package com.github.hemanthsridhar1992.pagefactory;

import com.github.hemanthsridhar1992.support.SearchAll;
import com.github.hemanthsridhar1992.support.SearchBy;
import com.github.hemanthsridhar1992.support.SearchBys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hemanthsridhar on 1/23/19.
 */
public class SearchWithFieldDecorator extends DefaultFieldDecorator {

    public SearchWithFieldDecorator(ElementLocatorFactory factory) {
        super(factory);
        this.factory = factory;
    }

    protected boolean isDecoratableList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }

        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

        if (!WebElement.class.equals(listType)) {
            return false;
        }

        return field.getAnnotation(FindBy.class) != null ||
                field.getAnnotation(FindBys.class) != null ||
                field.getAnnotation(FindAll.class) != null ||
                field.getAnnotation(SearchBy.class) != null ||
                field.getAnnotation(SearchAll.class) != null ||
                field.getAnnotation(SearchBys.class) != null;
    }
}
