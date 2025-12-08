package com.selman.api.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            // BEST PRACTICE: Load file using ClassLoader.
            // This ensures the file is found whether running from IDE or Maven build (target folder).
            String fileName = "config.properties";
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName);

            if (input == null) {
                System.out.println("🚨 ERROR: '" + fileName + "' not found!");
                System.out.println("👉 Please ensure the file exists under 'src/test/resources'");
                throw new RuntimeException("config.properties file not found in Classpath.");
            }

            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load config.properties file!");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}