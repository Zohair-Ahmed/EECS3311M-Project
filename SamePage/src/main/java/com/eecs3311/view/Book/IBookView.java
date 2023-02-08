package com.eecs3311.view.Book;

import com.eecs3311.presenter.Book.IBookPresenter;

public interface IBookView {
    public IBookPresenter getPresenter();

    public void setPresenter(IBookPresenter bp);

    /**
     * Shows all the GUI code/components relating to the model
     */
    public void getView();
}
