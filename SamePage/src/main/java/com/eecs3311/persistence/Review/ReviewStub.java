package com.eecs3311.persistence.Review;

import com.eecs3311.model.Review.IReviewModel;

import java.util.ArrayList;

public class ReviewStub implements IReview{


    @Override
    public void submitReview(String review, String rating, String isbn) {

    }

    @Override
    public ArrayList<IReviewModel> getReviewData(String ISBN){
        return null;
    }

    public ReviewStub() {
        System.out.println("This is the Review Stub DB");
    }
}
