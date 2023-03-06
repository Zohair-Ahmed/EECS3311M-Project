package com.eecs3311.persistence.Login;

import com.eecs3311.model.User;

import java.sql.*;

public class LoginDB implements ILogin{

    private Connection conn;

    /**
     * Provide access and perform operations on Login DB
     */
    public LoginDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/samepageschema", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean isLoginValid(String email, String password) {
        try {
            String query = "select * from Users where Email = '" + email + "' and UserPassword = '" + password + "';";
            Statement temp = conn.createStatement();
            ResultSet rs = temp.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("Email").equals(email) && rs.getString("UserPassword").equals(password)) {
                    User.getInstance().setEmail(rs.getString("Email"));
                    User.getInstance().setUsername(rs.getString("Username"));
                    User.getInstance().setPassword(rs.getString("UserPassword"));
                    return true;
                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
