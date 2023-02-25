package com.eecs3311.view.layout;

import javax.swing.*;
import java.awt.*;

import com.eecs3311.view.components.ResultsPanel;
import com.eecs3311.view.components.Menubar;
import com.eecs3311.view.components.ResultsMediator;
import com.eecs3311.view.components.SearchBar;

public class LandingPanel {

    ResultsMediator mediator = new ResultsMediator();
    JPanel root = new JPanel();

    public LandingPanel() {

        root.setBackground(new Color(254, 255, 255));
        root.setLayout(new GridLayout(2, 1, 1, 1));

        SearchBar sbf = new SearchBar(mediator);
        ResultsPanel lbv = new ResultsPanel(mediator);

        mediator.setLbv(lbv);
        mediator.setSbf(sbf);

        root.add(sbf.getView());
        root.add(lbv.getView());
    }

    public static void main(String[] args) {
        new LandingPanel();
    }

    public JPanel getView() {
        return this.root;
    }
}
