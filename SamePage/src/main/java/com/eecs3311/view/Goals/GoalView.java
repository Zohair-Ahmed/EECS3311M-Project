package com.eecs3311.view.Goals;

import com.eecs3311.model.User.User;
import com.eecs3311.presenter.Goals.IGoalPresenter;
import com.eecs3311.view.IPanelView;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoalView implements IGoalView, IPanelView, ActionListener {

    private IGoalPresenter igp;
    private final JPanel root = new JPanel(new GridBagLayout());
    private final JLabel levelLbl = new JLabel("Level: 1");
    private final JButton incrementGoalBtn = new JButton("+");
    private final JProgressBar progressBar = new JProgressBar();

    @Override
    public void updateNumbers(int level, int numOfBooksRead) {
        this.progressBar.setValue((numOfBooksRead - (level - 1) * 10) * 10);
        levelLbl.setText("Level: " + level);
    }

    @Override
    public void initComponents() {
        int level = getPresenter().getGoalModel().getLevel();
        GridBagConstraints c = new GridBagConstraints();
        initGoalLabel(c);
        initLevelLbl(c, level);
        initProgressBar(c, level);
        initIncrementGoalBtn(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.incrementGoalBtn) {
            getPresenter().updateModelFromView();
        }
    }

    @Override
    public void updateLabel(String text) {
        this.progressBar.setString(text);
    }

    private void initGoalLabel(GridBagConstraints c) {
        JLabel goalLbl = new JLabel("Books Read");
        c.gridwidth = 1;
        c.gridy = 0;
        goalLbl.setFont(new Font("Futura", Font.BOLD, 25));
        root.add(goalLbl, c);
    }

    private void initLevelLbl(GridBagConstraints c, int level) {
        levelLbl.setText("Level: " + level);
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        levelLbl.setFont(new Font("Futura", Font.BOLD, 12));
        root.add(levelLbl, c);
    }

    private void initProgressBar(GridBagConstraints c, int level) {
        int numOfBooksRead = getPresenter().getGoalModel().getNumOfBooksReads();
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 2;
        progressBar.setStringPainted(true);
        progressBar.setValue((numOfBooksRead - (level - 1) * 10) * 10);
        progressBar.setString(numOfBooksRead +" / "+(level*10));
        progressBar.setFont(new Font("Futura", Font.BOLD, 12));
        progressBar.setForeground(new Color(64, 192, 87));
        progressBar.setBackground(Color.white);
        progressBar.setFont(new Font("Futura", Font.BOLD, 12));
        progressBar.setPreferredSize(new Dimension(275, 35));
        progressBar.setBorder(new LineBorder(Color.lightGray, 1, true));
        root.add(progressBar, c);
    }

    private void initIncrementGoalBtn(GridBagConstraints c){
        c.gridy = 1;
        c.gridx = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;
        incrementGoalBtn.setBackground(new Color(29, 152, 252));
        incrementGoalBtn.setForeground(new Color(255, 255, 255));
        incrementGoalBtn.setFont(new Font("Futura", Font.BOLD, 25));
        incrementGoalBtn.setPreferredSize(new Dimension(50, 30)); // set preferred size to 50x30 pixels
        incrementGoalBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // add empty border to separate from the right edge
        incrementGoalBtn.setFocusable(false);
        incrementGoalBtn.addActionListener(this);
        root.add(incrementGoalBtn, c);
    }

    @Override
    public IGoalPresenter getPresenter() {
        return igp;
    }

    @Override
    public void setPresenter(IGoalPresenter igp) {
        this.igp = igp;
    }

    @Override
    public JPanel getView() {
        return root;
    }

    @Override
    public JPanel getParentContainer() {
        return null;
    }

    @Override
    public void setParentContainer(JPanel parent) {
    }
}