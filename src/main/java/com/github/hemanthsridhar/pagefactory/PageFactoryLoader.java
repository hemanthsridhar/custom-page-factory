package com.github.hemanthsridhar.pagefactory;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class PageFactoryLoader {

    protected ClassLoader classLoader = getClass().getClassLoader();

    public static PageFactoryLoader newInstance(){
        return new PageFactoryLoader();
    }

    public <T> T initElements(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz}, new CustomPageFactoryProxy());
    }
}
