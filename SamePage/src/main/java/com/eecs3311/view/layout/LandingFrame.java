package com.eecs3311.view.layout;

import java.awt.*;

import javax.swing.*;

import com.eecs3311.view.Book.LatestBookView;
import com.eecs3311.view.components.MenubarFrame;

public class LandingFrame extends JFrame {

  public LandingFrame() {
    /*************** Menubar ******************/
    MenubarFrame menubar = new MenubarFrame();
    setJMenuBar(menubar.getJMenuBar());

    // Make screen full-screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize((int) (0.75 * screenSize.width), (int) (0.75 * screenSize.height));

    /*************** Landing Page Setup ******************/

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(128, 128, 255));
    LatestBookView bookView = new LatestBookView();
    mainPanel.add(bookView.getView());
    mainPanel.setSize(500, 500);
    add(mainPanel);

    setTitle("Same Page");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String[] args) {
    new LandingFrame();
  }
}
