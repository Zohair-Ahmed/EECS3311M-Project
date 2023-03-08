package com.eecs3311.persistence.Review;

import com.eecs3311.model.Review.IReviewModel;

import java.util.ArrayList;

public interface IReview {

    /**
     * Submits a review to the database (stub/real)
     * @param review review
     * @param rating rating
     * @param isbn isbn
     */
    void submitReview(String review, String rating, String isbn);

    /**
     * To retrieve all the reviews associated with the given ISBN
     * @param ISBN isbn
     * @return List of review models
     */
    ArrayList<IReviewModel> getReviewData(String ISBN);

    /**
     * To retrieve the average rating of the book associated with the given ISBN
     * @param ISBN isbn
     * @return average rating
     */
    double getAverageRating(String ISBN);

    /**
     * To retrieve the total amount of ratings on the book
     * @return total ratings
     */
    int getTotalRatings();
}
