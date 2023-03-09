package com.eecs3311.view.Review;

import com.eecs3311.presenter.Review.IReviewPresenter;

import javax.swing.*;

public interface IReviewPanelView {
    /**
     * Returns the presenter
     *
     * @return ILoginPresenter
     */
    IReviewPresenter getPresenter();

    /**
     * Sets the presenter
     *
     * @param irp
     */
    void setPresenter(IReviewPresenter irp);

    /**
     * Returns the view of the component
     *
     * @return JPanel
     */
    JPanel getView();
}
