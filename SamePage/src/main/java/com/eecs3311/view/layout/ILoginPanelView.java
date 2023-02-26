package com.eecs3311.view.layout;

import java.awt.event.ActionListener;

public interface ILoginPanelView {

    void setLoginPerformed(ActionListener listener);

    String getEmail();

    String getPassword();

    void loginStatus(String status);

}