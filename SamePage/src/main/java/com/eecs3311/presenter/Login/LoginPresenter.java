package com.eecs3311.presenter.Login;

import javax.swing.*;

import com.eecs3311.model.Member;
import com.eecs3311.view.layout.LoginPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPresenter {

    private LoginPanel loginView;
    private Member memberModel;
    private String status = "";

    /*
     * The implementation for the LoginPresenter class is done in the constructor
     * because the constructor will be
     * called once when an instance of the class is created. The connection between
     * LoginView and Member components
     * could be done when created in the constructor and we configure necessary
     * listeners.
     * 
     */

    // ToDo: Fix logic to make it so logic is not in presenter - should be in model
    public LoginPresenter(LoginPanel loginView, Member memberModel) {
        this.loginView = loginView; // Created connection between LoginPresenter with Member (Model) and LoginView
        this.memberModel = memberModel;
        loginView.setLoginPerformed(new ActionListener() {
            // On click of the "login" button from Login window the presenter will retrieve
            // data from view and model to process login request
            public void actionPerformed(ActionEvent e) {
                String email = loginView.getEmail();
                String password = loginView.getPassword();
                memberModel.setEmail(email);
                memberModel.setPassword(password);
                if (!(memberModel.getEmail().equals("temp@mail.ca"))) {
                    status = "This email is not linked to a SamePage account";
                } else if (!(memberModel.getPassword().equals("Test1234"))) {
                    status = "Incorrect password";
                } else if (memberModel.validLogin()) {
                    status = "You are logged in to SamePage as " + memberModel.getName();
                } else // login error showed to view
                    status = "Failed to login to SamePage";
                loginView.loginStatus(status);
            }
        });
    }
}