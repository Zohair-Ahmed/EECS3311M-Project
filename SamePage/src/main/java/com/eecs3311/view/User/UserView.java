package com.eecs3311.view.User;

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
    public UserView(String username){
        mainPanel.setLayout(new GridBagLayout());
        this.username = username;
        titleLbl = new JLabel(username);
        imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/images/profileimg.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        picLabel = new JLabel(imageIcon);
    }

    @Override
    public JPanel getView() {
        mainPanel.add(picLabel, c);
        titleLbl.setFont(new Font("Futura", Font.BOLD, 18));
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(titleLbl, c);
        mainPanel.addMouseListener(onUserClicked(username));
        mainPanel.revalidate();
        return mainPanel;
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
