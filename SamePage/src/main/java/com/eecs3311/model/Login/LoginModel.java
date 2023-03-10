package com.eecs3311.model.Login;

import com.eecs3311.model.User;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Login.ILoginPresenter;
import com.eecs3311.view.Book.DisplayBookInformation;

public class LoginModel implements ILoginModel {

    private ILoginPresenter loginPresenter;
    private String email;
    private String password;

    @Override
    public ILoginPresenter getPresenter() {
        return loginPresenter;
    }

    @Override
    public void setPresenter(ILoginPresenter ilp) {
        this.loginPresenter = ilp;
    }

    @Override
    public void updateModelFromView(String email, String password) {
        this.email = email;
        this.password = password;
        validateLogin();
    }

    /**
     * Passes the login credentials from user input to the persistence layer to check for valid login attempt
     * Passes to the presenter a status update string for the current login attempt to be shown on LoginView
     */
    private void validateLogin() {
        String loginMessage = "Invalid Login Credentials";
        if (Database.getLoginInstance().isLoginValid(getEmail(), getPassword())) {
            loginMessage = "Successfully Logged in as " + User.getInstance().getUsername();
            DisplayBookInformation.setErrorMessage("");
        }
        this.getPresenter().updateViewFromModel(loginMessage);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
