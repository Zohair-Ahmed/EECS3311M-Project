package com.eecs3311.persistence.Register;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class RegisterDB implements IRegister {

    private Connection conn;

    public RegisterDB() {
        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties");

            Properties prop = new Properties();
            prop.load(input);

            conn = DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.username"),
                    prop.getProperty("db.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String registerUser(String username, String email, String password) {
        boolean matchingCredentials = false;
        String result = "";
        
        try {
            Statement temp = conn.createStatement();
            ResultSet rs = temp.executeQuery("select * from Users");

            while (rs.next() && !matchingCredentials) {
                String dbUsername = rs.getString("Username");
                String dbEmail = rs.getString("Email");

                if (dbUsername.equals(username)) {
                    result = "A SamePage account with this username already exists";
                    matchingCredentials = true;
                }

                else if (dbEmail.equals(email)) {
                    result = "A SamePage account with this email already exists";
                    matchingCredentials = true;
                }
            }
            
            if (matchingCredentials == false) {
            	temp.executeUpdate("insert into Users (Username, Email, UserPassword) values ('" + username + "', '" + email + "', '" + password + "')");
                result = "Successfully registered as a SamePage member";
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
