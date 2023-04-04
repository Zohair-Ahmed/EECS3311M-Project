package com.eecs3311.view.layout;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.*;

import javax.swing.*;
import java.awt.*;

public class FindFriendsPanel implements IPanelView {

    private FollowerMediator mediator;
    private JPanel root = new JPanel();
    private JPanel herobanner = new JPanel();
    private SearchFriends sbf;
    private UserResultsPanel lbv;

    // The new find friends frame panel
    public FindFriendsPanel() {
        mediator = new FollowerMediator();
        sbf = new SearchFriends(mediator);
        lbv = new UserResultsPanel(mediator);
        mediator.setURP(lbv);
        mediator.setSbf(sbf);

        Database.getFollowerInstance().getDBFollowedUsers(UserModel.getInstance().getUsername());
        root.setLayout(new GridBagLayout());
        herobanner.setLayout(new GridBagLayout());
        JLabel title = new JLabel("Search Users"); // Title text and UI configurations
        title.setForeground(new Color(12, 51, 127));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Futura", Font.BOLD, 25));
        herobanner.add(title);
        initComponents();
    }

    @Override
    public JPanel getView() {
        return this.root;
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
        // Initialize panels for the gridbaglayout, herobanner, search bar, results
        initHeroBannerPanelLayout();
        initSearchBarPanel();
        initResultsPanelLayout();
    }

    // Initializes the hero banner panel
    private void initHeroBannerPanelLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 0;
        c.ipady = 75;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        root.add(herobanner, c);
    }

    // Initializes the search bar panel
    private void initSearchBarPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        root.add(sbf.getView(), c);
    }

    // Initializes the latest book panel
    private void initResultsPanelLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 0;
        c.ipady = 150;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        root.add(lbv.getView(), c);
    }
}