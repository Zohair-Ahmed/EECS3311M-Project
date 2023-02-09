package com.eecs3311.view.Book;


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
     *
     * @return
     */
    @Override
    public String getView() {
        // Notes: Include updatedViewFromModel function to ensure the view is up-to-date
        // Notes: Change return type as needed
        // Todo: getView() in BookView to return a JPanel - a better way of displaying instead of using toString()
        System.out.println("Updated: " + bookPresenter.getUpdatedViewFromModel());
        return null;
    }
}