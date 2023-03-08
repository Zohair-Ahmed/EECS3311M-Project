package com.eecs3311.model.Register;

import com.eecs3311.presenter.Register.IRegisterPresenter;
public interface IRegisterModel {
/**
     * Return the presenter
     * 
     * @return IRegisterPresenter
     */
    IRegisterPresenter getPresenter();

    /**
     * Sets the presenter
     * 
     * @param ilp presenter
     */
    void setPresenter(IRegisterPresenter ilp);

    /**
     * Updates the model from interactions from the view
     * 
     * @param username username
     * @param email email
     * @param password password
     */
    void updateModelFromView(String username, String email, String password);
}

