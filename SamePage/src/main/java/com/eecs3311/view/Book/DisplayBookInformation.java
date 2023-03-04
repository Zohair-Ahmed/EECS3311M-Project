package com.eecs3311.view.Book;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.view.IPanelView;

public class DisplayBookInformation implements IPanelView {
    private final JPanel root;
    private String titleB = "";
    private String author = "";
    private String summary = "";
    private String isbn = "";
    private String genre = "";
    private final Color color = new Color(238, 238, 238);

    private static DisplayBookInformation instance = null;
    private DisplayBookInformation(IBookPresenter bookPresenter) {
        root = new JPanel(); // Root panel
        root.setLayout(new GridBagLayout());
        setClicked(bookPresenter.getUpdatedViewFromModel().getTitle(),
                bookPresenter.getUpdatedViewFromModel().getAuthor(),
                bookPresenter.getUpdatedViewFromModel().getDescription(),
                bookPresenter.getUpdatedViewFromModel().getISBN(),
                bookPresenter.getUpdatedViewFromModel().getGenre());
        JLabel title = new JLabel(titleB); // Title text and UI configurations
        title.setForeground(new Color(12, 51, 127));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Futura", Font.BOLD, 15));
        root.add(title);
        initComponents();
    }

    public static DisplayBookInformation getInstance(IBookPresenter bookPresenter) throws IOException {
        if(instance == null){
            instance = new DisplayBookInformation(bookPresenter);
        } else if (!instance.getTitleB().equals(bookPresenter.getUpdatedViewFromModel().getTitle())) {
            instance = new DisplayBookInformation(bookPresenter);
        }
        return instance;
    }

    public String getTitleB(){
        return instance.titleB;
    }

    @Override
    public void initComponents() {
        // Initialize panels for the gridbaglayout
        initGenre();
        initAuthor();
        initISBN();
        initSummary();
        initUserRating();
        initUserReview();
        initSubmitButton();
        //display list of reviews from other users
    }

    private void initGenre() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        JLabel GenreLabel = new JLabel("Genre: ");
        JTextField GenreField = new JTextField();
        GenreField.setText(genre);
        GenreField.setEditable(false);
        GenreField.setBackground(color);
        root.add(GenreLabel, c);
        c.gridx = 3;
        root.add(GenreField, c);
    }

    private void initAuthor() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel authorLabel = new JLabel("Author: ");
        JTextField authorField = new JTextField(20);
        authorField.setEditable(false);
        authorField.setBackground(color);
        authorField.setText(author);
        c.gridx = 0;
        c.gridy = 1;
        root.add(authorLabel,c);
        c.gridx = 1;
        c.gridy = 1;
        root.add(authorField,c);
    }

    private void initISBN() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel ISBNLabel = new JLabel("ISBN: ");
        JTextField ISBNField = new JTextField();
        ISBNField.setEditable(false);
        ISBNField.setBackground(new Color(238, 238, 238));
        ISBNField.setText(isbn);
        c.gridx = 2;
        c.gridy = 1;
        root.add(ISBNLabel, c);
        c.gridx =3;
        root.add(ISBNField, c);
    }

    private void initSummary() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel summaryLabel = new JLabel("Summary:");
        JTextArea summaryArea = new JTextArea(5, 20);
        summaryArea.setEditable(false);
        summaryArea.setText(summary);
        summaryArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(summaryArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(320, 110));
        c.gridx = 0;
        c.gridy = 2;
        root.add(summaryLabel, c);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 2;
        root.add(scrollPane, c);
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition( new Point(0, 0) ));
    }

    private void initUserRating() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel ratingLabel = new JLabel("Rating:");
        JSlider ratingSlider = new JSlider(1, 5, 3);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setSnapToTicks(true);
        c.gridx = 0;
        c.gridy = 3;
        root.add(ratingLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        root.add(ratingSlider, c);
    }

    private void initUserReview() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel reviewLabel = new JLabel("Review:");
        JTextArea reviewArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(reviewArea);
        c.gridx = 0;
        c.gridy = 4;
        root.add(reviewLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 2;
        root.add(scrollPane, c);
    }

    private void initSubmitButton(){
        GridBagConstraints c = new GridBagConstraints();
        JButton submitButton = new JButton("Submit");
        c.gridx = 1;
        c.gridy = 7;
        c.gridheight = 1;
        root.add(submitButton, c);
    }

    @Override
    public JPanel getView() {
        return this.root;
    }

    @Override
    public JPanel getParentContainer() {
        return null;
    }

    @Override
    public void setParentContainer(JPanel parent) {
    }

    public void setClicked(String title, String author, String description, String isbn, String genre) {
        this.titleB = title;
        this.author = author;
        this.summary = description;
        this.isbn = isbn;
        this.genre = genre;
    }
}