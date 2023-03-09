package com.eecs3311.presenter.Book;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.view.Book.IBookView;

public interface IBookPresenter {
    IBookModel getModel();

    void setModel(IBookModel bm);

    IBookView getView();

    void setView(IBookView bv);

    /**
     * Used when fetching updated Model data. getModel()
     * can be used but this class is more of a customizable class
     * based on the object. May need to implement separate methods
     * for each attribute - TBD
     *
     * @return any updated attribute(s)
     */
    IBookModel getUpdatedViewFromModel();
}
