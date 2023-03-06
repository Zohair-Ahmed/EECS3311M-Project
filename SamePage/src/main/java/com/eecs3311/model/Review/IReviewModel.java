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
     * @param irp
     */
    void setPresenter(IReviewPresenter irp);

    /**
     * Updates the model from interactions from the view
     *
     * @param review
     * @param rating
     * @param isbn
     */
    void updateModelFromView(String review, String rating, String isbn);

    public String getUsername();

    public String getRating();

    public String getReview();

}
