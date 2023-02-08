package com.eecs3311.presenter.Book;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.view.Book.IBookView;

public interface IBookPresenter {
    public IBookModel getModel();

    public void setModel(IBookModel bm);

    public IBookView getView();

    public void setView(IBookView bv);

    /**
     * To be used when a UI component is changing any information on the screen.
     * Ex: Loading new page, submitting a form, etc. Params would be made according
     * to what is expected to be changed through an action
     * 
     * @param title - Attribute(s) to be changed
     */
    public void updateModelFromView(String title);

    /**
     * Used when fetching updated Model data. getModel()
     * can be used but this class is more of a customizable class
     * based on the object. May need to implement separate methods
     * for each attribute - TBD
     * 
     * @return any updated attribute(s)
     */
    public String getUpdatedViewFromModel();
}
