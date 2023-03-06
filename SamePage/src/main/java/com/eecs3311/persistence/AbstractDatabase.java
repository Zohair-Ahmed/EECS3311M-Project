package com.eecs3311.persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public abstract class AbstractDatabase {

    private Connection connection;

    public AbstractDatabase() {
        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties");

            Properties prop = new Properties();
            prop.load(input);

            Class.forName(prop.getProperty("db.driver"));
            this.connection = DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.username"),
                    prop.getProperty("db.password")
            );

            if (this.connection != null) {
                System.out.println("Connection is successful");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
