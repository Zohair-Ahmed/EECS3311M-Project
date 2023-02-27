package com.eecs3311.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class RegisterDB {

    public RegisterDB() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/samepageuserschema", "root",
                    "root1234");
            System.out.println("connection successful via ip address");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerUser() {

    }

}
