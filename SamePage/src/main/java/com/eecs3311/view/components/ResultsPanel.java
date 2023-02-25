package com.eecs3311.view.components;

import com.eecs3311.model.Book.BookDatabase;
import com.eecs3311.model.Book.IBookModel;
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
    private BookDatabase bookDatabase = new BookDatabase();

    // Mediator:
    ResultsMediator mediator;

    public ResultsPanel(ResultsMediator mediator) {
        this.mediator = mediator;
        initComponents();
    }

    public void updateBookView(ArrayList<IBookModel> results) {
        this.releaseContainer.removeAll();
        this.container.removeAll();
        state = "resultPage";
        initReleaseContainer(results);
        this.container.updateUI();
    }

    private void initReleaseContainer(ArrayList<IBookModel> results) {

        if (results == null)
            return;

        for (IBookModel ibm : results) {
            releaseContainer.add(ibm.getPresenter().getView().getView());
        }

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1;
        c.ipady = 1;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        container.add(textJLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1250;
        c.ipady = 300;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JScrollPane scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        if (state.equals("resultPage")) {
            this.textJLabel
                    .setText(results.size() + " " + ((results.size() == 1 ? "result" : "results") + " found..."));
            scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            releaseContainer.setLayout(new GridLayout(results.size(), 1, 1, 1));
        }
        container.add(scroll, c);
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
        return null;
    }

    @Override
    public void setParentContainer(JPanel parent) {

    }

    @Override
    public void initComponents() {
        container.setLayout(new GridBagLayout());
        initReleaseContainer(this.bookDatabase.getLatestReleases());
    }

}