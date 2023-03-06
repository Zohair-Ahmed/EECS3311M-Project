package com.eecs3311.persistence.Register;

import com.eecs3311.persistence.AbstractDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDB extends AbstractDatabase implements IRegister {

    public RegisterDB() {
        super();
    }

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
                } else if (dbEmail.equals(email)) {
                    result = "A SamePage account with this email already exists";
                }
                matchingCredentials = true;
            }
            
            if (!matchingCredentials) {
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
