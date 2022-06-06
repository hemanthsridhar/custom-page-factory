package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.builder.CustomAnnotations;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * @author hemanthsridhar
 * @Date 06/06/22
 */

public class FileBasedElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;
    private final Object page;

    public FileBasedElementLocatorFactory(SearchContext searchContext, Object page) {
        this.searchContext = searchContext;
        this.page = page;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new FileBasedElementLocator(searchContext, new CustomAnnotations(field, page));
    }
}
