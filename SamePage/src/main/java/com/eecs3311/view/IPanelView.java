package com.eecs3311.view;

import javax.swing.JPanel;

public interface IPanelView {
    /**
     * Returns the view of the component
     * 
     * @return JPanel - returns current view
     */
    JPanel getView();

    /**
     * To be used soon - returns parent container
     * 
     * @return JPanel - returns the parent view of the current view
     */
    JPanel getParentContainer();

    /**
     * Sets parent container of current view
     * 
     * @param parent - parent view
     */
    void setParentContainer(JPanel parent);

    /**
     * Method to be used to initialize UI logic
     */
    void initComponents();
}
