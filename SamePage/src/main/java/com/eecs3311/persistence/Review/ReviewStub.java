package com.eecs3311.persistence.Review;

public class ReviewStub implements IReview{


    @Override
    public void submitReview(String review, String rating, String isbn) {

    }
    public ReviewStub() {
        System.out.println("This is the Review Stub DB");
    }
}
