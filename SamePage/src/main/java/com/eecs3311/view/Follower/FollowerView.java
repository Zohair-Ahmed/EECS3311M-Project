package com.eecs3311.view.Follower;

import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Follower.IFollowerPresenter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class FollowerView implements ActionListener, IFollowerView {
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
    }

    public void initComponents() {
        mainPanel.setLayout(new GridBagLayout());
        titleLbl = new JLabel(username);
        imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/images/profileimg.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        picLabel = new JLabel(imageIcon);
        followBtn = new JButton("Follow");
        followBtn.addActionListener(this);
        followers = new JLabel("Followers: "+this.followerCount);
        followBtn.setText(getPresenter().checkModelFollowing() ? "Unfollow" : "Follow");
        followBtn.setBackground(initFollowBtnColour(followBtn));
        initFonts();
    }

    @Override
    public IFollowerPresenter getPresenter() { return iup; }

    @Override
    public void setPresenter(IFollowerPresenter iup) { this.iup = iup; }

    @Override
    public JPanel getView() {
        followBtn.setBackground(initFollowBtnColour(followBtn));
        c.gridy = 0;
        c.gridheight = 3;
        mainPanel.add(picLabel, c);
        c.gridy = 3;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(titleLbl, c);
        c.gridy = 4;
        mainPanel.add(followers, c);
        c.gridy = 5;
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
        try {
            userFrame.addWindowListener((WindowListener) userFrame);
        } catch (Exception e) {
        }
    }

    public Color initFollowBtnColour(JButton button) {
        if (button.getText().equals("Follow"))
            return new Color(3, 83, 196, 255);
        else return new Color (89, 87, 87, 255);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == followBtn) {
            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();
            initComponents();
            mainPanel = getView();
            if (getPresenter().checkModelFollowing()) {
                getPresenter().removeFollower();
                followBtn.setText("Follow");
                followers.setText("Followers: "+(--followerCount));
            } else {
                followers.setText("Followers: "+(++followerCount));
                getPresenter().updateModelFollowers();
                followBtn.setText("Unfollow");
            }
            followBtn.setBackground(initFollowBtnColour(followBtn));
        }
    }
}