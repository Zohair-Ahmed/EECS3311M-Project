package com.eecs3311.presenter.Book;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.view.Book.IBookView;

public class BookPresenter implements IBookPresenter {

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
     * Sends updated information about the model to the view
     *
     * @return - implement return type as needed
     */
    @Override
    public IBookModel getUpdatedViewFromModel() {
        return this.bookModel;
    }

    public void updateModelFavBooks() {
        getModel().addFavoriteBook();
    }

    public void removeFavBook() {
        getModel().removeFavoriteBook();
    }

    public boolean checkModelFavBooks() {
        boolean check = getModel().checkFavoriteBook();
        return check;
    }
}
