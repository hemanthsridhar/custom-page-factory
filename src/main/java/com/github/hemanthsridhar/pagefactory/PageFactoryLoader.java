package com.github.hemanthsridhar.pagefactory;

import java.lang.reflect.Proxy;

public class PageFactoryLoader {

    protected ClassLoader classLoader = getClass().getClassLoader();

    public static PageFactoryLoader newInstance(){
        return new PageFactoryLoader();
    }

    public <T> T initElements(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz}, new CustomPageFactoryProxy());
    }
}
