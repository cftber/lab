package com.tgzhao.core.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tgzhao on 16/7/17.
 */
public abstract class ConnectionFactory {

    private static final String url;

    private static final String username;

    private static final String password;

    private static final String driver;

    static {
        try {
            url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf8";
            username = "blog";
            password = "blog";
            driver = "com.mysql.jdbc.Driver";

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
