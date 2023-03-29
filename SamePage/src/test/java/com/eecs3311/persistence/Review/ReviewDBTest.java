package com.eecs3311.persistence.Review;

import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.model.User.UserStub;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class ReviewDBTest {
    ReviewStub reviews = ReviewStub.getInstance();
    ArrayList<UserStub> users = UserStub.getInstance().userList();

    @Test
    void getReviewData() {
        //check stub review data with user review stub data
        assertEquals(reviews.getAllReviews().get(0).getReview(),users.get(0).getUserReviews().get(0).getReview());

    }

    @Test
    void submitReview() {
        ArrayList<IReviewModel> reviewsList = reviews.getAllReviews();
        int currentSize = reviewsList.size();
        reviews.submitReview("Great book, lots of great insight","5","9898439598402");
        assertEquals(reviews.getAllReviews().size(),currentSize+1);
    }

    /*
        FAILING TEST CASE: Read ToDo on BookIntegrationTest
     */
    @Test
    void getTotalRatings() {
        assertEquals(reviews.getTotalRatings(),6);
    }


    /*
        FAILING TEST CASE: Read ToDo on BookIntegrationTest
     */
    @Test
    void getAverageRating() {
        String avgRating = String.format("%.1f",reviews.getAverageRating("isbn"));
        assertEquals(avgRating,"2.7");
    }
}