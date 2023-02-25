package com.eecs3311.view;

import javax.swing.JPanel;

public interface IPanelView {
    JPanel getView();

    JPanel getParentContainer();

    void setParentContainer(JPanel parent);

    void initComponents();
}
