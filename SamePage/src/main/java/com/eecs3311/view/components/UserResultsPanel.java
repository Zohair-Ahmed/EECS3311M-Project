package com.eecs3311.view.components;

import com.eecs3311.persistence.Database;
import com.eecs3311.view.IPanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserResultsPanel implements ActionListener, IPanelView {

    private JPanel container = new JPanel();
    private JPanel releaseContainer = new JPanel();
    private JLabel textJLabel = new JLabel("Find Friends");
    private String state = "releasePage";

    public UserResultsPanel() {
        initComponents();
    }

    @Override
    public void initComponents() {
        container.setLayout(new GridBagLayout());
        initReleaseContainer(Database.getRegisterInstance().getUserList());
    }

    // Update book view from search input from search bar
    public void updateFriendsView(ArrayList<String> results) {
        this.releaseContainer.removeAll();
        this.releaseContainer.revalidate();
        this.releaseContainer.repaint();
        this.container.removeAll();
        this.container.revalidate();
        this.container.repaint();
        state = "resultPage";
        initReleaseContainer(results);
        this.container.updateUI();
    }

    /**
     * To initialize
     */
    private void initTextLayout() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1;
        c.ipady = 1;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        container.add(textJLabel, c);
    }

    private void initScrollPaneView(ArrayList<String> results) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1250;
        c.ipady = 300;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JScrollPane scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        if (state.equals("resultPage")) {
            this.textJLabel.setText(results.size() + " " + ((results.size() == 1 ? "result" : "results") + " found..."));
            scroll = new JScrollPane(releaseContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            releaseContainer.setLayout(new GridLayout((int)Math.ceil(results.size()/7)+1, 7));
        }
        container.add(scroll, c);
    }

    private void initReleaseContainer(ArrayList<String> results) {
        if (results == null)
            return;
        GridLayout gridLayout = new GridLayout((int)Math.ceil(results.size()/7)+1, 7);
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/images/profileimg.png")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        JLabel picLabel = new JLabel(imageIcon);
        releaseContainer.setLayout(gridLayout);
        releaseContainer.add(picLabel, c);
        results.parallelStream().forEach(ibm -> {
            JLabel label = new JLabel(ibm);
            label.addMouseListener(onUserClicked(label.getText()));
            releaseContainer.add(label);
        });
        initTextLayout();
        initScrollPaneView(results);
    }

    private MouseListener onUserClicked(String label) {
        return new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                System.out.printf("Clicked %s\n",label);
                JOptionPane.showMessageDialog(releaseContainer, "Selected user "+label);
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public JPanel getView() {
        return this.container;
    }

    @Override
    public JPanel getParentContainer() {
        return null;
    }

    @Override
    public void setParentContainer(JPanel parent) {
    }
}
