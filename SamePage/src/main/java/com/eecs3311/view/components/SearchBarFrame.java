package com.eecs3311.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SearchBarFrame {
    JFrame frame = new JFrame("");

    JList<String> booksList = new JList<>();

    JTextField searchBar = new JTextField("Search");

    DefaultListModel<String> defaultListModel = new DefaultListModel<>();

    public SearchBarFrame() {
        this.bindData();
        initComponents();
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.add(new JLabel("Search"));
        panel.add(this.searchBar);
        panel.add(this.booksList);
        panel.setVisible(true);

        this.frame.setSize(400,400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new GridLayout(1,2));

        this.searchBar.setColumns(30);
        this.searchBar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                searchTextKeyReleased();
            }
        });

        this.frame.add(panel);
        this.frame.setVisible(true);
    }

    private ArrayList<String> getBooks() {
        ArrayList<String> books = new ArrayList<>();
        books.add("Harry Potter");
        books.add("To Kill a Mockingbird");
        books.add("The Great Gatsby");
        books.add("One Hundred Years of Solitude");
        books.add("A Passage to India");
        books.add("Invisible Man");
        books.add("Don Quixote");
        books.add("Beloved");
        books.add("Things Fall Apart");
        books.add("Mrs. Dalloway");
        books.add("Jane Eyre");
        books.add("The Color Purple");
        return books;
    }

    private void bindData() {
        getBooks().forEach((book) ->{
            defaultListModel.addElement(book);
        });
        booksList.setModel(defaultListModel);
        booksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void searchFilter(String searchTerm) {
        DefaultListModel<String> filteredBooks = new DefaultListModel<>();
        ArrayList<String> books = getBooks();

        books.forEach((book) -> {
            String bookName = book.toString().toLowerCase();
            if (bookName.contains(searchTerm.toLowerCase())) {
                filteredBooks.addElement(book);
            }
        });

        defaultListModel = filteredBooks;
        booksList.setModel(defaultListModel);
    }

    private void searchTextKeyReleased() {
        searchFilter(searchBar.getText());
    }

    public static void main(String[] args) {
        new SearchBarFrame();
    }

}
