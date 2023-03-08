package com.eecs3311.view.components;

import com.eecs3311.util.log.console.ConsoleLogs;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.Wishlist.WishlistPanel;

import javax.swing.*;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;

/**
 * Panel to be returned when no book is found
 * Consists of button to add book to user wishlist
 */
public class NoBookFoundPanel implements IPanelView {

    // root panel
    private JPanel bookNotFoundPanel;

    // wishlist frame
    private JFrame wishlistFrame;

    private WishlistPanel wishlistPanel;

    /**
     * Initialize components of NoBookFoundPanel
     */
    public NoBookFoundPanel() {
        initComponents();
    }

    /**
     * Returns panel of NoBookFoundPanel
     *
     * @return JPanel of NoBookFoundPanel
     */
    @Override
    public JPanel getView() {
        return this.bookNotFoundPanel;
    }

    @Override
    public JPanel getParentContainer() {
        return null;
    }

    @Override
    public void setParentContainer(JPanel parent) {}

    /**
     * Initialize components of book panel (JPanel -> JLabel, Icon, JButton)
     */
    @Override
    public void initComponents() {
        // set JPanel
        this.bookNotFoundPanel = new JPanel();
        this.bookNotFoundPanel.setLayout(new GridBagLayout());

        // get label and its icon
        ImageIcon notFoundIcon = new ImageIcon(
                Objects.requireNonNull(this.getClass().getClassLoader().getResource("images/book_not_found_icon.png"))
        );
        Image scaledIcon = notFoundIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        notFoundIcon = new ImageIcon(scaledIcon);
        JLabel notFoundMsg = new JLabel("No books found... Add book to wishlist?");
        notFoundMsg.setIcon(notFoundIcon);

        // button to add book
        JButton addBookButton = new JButton("Add book!");
        addBookButton.addMouseListener(onAddButtonClicked());

        // init components
        this.bookNotFoundPanel.add(notFoundMsg);
        this.bookNotFoundPanel.add(addBookButton);
        this.bookNotFoundPanel.setVisible(true);
    }

    /**
     * Display user prompt to add book to wishlist
     *
     * @return
     */
    private MouseListener onAddButtonClicked() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(ConsoleLogs.ACTION("`Add button!` button clicked..."));
                if (!WishlistPanel.isActive()) {
                    WishlistPanel.getInstance();
                    WishlistPanel.displayWishlistFrame();
                }
            }
        };
    }
}
