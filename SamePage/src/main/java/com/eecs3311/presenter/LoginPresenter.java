package com.eecs3311.presenter;

public class LoginPresenter {
    
    private LoginView loginView;
    private Member memberModel;

    public LoginPresenter(LoginView loginView, Member memberModel) {
        this.loginView = loginView;
        this.memberModel = memberModel;
    }

    public void login() {
        if (memberModel.getEmail.isEmpty()) {
            // user name error shown to view
        }

        if (memberModel.getPassword().isEmpty()) {
            // password error shown to view
        }

        if (memberModel.validLogin()) {
            // login success showed to view
        }

        else {
            // login error showed to view
        }
    }
}