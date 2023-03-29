package com.eecs3311.view.User;

import com.eecs3311.model.User.User;
import com.eecs3311.model.enums.State;
import com.eecs3311.view.IPanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserView implements ActionListener, IPanelView {
    private JPanel mainPanel = new JPanel();
    private JLabel titleLbl;
    private GridBagConstraints c = new GridBagConstraints();
    private ImageIcon imageIcon;
    private JLabel picLabel;
    private String username;
    private JLabel followers;
    private JButton followBtn;
    public UserView(String username){
        mainPanel.setLayout(new GridBagLayout());
        this.username = username;
        titleLbl = new JLabel(username);
        imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/images/profileimg.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        picLabel = new JLabel(imageIcon);
        followers = new JLabel("Followers: NaN");
        followBtn = new JButton("Follow");
        initFollowBtn();
        followBtn.setBackground(initFollowBtnColour());
    }

    @Override
    public JPanel getView() {
        initFonts();
        c.gridy = 0;
        c.gridheight = 3;
        mainPanel.add(picLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.fill = GridBagConstraints.WEST;
        mainPanel.add(titleLbl, c);
        c.gridy = 1;
        mainPanel.add(followers, c);
        c.gridy = 2;
        mainPanel.add(followBtn, c);
        mainPanel.addMouseListener(onUserClicked(username));
        mainPanel.revalidate();
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

    public void initFollowBtn() {
        followBtn.addActionListener(e -> {
            if (followBtn.getText().equals("Follow")) {
//                JOptionPane.showMessageDialog(mainPanel, "Only members signed into SamePage can add books to favourites");
                followBtn.setText("Unfollow");
                User.getInstance().getMainInit().addProfilePanel();
                // TO DO - add call to DB
            } else {
                followBtn.setText("Follow");
                User.getInstance().getMainInit().addProfilePanel();
                // TO DO - add call to DB
            }
            followBtn.setBackground(initFollowBtnColour());
        });
    }

    public Color initFollowBtnColour() {
        if (followBtn.getText().equals("Follow"))
            return new Color(3, 83, 196, 255);
        else return new Color (89, 87, 87, 255);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
