package com.eecs3311.view.Book;

import javax.swing.*;
import java.awt.*;

import com.eecs3311.presenter.Book.IBookPresenter;

public class BookView implements IBookView {

    private IBookPresenter bookPresenter;

    public BookView() {
    }

    @Override
    public IBookPresenter getPresenter() {
        return bookPresenter;
    }

    @Override
    public void setPresenter(IBookPresenter bp) {
        this.bookPresenter = bp;
    }

    /**
     * Returns a GUI component relating to the model. Include updatedViewFromModel
     * function to ensure the view is up-to-date and change return type as needed
     * 
     * @return JPanel - Component that has views related to BookModel
     */
    @Override
    public JPanel getView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 1, 1));
        mainPanel.setBackground(new Color(179, 191, 184));

        JLabel titleLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getTitle());
        JLabel authorLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getAuthor());
        JLabel genreLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getGenre());

        mainPanel.setPreferredSize(new Dimension(300, 300));

        mainPanel.add(titleLbl);
        mainPanel.add(genreLbl);
        mainPanel.add(authorLbl);

        return mainPanel;
    }
}