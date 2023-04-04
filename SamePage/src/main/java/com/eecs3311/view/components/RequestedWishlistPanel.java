package com.eecs3311.view.components;

import com.eecs3311.model.Wishlist.IWishlistModel;
import com.eecs3311.model.Wishlist.WishlistModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Wishlist.IWishlistPreseter;
import com.eecs3311.presenter.Wishlist.WishlistPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RequestedWishlistPanel {
    private JPanel container = new JPanel();
    private JPanel wishlistContainer = new JPanel();

    private IWishlistPreseter iwp = new WishlistPresenter();
    private IWishlistModel iwm = new WishlistModel();

    public RequestedWishlistPanel() {
        initComponents();
    }

    public JPanel getView() {
        return this.container;
    }

    public void initComponents() {
        container.setLayout(new GridLayout(0, 1));
        initHeading();
        container.add(new JLabel("Book\t\tAuthor"));
        initWishlist();
    }

    private void initHeading() {
        JLabel heading = new JLabel("Your Wishlist");
        heading.setSize(200, 100);
        container.add(heading);
    }

    private void initWishlist() {
        ArrayList<IWishlistModel> wishlistData = getWishlistData();
        if (wishlistData == null || wishlistData.size() == 0)
            return;
        initScrollPaneView(wishlistData);
        wishlistData.forEach(wd -> container.add(singleWishlistPanel(wd.getBookTitle(), wd.getAuthor())));
    }

    private JPanel singleWishlistPanel(String bookTitle, String bookAuthor) {
        JPanel singleWishlistPanel = new JPanel();
        JLabel wishlistInfo = new JLabel(bookTitle + "\t" + bookAuthor);
        singleWishlistPanel.add(wishlistInfo);
        singleWishlistPanel.add(new JSeparator());
        return singleWishlistPanel;
    }
    private void initScrollPaneView(ArrayList<IWishlistModel> wishlistData) {
        GridBagConstraints c = new GridBagConstraints();
        container.setLayout(new GridLayout(wishlistData.size(), 1, 1, 1));
        JScrollPane scroll = new JScrollPane(wishlistContainer,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(115, 135));
        container.add(scroll, c);
    }

    private ArrayList<IWishlistModel> getWishlistData() {
        return Database.getWishlistInstance().getBooksSubmitted();
    }

    public void setPresenter(IWishlistPreseter iwp) {
        this.iwp = iwp;
    }

    public void setModel(IWishlistModel iwm) {
        this.iwm = iwm;
    }
}
