package com.eecs3311.view.components;

import com.eecs3311.model.Login.Follower.IFollowerModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.view.Follower.IFollowerView;
import com.eecs3311.view.IPanelView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserResultsPanel implements ActionListener, IPanelView {

    private JPanel container = new JPanel();
    private JPanel releaseContainer = new JPanel();
    private JLabel textJLabel = new JLabel("Find Friends");
    private String state = "releasePage";

    private FollowerMediator mediator;

    public UserResultsPanel(FollowerMediator mediator) {
        this.mediator = mediator;
        initComponents();
    }

        // Update book view from search input from search bar
    public void updateFriendsView(ArrayList<IFollowerModel> results) {
        this.releaseContainer.removeAll();
        this.container.removeAll();
        initReleaseContainer(results);
        this.container.updateUI();
    }

    @Override
    public JPanel getView() {
        return this.container;
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
        container.setLayout(new GridBagLayout());
        initReleaseContainer(Database.getFollowerInstance().getUserList());
    }

    private void initReleaseContainer(ArrayList<IFollowerModel> results) {
        releaseContainer.setPreferredSize(new Dimension(1250, 425));

        if (results == null)
            return;

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 10, 10);
        releaseContainer.setLayout(flowLayout);

        for (IFollowerModel ifm : results) {
            System.out.println("--> "+ifm.getCurrentUser());
            IFollowerView ifv = ifm.getPresenter().getUserView();
            releaseContainer.add(ifv.getView());
        }

        initTextLayout();
        initScrollPaneView(results);
    }

        /**
     * To initialize
     */
    private void initTextLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1;
        c.ipady = 1;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        container.add(textJLabel, c);
    }

    private void initScrollPaneView(ArrayList<IFollowerModel> results) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1250;
        c.ipady = 425;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JScrollPane scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.textJLabel.setText(results.size() + " " + ((results.size() == 1 ? "result" : "results") + " found..."));
        scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        releaseContainer.setLayout(new GridLayout((int)Math.ceil(results.size()/4)+1, 4));
        container.add(scroll, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
