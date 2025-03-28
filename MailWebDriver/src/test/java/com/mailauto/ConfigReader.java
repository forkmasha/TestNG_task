package com.mailauto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mailauto.entities.User;

public class ConfigReader {

    private Properties properties = new Properties();

    public ConfigReader(String environment) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(environment + ".properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + environment + ".properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public User getUser() {
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        return new User(email, password);
    }
}