package com.github.hemanthsridhar.builder;

import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hemanthsridhar on 1/6/19.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomPageFactoryFinder {
    Class<? extends AbstractCustomFindByBuilder> value();
}
