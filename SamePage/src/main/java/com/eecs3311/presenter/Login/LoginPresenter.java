package com.eecs3311.presenter.Login;

import com.eecs3311.model.Login.ILoginModel;
import com.eecs3311.view.Login.ILoginPanelView;

public class LoginPresenter implements ILoginPresenter {

    private ILoginPanelView loginView;
    private ILoginModel loginModel;

    @Override
    public ILoginModel getModel() {
        return this.loginModel;
    }

    @Override
    public void setModel(ILoginModel ilm) {
        this.loginModel = ilm;
    }

    @Override
    public ILoginPanelView getLoginPanelView() {
        return this.loginView;
    }

    @Override
    public void updateModelFromView(String username, String password) {
        this.loginModel.updateModelFromView(username, password);
    }

    @Override
    public void updateViewFromModel(String loginMessage) {
        this.getLoginPanelView().updateLoginStatus(loginMessage);
    }

    /**
     * Update model as needed
     */
    @Override
    public ILoginModel getUpdatedViewFromModel() {
        return this.loginModel;
    }

    @Override
    public void setView(ILoginPanelView ilv) {
        this.loginView = ilv;
    }
}