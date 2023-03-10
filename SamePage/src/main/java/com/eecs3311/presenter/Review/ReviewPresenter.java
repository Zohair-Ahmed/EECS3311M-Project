package com.eecs3311.presenter.Review;

import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.view.Review.IReviewPanelView;

public class ReviewPresenter implements IReviewPresenter{

    private IReviewModel reviewModel;
    private IReviewPanelView reviewPanelView;

    @Override
    public IReviewModel getModel() {
        return reviewModel;
    }

    @Override
    public void setModel(IReviewModel irm) {
        reviewModel = irm;
    }

    @Override
    public IReviewPanelView getReviewPanelView() {return this.reviewPanelView;}


    @Override
    public void setView(IReviewPanelView irv) {
        this.reviewPanelView = irv;
    }

    @Override
    public void updateModelFromView(String review, String rating, String isbn) {
        getModel().updateModelFromView(review, rating, isbn);
    }

    @Override
    public void updateViewFromModel() {
    }

}
