package com.eecs3311.presenter.Login;

import com.eecs3311.model.Login.ILoginModel;
import com.eecs3311.view.Login.ILoginPanelView;

public interface ILoginPresenter {
    /**
     * Returns the model
     * 
     * @return ILoginModel
     */
    ILoginModel getModel();

    /**
     * Sets the model
     * 
     * @param ilm
     */
    void setModel(ILoginModel ilm);

    /**
     * Returns the LoginPanelView
     * 
     * @return ILoginPanelView
     */
    ILoginPanelView getLoginPanelView();

    /**
     * Sets the view
     * 
     * @param ilv
     */
    void setView(ILoginPanelView ilv);

    /**
     * Updates the model from view interactions. To be used when a UI component is
     * changing any information on the screen.
     * Ex: Loading new page, submitting a form, etc. Params would be made according
     * to what is expected to be changed through an action
     */
    void updateModelFromView(String username, String password);

    /**
     * Updates the view based on the current valid changes made to the model
     * 
     * @param loginMessage - Displays a message based on login credentials
     */
    void updateViewFromModel(String loginMessage);

    /**
     * Used when fetching updated Model data. getModel()
     * can be used but this class is more of a customizable class
     * based on the object. May need to implement separate methods
     * for each attribute - TBD
     *
     * @return any updated attribute(s)
     */
    ILoginModel getUpdatedViewFromModel();
}
