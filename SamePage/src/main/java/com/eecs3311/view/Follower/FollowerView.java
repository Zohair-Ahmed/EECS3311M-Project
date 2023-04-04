package com.eecs3311.view.Follower;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Follower.IFollowerPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class FollowerView implements IFollowerView {
    private JPanel mainPanel = new JPanel();

    private IFollowerPresenter iup;
    private JLabel titleLbl;
    private GridBagConstraints c = new GridBagConstraints();
    private ImageIcon imageIcon;
    private JLabel picLabel;
    private String username;
    private JLabel followers;
    private int followerCount;
    private JButton followBtn;
    private JFrame userFrame;
    private DisplayFollowInformation user;
    public FollowerView(String username, String followerCount){
        this.followerCount = Integer.parseInt(followerCount);
        this.username = username;
        imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/images/profileimg.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        picLabel = new JLabel(imageIcon);
    }

    @Override
    public IFollowerPresenter getPresenter() { return iup; }

    @Override
    public void setPresenter(IFollowerPresenter iup) { this.iup = iup; }

    @Override
    public JPanel getView() {
        mainPanel.setLayout(new GridBagLayout());
        titleLbl = new JLabel(username);
        followers = initFollowerLabel(this.followerCount);
        followBtn = new JButton(getPresenter().checkModelFollowing() == true ? "Unfollow" : "Follow");
        initFonts();
        initFollowBtn(followBtn);
        followBtn.setBackground(initFollowBtnColour(followBtn));
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 3;
        mainPanel.add(picLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(titleLbl, c);
        c.gridy = 1;
        mainPanel.add(followers, c);
        c.gridy = 2;
        mainPanel.add(followBtn, c);
        mainPanel.addMouseListener(onUserClicked(username));
        return mainPanel;
    }

    private void initFonts() {
        titleLbl.setFont(new Font("Futura", Font.BOLD, 18));
        followers.setFont(new Font("Futura", Font.ITALIC, 14));
        followBtn.setForeground(new Color(255, 255, 255));
        followBtn.setFont(new Font("Euphemia UCAS", Font.BOLD, 12));
        followBtn.setOpaque(true);
        followBtn.setBorderPainted(false);
    }

    private MouseListener onUserClicked(String label) {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (userFrame == null) {
                    displaySelectedUser();
                } else if (user != null && !user.getUsername().equals(getPresenter().getModel().getCurrentUser())) {
                    userFrame.dispose();
                    displaySelectedUser();
                } else {
                    userFrame.setVisible(true);
                    userFrame.toFront();
                }
            }
        };
    }

    /**
     * Opens a bigger display for the Book View on a mouse click
     */
    private void displaySelectedUser(){
        try {
            user = DisplayFollowInformation.getInstance(this.username,
                    imageIcon,
                    Database.getFollowerInstance().getFollowing(this.username),
                    followerCount,
                    Database.getFavBooksInstance().getUsersFavBooks(this.username),
                    getPresenter());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        userFrame = new JFrame(""+this.username+"'s Profile");
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.add(user.getView());
        userFrame.setSize(360, 300);
        userFrame.setVisible(true);
        userFrame.setLocationRelativeTo(null);
        addWindowListener();
    }

    public void initFollowBtn(JButton button) {
        button.addActionListener(e -> {
            if (getPresenter().checkModelFollowing()) {
                getPresenter().removeFollower();
                button.setText("Follow");
                followerCount--;
                followers.setText("Followers:"+followerCount);
                if (UserModel.getInstance().getMainInit().checkCurrentCard().equals("Profile")) {
                    UserModel.getInstance().getMainInit().getCard().show(UserModel.getInstance().getMainInit().getContainer(), "Profile");
                }
            } else {
                followerCount++;
                followers.setText("Followers:"+followerCount);
                getPresenter().updateModelFollowers();
                button.setText("Unfollow");
            }
            System.out.println(followerCount);
            button.setBackground(initFollowBtnColour(button));
        });
    }

    public Color initFollowBtnColour(JButton button) {
        if (button.getText().equals("Follow"))
            return new Color(3, 83, 196, 255);
        else return new Color (89, 87, 87, 255);
    }

    public JLabel initFollowerLabel(int count) {
        return new JLabel("Followers: "+this.followerCount);
    }

    private void addWindowListener() {
        WindowListener wl = new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e){
                userFrame.dispose();
                userFrame.setVisible(false);
            }
        };
        userFrame.addWindowListener(wl);
    }
}
