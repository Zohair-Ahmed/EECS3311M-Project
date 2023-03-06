package com.eecs3311.persistence.Review;

import com.eecs3311.model.Review.IReviewModel;

import java.util.ArrayList;

public interface IReview {

    void submitReview(String review, String rating, String isbn);
    ArrayList<IReviewModel> getReviewData(String ISBN);
}
