package com.eecs3311.view.components;

import com.eecs3311.model.Follower.IFollowerModel;
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
    private UserResultsPanel userResults;

    public SearchFriends(UserResultsPanel userResultsPanel) {
        this.userResults = userResultsPanel;
        this.container = new JPanel();
        initComponents();
        this.container.setSize(new Dimension(500, 500));
    }

    @Override
    public void initComponents() {
        this.searchInputField = new JTextField("Search...");
        this.searchButton = new JButton("Enter");
        this.container.setLayout(new GridBagLayout());
        initSearchInputFieldLayout();
        initSearchButtonLayout();
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

    // Searches results from list of users db and updates user panel object to update results
    private void getSearchResults(String search) {
        if (!(search == null || search.length() == 0)) {
            ArrayList<IFollowerModel> results = new ArrayList<>();
            Database.getFollowerInstance().getUserList().forEach((user) -> {
                if (user.getCurrentUser().contains(search.toLowerCase()))
                    results.add(user);
            });
            userResults.updateFriendsView(results);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            getSearchResults(this.searchInputField.getText());
        }

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
}