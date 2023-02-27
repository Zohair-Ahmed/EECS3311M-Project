package com.eecs3311.presenter.Register;

import com.eecs3311.model.Register.IRegisterModel;
import com.eecs3311.view.Resgister.IRegisterView;

public interface IRegisterPresenter {
    IRegisterModel getModel();

    void setModel(IRegisterModel irm);

    IRegisterView getIRegisterView();

    void setView(IRegisterView irv);

    /**
     * Updates the model from view interactions. To be used when a UI component is
     * changing any information on the screen.
     * Ex: Loading new page, submitting a form, etc. Params would be made according
     * to what is expected to be changed through an action
     */
    void updateModelFromView(String title);

    /**
     * Updates the view based on the current valid changes made to the model
     */
    void updateViewFromModel();

    IRegisterModel getUpdatedViewFromModel();
}