package com.eecs3311.presenter.Register;

import com.eecs3311.model.Register.IRegisterModel;
import com.eecs3311.view.Resgister.IRegisterView;

// import com.eecs3311.model.Register.IRegisterModel;
// import com.eecs3311.view.Register.IRegisterView;

public class RegisterPresenter implements IRegisterPresenter {

    // Each Presenter object should have 1 Model and 1 View
    private IRegisterModel RegisterModel;
    private IRegisterView RegisterView;

    @Override
    public IRegisterModel getModel() {
        return RegisterModel;
    }

    @Override
    public void setModel(IRegisterModel bm) {
        this.RegisterModel = bm;
    }

    @Override
    public IRegisterView getIRegisterView() {
        return RegisterView;
    }

    @Override
    public void setView(IRegisterView bv) {
        this.RegisterView = bv;
    }

    /**
     * Presenter class gets details of what to change for the model.
     * 
     * @param title - Attribute to be updated/changed - can be multiple
     */
    @Override
    public void updateModelFromView(String title) {
        // getModel().setTitle(title);
    }

    @Override
    public void updateViewFromModel() {

    }

    /**
     * Sends updated information about the model to the view
     *
     * @return - implement return type as needed
     */
    @Override
    public IRegisterModel getUpdatedViewFromModel() {
        return this.RegisterModel;
    }
}
