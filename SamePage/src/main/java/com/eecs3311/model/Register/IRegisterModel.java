package com.eecs3311.model.Register;

import com.eecs3311.presenter.Register.IRegisterPresenter;
import com.eecs3311.persistence.Register.RegisterDB;


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
     * @param ilp
     */
    void setPresenter(IRegisterPresenter ilp);

    /**
     * Updates the model from interactions from the view
     * 
     * @param username
     * @param email
     * @param password
     */
    void updateModelFromView(String username, String email, String password);
}

