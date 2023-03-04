package com.eecs3311.view.Login;

import javax.swing.JPanel;

import com.eecs3311.presenter.Login.ILoginPresenter;
import com.eecs3311.view.layout.Main;

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

    
    /*
     * Sets the Main for the Login View, used for when a successful login is made and login page should be disabled
     */
    void setMain(Main main);
}