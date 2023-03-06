package com.eecs3311.presenter.Review;

import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.presenter.Book.BookPresenter;
import com.eecs3311.view.Book.DisplayBookInformation;
import com.eecs3311.view.Review.IReviewPanelView;

import java.awt.print.Book;
import java.io.IOException;

public class ReviewPresenter implements IReviewPresenter{

    private IReviewModel reviewModel;

    @Override
    public IReviewModel getModel() {
        return reviewModel;
    }

    @Override
    public void setModel(IReviewModel irm) {
        reviewModel = irm;
    }

    @Override
    public IReviewPanelView getLoginPanelView() {
        return null;
    }

    @Override
    public void setView(IReviewPanelView irv) {

    }

    @Override
    public void updateModelFromView(String review, String rating, String isbn) {
        getModel().updateModelFromView(review, rating, isbn);
    }

    @Override
    public void updateViewFromModel(String message) {
        try {
            DisplayBookInformation.getInstance(null).setErrorMessage(message);
        } catch (IOException e) {

        } catch (NullPointerException e) {

        }

    }

}
