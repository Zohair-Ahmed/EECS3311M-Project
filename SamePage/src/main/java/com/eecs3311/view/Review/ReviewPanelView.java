package com.eecs3311.view.Review;

import com.eecs3311.presenter.Review.IReviewPresenter;

import javax.swing.*;
import java.awt.*;

public class ReviewPanelView implements IReviewPanelView{

    private IReviewPresenter irp;
    private JPanel root;

    @Override
    public IReviewPresenter getPresenter() {
        return irp;
    }

    @Override
    public void setPresenter(IReviewPresenter irp) {
        this.irp = irp;
    }

    @Override
    public JPanel getView() {
        root = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JTextArea userRating = new JTextArea();
        userRating.setText(getPresenter().getModel().getDate()+ "\n\nBy: "+getPresenter().getModel().getUsername()+ "\nRating: "+getPresenter().getModel().getRating()+"/5");
        userRating.setEditable(false);
        userRating.setLineWrap(true);
        userRating.setBackground(new Color(238, 238, 238));
        JTextArea review = new JTextArea();
        review.setText(getPresenter().getModel().getReview());
        review.setEditable(false);
        review.setLineWrap(true);
        review.setBackground(new Color(238, 238, 238));
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        root.add(userRating, c);
        c.gridx = 1;
        root.add(review, c);


        // Check if user has already left a review for the book
        boolean reviewed = getPresenter().hasReviewedBook(getPresenter().getModel().getISBN());
        if (!reviewed) {
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 2;
            JButton submitButton = new JButton("Submit Review");
            submitButton.addActionListener(e -> {
                String reviewText = JOptionPane.showInputDialog(root, "Enter your review:");
                String ratingText = JOptionPane.showInputDialog(root, "Enter your rating (1-5):");
                getPresenter().updateModelFromView(reviewText, ratingText, getPresenter().getModel().getISBN());
            });
            root.add(submitButton, c);
        }

        return root;
    }
}
