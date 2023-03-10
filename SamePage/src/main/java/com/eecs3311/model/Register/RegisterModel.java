package com.eecs3311.model.Register;

import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Register.IRegisterPresenter;

public class RegisterModel implements IRegisterModel {
    private IRegisterPresenter RegisterPresenter;
    private String username;
    private String email;
    private String password;

    @Override
    public IRegisterPresenter getPresenter() {
        return RegisterPresenter;
    }

    @Override
    public void setPresenter(IRegisterPresenter irp) {
        this.RegisterPresenter = irp;
    }

    @Override
    public void updateModelFromView(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        validateRegister();
    }

    /**
     * Passes the register credentials from user input to the persistence layer to check for existing users before adding to database
     * Passes to the presenter a status update string for the current register attempt to be shown on RegisterView
     */
    private void validateRegister() {
        String queryResult = Database.getRegisterInstance().registerUser(this.username, this.email, this.password);
        this.getPresenter().updateViewFromModel(queryResult);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}