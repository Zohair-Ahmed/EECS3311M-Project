package com.eecs3311.view.components;

import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ResultsPanel implements ActionListener, IPanelView {

    private JPanel container = new JPanel();

    private JPanel releaseContainer = new JPanel();
    private JLabel textJLabel = new JLabel("Latest Releases");
    private String state = "releasePage";

    // Mediator:
    ResultsMediator mediator;

    public ResultsPanel(ResultsMediator mediator) {
        this.mediator = mediator;
        initComponents();
    }

    /**
     * Initializes a result panel JPanel containing bookView components for all book models
     * in the logged-in users favorites list
     * @param books
     */
    public ResultsPanel(ArrayList<IBookModel> books) {
        container.setLayout(new GridBagLayout());
        textJLabel.setText("Favorite Books:");
        initReleaseContainer(books);
    }

    @Override
    public void initComponents() {
        container.setLayout(new GridBagLayout());

        initReleaseContainer(Database.getBookInstance().getLatestReleases());
    }

    // Update book view from search input from search bar
    public void updateBookView(ArrayList<IBookModel> results) {
        this.releaseContainer.removeAll();
        this.container.removeAll();
        state = "resultPage";
        initReleaseContainer(results);
        this.container.updateUI();
    }

    /**
     * To initialize
     */
    private void initTextLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1;
        c.ipady = 1;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        container.add(textJLabel, c);
    }

    private void initScrollPaneView(ArrayList<IBookModel> results) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1250;
        c.ipady = 300;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JScrollPane scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        if (state.equals("resultPage")) {
            this.textJLabel.setText(results.size() + " " + ((results.size() == 1 ? "result" : "results") + " found..."));
            scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            releaseContainer.setLayout(new GridLayout((int)Math.ceil(results.size()/7)+1, 7));
        }
        container.add(scroll, c);
    }

    private void initReleaseContainer(ArrayList<IBookModel> results) {

        if (results == null)
            return;
        GridLayout gridLayout = new GridLayout((int)Math.ceil(results.size()/7)+1, 7);
        releaseContainer.setLayout(gridLayout);

        // Temp Code
        for (IBookModel ibm : results) {
            releaseContainer.add(ibm.getPresenter().getView().getView());
        }

        initTextLayout();
        initScrollPaneView(results);
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
        return releaseContainer;
    }

    @Override
    public void setParentContainer(JPanel parent) {

    }
}