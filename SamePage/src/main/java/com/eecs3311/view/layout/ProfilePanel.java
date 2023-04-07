package com.eecs3311.view.layout;

import com.eecs3311.model.Follower.IFollowerModel;
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

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfilePanel implements IPanelView {

    private JPanel root;
    private SpringLayout layout = new SpringLayout();
    private Spring x;
    private Spring y;
    private Spring width = Spring.constant(0);
    private Spring height = Spring.constant(0);

    private ResultsPanel lbv;

    private JPanel bookView;
    private JPanel userPanel = new JPanel();
    private JPanel userGoalPanel = new JPanel();

    public ProfilePanel() {
        Database.getFavBooksInstance().getDBdata();
        Database.getFollowerInstance().getDBFollowedUsers(UserModel.getInstance().getUsername());
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
        initGoalPanel();
        initBooksLayout();

        // Set the vertical split between userPanel and bookView
        root.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel botPanel = new JPanel();

        topPanel.setLayout(new BorderLayout());
        topPanel.add(userPanel, BorderLayout.WEST);
        topPanel.add(userGoalPanel, BorderLayout.EAST);

        botPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        botPanel.add(bookView, c);

        root.add(topPanel, BorderLayout.NORTH);
        root.add(botPanel);
    }

    private void initBooksLayout() {
        // Set the preferred size of bookView
        bookView.setPreferredSize(new Dimension(2000, 1000));
    }

    private void initUserPanel() {
        userPanel = new JPanel();
        ProfileIcon profileIcon = new ProfileIcon(generateInitials());
        JLabel username = new JLabel("  "+UserModel.getInstance().getUsername()+"'s Profile -- ");
        ArrayList<IFollowerModel> users = Database.getFollowerInstance().getAllUsers();
        JLabel followers = new JLabel(Database.getFollowerInstance().getFollowers(UserModel.getInstance().getUsername()).size()+" Followers");
        username.setFont(new Font("Futura", Font.BOLD, 40));
        followers.setFont(new Font("Futura", Font.ITALIC, 25));

        userPanel.add(profileIcon, BorderLayout.NORTH);
        userPanel.add(username, BorderLayout.CENTER);
        userPanel.add(followers, BorderLayout.NORTH);

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