package com.java.dao;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {
    static Logger log = Logger.getLogger(DatabaseUtil.class);

    private static BasicDataSource ds = new BasicDataSource();

    static {
        log.info("Setting up database");
        ds.setUrl(DBPropertiesUtil.getUrl());
        ds.setUsername(DBPropertiesUtil.getUsername());
        ds.setPassword(DBPropertiesUtil.getPassword());
        ds.setDriverClassName(DBPropertiesUtil.getDriver());
        ds.setDefaultAutoCommit(false);
        ds.setMaxIdle(5);
        ds.setMaxTotal(100);
        ds.setMaxWaitMillis(2000);
        ds.setMaxConnLifetimeMillis(1000);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
