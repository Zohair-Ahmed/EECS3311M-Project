package com.eecs3311.view.components;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.Review.IReviewModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.persistence.Review.IReview;
import com.eecs3311.view.IPanelView;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

public class ReviewsPanel implements ActionListener, IPanelView {



    /*
        Get ISBN of the book
            - Pass through the ISBN
                - This component is associated with DisplayBookInformation
        Access the DB for reviews
            - write a query for reviews
            - convert the ReviewModel to ReviewView
            - Place all ReviewViews in a list
            - Display the list
     */


    private JPanel container = new JPanel();
    private JPanel reviewsContainer = new JPanel();
    private String ISBN;

    public ReviewsPanel(String ISBN) {
        this.ISBN = ISBN;
        initComponents();
    }

    private void initReviewsContainer(ArrayList<IReviewModel> reviews) {
        if (reviews == null || reviews.size() == 0)
            return;
        for (IReviewModel irm : reviews) {
            reviewsContainer.add(irm.getPresenter().getReviewPanelView().getView());
        }
        initScrollPaneView(reviews);
    }

    private void initScrollPaneView(ArrayList<IReviewModel> reviews) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1250;
        c.ipady = 300;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JScrollPane scroll = new JScrollPane(reviewsContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll = new JScrollPane(reviewsContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        reviewsContainer.setLayout(new GridLayout(reviews.size(), 1, 1, 1));
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