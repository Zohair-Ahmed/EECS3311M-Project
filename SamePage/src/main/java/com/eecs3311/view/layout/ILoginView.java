package com.eecs3311.view.layout;

import java.awt.event.ActionListener;

public interface ILoginView {

    void setLoginPerformed(ActionListener listener);
    String getEmail();
    String getPassword();
    void loginStatus(String status);

}