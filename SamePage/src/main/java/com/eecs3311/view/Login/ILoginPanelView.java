package com.eecs3311.view.Login;

import javax.swing.JPanel;

import com.eecs3311.presenter.Login.ILoginPresenter;

public interface ILoginPanelView {

    /**
     * Used to update the login status shown on screen wether the login was
     * successful or not
     * 
     * @param status
     */
    void updateLoginStatus(String status);

    /**
     * Returns the presenter
     * 
     * @return ILoginPresenter
     */
    ILoginPresenter getPresenter();

    /**
     * Sets the presenter
     * 
     * @param ilp
     */
    void setPresenter(ILoginPresenter ilp);

    /**
     * Returns the view of the component
     * 
     * @return JPanel
     */
    JPanel getView();
}