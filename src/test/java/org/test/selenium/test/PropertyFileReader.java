package org.test.selenium.test;

import java.io.FileReader;
import java.util.Properties;
/**
 * Created by hemanthsridhar on 1/23/19.
 */
public class PropertyFileReader {

    public String propertiesReader(String filePath,String key)
    {
        try
        {
            FileReader reader = new FileReader(filePath);
            Properties properties = new Properties();
            properties.load(reader);
            return properties.getProperty(key);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
