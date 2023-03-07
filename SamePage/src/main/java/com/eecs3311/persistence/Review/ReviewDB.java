package com.eecs3311.persistence.Review;

import com.eecs3311.model.Book.BookModel;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Review.ReviewModel;
import com.eecs3311.model.User;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.presenter.Review.IReviewPresenter;
import com.eecs3311.presenter.Review.ReviewPresenter;
import com.eecs3311.view.Review.IReviewPanelView;
import com.eecs3311.view.Review.ReviewPanelView;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDB extends AbstractDatabase implements IReview{

    private String username;
    private String reviewBody;
    private ArrayList<IReviewModel> reviews = new ArrayList<>();
    private String bookID;
    private String date;
    private String rating;

    @Override
    public void submitReview(String review, String rating, String isbn) {
        String query = "INSERT INTO Reviews VALUES ("+isbn+", 0, \""+review+"\", \""+rating+"\", \""+ User.getInstance().getUsername()+"\", CURDATE());";

        try {
            Statement temp = getConnection().createStatement();
            temp.executeUpdate(query);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<IReviewModel> getReviewData(String ISBN) {
        this.reviews = new ArrayList<>();
        try {
            if (getConnection() != null) {
                ArrayList<IReviewModel> revs = new ArrayList<>();
                String query = "SELECT * FROM Reviews WHERE BookID = \""+ISBN+"\";";
                Statement st = getConnection().createStatement();
                ResultSet rs = st.executeQuery(query); // execute the query, and get a java resultset
                while (rs.next()) { // iterate through the java resultset
                    username = rs.getString("Username");
                    reviewBody = rs.getString("ReviewDesc");
                    date = rs.getString("DatePosted");
                    rating = rs.getString("Rating");
                    bookID = rs.getString("BookID");
                    revs.add(new ReviewModel(username, reviewBody, date, rating, bookID));
                }
                addToList(revs);
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public double getAverageRating(String ISBN) {
        reviews = getReviewData(ISBN);
        if (reviews == null || reviews.size() == 0)
            return 0;
        double sum = 0;
        double total = this.reviews.size();
        for (IReviewModel irm : this.reviews) {
            sum += Double.parseDouble(irm.getRating());
        }
        return sum/total;
    }

    @Override
    public int getTotalRatings() {
        return this.reviews == null ? 0 : this.reviews.size();
    }

    private void addToList(ArrayList<IReviewModel> revs) {
        for (IReviewModel irm : revs) {
            IReviewPresenter irp = new ReviewPresenter();
            IReviewPanelView irpv = new ReviewPanelView();
            irp.setModel(irm);
            irm.setPresenter(irp);
            irp.setView(irpv);
            irpv.setPresenter(irp);
            this.reviews.add(irm);
        }
    }


    private Connection conn;
    public ReviewDB() {
       super();
    }
}
