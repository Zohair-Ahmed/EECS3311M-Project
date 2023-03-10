package com.eecs3311.persistence.Review;

import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.model.Review.ReviewModel;
import com.eecs3311.model.UserStub;
import com.eecs3311.presenter.Review.IReviewPresenter;

import java.util.ArrayList;

public class ReviewStub implements IReview{

    private final static ArrayList<IReviewModel> reviewList = new ArrayList<>();
    private static ReviewStub instance = null;

    public static ReviewStub getInstance() {
        if (instance == null)
            instance = new ReviewStub();
        return instance;
    }

    private ReviewStub() {
        // Assumes that a User leaves ONE review per book
        IReviewModel review1 = new ReviewModel(UserStub.getInstance().userList().get(0).getUsername(), "Good book", "2023-03-07", "4", "9789000307975");
        IReviewModel review4 = new ReviewModel(UserStub.getInstance().userList().get(1).getUsername(), "I don't like it", "2022-03-05", "1", "9789000307975");
        UserStub.getInstance().userList().get(0).getUserReviews().add(review1);
        UserStub.getInstance().userList().get(1).getUserReviews().add(review4);

        IReviewModel review2 = new ReviewModel(UserStub.getInstance().userList().get(0).getUsername(), "bad book", "2023-02-01", "1", "9780552159722" );
        IReviewModel review3 = new ReviewModel(UserStub.getInstance().userList().get(1).getUsername(), "nice read", "2023-03-05", "3", "9780552159722");
        IReviewModel review6 = new ReviewModel(UserStub.getInstance().userList().get(2).getUsername(), "terrible", "2020-01-01", "2", "9780552159722");
        UserStub.getInstance().userList().get(0).getUserReviews().add(review2);
        UserStub.getInstance().userList().get(1).getUserReviews().add(review3);
        UserStub.getInstance().userList().get(2).getUserReviews().add(review6);

        IReviewModel review5 = new ReviewModel(UserStub.getInstance().userList().get(0).getUsername(), "I love this book", "2013-01-05", "5", "9781841499789" );
        UserStub.getInstance().userList().get(0).getUserReviews().add(review5);

        reviewList.add(review1);
        reviewList.add(review2);
        reviewList.add(review3);
        reviewList.add(review4);
        reviewList.add(review5);
        reviewList.add(review6);
    }

    @Override
    public void submitReview(String review, String rating, String isbn) {

    }

    @Override
    public int getTotalRatings(){
        return reviewList.size();
    }


    @Override
    public double getAverageRating(String ISBN){
        double avg = 0; double total = getTotalRatings();

        if (total == 0)
            return 0;

        for (IReviewModel irm : reviewList) {
            if (irm.getISBN().equals(ISBN))
                avg += Double.parseDouble(irm.getRating());
        }

        return avg/total;
    }

    @Override
    public ArrayList<IReviewModel> getReviewData(String ISBN){
        return null;
    }

    public ArrayList<IReviewModel> getAllReviews(){
        return reviewList;
    }

}
