package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    public ConfigReader() {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            System.err.println("Unable to load config.properties: " + e.getMessage());
            // Optionally re-throw as unchecked exception
        }
    }

  
     // Get property value by key. 
     // @param key The property key
     // @return the property value, or null if not found
     
    public String get(String key) {
        return prop.getProperty(key);
    }

    
     //Convenience methods for typed values 
     
    public int getInt(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(prop.getProperty(key));
    }
}
