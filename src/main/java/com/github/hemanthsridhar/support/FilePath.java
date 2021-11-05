package com.github.hemanthsridhar.support; /**
 * Created by hemanthsridhar on 1/6/19.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FilePath {

    String value();

}