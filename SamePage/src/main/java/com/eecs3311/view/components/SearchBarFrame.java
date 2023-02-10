package com.eecs3311.view.components;

import javax.swing.*;

// Create the GUI SearchBarFrame
public class SearchBarFrame {

    JList<String> booksList = new JList<>();

    JTextField searchBar = new JTextField("Search");

    DefaultListModel<String> defaultListModel = new DefaultListModel<>();

    public JTextField getView() {
        return this.searchBar;
    }

}
