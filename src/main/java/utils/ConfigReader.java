package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    public static Properties initProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            // System.getProperty("user.dir") +
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("CRITICAL ERROR: Could not find 'config.properties' at src/test/resources/. " +
                    "Please ensure the file exists and the path is correct.", e);
        }
        return properties;
    }

    public static Properties getProperties() {
        if (properties == null) {
            return initProperties(); // Safety net: if not initialized, load it now
        }
        return properties;
    }
}