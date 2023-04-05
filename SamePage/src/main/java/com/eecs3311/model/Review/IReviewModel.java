package com.eecs3311.model.Review;

import com.eecs3311.presenter.Review.IReviewPresenter;

public interface IReviewModel {
    /**
     * Return the presenter
     *
     * @return IReviewPresenter
     */
    IReviewPresenter getPresenter();

    /**
     * Sets the presenter
     *
     * @param irp presenter
     */
    void setPresenter(IReviewPresenter irp);

    /**
     * Updates the model from interactions from the view
     *
     * @param review review
     * @param rating rating
     * @param isbn isbn
     */
    void updateModelFromView(String review, String rating, String isbn);

    String getUsername();

    String getRating();

    String getReview();

    String getDate();

    String getISBN();

}
