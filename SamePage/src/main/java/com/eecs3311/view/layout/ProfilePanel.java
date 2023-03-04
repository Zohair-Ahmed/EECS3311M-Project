package com.eecs3311.view.layout;

import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.ProfileIcon;
import com.eecs3311.view.components.ResultsMediator;
import com.eecs3311.view.components.ResultsPanel;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel implements IPanelView {

    private JPanel root;
    private ResultsMediator mediator;
    private ResultsPanel lbv;

    public ProfilePanel() {
        root = new JPanel(); // Root panel
        root.setLayout(new GridBagLayout());
        mediator = new ResultsMediator(); // Used for connecting search bar and results panel
        lbv = new ResultsPanel(mediator); // Latest book view (results panel)
        initComponents();
    }
    @Override
    public JPanel getView() {
        return root;
    }

    @Override
    public JPanel getParentContainer() {
        return null;
    }

    @Override
    public void setParentContainer(JPanel parent) {

    }

    @Override
    public void initComponents() {
        initProfileLayout();
        initUserPanel();
    }

    private void initProfileLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 0;
        c.ipady = 150;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        root.add(lbv.getView(), c);
    }

    private void initUserPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JPanel userPanel = new JPanel();
        ProfileIcon profileIcon = new ProfileIcon("SamePage");
        userPanel.add(profileIcon);
        root.add(userPanel, c);
    }
}
