package com.eecs3311.persistence;

import com.eecs3311.util.log.console.ConsoleLogs;
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

            String result = getConnection() != null ? ConsoleLogs.SUCCESSFUL : ConsoleLogs.ERROR;
            System.out.println("--- CONNECTION TO " + ConsoleLogs.DATABASE(this.getClass().getSimpleName()) + ".........." + result + " ---");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
