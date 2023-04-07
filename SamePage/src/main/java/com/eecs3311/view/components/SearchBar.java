package com.eecs3311.view.components;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;

// Create the GUI SearchBarFrame
public class SearchBar implements ActionListener, IPanelView {

    private JPanel container;
    private JTextField searchInputField;
    private JButton searchButton;

    // Mediator:
    private ResultsMediator mediator;

    public SearchBar(ResultsMediator mediator) {
        this.mediator = mediator;
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

    // Gets results when key is released
    private void searchTextKeyReleased() {
        getSearchResults(this.searchInputField.getText());
    }

    // Searches results through db and updates mediator object to update results
    // panel
    private void getSearchResults(String search) {
        ArrayList<IBookModel> results = new ArrayList<>();
        Database.getBookInstance().getLatestReleases().forEach((book) -> {
            if (book.getTitle().toLowerCase().contains(search.toLowerCase()))
                results.add(book);
        });
        mediator.updateBookView(results);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            String searchInput = this.searchInputField.getText();
            if (searchInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.container, "Please enter Book name.", "Empty search input", JOptionPane.WARNING_MESSAGE);
            } else {
                getSearchResults(this.searchInputField.getText());
            }
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