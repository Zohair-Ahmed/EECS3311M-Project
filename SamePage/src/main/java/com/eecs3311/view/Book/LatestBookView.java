package com.eecs3311.view.Book;

import com.eecs3311.model.Book.BookDatabase;
import com.eecs3311.model.Book.IBookModel;
import com.eecs3311.view.IView;
import com.eecs3311.view.components.SearchAndResults;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LatestBookView extends JFrame implements ActionListener, IView {

    private JPanel container = new JPanel();

    private JPanel releaseContainer = new JPanel();
    private JLabel textJLabel = new JLabel("Latest Releases");

    private BookDatabase bookDatabase = new BookDatabase();

    // Mediator:
    SearchAndResults mediator;

    public LatestBookView(SearchAndResults mediator) {
        this.mediator = mediator;
        container.setLayout(new GridBagLayout());
        initReleaseContainer(this.bookDatabase.getLatestReleases());
    }

    public void updateBookView(ArrayList<IBookModel> results) {
        this.releaseContainer.removeAll();
        this.container.removeAll();
        this.textJLabel.setText(results.size() + " " + ((results.size() == 1 ? "result" : "results") + " found..."));
        this.textJLabel.setToolTipText("w");
        for (IBookModel ibm : results) {
            System.out.println(ibm.getTitle());
        }
        initReleaseContainer(results);
        this.container.updateUI();
    }

    private void initReleaseContainer(ArrayList<IBookModel> results) {

        if (results == null)
            return;

        releaseContainer.setLayout(new GridLayout(1,
                results.size(), 1, 1));

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
        c.ipadx = 1000;
        c.ipady = 300;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        JScrollPane scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        container.add(scroll, c);
        // container.setLayout(new GridLayout(2, 1, 1, 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public JPanel getView() {
        return this.container;
    }

}