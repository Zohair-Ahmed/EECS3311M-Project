package com.eecs3311.view.components;

import com.eecs3311.model.Follower.IFollowerModel;
import com.eecs3311.persistence.Database;
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

    public UserResultsPanel() {
        initComponents();
    }

    public UserResultsPanel(ArrayList<IFollowerModel> following) {
        initReleaseContainer(Database.getFollowerInstance().getUserList());
    }

    @Override
    public void initComponents() {
        container.setLayout(new GridBagLayout());
        initReleaseContainer(Database.getFollowerInstance().getUserList());
    }

    // Update user view from search input from search bar
    public void updateFriendsView(ArrayList<IFollowerModel> results) {
        this.releaseContainer.removeAll();
        this.releaseContainer.revalidate();
        this.releaseContainer.repaint();
        this.container.removeAll();
        this.container.revalidate();
        this.container.repaint();
        state = "resultPage";
        initReleaseContainer(results);
        this.container.updateUI();
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
        results = Database.getFollowerInstance().getUserList();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1250;
        c.ipady = 425;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JScrollPane scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        if (state.equals("releasePage")) {
            this.textJLabel.setText(results.size() + " " + ((results.size() == 1 ? "result" : "results") + " found..."));
            scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            releaseContainer.setLayout(new GridLayout((int)Math.ceil(results.size()/4)+1, 4));
        }
        container.add(scroll, c);
    }

    private void initReleaseContainer(ArrayList<IFollowerModel> results) {
        results = Database.getFollowerInstance().getUserList();
        if (results == null)
            return;
        GridLayout gridLayout = new GridLayout((int)Math.ceil(results.size()/4)+1, 4);
        GridBagConstraints c = new GridBagConstraints();
        releaseContainer.setLayout(gridLayout);
//        results.parallelStream().forEach(ifm -> {
//            releaseContainer.add(ifm.getPresenter().getUserView().getView(), c);
//        });

        for (IFollowerModel ifm : results) {
            releaseContainer.add(ifm.getPresenter().getUserView().getView(), c);
        }

        initTextLayout();
        initScrollPaneView(Database.getFollowerInstance().getUserList());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public JPanel getView() {

        System.out.println(this.container.getComponents());

        return this.container;
    }

    @Override
    public JPanel getParentContainer() {
        return null;
    }

    @Override
    public void setParentContainer(JPanel parent) {
    }
}
