package com.eecs3311.presenter.Login;

import javax.swing.*;

import com.eecs3311.model.Member;
import com.eecs3311.view.layout.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPresenter {
    
    private LoginView loginView;
    private Member memberModel;
    private String status = "";

    /* The implementation for the LoginPresenter class is done in the constructor because the constructor will be 
    called once when an instance of the class is created. The connection between LoginView and Member components 
    could be done when created in the constructor and we configure necessary listeners.

    */

    public LoginPresenter(LoginView loginView, Member memberModel) {
        this.loginView = loginView;
        this.memberModel = memberModel;

        loginView.setLoginPerformed(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String email = loginView.getEmail();
                String password = loginView.getPassword();
                

                memberModel.setEmail(email);
                memberModel.setPassword(password);

                if (memberModel.getEmail().isEmpty()) {
                    // user name error shown to view
                    status = "This email is not linked to a SamePage account";
                }
        
                if (memberModel.getPassword().isEmpty()) {
                    // password error shown to view
                    status = "Incorrect password";
                }
        
                if (memberModel.validLogin()) {
                    // login success showed to view
                    status = "You are logged in to SamePage as " + memberModel.getName();
                }
        
                else {
                    // login error showed to view
                    status = "Failed to login to SamePage";
                }

                loginView.loginStatus(status);
            }

        });

    }



        
        
    

}