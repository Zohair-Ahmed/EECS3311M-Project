package com.eecs3311.view.components;

import com.eecs3311.model.Wishlist.IWishlistModel;
import com.eecs3311.model.Wishlist.WishlistModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Wishlist.IWishlistPreseter;
import com.eecs3311.presenter.Wishlist.WishlistPresenter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;

public class RequestedWishlistPanel {
    private JPanel container = new JPanel();
    private IWishlistPreseter iwp = new WishlistPresenter();
    private IWishlistModel iwm;

    private static ArrayList<IWishlistModel> wishlistData;

    public RequestedWishlistPanel() {
        iwp.setView(this);
        initComponents();
    }

    public JPanel getView() {
        return this.container;
    }

    public IWishlistPreseter getWishlistPresenter() {
        return this.iwp;
    }

    public IWishlistModel getWishlistModel() {
        return this.iwm;
    }

    public void initComponents() {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        initHeading();
        initWishlist();
    }

    private void initHeading() {
        JLabel heading = new JLabel("Your Wishlist");
        heading.setFont(new Font("Futura", Font.BOLD, 25));
        container.add(heading);
    }

    private void initWishlist() {
        this.wishlistData = getWishlistData();
        initPanelView();
    }

    public void updateTable() {
        initWishlist();
    }

    /**
     * Create JPanel consisting of JTable of submitted wishlist
     */
    private void initPanelView() {
        if (wishlistData.size() == 0) {
            JScrollPane jpane = new JScrollPane(new NoBookFoundPanel().getView());
            container.add(jpane);
        } else {
            // wishlist data
            String[] columns = {"Book Title", "Author"};
            String[][] wishlistArray = convertWishlistDate(this.wishlistData);

            JTable wishlistTable = new JTable(wishlistArray, columns) {
                public boolean editCellAt(int row, int column, EventObject e) {
                    return false;
                }
            };
            wishlistTable.removeEditor();
            JScrollPane jpane = new JScrollPane(
                    wishlistTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
            );
            jpane.setPreferredSize(new Dimension(300, 135));
            container.add(jpane);
        }
    }

    /**
     * Get wishlist data from DB
     * @return wishlist data from DB
     */
    private ArrayList<IWishlistModel> getWishlistData() {
        return Database.getWishlistInstance().getBooksSubmitted();
    }

    /**
     * Convert ArrayList<IWishlistModel> to nested string to create JTable
     * @param wishlistData wishlist data from DB
     * @return a nested string array of the wishlist model
     */
    private String[][] convertWishlistDate(ArrayList<IWishlistModel> wishlistData) {
        int wishlistSize = wishlistData.size();
        String[][] wishlistArray = new String[wishlistSize][2];
        for (int i = 0; i < wishlistSize; i++) {
            wishlistArray[i][0] = wishlistData.get(i).getBookTitle();
            wishlistArray[i][1] = wishlistData.get(i).getAuthor();
        }
        return wishlistArray;
    }

    public void setPresenter(IWishlistPreseter iwp) {
        this.iwp = iwp;
    }

    public void setModel(IWishlistModel iwm) {
        this.iwm = iwm;
    }
}
