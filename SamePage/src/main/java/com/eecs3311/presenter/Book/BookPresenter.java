package com.eecs3311.presenter.Book;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.view.Book.IBookView;

public class BookPresenter implements IBookPresenter {

    // Each Presenter object should have 1 Model and 1 View
    private IBookModel bookModel;
    private IBookView bookView;

    @Override
    public IBookModel getModel() {
        return bookModel;
    }

    @Override
    public void setModel(IBookModel bm) {
        this.bookModel = bm;
    }

    @Override
    public IBookView getView() {
        return bookView;
    }

    @Override
    public void setView(IBookView bv) {
        this.bookView = bv;
    }

    /**
     * Presenter class gets details of what to change for the model.
     * 
     * @param title - Attribute to be updated/changed - can be multiple
     */
    @Override
    public void updateModelFromView(String title) {
        // There may be one method for every mutable attribute - TBD
        // Default: getModel().<setAttribute(param)> -- depending on what's exactly
        // being changed, it won't be enough
        getModel().setTitle(title);
    }

    /**
     * Sends updated information about the model to the view
     * 
     * @return - implement return type as needed
     */
    @Override
    public String getUpdatedViewFromModel() {
        return getView().getPresenter().getModel().getTitle();
        // would we print out these values or return them? Wouldn't they be returned so
        // the View can read them?
        // I mean its fine for now, but just wondering how would the view get these
        // values for the future?

        // To be honest, I think its fine for itr 1 - since we are hardcoding the values
        // for itr 1 anyway, plus we don't have a view you can test on

        // B/c it's presenter, we should return the values -- only have void right now,
        // but will change to return
    }
}
