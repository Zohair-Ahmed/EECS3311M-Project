package com.eecs3311.persistence.Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDB {

    private Connection conn;

    public RegisterDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/samepageuserschema", "root", "root1234");
            // System.out.println("connection successful via ip address");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs a retrieval query first in order to check credentials for possible matches in the database,
     * otherwise it will perform an insert update to add a new SamePage user to the database with the provided
     * username, email and password from the client input
     * 
     * Returns a string to model with status update of the register attempt that will be passed to the view (GUI)
     * 
     * @param username
     * @param email
     * @param password
     * @return
     */ 
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
