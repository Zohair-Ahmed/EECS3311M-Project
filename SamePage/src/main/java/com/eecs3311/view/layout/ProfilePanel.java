package com.eecs3311.view.layout;

import com.eecs3311.model.Goals.GoalModel;
import com.eecs3311.model.Goals.IGoalModel;
import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Goals.GoalPresenter;
import com.eecs3311.presenter.Goals.IGoalPresenter;
import com.eecs3311.view.Goals.GoalView;
import com.eecs3311.view.Goals.IGoalView;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.ProfileIcon;
import com.eecs3311.view.components.ResultsPanel;
import com.eecs3311.view.components.UserResultsPanel;

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
    private UserResultsPanel urp;
    private JPanel followersView;

    private JPanel bookView;
    private JPanel userPanel = new JPanel();
    private JPanel userGoalPanel = new JPanel();

    public ProfilePanel() {
        Database.getFavBooksInstance().getDBdata();
        Database.getFollowerInstance().getDBFollowedUsers(UserModel.getInstance().getUsername());
        root = new JPanel(); // Root panel
        root.setLayout(new GridBagLayout());
        lbv = new ResultsPanel(Database.getFavBooksInstance().getFavBooks()); // Favorite books
        urp = new UserResultsPanel(Database.getFollowerInstance().getFollowing(UserModel.getInstance().getUsername()));
        bookView = lbv.getView();
        followersView = urp.getView();
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
        initGoalPanel();
        initFollowingLayout();
        initBooksLayout();

        // Set the vertical split between userPanel and bookView
        root.setLayout(new BorderLayout());
        root.add(userPanel, BorderLayout.WEST);
        root.add(userGoalPanel, BorderLayout.EAST);
        root.add(bookView);
        root.add(followersView, BorderLayout.SOUTH);
    }

    private void initBooksLayout() {
        // Set the preferred size of bookView
        bookView.setPreferredSize(new Dimension(0, 300));
    }

    private void initFollowingLayout() {
        followersView.setPreferredSize(new Dimension(300, 300));
    }

    private void initUserPanel() {
        userPanel = new JPanel();
        ProfileIcon profileIcon = new ProfileIcon(generateInitials());
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

    /**
     * Generate the initials for the default profile picture based on the Username
     * @return initials of Username
     */
    private String generateInitials() {
        String[] username = UserModel.getInstance().getUsername().split(" ");
        StringBuilder initial = new StringBuilder();
        for (String s : username)
            initial.append(s.charAt(0));
        return initial.toString().toUpperCase();
    }

    /**
     * Initialize the Goal Panel for User Goals
     */
    private void initGoalPanel() {
        IGoalModel igm = new GoalModel(UserModel.getInstance().getUserID());
        IGoalPresenter igp = new GoalPresenter();
        IGoalView igv = new GoalView();
        igm.setPresenter(igp);
        igp.setModel(igm);
        igv.setPresenter(igp);
        igp.setView(igv);
        igv.initComponents(); // Temp Fix - initiates the current user goal text
        userGoalPanel.add(igv.getView());
        root.add(userGoalPanel);
        x = Spring.constant(0);
        y = Spring.constant(0);
        width = Spring.width(bookView); // set the width of userPanel to be same as bookView
        height = Spring.scale(height, 1f/2); //
        layout.putConstraint(SpringLayout.NORTH, userGoalPanel, y, SpringLayout.NORTH, root);
        layout.putConstraint(SpringLayout.WEST, userGoalPanel, x, SpringLayout.WEST, root);
        layout.putConstraint(SpringLayout.EAST, userGoalPanel, width, SpringLayout.WEST, userGoalPanel);
        layout.putConstraint(SpringLayout.SOUTH, userGoalPanel, height, SpringLayout.NORTH, userGoalPanel);
    }
}