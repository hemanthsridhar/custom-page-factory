package com.github.hemanthsridhar.pagefactory.dynamic;

import com.github.hemanthsridhar.pagefactory.AbstractCustomFindByBuilder;
import com.github.hemanthsridhar.support.FilePath;
import com.github.hemanthsridhar.support.SearchAll;
import com.github.hemanthsridhar.support.SearchBy;
import com.github.hemanthsridhar.support.SearchBys;
import io.appium.java_client.pagefactory.bys.builder.ByAll;
import io.appium.java_client.pagefactory.bys.builder.ByChained;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @author hemanthsridhar
 */

public class CustomPageFactoryProxy extends AbstractCustomFindByBuilder implements InvocationHandler {

    private static final String CLASSPATH_PROTOCOL = "classpath:";

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String filePath;
        String fileCompletePath;
        try {
            fileCompletePath = method.getDeclaringClass().getAnnotation(FilePath.class).value();
        } catch (NullPointerException e) {
            throw new NullPointerException("missing @FilePath annotation above the interface");
        }

        if (fileCompletePath.startsWith(CLASSPATH_PROTOCOL)) {
            String path = fileCompletePath.substring(CLASSPATH_PROTOCOL.length());
            URL url = method.getDeclaringClass().getClassLoader().getResource(path);
            if (url != null) {
                filePath = url.getPath();
            } else {
                throw new IllegalArgumentException("Unable to find by classpath with path " + path);
            }
        } else {
            filePath = method.getDeclaringClass().getAnnotation(FilePath.class).value();
        }

        if (method.isAnnotationPresent(SearchBy.class)) {
            return buildItSearchBy(method.getAnnotation(SearchBy.class), method.getName(),
                    filePath, args);
        } else if (method.isAnnotationPresent(SearchAll.class)) {
            return buildItSearchAll(method.getAnnotation(SearchAll.class),
                    filePath, args);
        } else if (method.isAnnotationPresent(SearchBys.class)) {
            return buildItSearchBys(method.getAnnotation(SearchBys.class),
                    filePath, args);
        } else {
            throw new Exception("Unsupported Annotation. Please use SearchBy, SearchBys, SearchAll.");
        }
    }

    private By buildItSearchAll(SearchAll findBys, String filePath, Object... args) {
        SearchBy[] findByArray = findBys.value();
        if (args != null) {
            if (args.length > findByArray.length) {
                throw new IllegalArgumentException("Number of method parameters is greater than the SearchAll array.");
            }
        }
        By[] byArray = new By[findByArray.length];
        int j = 0;
        for (int i = 0; i < findByArray.length; i++) {
            if (args != null) {
                if (j < args.length) {
                    byArray[i] = buildByFromShortFindBy(findByArray[i], findByArray[i].nameOfTheLocator(), filePath, ((Object[]) args[j]));
                    j++;
                } else {
                    byArray[i] = buildByFromShortFindBy(findByArray[i], findByArray[i].nameOfTheLocator(), filePath);
                }
            } else {
                byArray[i] = buildByFromShortFindBy(findByArray[i], findByArray[i].nameOfTheLocator(), filePath);
            }
        }
        return new ByAll(byArray);
    }

    public By buildItSearchBy(SearchBy annotation, String field, String filePath, Object... args) {
        if (annotation.nameOfTheLocator().trim().equals("")) {
            return buildByFromShortFindBy(annotation, field, filePath, args);
        } else {
            return buildByFromShortFindBy(annotation, annotation.nameOfTheLocator(), filePath, args);
        }
    }

    public By buildItSearchBys(SearchBys findBys, String filePath, Object... args) {
        SearchBy[] findByArray = findBys.value();
        if (args != null) {
            if (args.length > findByArray.length) {
                throw new IllegalArgumentException("Number of method parameters is greater than the SearchBys array.");
            }
        }
        By[] byArray = new By[findByArray.length];

        int j = 0;
        for (int i = 0; i < findByArray.length; i++) {
            if (args != null) {
                if (j < args.length) {
                    byArray[i] = buildByFromShortFindBy(findByArray[i], findByArray[i].nameOfTheLocator(), filePath, ((Object[]) args[j]));
                    j++;
                } else {
                    byArray[i] = buildByFromShortFindBy(findByArray[i], findByArray[i].nameOfTheLocator(), filePath);
                }
            } else {
                byArray[i] = buildByFromShortFindBy(findByArray[i], findByArray[i].nameOfTheLocator(), filePath);
            }
        }
        return new ByChained(byArray);
    }

    @Override
    public By buildIt(Object annotation, Field field, String filePath) {
        return buildByFromShortFindBy((SearchBy) annotation, field, filePath);
    }
}
