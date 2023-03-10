package com.eecs3311.view.layout;

import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.ProfileIcon;
import com.eecs3311.view.components.ResultsPanel;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel implements IPanelView {

    private JPanel root;
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
        initUserPanel();
        initBooksLayout();
    }

    private void initBooksLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.insets = new Insets(50, 0,0,0);
        root.add(bookView, c);
    }

    private void initUserPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 50, 0);
        c.fill = GridBagConstraints.VERTICAL;
        c.fill = GridBagConstraints.HORIZONTAL;

        JPanel userPanel = new JPanel();
        ProfileIcon profileIcon = new ProfileIcon("SamePage");
        userPanel.add(profileIcon);
        root.add(userPanel, c);
    }

    public void updatePanel() {
        root.remove(bookView);

        lbv = new ResultsPanel(Database.getFavBooksInstance().getFavBooks());
        bookView = lbv.getView();

        initBooksLayout();

        root.revalidate(); // revalidate the root panel to update the UI
        root.repaint(); // repaint the root panel to update the UI
    }
}
