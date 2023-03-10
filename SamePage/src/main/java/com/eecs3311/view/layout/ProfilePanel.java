package com.eecs3311.view.layout;

import com.eecs3311.model.User.User;
import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.ProfileIcon;
import com.eecs3311.view.components.ResultsPanel;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel implements IPanelView {

    private JPanel root;
    private SpringLayout layout = new SpringLayout();
    private Spring x;
    private Spring y;
    private Spring width = Spring.constant(0);
    private Spring height = Spring.constant(0);


    private ResultsPanel lbv;

    private JPanel bookView;
    private JPanel userPanel;

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

        // Set the vertical split between userPanel and bookView
        root.setLayout(new BorderLayout());
        root.add(userPanel, BorderLayout.NORTH);
        root.add(bookView);
    }

    private void initBooksLayout() {

        // Set the preferred size of bookView
        bookView.setPreferredSize(new Dimension(0, 300));
    }

    private void initUserPanel() {
        userPanel = new JPanel();
        ProfileIcon profileIcon = new ProfileIcon("SamePage");
        JLabel username = new JLabel("  My Profile");
        username.setFont(new Font("Futura", Font.BOLD, 30));

        userPanel.add(profileIcon, BorderLayout.NORTH);
        userPanel.add(username);
        layout.putConstraint(SpringLayout.NORTH, username, 40, SpringLayout.NORTH, root);

        root.add(userPanel);

        x = Spring.constant(0);
        y = Spring.constant(0);
        width = Spring.width(bookView); // set the width of userPanel to be same as bookView
        height = Spring.scale(height, 1f/2); // set the height to be half of the root panel height
        layout.putConstraint(SpringLayout.NORTH, userPanel, y, SpringLayout.NORTH, root);
        layout.putConstraint(SpringLayout.WEST, userPanel, x, SpringLayout.WEST, root);
        layout.putConstraint(SpringLayout.EAST, userPanel, width, SpringLayout.WEST, userPanel);
        layout.putConstraint(SpringLayout.SOUTH, userPanel, height, SpringLayout.NORTH, userPanel);
    }
}
