package com.eecs3311.persistence.Review;

import com.eecs3311.model.Review.ReviewModel;
import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.AbstractDatabase;
import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.presenter.Review.IReviewPresenter;
import com.eecs3311.presenter.Review.ReviewPresenter;
import com.eecs3311.view.Review.IReviewPanelView;
import com.eecs3311.view.Review.ReviewPanelView;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDB extends AbstractDatabase implements IReview{

    private ArrayList<IReviewModel> reviews = new ArrayList<>();

    @Override
    public void submitReview(String review, String rating, String isbn) {
        String query = "SELECT COUNT(*) FROM Reviews WHERE BookID = \""+isbn+"\" AND Username =\""+UserModel.getInstance().getUsername()+"\";";
        try {
            Statement temp = getConnection().createStatement();
            ResultSet rs = temp.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            if(count > 0){
                query = "UPDATE Reviews SET ReviewDesc = \""+review+"\", Rating = \""+rating+"\", DatePosted = CURDATE() WHERE BookID = \""+isbn+"\" AND Username =\""+UserModel.getInstance().getUsername()+"\";";
            }
            else{
                query = "INSERT INTO Reviews VALUES ("+isbn+", 0, \""+review+"\", \""+rating+"\", \""+ UserModel.getInstance().getUsername()+"\", CURDATE());";
            }
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
                    String username = rs.getString("Username");
                    String reviewBody = rs.getString("ReviewDesc");
                    String date = rs.getString("DatePosted");
                    String rating = rs.getString("Rating");
                    String bookID = rs.getString("BookID");
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

    /**
     * To convert the Review models to incorporate an MVP connection in order to serve the view
     * @param revs reviews
     */
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

    public ReviewDB() {
       super();
    }
}
