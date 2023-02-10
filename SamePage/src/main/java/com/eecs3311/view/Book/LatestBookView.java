package com.eecs3311.view.Book;

import com.eecs3311.model.Book.BookDatabase;
import com.eecs3311.model.Book.IBookModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * LatestBookView to retireve data from IBookPresenter and IBookModel
 * for the latest available books following MVP patterns
 */
public class LatestBookView extends JFrame {

    private BookDatabase bookDatabase;
    private JPanel latestBookViewPanel;
    private JPanel parentPanel;

    DefaultListModel<JPanel> defaultListModel = new DefaultListModel<>();
    JList<JPanel> booksList = new JList<>();
    JTextField searchBar = new JTextField("Search");

    public LatestBookView() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        bookDatabase = new BookDatabase();

        parentPanel = new JPanel();
        parentPanel.setLayout(new GridLayout(3, 1, 2, 2));

        booksList.setLayout(new GridLayout(1, bookDatabase.getLatestReleases().size(), 1, 1));

        JLabel latestBookViewlbl = new JLabel("Latest Releases");
        latestBookViewlbl.setHorizontalTextPosition(JLabel.LEFT);
        latestBookViewlbl.setVerticalTextPosition(JLabel.BOTTOM);

        // Add book models from database to panel
        latestBookViewPanel = new JPanel();
        for (IBookModel ibm : bookDatabase.getLatestReleases()) {
            latestBookViewPanel.add(ibm.getPresenter().getView().getView());
        }

        parentPanel.add(this.searchBar);
        parentPanel.add(latestBookViewlbl);
        parentPanel.setLayout(null);

        JScrollPane scroll = new JScrollPane(latestBookViewPanel);
        scroll.setBounds(0, 0, (int) (screenSize.getWidth() * .75), 332);
        parentPanel.add(scroll);
    }

    /**
     * Initialize search bar component
     */
    private void initSearchBar() {
        this.searchBar.add(new JLabel("Search..."));
        this.searchBar.setColumns(30);
        this.searchBar.setSize(30, 30);

        this.searchBar.addKeyListener(new KeyListener() {
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
    }

    /**
     * Fetches data from database using input from search bar component and displays
     * onto page
     */
    private void searchFilter(String searchTerm) {
        DefaultListModel<JPanel> filteredBooks = new DefaultListModel<>();
        ArrayList<IBookModel> books = bookDatabase.getLatestReleases();

        books.forEach((book) -> {
            String bookName = book.getTitle().toLowerCase();
            if (bookName.contains(searchTerm.toLowerCase())) {
                filteredBooks.addElement(book.getPresenter().getView().getView());
            }
        });

        defaultListModel = filteredBooks;
        booksList.setModel(defaultListModel);
    }

    /**
     * Dynamic search bar functionality
     */
    private void searchTextKeyReleased() {
        searchFilter(this.searchBar.getText());
    }

    public JPanel getView() {
        return this.parentPanel;
    }
}
