package com.eecs3311.presenter.Register;

import com.eecs3311.model.Register.IRegisterModel;
import com.eecs3311.view.Register.IRegisterPanelView;

// import com.eecs3311.model.Register.IRegisterModel;
// import com.eecs3311.view.Register.IRegisterView;

public class RegisterPresenter implements IRegisterPresenter {

    // Each Presenter object should have 1 Model and 1 View
    private IRegisterModel RegisterModel;
    private IRegisterPanelView RegisterView;

    @Override
    public IRegisterModel getModel() {
        return this.RegisterModel;
    }

    @Override
    public void setModel(IRegisterModel ilm) {
        this.RegisterModel = ilm;
    }

    @Override
    public IRegisterPanelView getRegisterPanelView() {
        return this.RegisterView;
    }

    @Override
    public void updateModelFromView(String username, String email, String password) {
        this.RegisterModel.updateModelFromView(username, email, password);
    }

    @Override
    public void updateViewFromModel(String RegisterMessage) {
        this.getRegisterPanelView().updateRegisterStatus(RegisterMessage);
    }

    /**
     * Update model as needed
     */
    @Override
    public IRegisterModel getUpdatedViewFromModel() {
        return this.RegisterModel;
    }

    @Override
    public void setView(IRegisterPanelView ilv) {
        this.RegisterView = ilv;
    }
}
