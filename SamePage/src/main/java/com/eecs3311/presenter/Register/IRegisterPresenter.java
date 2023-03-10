package com.eecs3311.presenter.Register;

import com.eecs3311.model.Register.IRegisterModel;
import com.eecs3311.view.Register.IRegisterPanelView;

public interface IRegisterPresenter {
    /**
     * Returns the model
     * 
     * @return IRegisterModel
     */
    IRegisterModel getModel();

    /**
     * Sets the model
     * 
     * @param irm model
     */
    void setModel(IRegisterModel irm);

    /**
     * Returns the RegisterPanelView
     * 
     * @return IRegisterPanelView
     */
    IRegisterPanelView getRegisterPanelView();

    /**
     * Sets the view
     * 
     * @param irv view
     */
    void setView(IRegisterPanelView irv);

    /**
     * Updates the model from view interactions. To be used when a UI component is
     * changing any information on the screen.
     * Ex: Loading new page, submitting a form, etc. Params would be made according
     * to what is expected to be changed through an action
     */
    void updateModelFromView(String username, String email, String password);

    /**
     * Updates the view based on the current valid changes made to the model
     * 
     * @param RegisterMessage - Displays a message based on Register credentials
     */
    void updateViewFromModel(String RegisterMessage);

    /**
     * Used when fetching updated Model data. getModel()
     * can be used but this class is more of a customizable class
     * based on the object. May need to implement separate methods
     * for each attribute - TBD
     *
     * @return any updated attribute(s)
     */
    IRegisterModel getUpdatedViewFromModel();
}