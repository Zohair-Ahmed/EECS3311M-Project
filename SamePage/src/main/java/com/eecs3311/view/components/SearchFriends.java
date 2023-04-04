package com.eecs3311.view.components;

import com.eecs3311.model.Login.Follower.IFollowerModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SearchFriends implements ActionListener, IPanelView {

    private JPanel container;
    private JTextField searchInputField;
    private JButton searchButton;
    private JComboBox followerFilter;
    private FollowerMediator mediator;

    public SearchFriends(FollowerMediator mediator) {
        this.mediator = mediator;
        this.container = new JPanel();
        initComponents();
        this.container.setSize(new Dimension(1000, 500));
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

    // Searches results from list of users db and updates user panel object to update results
    private void getSearchResults(String search) {
            String finalSearch = search.toLowerCase();

            ArrayList<IFollowerModel> results;

            if (followerFilter.getSelectedIndex() == 0) {
              results = Database.getFollowerInstance().getAllUsers();
            } else if (followerFilter.getSelectedIndex() == 1) { // My Following
                results = Database.getFollowerInstance().getFollowing();
            } else { // My Followers
                results = Database.getFollowerInstance().getFollowers();
            }

            ArrayList<IFollowerModel> finalResults = new ArrayList<>();

            results.forEach((user -> {
                if (user.getCurrentUser().contains(finalSearch)){
                    System.out.println(user.getCurrentUser());
                    finalResults.add(user);
                }
            }));
            mediator.updateUserResultsPanelView(finalResults);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            getSearchResults(this.searchInputField.getText());
        }

    }

    @Override
    public void initComponents() {
        this.searchInputField = new JTextField("");
        this.searchButton = new JButton("Enter");
        this.container.setLayout(new GridBagLayout());
        initSearchInputFieldLayout();
        initSearchButtonLayout();
        initFilterDropDown();
    }

    private void initFilterDropDown() {
        String options[] = {"All", "My Following", "My Followers"};
        followerFilter = new JComboBox(options);
        followerFilter.setSelectedIndex(0);
        followerFilter.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 11;
        c.ipady = 15;
        c.weightx = 0.0;
        c.gridwidth = 2;
        c.gridx = 402;
        c.gridy = 1;
        this.container.add(followerFilter, c);

    }

    // Initialize search button layout
    private void initSearchButtonLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 10;
        c.ipady = 15;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 401;
        c.gridy = 0;
        this.searchButton.addActionListener(this);
        this.container.add(this.searchButton, c);
    }

    // Initialize search input layout
    private void initSearchInputFieldLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 400;
        c.ipady = 20;
        c.weightx = 0.0;
        c.gridwidth = 1;
        this.searchInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.container.add(this.searchInputField, c);
    }
}