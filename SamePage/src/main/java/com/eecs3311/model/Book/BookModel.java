package com.eecs3311.model.Book;

import com.eecs3311.presenter.Book.IBookPresenter;

public class BookModel implements IBookModel {

    // Attribute(s)
    private String title;

    // Each Model class needs ONE Presenter class Interface
    private IBookPresenter bookPresenter;

    public BookModel() {
        // Object initialiaztion ...
        this.title = "a";
    }

    @Override
    public void setPresenter(IBookPresenter bp) {
        this.bookPresenter = bp;
    }

    // Getters and Setters
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

}
