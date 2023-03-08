package com.eecs3311.view.components;

import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class ReviewsPanel implements ActionListener, IPanelView {
    private JPanel container = new JPanel();
    private JPanel reviewsContainer = new JPanel();
    private String ISBN;

    public ReviewsPanel(String ISBN) {
        this.ISBN = ISBN;
        initComponents();
    }

    /**
     * Initializes the container to hold the reviews for the books
     * @param reviews
     */
    private void initReviewsContainer(ArrayList<IReviewModel> reviews) {
        if (reviews == null || reviews.size() == 0)
            return;
        for (IReviewModel irm : reviews) {
            reviewsContainer.add(irm.getPresenter().getReviewPanelView().getView());
        }
        initScrollPaneView(reviews);
    }

    /**
     * Initializes a scrollable panel view for the Reviews container
     * @param reviews reviews
     */
    private void initScrollPaneView(ArrayList<IReviewModel> reviews) {
        GridBagConstraints c = new GridBagConstraints();
        reviewsContainer.setLayout(new GridLayout(reviews.size(), 1, 1, 1));
        JScrollPane scroll = new JScrollPane(reviewsContainer,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(315, 235));
        container.add(scroll, c);
    }

    @Override
    public void initComponents(){
        initReviewsContainer(Database.getReviewInstance().getReviewData(ISBN));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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