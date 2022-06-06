package com.github.hemanthsridhar.builder;

import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author hemanthsridhar
 * @Date 06/06/22
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomPageFactoryFinder {
    Class<? extends AbstractCustomFindByBuilder> value();
}
