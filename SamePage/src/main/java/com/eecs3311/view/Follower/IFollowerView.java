package com.eecs3311.view.Follower;

import com.eecs3311.presenter.User.IFollowerPresenter;

import javax.swing.*;

public interface IFollowerView {
    /**
     * Returns the presenter
     *
     * @return ILoginPresenter
     */
    IFollowerPresenter getPresenter();

    /**
     * Sets the presenter
     *
     * @param iup
     */
    void setPresenter(IFollowerPresenter iup);

    void initComponents();

    /**
     * Returns the view of the component
     *
     * @return JPanel
     */
    JPanel getView();
}
