package com.eecs3311.view.layout;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.eecs3311.view.components.ResultsPanel;
import com.eecs3311.view.components.Menubar;

public class Main extends JFrame {

  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  private final int HEIGHT = (int) (0.75 * screenSize.height);
  private final int WIDTH = (int) (0.75 * screenSize.width);

  public Main() {
    /*************** Menubar ******************/
    // MenubarFrame menubar = new MenubarFrame();
    // setJMenuBar(menubar.getJMenuBar());

    JMenuBar menuBar = new JMenuBar();
    // Create a JMenu object
    JMenu menu = new JMenu("Login");

    // Create a JMenuItem object
    JMenuItem menuItem = new JMenuItem("Open");

    // Make screen full-screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize((int) (0.75 * screenSize.width), (int) (0.75 * screenSize.height));

    /*************** Landing Page Setup ******************/

    JPanel contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    setContentPane(contentPane);

    CardLayout cards = new CardLayout();
    JPanel container = new JPanel(cards);
    LoginPanel login = new LoginPanel();
    RegisterPanel register = new RegisterPanel();

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(128, 128, 255));

    LandingPanel landingPanel = new LandingPanel();
    mainPanel.add(landingPanel.getView());
    mainPanel.setSize(500, 500);

    container.add(mainPanel, "Landing");
    container.add(login.getView(), "Login");
    container.add(register.getView(), "Register");

    // on the menubar
    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cards.show(container, "Login");
      }
    });

    JButton registerButton = new JButton("Register");
    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cards.show(container, "Register");
      }
    });

    JButton homeButton = new JButton("Home");
    homeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cards.show(container, "Landing");
      }
    });

    // Add the button to the menu bar
    menuBar.add(homeButton);
    menuBar.add(loginButton);
    menuBar.add(registerButton);

    // Set the menu bar as the menu bar of the JFrame
    setJMenuBar(menuBar);

    // JPanel controlPanel = new JPanel();
    // controlPanel.setBackground(Color.LIGHT_GRAY);
    // controlPanel.add(loginButton);
    // controlPanel.add(registerButton);

    contentPane.add(container);
    // contentPane.add(controlPanel);

    cards.show(container, "Landing");

    setTitle("Same Page");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }

}
