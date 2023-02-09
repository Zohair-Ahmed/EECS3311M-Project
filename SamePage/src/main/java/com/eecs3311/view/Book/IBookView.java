package com.eecs3311.view.Book;

import com.eecs3311.presenter.Book.IBookPresenter;

public interface IBookView {
    IBookPresenter getPresenter();

    void setPresenter(IBookPresenter bp);

    /**
     * Shows all the GUI code/components relating to the model
     *
     * @return
     */
    String getView();
}
