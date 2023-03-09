package com.eecs3311.view.layout;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.model.User;
import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.ProfileIcon;
import com.eecs3311.view.components.ResultsMediator;
import com.eecs3311.view.components.ResultsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfilePanel implements IPanelView {

    private JPanel root;

    private ArrayList<IBookModel> favBooks;
    private ResultsMediator mediator;
    private ResultsPanel lbv;

    private JPanel bookView;

    public ProfilePanel() {
        Database.getFavBooksInstance().getDBdata();
        root = new JPanel(); // Root panel
        root.setLayout(new GridBagLayout());
        lbv = new ResultsPanel(Database.getFavBooksInstance().getFavBooks()); // Favorite books
        bookView = lbv.getView();
        initComponents();
    }
    @Override
    public JPanel getView() {
        return root;
    }

    @Override
    public JPanel getParentContainer() {
        return null;
    }

    @Override
    public void setParentContainer(JPanel parent) {

    }

    @Override
    public void initComponents() {
        initBooksLayout();
        initUserPanel();
    }

    private void initBooksLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 0;
        c.ipady = 150;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        root.add(bookView, c);
    }

    private void initUserPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JPanel userPanel = new JPanel();
        ProfileIcon profileIcon = new ProfileIcon("SamePage");
        userPanel.add(profileIcon);
        root.add(userPanel, c);
    }

    public void updatePanel() {
        root.remove(bookView);

        lbv = new ResultsPanel(Database.getFavBooksInstance().getFavBooks());
        bookView = lbv.getView();

        // add the new bookView
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        root.add(bookView, c);

        root.revalidate(); // revalidate the root panel to update the UI
        root.repaint(); // repaint the root panel to update the UI
    }
}
