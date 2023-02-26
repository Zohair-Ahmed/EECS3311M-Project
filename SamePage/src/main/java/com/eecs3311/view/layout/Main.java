package com.eecs3311.view.layout;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import com.eecs3311.view.components.Menubar;

public class Main extends JFrame implements ActionListener {

  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  private final int HEIGHT = (int) (0.75 * screenSize.height);
  private final int WIDTH = (int) (0.75 * screenSize.width);

  private JButton loginButton;
  private JButton registerButton;
  private JButton homeButton;
  private JPanel contentPane;

  private CardLayout cards = new CardLayout();
  private JPanel container = new JPanel(cards);
  private LoginPanel login = new LoginPanel();
  private RegisterPanel register = new RegisterPanel();

  private void initHomeButtonUI() {
    homeButton = new JButton("Home");
    homeButton.setFont(new Font("Euphemia UCAS", Font.BOLD, 17));
    homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        homeButton.setForeground(new Color(0, 66, 131));
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
    homeButton.addActionListener(this);
  }

  private void initRegisterButtonUI() {
    registerButton = new JButton("Register");
    registerButton.setFont(new Font("Euphemia UCAS", Font.BOLD, 17));
    registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        registerButton.setForeground(new Color(0, 66, 131));
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
    registerButton.addActionListener(this);
  }

  private void initLoginButtonUI() {
    // on the menubar
    loginButton = new JButton("Login");
    loginButton.setFont(new Font("Euphemia UCAS", Font.BOLD, 17));
    loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        loginButton.setForeground(new Color(0, 66, 131));
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
    loginButton.addActionListener(this);
  }

  private void initPageSetup() {
    contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    setContentPane(contentPane);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(128, 128, 255));

    LandingPanel landingPanel = new LandingPanel();
    mainPanel.add(landingPanel.getView());
    mainPanel.setSize(500, 500);

    container.add(mainPanel, "Landing");
    container.add(login.getView(), "Login");
    container.add(register.getView(), "Register");
  }

  public Main() {
    Menubar menuBar = new Menubar();
    menuBar.add(Box.createHorizontalGlue());
    setJMenuBar(menuBar);
    setSize(WIDTH, HEIGHT);

    initPageSetup();
    initLoginButtonUI();
    initRegisterButtonUI();
    initHomeButtonUI();

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

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton)
      cards.show(container, "Login");
    if (e.getSource() == registerButton)
      cards.show(container, "Register");
    if (e.getSource() == homeButton)
      cards.show(container, "Landing");
  }

}
