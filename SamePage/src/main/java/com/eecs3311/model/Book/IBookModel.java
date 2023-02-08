package com.eecs3311.model.Book;

import com.eecs3311.presenter.Book.IBookPresenter;

// ! NOTE: Getters and Setters for the attributes - does not have to be implemented in the interface
public interface IBookModel {

    public String getTitle();

    public void setTitle(String title);

    public void setPresenter(IBookPresenter bookPresenter);

}
