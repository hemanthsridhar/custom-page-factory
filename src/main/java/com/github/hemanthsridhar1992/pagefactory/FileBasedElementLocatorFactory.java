package com.github.hemanthsridhar1992.pagefactory;

import com.github.hemanthsridhar1992.builder.CustomAnnotations;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * Created by hemanthsridhar on 1/6/19.
 */

public class FileBasedElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;

    public FileBasedElementLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new FileBasedElementLocator(searchContext, new CustomAnnotations(field));
    }
}
