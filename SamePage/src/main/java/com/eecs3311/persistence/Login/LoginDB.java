package com.eecs3311.persistence.Login;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.persistence.Database;

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
                int getUserID = rs.getInt("UserID");

                if (getEmail.equals(email) && getPassword.equals(password)) {
                    UserModel.getInstance().setEmail(getEmail);
                    UserModel.getInstance().setUsername(getUsername);
                    UserModel.getInstance().setPassword(getPassword);
                    UserModel.getInstance().setUserID(getUserID);
                    Database.getFavBooksInstance().getDBdata();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
