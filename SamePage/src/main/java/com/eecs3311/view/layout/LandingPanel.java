package com.eecs3311.view.layout;

import javax.swing.*;
import java.awt.*;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.view.components.ResultsPanel;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.components.ResultsMediator;
import com.eecs3311.view.components.SearchBar;

public class LandingPanel implements IPanelView {

    private ResultsMediator mediator;
    private JPanel root;
    private JPanel herobanner;
    private SearchBar sbf;
    private ResultsPanel lbv;

    // The landing frame panel, upon app launch this frame will show up
    public LandingPanel() {
        mediator = new ResultsMediator(); // Used for connecting search bar and results panel
        root = new JPanel(); // Root panel
        herobanner = new JPanel(); // Initial panel containing title
        sbf = new SearchBar(mediator); // Search bar frame
        lbv = new ResultsPanel(mediator); // Latest book view (results panel)
        root.setLayout(new GridBagLayout());

        herobanner.setLayout(new GridBagLayout());

        JLabel title = new JLabel("Same Page Books"); // Title text and UI configurations
        title.setForeground(new Color(12, 51, 127));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Futura", Font.BOLD, 35));
        herobanner.add(title);

        mediator.setLbv(lbv); // Setup the connection between the search bar and results panel
        mediator.setSbf(sbf);
        initComponents();
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

}
