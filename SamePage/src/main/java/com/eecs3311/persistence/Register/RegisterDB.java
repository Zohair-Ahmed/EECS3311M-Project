package com.eecs3311.persistence.Register;

import com.eecs3311.model.User.User;
import com.eecs3311.persistence.AbstractDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegisterDB extends AbstractDatabase implements IRegister {
    ArrayList<String> users = new ArrayList<>();
    public RegisterDB() {
        super();
    }

    /**
     * Registers the username, email, and password to be able to log in.
     * Checks if the params are already taken and returns a string message, else
     * returns a successful String message that registration was successful
     * @param username username
     * @param email email
     * @param password password
     */
    public String registerUser(String username, String email, String password) {
        boolean matchingCredentials = false;
        String result = "";
        
        try {
            Statement temp = getConnection().createStatement();
            ResultSet rs = temp.executeQuery("select * from Users");

            while (rs.next() && !matchingCredentials) {
                String dbUsername = rs.getString("Username");
                String dbEmail = rs.getString("Email");
                if (dbUsername.equals(username)) {
                    result = "A SamePage account with this username already exists";
                    matchingCredentials = true;
                } else if (dbEmail.equals(email)) {
                    result = "A SamePage account with this email already exists";
                    matchingCredentials = true;
                }
            }
            
            if (!matchingCredentials) {
            	temp.executeUpdate("insert into Users (Username, Email, UserPassword) values ('" + username + "', '" + email + "', '" + password + "')");
                result = "Successfully registered!";
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Gets all the usernames registered and returns arraylist of string
     */
    public ArrayList<String> getUserList(){
        if(users.isEmpty()){
            try {
                Statement temp = getConnection().createStatement();
                ResultSet rs = temp.executeQuery("select * from Users");
                while (rs.next()) {
                    String dbUsername = rs.getString("Username");
                    if(!this.users.contains(dbUsername)){
                        this.users.add(dbUsername);
                    }
                }
                //removes the user currently logged in from the list
                this.users.remove(User.getInstance().getUsername());
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public int getLatestRegisterUserID() {
        try {
            Statement temp = getConnection().createStatement();
            ResultSet rs = temp.executeQuery("SELECT UserID FROM Users ORDER BY UserID DESC LIMIT 1");
            while (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}