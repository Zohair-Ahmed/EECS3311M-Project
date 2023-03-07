package com.eecs3311.model.Review;

import com.eecs3311.model.User;
import com.eecs3311.model.enums.State;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Review.IReviewPresenter;
import com.eecs3311.presenter.Review.ReviewPresenter;

import java.util.Date;

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

    private String username;
    private String review;
    private String rating;
    private String isbn;
    private String date;
    private IReviewPresenter reviewPresenter;
    private int reviewLikes;
    private int reviewDislikes;
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
        this.review = review;
        this.rating = rating;
        this.isbn = isbn;

        createReview(review, rating, isbn);
    }

    public void createReview(String review, String rating, String isbn) {
        if (User.getInstance().getLoginState().equals(State.MEMBER)) {
            Database.getReviewInstance().submitReview(review, rating, isbn);
        }
    }
}
