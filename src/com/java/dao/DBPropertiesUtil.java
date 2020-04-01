package com.java.dao;

import lombok.AccessLevel;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class DBPropertiesUtil {
    static Logger log = Logger.getLogger(DBPropertiesUtil.class);

    @Getter private static String url, driver, username, password;
    @Getter(AccessLevel.NONE) private static Properties p = new Properties();

    static {
        try {
            log.info("Loading database properties.");
            p.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties")));
            url = p.getProperty("url");
            driver = p.getProperty("driver");
            username = p.getProperty("username");
            password = p.getProperty("password");
        } catch (IOException e) {
            log.error("Failed to load database properties.");
        }
    }

    public static Properties getProperty() throws IOException {
        return p;
    }
}
