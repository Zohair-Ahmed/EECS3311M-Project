package com.eecs3311.view.layout;

import javax.swing.*;
import java.awt.*;

import com.eecs3311.view.Book.LatestBookView;
import com.eecs3311.view.components.MenubarFrame;
import com.eecs3311.view.components.SearchAndResults;
import com.eecs3311.view.components.SearchBarFrame;

public class LandingTest {

    SearchAndResults mediator = new SearchAndResults();
    JPanel root = new JPanel();

    public LandingTest() {

        root.setBackground(new Color(254, 255, 255));
        root.setLayout(new GridLayout(2, 1, 1, 1));

        SearchBarFrame sbf = new SearchBarFrame(mediator);
        LatestBookView lbv = new LatestBookView(mediator);

        mediator.setLbv(lbv);
        mediator.setSbf(sbf);

        root.add(sbf.getView());
        root.add(lbv.getView());
    }

    public static void main(String[] args) {
        new LandingTest();
    }

    public JPanel getView() {
        return this.root;
    }
}
