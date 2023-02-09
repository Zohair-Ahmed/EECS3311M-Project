package com.eecs3311.model.Book;

import com.eecs3311.model.Reviews;
import com.eecs3311.presenter.Book.IBookPresenter;

import java.util.ArrayList;

// ! NOTE: Getters and Setters for the attributes - does not have to be implemented in the interface
public interface IBookModel {
    String getTitle();
    String getAuthor();
    int getISBN();
    String getGenre();
    void setTitle(String title);
    void setAuthor(String author);
    void setPresenter(IBookPresenter bookPresenter);
    String getDescription();
    ArrayList<Reviews> getReviews();
    String toString();
}
