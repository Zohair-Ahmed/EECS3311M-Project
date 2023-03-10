package com.eecs3311.model.Login;

import com.eecs3311.presenter.Login.ILoginPresenter;

public interface ILoginModel {
    /**
     * Return the presenter
     * 
     * @return ILoginPresenter
     */
    ILoginPresenter getPresenter();

    /**
     * Sets the presenter
     * 
     * @param ilp presenter
     */
    void setPresenter(ILoginPresenter ilp);

    /**
     * Updates the model from interactions from the view
     * 
     * @param username username
     * @param password password
     */
    void updateModelFromView(String username, String password);
}
