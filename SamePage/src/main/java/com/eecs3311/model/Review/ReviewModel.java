package com.eecs3311.model.Review;

import com.eecs3311.model.User.User;
import com.eecs3311.model.enums.State;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Review.IReviewPresenter;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

/**
 * Reviews class with intended functionality in itr2
 * Obtains Reviews for each Book.
 */
public class ReviewModel implements IReviewModel{
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getRating() {
        return rating;
    }

    @Override
    public String getReview() {
        return review;
    }

    @Override
    public String getDate() {return date;}

    @Override
    public String getISBN() {return isbn;}

    private String username;
    private String review;
    private String rating;
    private String isbn;
    private String date;
    private IReviewPresenter reviewPresenter;

    public ReviewModel(String username, String reviewBody, String date, String rating, String BookID){
        this.username = username;
        this.review = reviewBody;
        this.date = date;
        this.rating = rating;
        this.isbn = BookID;
    }

    @Override
    public IReviewPresenter getPresenter() {
        return this.reviewPresenter;
    }

    @Override
    public void setPresenter(IReviewPresenter irp) {
        this.reviewPresenter = irp;
    }

    @Override
    public void updateModelFromView(String review, String rating, String isbn) {
        if (User.getInstance().getLoginState().equals(State.MEMBER)) {
            String username = User.getInstance().getUsername();
            if (hasReviewedBook(isbn, username)) {
                // The user has already submitted a review, so update the existing review
                String query = "UPDATE Reviews SET ReviewDesc = ?, Rating = ? WHERE ISBN = ? AND Username = ?";
                try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
                    stmt.setString(1, review);
                    stmt.setString(2, rating);
                    stmt.setString(3, isbn);
                    stmt.setString(4, username);
                    stmt.executeUpdate();
                    this.review = review;
                    this.rating = rating;
                    this.isbn = isbn;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                createReview(review, rating, isbn);
            }
        }
    }

    public void createReview(String review, String rating, String isbn) {
        if (User.getInstance().getLoginState().equals(State.MEMBER)) {
            Database.getReviewInstance().submitReview(review, rating, isbn);
        }
    }

    @Override
    public boolean hasReviewedBook(String isbn, String username) {
        String query = "SELECT * FROM Reviews WHERE ISBN = ? AND Username = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, isbn);
            stmt.setString(2, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://127.0.0.1:3306/samepageschema";
        String username = "root";
        String password = "Eddie13@";
        return DriverManager.getConnection(url,username,password);
    }

}
