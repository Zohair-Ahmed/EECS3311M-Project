package com.eecs3311.view.Follower;

import com.eecs3311.model.User.UserModel;
import com.eecs3311.presenter.User.IFollowerPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    public FollowerView(String username, String followerCount){
        this.followerCount = Integer.parseInt(followerCount);
        this.username = username;
        mainPanel.setLayout(new GridBagLayout());
        titleLbl = new JLabel(username);
        imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/images/profileimg.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        picLabel = new JLabel(imageIcon);
    }

    @Override
    public IFollowerPresenter getPresenter() { return iup; }

    @Override
    public void setPresenter(IFollowerPresenter iup) { this.iup = iup; }

    @Override
    public JPanel getView() {
        System.out.println(username + " - " + followerCount);
        followers = initFollowerLabel(this.followerCount);
        followBtn = new JButton(getPresenter().checkModelFollowing() == true ? "Unfollow" : "Follow");
        initFonts();
        initFollowBtn(followBtn);
        followBtn.setBackground(initFollowBtnColour(followBtn));
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
        return new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                System.out.printf("Clicked %s\n",label);
                JOptionPane.showMessageDialog(mainPanel, "Selected user "+label);
            }
        };
    }

    public void initFollowBtn(JButton button) {
        button.addActionListener(e -> {
            if (getPresenter().checkModelFollowing()) {
                getPresenter().removeFollower();
                button.setText("Follow");
//                UserModel.getInstance().getMainInit().addProfilePanel();
//                UserModel.getInstance().getMainInit().addFindFriendsPanel();
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
//                UserModel.getInstance().getMainInit().addProfilePanel();
//                UserModel.getInstance().getMainInit().addFindFriendsPanel();
            }
            button.setBackground(initFollowBtnColour(button));
            mainPanel.repaint();
            mainPanel.revalidate();
            mainPanel.updateUI();
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
}
