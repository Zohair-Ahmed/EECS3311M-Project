package com.eecs3311.model.Login;

import com.eecs3311.presenter.Login.ILoginPresenter;

public class LoginModel implements ILoginModel {

    private ILoginPresenter loginPresenter;
    private String username;
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
    public void updateModelFromView(String username, String password) {
        this.username = username;
        this.password = password;
        validateLogin();
    }

    private void validateLogin() {
        // Write Query
        // Send query to db
        // Get results from db
        // Compare results
        // Based on results, update view

        if (this.username.equals("temp@mail.ca") && this.password.equals("Test1234")) {
            this.getPresenter().updateViewFromModel("Successfully Logged In");
        } else {
            this.getPresenter().updateViewFromModel("Incorrect Details Entered");
        }
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
