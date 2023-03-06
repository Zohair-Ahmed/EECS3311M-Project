package com.eecs3311.persistence.Login;

import com.eecs3311.model.User;
import com.eecs3311.persistence.AbstractDatabase;
import java.sql.*;

public class LoginDB extends AbstractDatabase implements ILogin{

    /**
     * Provide access and perform operations on Login DB
     */
    public LoginDB() {
        super();
    }

    @Override
    public boolean isLoginValid(String email, String password) {
        try {
            String query = "select * from Users where Email = '" + email + "' and UserPassword = '" + password + "';";
            Statement temp = getConnection().createStatement();
            ResultSet rs = temp.executeQuery(query);
            while (rs.next()) {
                String getEmail = rs.getString("Email");
                String getUsername = rs.getString("Username");
                String getPassword = rs.getString("UserPassword");

                if (getEmail.equals(email) && getPassword.equals(password)) {
                    User.getInstance().setEmail(getEmail);
                    User.getInstance().setUsername(getUsername);
                    User.getInstance().setPassword(getPassword);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
