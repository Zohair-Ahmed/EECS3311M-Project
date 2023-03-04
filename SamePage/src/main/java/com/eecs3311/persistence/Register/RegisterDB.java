package com.eecs3311.persistence.Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDB implements IRegister {

    private Connection conn;

    public RegisterDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/samepageuserschema", "root", "Ammadq87");
            // System.out.println("connection successful via ip address");
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
