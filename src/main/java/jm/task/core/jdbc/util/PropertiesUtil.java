package jm.task.core.jdbc.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    private static void loadProperties() {
        try (var inputstream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputstream);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);

    }
}
