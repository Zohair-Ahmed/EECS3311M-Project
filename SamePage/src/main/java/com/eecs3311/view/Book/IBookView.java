package com.eecs3311.view.Book;

import javax.swing.JPanel;

import com.eecs3311.presenter.Book.IBookPresenter;

public interface IBookView {
    IBookPresenter getPresenter();

    void setPresenter(IBookPresenter bp);

    /**
     * Only called when button needs to be updated
     * @param b - set True if Favourite -> Remove
     */
    void changeFavouriteBtnText(boolean b);

    /**
     * Returns a GUI component relating to the model. Include updatedViewFromModel
     * function to ensure the view is up-to-date and change return type as needed
     * 
     * @return JPanel - Component that has views related to BookModel
     */
    JPanel getView();
}
