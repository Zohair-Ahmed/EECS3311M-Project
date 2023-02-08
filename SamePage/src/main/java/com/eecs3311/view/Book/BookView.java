package com.eecs3311.view.Book;

import java.util.Scanner;

import com.eecs3311.presenter.Book.IBookPresenter;

public class BookView implements IBookView {

    private IBookPresenter bookPresenter;

    private String bookName = "";

    public BookView() {
        // Initialize view logic here
    }

    @Override
    public IBookPresenter getPresenter() {
        return bookPresenter;
    }

    @Override
    public void setPresenter(IBookPresenter bp) {
        this.bookPresenter = bp;
    }

    /**
     * Returns a GUI component relating to the model
     */
    @Override
    public void getView() {
        // ToDo: Include updatedViewFromModel function to ensure the view is up-to-date
        // ToDo: Change return type as needed
        System.out.println("Updated: " + bookPresenter.getUpdatedViewFromModel());
    }
}