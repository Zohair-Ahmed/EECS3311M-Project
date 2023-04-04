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
import com.eecs3311.model.User.UserModel;
import com.eecs3311.model.enums.State;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Book.IBookPresenter;
import com.eecs3311.presenter.Review.IReviewPresenter;
import com.eecs3311.presenter.Review.ReviewPresenter;
import com.eecs3311.model.Review.ReviewModel;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.ReviewsPanel;

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
    private static JLabel errMsg = new JLabel("");
    private JLabel reviewLabel = new JLabel();
    private JButton submitButton = new JButton("Submit");
    private JSlider ratingSlider = new JSlider(1, 5, 3);
    private JTextArea reviewArea = new JTextArea(5, 20);
    private static DisplayBookInformation instance = null;

    private static IReviewPresenter reviewPresenter = new ReviewPresenter();
    private static IReviewModel reviewModel = new ReviewModel("", "", "", "", "");
    private ReviewsPanel reviews = new ReviewsPanel("");

    /**
     * private Constructor to manage one instance of the book details popup
     */
    private DisplayBookInformation(IBookPresenter bookPresenter) {
        reviewModel.setPresenter(reviewPresenter); // M <-> P for uploading review
        reviewPresenter.setModel(reviewModel);
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
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        title.setFont(new Font("Futura", Font.BOLD, 30));
        root.add(title, c);
        errMsg.setText("");
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
        initErrorMessage();
        initAllReviews();
        //display list of reviews from other users
    }

    /**
     * To initialize the book image
     */
    private void initImage() {
        try{
            URL url = new URL(imgUrl);
            BufferedImage img = ImageIO.read(url);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(170, 210, Image.SCALE_SMOOTH));
            JLabel picLabel = new JLabel(imageIcon);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 1;
            root.add(picLabel,c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * To initialize the book genre
     */
    private void initGenre() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        JLabel GenreLabel = new JLabel("Genre:  "+genre);
        GenreLabel.setFont(new Font("Futura", Font.BOLD, 13));
        root.add(GenreLabel, c);
    }

    /**
     * To initialize the book author
     */
    private void initAuthor() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel authorLabel = new JLabel("Author:  "+author);
        authorLabel.setFont(new Font("Futura", Font.BOLD, 13));
        c.gridx = 0;
        c.gridy = 2;
        root.add(authorLabel,c);

    }

    /**
     * To initialize the ISBN
     */
    private void initISBN() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel ISBNLabel = new JLabel("ISBN:  "+isbn);
        ISBNLabel.setFont(new Font("Futura", Font.BOLD, 13));
        c.gridx = 0;
        c.gridy = 4;
        root.add(ISBNLabel, c);
    }

    /**
     * To initialize the summary of the book
     */
    private void initSummary() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel summaryLabel = new JLabel("About the novel:");
        summaryLabel.setFont(new Font("Futura", Font.BOLD, 12));
        JTextArea summaryArea = new JTextArea(5, 30);
        summaryArea.setEditable(false);
        summaryArea.setText(summary);
        summaryArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(summaryArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 110));
        summaryArea.setBackground(new Color(238, 238, 238));
        c.insets = new Insets(0, 30, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        root.add(summaryLabel, c);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        root.add(scrollPane, c);
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition( new Point(0, 0) ));
    }

    /**
     * To initialize the label to leave a rating
     */
    private void initUserRating() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setFont(new Font("Futura", Font.BOLD, 12));
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setSnapToTicks(true);
        c.gridx = 1;
        c.gridy = 2;
        root.add(ratingLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        root.add(ratingSlider, c);
    }

    /**
     * To initialize a label to leave a review for the book
     */
    private void initUserReview() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel reviewLabel = new JLabel("Leave a review:");
        reviewLabel.setFont(new Font("Futura", Font.BOLD, 12));
        reviewArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(reviewArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        c.gridx = 1;
        c.gridy = 5;
        root.add(reviewLabel, c);
        c.gridx = 1;
        c.gridy = 6;
        c.gridheight = 2;
        root.add(scrollPane, c);
    }

    /**
     * To initialize the submit review button
     */
    private void initSubmitButton(){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 8;
        submitButton.addActionListener(this);
        root.add(submitButton, c);
    }

    /**
     * To initialize a message on an unsuccessful review submission
     */
    private void initErrorMessage(){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 7;
        c.gridheight = 1;
        errMsg.setFont(new Font("Futura", Font.ITALIC, 13));
        errMsg.setForeground(new Color(255, 0, 20));
        root.add(errMsg, c);
    }

    /**
     * To initialize a container to display all the reviews relevant to the clicked book
     */
    public void initAllReviews() {
        reviews = new ReviewsPanel(isbn);
        reviewLabel = new JLabel("Reviews:     Total Reviews: "+
                Database.getReviewInstance().getTotalRatings()+
                "     "+String.format("%.1f",Database.getReviewInstance().getAverageRating(isbn))+" ☆");
        reviewLabel.setFont(new Font("Futura", Font.BOLD, 12));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        root.add(reviewLabel, c);
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(0, 20, 0, 0);
        root.add(reviews.getView(), c);
    }

    /**
     * To set an error message on an unsuccessful attempt at leaving a review
     * @param errorMessage
     */
    public static void setErrorMessage(String errorMessage) {
        errMsg.setText(errorMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            if (UserModel.getInstance().getLoginState().equals(State.MEMBER)) {
                if(!getReviewText().isEmpty()){
                reviewPresenter.updateModelFromView(getReviewText(), getRating(), isbn);
                root.remove(reviewLabel);
                root.remove(reviews.getView());
                initAllReviews();
                root.updateUI();
                }
                else errMsg.setText("Please enter a review!");
            }
            else
                errMsg.setText("Please login to leave a review!");
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

    /**
     * To set the information of the book to display
     * @param title
     * @param author
     * @param description
     * @param isbn
     * @param genre
     * @param imgUrl
     */
    private void setClicked(String title, String author, String description, String isbn, String genre, String imgUrl) {
        this.titleB = title;
        this.author = author;
        this.summary = description;
        this.isbn = isbn;
        this.genre = genre;
        this.imgUrl = imgUrl;
    }
}