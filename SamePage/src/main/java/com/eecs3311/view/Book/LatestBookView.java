package com.eecs3311.view.Book;

import com.eecs3311.model.Book.BookDatabase;
import com.eecs3311.model.Book.IBookModel;

import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * LatestBookView to retireve data from IBookPresenter and IBookModel
 * for the latest available books following MVP patterns
 */
// public class LatestBookView extends JFrame implements ActionListener {

// private BookDatabase bookDatabase;
// private JPanel latestBookViewPanel;
// private JPanel parentPanel;
// private JPanel searchBarPanel = new JPanel();
// private JButton searchBtn = new JButton("Find");

// DefaultListModel<JPanel> defaultListModel = new DefaultListModel<>();
// JList<JPanel> booksList = new JList<>();
// JTextField searchInput = new JTextField("Search");

// public LatestBookView() {

// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

// bookDatabase = new BookDatabase();

// parentPanel = new JPanel();
// parentPanel.setLayout(new GridLayout(3, 1, 2, 2));

// booksList.setLayout(new GridLayout(1,
// bookDatabase.getLatestReleases().size(), 1, 1));

// // Add book models from database to panel
// latestBookViewPanel = new JPanel();
// for (IBookModel ibm : bookDatabase.getLatestReleases()) {
// latestBookViewPanel.add(ibm.getPresenter().getView().getView());
// }

// // parentPanel.add(this.searchInput);

// initSearchBar();
// parentPanel.add(this.searchBarPanel);
// parentPanel.setLayout(new GridLayout(2, 2, 1, 1));

// JScrollPane scroll = new JScrollPane(latestBookViewPanel);
// scroll.setBounds(0, 0, (int) (screenSize.getWidth() * .75), 332);
// parentPanel.add(scroll);
// }

// public void initSearchBar() {
// this.searchBarPanel.add(searchInput);
// this.searchBtn.addActionListener(this);
// this.searchBarPanel.add(searchBtn);
// this.searchBarPanel.setLayout(new GridLayout(1, 2, 1, 1));
// }

// public JPanel getView() {
// return this.parentPanel;
// }

// @Override
// public void actionPerformed(ActionEvent e) {
// if (e.getSource() == this.searchBtn) {

// }
// }
// }

public class LatestBookView extends JFrame implements ActionListener {

    private JPanel container = new JPanel();

    private JPanel searchContainer = new JPanel();
    private JTextField searchInput = new JTextField("Search...");
    private JButton searchBtn = new JButton("Enter");

    private JPanel childContainer = new JPanel();
    private JLabel titleLbl = new JLabel("Latest Releases");

    private JPanel releaseContainer = new JPanel();

    private BookDatabase bookDatabase = new BookDatabase();

    public LatestBookView() {
        initSearchContainer();
        this.container.add(this.searchContainer);

        initChildContainer();
        this.container.add(this.childContainer);

    }

    private void initSearchContainer() {
        this.searchContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 400;
        c.ipady = 20;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.searchInput.addKeyListener(new KeyListener() {
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
        this.searchContainer.add(this.searchInput, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 10;
        c.ipady = 15;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 401;
        c.gridy = 0;
        this.searchBtn.addActionListener(this);
        this.searchContainer.add(this.searchBtn, c);
    }

    private void initChildContainer() {
        this.childContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 400;
        c.ipady = 100;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.titleLbl.setBackground(Color.red);
        this.childContainer.add(this.titleLbl, c);

        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.ipadx = 100;
        // c.ipady = 100;

        // c.weightx = 0.0;
        // c.gridwidth = 0;
        // c.gridx = 0;
        // c.gridy = 1;

        for (IBookModel ibm : bookDatabase.getLatestReleases()) {
            this.releaseContainer.add(ibm.getPresenter().getView().getView());
        }

        this.releaseContainer.setBackground(Color.red);
        releaseContainer.setLayout(new GridLayout(1, bookDatabase.getLatestReleases().size(), 1, 1));

        JScrollPane scroll = new JScrollPane(this.releaseContainer);
        scroll.setBounds(0, 0, (int) (600), 332);
        this.childContainer.add(scroll);

        // this.childContainer.add(this.releaseContainer, c);
    }

    public JPanel getView() {
        return this.container;
    }

    private void searchTextKeyReleased() {
        search(this.searchInput.getText());
    }

    private void search(String searchTerm) {
        ArrayList<IBookModel> books = bookDatabase.getLatestReleases();
        this.releaseContainer.removeAll();

        books.forEach((book) -> {
            String bookName = book.getTitle().toLowerCase();
            if (bookName.contains(searchTerm.toLowerCase())) {
                this.releaseContainer.add(book.getPresenter().getView().getView());
                this.releaseContainer.revalidate();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchBtn) {
            search(this.searchInput.getText());
        }
    }
}