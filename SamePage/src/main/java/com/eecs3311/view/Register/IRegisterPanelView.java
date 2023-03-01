package com.eecs3311.view.Register;

import javax.swing.JPanel;

import com.eecs3311.presenter.Register.IRegisterPresenter;

public interface IRegisterPanelView {

    /**
     * Used to update the Register status shown on screen wether the Register was
     * successful or not
     * 
     * @param status
     */
    void updateRegisterStatus(String status);

    /**
     * Returns the presenter
     * 
     * @return IRegisterPresenter
     */
    IRegisterPresenter getPresenter();

    /**
     * Sets the presenter
     * 
     * @param ilp
     */
    void setPresenter(IRegisterPresenter ilp);

    /**
     * Returns the view of the component
     * 
     * @return JPanel
     */
    JPanel getView();
}
