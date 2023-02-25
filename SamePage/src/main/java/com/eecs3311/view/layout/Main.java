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

    Menubar menuBar = new Menubar();
    menuBar.add(Box.createHorizontalGlue());
    setJMenuBar(menuBar);

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
    loginButton.setFont(UIManager.getFont("CheckBoxMenuItem.acceleratorFont"));
    loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
        	loginButton.setForeground(new Color(0, 0, 0));
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
        	loginButton.setForeground(new Color(255, 255, 255));
        }
    });
    loginButton.setHorizontalAlignment(SwingConstants.LEFT);
    loginButton.setForeground(new Color(255, 255, 255));
    loginButton.setBackground(new Color(0, 128, 255));
    loginButton.setOpaque(true);
    loginButton.setBorderPainted(false);
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cards.show(container, "Login");
      }
    });

    JButton registerButton = new JButton("Register");
    registerButton.setFont(UIManager.getFont("CheckBoxMenuItem.acceleratorFont"));
    registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
        	registerButton.setForeground(new Color(0, 0, 0));
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
        	registerButton.setForeground(new Color(255, 255, 255));
        }
    });
    registerButton.setHorizontalAlignment(SwingConstants.LEFT);
    registerButton.setForeground(new Color(255, 255, 255));
    registerButton.setBackground(new Color(0, 128, 255));
    registerButton.setOpaque(true);
    registerButton.setBorderPainted(false);
    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cards.show(container, "Register");
      }
    });

    JButton homeButton = new JButton("Home");
    homeButton.setFont(UIManager.getFont("CheckBoxMenuItem.acceleratorFont"));
    homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
        	homeButton.setForeground(new Color(0, 0, 0));
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
        	homeButton.setForeground(new Color(255, 255, 255));
        }
    });
    homeButton.setHorizontalAlignment(SwingConstants.LEFT);
    homeButton.setForeground(new Color(255, 255, 255));
    homeButton.setBackground(new Color(0, 128, 255));
    homeButton.setOpaque(true);
    homeButton.setBorderPainted(false);
    homeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cards.show(container, "Landing");
      }
    });

    // Add the button to the menu bar
    menuBar.add(loginButton);
    menuBar.add(registerButton);
    menuBar.add(homeButton);
    
    contentPane.add(container);

    cards.show(container, "Landing");

    setTitle("Same Page");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }

}
