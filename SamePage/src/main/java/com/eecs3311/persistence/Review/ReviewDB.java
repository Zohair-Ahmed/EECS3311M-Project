package com.eecs3311.persistence.Review;

import com.eecs3311.model.User;

import java.sql.*;

public class ReviewDB implements IReview{

    @Override
    public void submitReview(String review, String rating, String isbn) {
        String query = "INSERT INTO Reviews VALUES ("+isbn+", 0, \""+review+"\", \""+rating+"\", \""+ User.getInstance().getUsername()+"\", CURDATE());";

        try {
            Statement temp = conn.createStatement();
            temp.executeUpdate(query);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Connection conn;
    public ReviewDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/samepageschema", "root", "1234");
            // System.out.println("connection successful via ip address");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
