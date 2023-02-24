package com.eecs3311.view.components;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.eecs3311.model.Book.BookDatabase;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.view.IView;

// Create the GUI SearchBarFrame
public class SearchBarFrame implements ActionListener, IView {

    private JPanel container;
    private JTextField searchInputField;
    private JButton searchButton;
    BookDatabase bookDatabase = new BookDatabase();

    // Mediator:
    SearchAndResults mediator;

    public SearchBarFrame(SearchAndResults mediator) {
        this.mediator = mediator;

        this.container = new JPanel();
        initComponents();

        // Styling
        this.container.setSize(new Dimension(500, 500));
    }

    private void initComponents() {
        this.searchInputField = new JTextField("Search...");
        this.searchButton = new JButton("Enter");

        this.container.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 400;
        c.ipady = 20;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.searchInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                searchTextKeyReleased();
            }
        });
        this.container.add(this.searchInputField, c);

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

    @Override
    public JPanel getView() {
        return this.container;
    }

    private void searchTextKeyReleased() {
        getSearchResults(this.searchInputField.getText());
    }

    private void getSearchResults(String search) {
        if (!(search == null || search.length() == 0)) {
            ArrayList<IBookModel> results = new ArrayList<>();
            bookDatabase.getLatestReleases().forEach((book) -> {
                if (book.getTitle().toLowerCase().contains(search.toLowerCase()))
                    results.add(book);
            });
            mediator.updateBookView(results);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            getSearchResults(this.searchInputField.getText());
        }

    }

}
