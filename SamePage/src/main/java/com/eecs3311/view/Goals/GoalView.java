package com.eecs3311.view.Goals;

import com.eecs3311.presenter.Goals.IGoalPresenter;
import com.eecs3311.view.IPanelView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoalView implements IGoalView, IPanelView, ActionListener {

    private IGoalPresenter igp;
    private JPanel root = new JPanel(new GridBagLayout());
    private JLabel trackerLbl = new JLabel("0 / 10");
    private JButton incrementGoalBtn = new JButton("+");
    private JProgressBar progressBar = new JProgressBar();
    private SpringLayout layout = new SpringLayout();
    private int level = 0;
    private int numOfBooksRead = 0;

    public GoalView() {
        initComponents();
    }

    @Override
    public void initiateText() {
        level = getPresenter().getGoalModel().getLevel();
        numOfBooksRead = getPresenter().getGoalModel().getNumOfBooksReads();
        trackerLbl.setText(igp.getGoalModel().getGoalInfo());
        progressBar.setValue((numOfBooksRead - (level - 1) * 10) * 10);
    }

    @Override
    public void updateNumbers(int level, int numOfBooksRead) {
        this.progressBar.setValue((numOfBooksRead - (level - 1) * 10) * 10);
    }

    @Override
    public void initComponents() {
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        JLabel goalLbl = new JLabel("Read Books");
        incrementGoalBtn.addActionListener(this);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        goalLbl.setFont(new Font("Futura", Font.BOLD, 25));
        root.add(goalLbl, c);
        c.gridx = 1;
        c.gridwidth = 1;
        trackerLbl.setFont(new Font("Futura", Font.BOLD, 25));
        root.add(trackerLbl, c);
        c.gridy = 2;
        c.gridwidth = 4;
        root.add(incrementGoalBtn, c);
        c.gridy = 3;
        c.gridwidth = 4;
        root.add(progressBar, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.incrementGoalBtn) {
            getPresenter().updateModelFromView();
        }
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
    public void updateLabel(String text) {
        this.trackerLbl.setText(text);
        this.progressBar.setString(text);
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