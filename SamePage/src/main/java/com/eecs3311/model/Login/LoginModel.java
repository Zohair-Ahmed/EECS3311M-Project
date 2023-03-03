package com.eecs3311.model.Login;

import com.eecs3311.model.User;
import com.eecs3311.persistence.Login.LoginDB;
import com.eecs3311.presenter.Login.ILoginPresenter;

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

    private void validateLogin() {
        String loginMessage = "Invalid Login Credentials";
        LoginDB loginDB = new LoginDB();
        if (loginDB.isLoginValid(getEmail(), getPassword()))
            loginMessage = "Successfully Logged in as " + User.getInstance().getUsername();

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
