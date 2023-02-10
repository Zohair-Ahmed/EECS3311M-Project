package com.eecs3311.view.Book;

import com.eecs3311.model.Book.BookDatabase;
import com.eecs3311.model.Book.IBookModel;

import javax.swing.*;
import java.awt.*;

public class LatestBookView extends JFrame {

    private BookDatabase bookDatabase;
    private JPanel latestBookViewPanel;
    private JPanel parentPanel;

    public LatestBookView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        bookDatabase = new BookDatabase();

        parentPanel = new JPanel();

        latestBookViewPanel = new JPanel();
        latestBookViewPanel.setLayout(new GridLayout(1,
                bookDatabase.getLatestReleases().size(), 1, 1));

        for (IBookModel ibm : bookDatabase.getLatestReleases()) {
            latestBookViewPanel.add(ibm.getPresenter().getView().getView());
        }
        parentPanel.setLayout(null);

        JScrollPane scroll = new JScrollPane(latestBookViewPanel);
        scroll.setBounds(0, 0, (int) (screenSize.getWidth() * .75), 332);
        parentPanel.add(scroll);

    }

    /**
     * Returns the parent component
     * 
     * @return JPanel component
     */
    public JPanel getView() {
        // Notes: Include updatedViewFromModel function to ensure the view is up-to-date
        // Notes: Change return type as needed
        return this.parentPanel;
    }
}
