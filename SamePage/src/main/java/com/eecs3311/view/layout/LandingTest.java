package com.eecs3311.view.layout;

import javax.swing.*;
import java.awt.*;

import com.eecs3311.view.Book.LatestBookView;
import com.eecs3311.view.components.MenubarFrame;
import com.eecs3311.view.components.SearchAndResults;
import com.eecs3311.view.components.SearchBarFrame;

public class LandingTest extends JFrame {

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int HEIGHT = (int) (0.75 * screenSize.height);
    private final int WIDTH = (int) (0.75 * screenSize.width);

    SearchAndResults mediator = new SearchAndResults();

    public LandingTest() {

        /*************** Menubar ******************/
        MenubarFrame menubar = new MenubarFrame();
        setJMenuBar(menubar.getJMenuBar());

        JPanel root = new JPanel();
        root.setBackground(new Color(254, 255, 255));
        root.setLayout(new GridLayout(2, 1, 1, 1));

        SearchBarFrame sbf = new SearchBarFrame(mediator);
        LatestBookView lbv = new LatestBookView(mediator);

        mediator.setLbv(lbv);
        mediator.setSbf(sbf);

        root.add(sbf.getView());
        root.add(lbv.getView());

        add(root);
        setSize(WIDTH, HEIGHT);
        setTitle("Same Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LandingTest();
    }
}
