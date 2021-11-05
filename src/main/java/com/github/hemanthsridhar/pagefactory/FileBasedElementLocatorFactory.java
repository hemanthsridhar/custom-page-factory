package com.github.hemanthsridhar.pagefactory;

import com.github.hemanthsridhar.builder.CustomAnnotations;
import com.github.hemanthsridhar.support.FilePath;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
 * Created by hemanthsridhar on 1/6/19.
 */

public class FileBasedElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;
    private final Object page;
    private String filePath;

    public FileBasedElementLocatorFactory(SearchContext searchContext, Object page) {
        this.searchContext = searchContext;
        this.page = page;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new FileBasedElementLocator(searchContext, new CustomAnnotations(field, page));
    }
}
