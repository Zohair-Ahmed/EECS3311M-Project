package com.eecs3311.view.Follower;

import com.eecs3311.model.Follower.IFollowerModel;
import com.eecs3311.presenter.Follower.IFollowerPresenter;
import com.eecs3311.view.IPanelView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayFollowInformation implements IPanelView {

    private final JPanel root;
    private ImageIcon imageIcon;
    private JLabel picLabel;
    private String username = "";
    private JLabel userLbl;
    private String followers = "";
    private JLabel followersLbl;
    private String following = "";
    private JLabel followingLbl;
    private ArrayList<String> favBooks;
    private JLabel favBooksLbl;
    private static JLabel errMsg = new JLabel("");
    private static DisplayFollowInformation instance  = null;

    public static DisplayFollowInformation getInstance(String name,
                                                       ImageIcon image,
                                                       ArrayList<IFollowerModel> followedUsers,
                                                       int usersFollowing,
                                                       ArrayList<String> favBookTitles,
                                                       IFollowerPresenter presenter) throws IOException {
        if(instance == null){
            instance = new DisplayFollowInformation(name, image, followedUsers, usersFollowing, favBookTitles);
        } else if (!instance.getUsername().equals(presenter.getModel().getCurrentUser())) {
            instance = new DisplayFollowInformation(name, image, followedUsers, usersFollowing, favBookTitles);
        }
        return instance;
    }

    /**
     * private Constructor to manage one instance of the follower details popup
     */
    private DisplayFollowInformation(String name,
                                     ImageIcon image,
                                     ArrayList<IFollowerModel> followedUsers,
                                     int usersFollowing,
                                     ArrayList<String> favBookTitles) {
        root = new JPanel(); // Root panel
        root.setLayout(new GridBagLayout());
        setClicked(name, image, usersFollowing, followedUsers.size(), favBookTitles);
        errMsg.setText("");
        initComponents();
    }

    private void setClicked(String username,
                            ImageIcon image,
                            int followers,
                            int following,
                            ArrayList<String> favBooks) {
        this.imageIcon = image;
        this.username = username;
        this.followers = String.valueOf(followers);
        this.following = String.valueOf(following);
        this.favBooks = favBooks;
    }

    @Override
    public void initComponents() {
        initImgLbl();
        initUserLbl();
        initFollowersLbl();
        initFollowingLbl();
        initFavBooksLbl();
        initFavBooks();
    }

    /**
     * Initialize the book image
     */
    private void initImgLbl() {
        picLabel = new JLabel(imageIcon);
        GridBagConstraints c = new GridBagConstraints();
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        root.add(picLabel,c);
    }

    /**
     * Initialize user label
     */
    private void initUserLbl() {
        userLbl = new JLabel(username);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        userLbl.setFont(new Font("Futura", Font.BOLD, 30));
        root.add(userLbl, c);
    }

    /**
     * To initialize the followers label
     */
    private void initFollowersLbl() {
        GridBagConstraints c = new GridBagConstraints();
        followersLbl = new JLabel("Followers:  "+followers);
        followersLbl.setFont(new Font("Futura", Font.ITALIC, 25));
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        root.add(followersLbl,c);

    }

    /**
     * To initialize the following label
     */
    private void initFollowingLbl() {
        GridBagConstraints c = new GridBagConstraints();
        followingLbl = new JLabel("Following:  "+following);
        followingLbl.setFont(new Font("Futura", Font.ITALIC, 25));
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        root.add(followingLbl,c);
    }

    /**
     * Initialize favourite books label
     */
    private void initFavBooksLbl() {
        GridBagConstraints c = new GridBagConstraints();
        favBooksLbl = new JLabel("Favourite Books:");
        favBooksLbl.setFont(new Font("Futura", Font.BOLD, 16));
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        root.add(favBooksLbl, c);
    }

    /**
     * Initialize favourite books list
     */
    private void initFavBooks() {
        GridBagConstraints c = new GridBagConstraints();
        String booksList = "";
        for (int i = 0; i < favBooks.size(); i++) {
            booksList += "- "+favBooks.get(i);
            if (i != favBooks.size() - 1) {
                booksList += "\n";
            }
        }
        JTextArea favBooksArea = new JTextArea(5, 30);
        favBooksArea.setEditable(false);
        favBooksArea.setText(booksList);
        favBooksArea.setLineWrap(true);
        favBooksArea.setBackground(new Color(238, 238, 238));
        JScrollPane scrollPane = new JScrollPane(favBooksArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(250, 100));
        favBooksArea.setFont(new Font("Futura", Font.BOLD, 13));
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        root.add(scrollPane, c);
        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition( new Point(0, 0) ));
    }

    public String getUsername() {
        return this.username;
    }
    @Override
    public JPanel getView() {return this.root;}

    @Override
    public JPanel getParentContainer() {return null;}

    @Override
    public void setParentContainer(JPanel parent) {}

}
