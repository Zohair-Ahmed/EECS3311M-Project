package com.eecs3311.presenter.Review;

import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.view.Review.IReviewPanelView;

public interface IReviewPresenter {
    /**
     * Returns the model
     *
     * @return IReviewModel
     */
    IReviewModel getModel();

    /**
     * Sets the model
     *
     * @param irm model
     */
    void setModel(IReviewModel irm);

    /**
     * Returns the LoginPanelView
     *
     * @return ILoginPanelView
     */
    IReviewPanelView getReviewPanelView();

    /**
     * Sets the view
     *
     * @param irv view
     */
    void setView(IReviewPanelView irv);

    /**
     * Updates the model from view interactions. To be used when a UI component is
     * changing any information on the screen.
     * Ex: Loading new page, submitting a form, etc. Params would be made according
     * to what is expected to be changed through an action
     */
    void updateModelFromView(String review, String rating, String isbn);

    /**
     * Used when fetching updated Model data. getModel()
     * can be used but this class is more of a customizable class
     * based on the object. May need to implement separate methods
     * for each attribute - TBD
     *
     */
    void updateViewFromModel();
}
