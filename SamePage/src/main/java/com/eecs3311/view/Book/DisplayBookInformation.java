package com.eecs3311.view.Book;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.presenter.Review.IReviewPresenter;
import com.eecs3311.presenter.Review.ReviewPresenter;
import com.eecs3311.model.Review.ReviewModel;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.Review.IReviewPanelView;
import com.eecs3311.view.Review.ReviewPanelView;

/**
 * DisplayBookInformation Singleton - Book View Frame class to get the specific book's details
 * such as title, cover page, author, summary, ISBN etc.
 */

public class DisplayBookInformation implements ActionListener, IPanelView {
    private final JPanel root;
    private String titleB = "";
    private String author = "";
    private String summary = "";
    private String isbn = "";
    private String genre = "";
    private String imgUrl = "";
    private JLabel errMsg;
    private final Color color = new Color(238, 238, 238);

    private JButton submitButton = new JButton("Submit");
    private JSlider ratingSlider = new JSlider(1, 5, 3);
    private JTextArea reviewArea = new JTextArea(5, 20);
    private static DisplayBookInformation instance = null;

    private static IReviewPresenter reviewPresenter = new ReviewPresenter();
    private static IReviewModel reviewModel = new ReviewModel();
    private static IReviewPanelView reviewView = new ReviewPanelView();

    /**
     * private Constructor to manage one instance of the book details popup
     */
    private DisplayBookInformation(IBookPresenter bookPresenter) {
        // M <-> P
        reviewModel.setPresenter(reviewPresenter);
        reviewPresenter.setModel(reviewModel);

        // P <-> V
//        reviewPresenter.setView(reviewView);
//        reviewView.setPresenter(reviewPresenter);

        root = new JPanel(); // Root panel
        root.setLayout(new GridBagLayout());
        //Set the book values the user clicked
        setClicked(bookPresenter.getUpdatedViewFromModel().getTitle(),
                bookPresenter.getUpdatedViewFromModel().getAuthor(),
                bookPresenter.getUpdatedViewFromModel().getDescription(),
                bookPresenter.getUpdatedViewFromModel().getISBN(),
                bookPresenter.getUpdatedViewFromModel().getGenre(),
                bookPresenter.getUpdatedViewFromModel().getImg());
        JLabel title = new JLabel(titleB); // Title text and UI configurations
        title.setForeground(new Color(12, 51, 127));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Futura", Font.BOLD, 15));
        root.add(title);
        initComponents();
    }

    /**
     * getInstance method, if null call the constructor
     * if the book clicked from the user is different from their current popup view,
     * call the constructor
     * Else return the instance.
     */
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
        initImage();
        initAuthor();
        initISBN();
        initSummary();
        initUserRating();
        initUserReview();
        initSubmitButton();
        //display list of reviews from other users
    }

    private void initImage() {
        try{
            URL url = new URL(imgUrl);
            BufferedImage img = ImageIO.read(url);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(170, 210, Image.SCALE_SMOOTH));
            JLabel picLabel = new JLabel(imageIcon);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 1;
            c.gridheight = 0;
            c.gridwidth = 0;
            root.add(picLabel,c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        c.gridx = 1;
        c.gridy = 7;
        c.gridheight = 1;
        submitButton.addActionListener(this);
        root.add(submitButton, c);
    }

    public void setErrorMessage(String errorMessage) {
        errMsg.setText(errorMessage);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // get review info then submit review info to db to be added
            this.reviewPresenter.updateModelFromView(getReviewText(), getRating(), isbn);
        }
    }

    public String getReviewText() {
        return reviewArea.getText();
    }

    public String getRating() {
        return ""+ratingSlider.getValue();
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

    public void setClicked(String title, String author, String description, String isbn, String genre, String imgUrl) {
        this.titleB = title;
        this.author = author;
        this.summary = description;
        this.isbn = isbn;
        this.genre = genre;
        this.imgUrl = imgUrl;
    }
}