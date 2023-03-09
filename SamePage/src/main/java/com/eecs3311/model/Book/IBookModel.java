package com.eecs3311.model.Book;

import com.eecs3311.presenter.Book.IBookPresenter;

// Interface for accessing data of a book object
public interface IBookModel {
    String getTitle();

    String getAuthor();

    String getISBN();

    String getGenre();

    void setPresenter(IBookPresenter bookPresenter);

    IBookPresenter getPresenter();

    String getDescription();

    String getImg();

    double getAverageReview();
}
