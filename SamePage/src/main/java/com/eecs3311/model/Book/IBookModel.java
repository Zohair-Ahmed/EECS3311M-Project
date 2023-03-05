package com.eecs3311.model.Book;

import com.eecs3311.model.Reviews;
import com.eecs3311.presenter.Book.IBookPresenter;

import java.util.ArrayList;

// Interface for accessing data of a book object
public interface IBookModel {
    String getTitle();

    String getAuthor();

    String getISBN();

    String getGenre();

    void setTitle(String title);

    void setAuthor(String author);

    void setPresenter(IBookPresenter bookPresenter);

    IBookPresenter getPresenter();

    String getDescription();

    ArrayList<Reviews> getReviews();

    String toString();

    String getImg();
}
