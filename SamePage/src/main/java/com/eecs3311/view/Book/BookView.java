package com.eecs3311.view.Book;

import javax.swing.*;
import javax.swing.border.Border;

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
        mainPanel.setLayout(new GridBagLayout());
        JLabel titleLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getTitle());
        JLabel authorLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getAuthor());
        JLabel genreLbl = new JLabel(getPresenter().getUpdatedViewFromModel().getGenre());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(blackline);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 100;
        c.ipady = 25;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(titleLbl, c);
        c.gridy++;
        mainPanel.add(genreLbl, c);
        c.gridy++;
        mainPanel.add(authorLbl, c);
        return mainPanel;
    }
}