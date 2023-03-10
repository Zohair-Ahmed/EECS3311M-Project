package com.eecs3311.view.layout;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import com.eecs3311.model.Login.ILoginModel;
import com.eecs3311.model.Login.LoginModel;
import com.eecs3311.model.User.User;
import com.eecs3311.model.enums.State;
import com.eecs3311.persistence.Database;
import com.eecs3311.presenter.Login.ILoginPresenter;
import com.eecs3311.presenter.Login.LoginPresenter;
import com.eecs3311.view.Login.ILoginPanelView;
import com.eecs3311.view.Login.LoginPanel;
import com.eecs3311.model.Register.IRegisterModel;
import com.eecs3311.model.Register.RegisterModel;
import com.eecs3311.presenter.Register.IRegisterPresenter;
import com.eecs3311.presenter.Register.RegisterPresenter;
import com.eecs3311.view.Register.IRegisterPanelView;
import com.eecs3311.view.Register.RegisterPanel;
import com.eecs3311.view.components.Menubar;

public class Main extends JFrame implements ActionListener {

  private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  private final int HEIGHT = (int) (screenSize.height);
  private final int WIDTH = (int) (screenSize.width);

  private JButton loginButton;
  private JButton registerButton;
  private JButton homeButton;

  private JButton profileButton;
  private JPanel contentPane;

  private CardLayout cards = new CardLayout();
  private JPanel container = new JPanel(cards);
  private JPanel loginPanel;
  private JPanel registerPanel;

  private ILoginPanelView ilv = new LoginPanel();
  private ILoginPresenter ilp = new LoginPresenter();
  private ILoginModel ilm = new LoginModel();

  
  private IRegisterPanelView irv = new RegisterPanel();
  private IRegisterPresenter irp = new RegisterPresenter();
  private IRegisterModel irm = new RegisterModel();

  private ProfilePanel profile;
  private Menubar menuBar;

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

  private void initProfileButtonUI() {
    profileButton = new JButton("Profile");
    profileButton.setFont(new Font("Euphemia UCAS", Font.BOLD, 17));
    profileButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        profileButton.setForeground(new Color(0, 66, 131));
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        profileButton.setForeground(new Color(255, 255, 255));
      }
    });
    profileButton.setHorizontalAlignment(SwingConstants.LEFT);
    profileButton.setForeground(new Color(255, 255, 255));
    profileButton.setBackground(new Color(0, 128, 255));
    profileButton.setOpaque(true);
    profileButton.setBorderPainted(false);
    profileButton.addActionListener(this);
  }

  JPanel mainPanel;
  LandingPanel landingPanel;
  private void initPageSetup() {
    contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    setContentPane(contentPane);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(128, 128, 255));

    landingPanel = new LandingPanel();
    mainPanel.add(landingPanel.getView());
    mainPanel.setSize(500, 500);

    configureLoginMVP();
    configureRegisterMVP();
    configurePanelNames(mainPanel, ilv.getView(), irv.getView());
    container.add(mainPanel, "Landing");
    container.add(this.loginPanel, "Login");
    container.add(this.registerPanel, "Register");
  }

  public void setLandingPanel(LandingPanel lp) {
    mainPanel.remove(this.landingPanel.getView());
    this.landingPanel = lp;
    mainPanel.add(this.landingPanel.getView());
  }

  private void configureLoginMVP() {
    ilp.setModel(ilm);
    ilm.setPresenter(ilp);
    ilp.setView(ilv);
    ilv.setPresenter(ilp);
    ilv.setMain(this);
  }

  private void configureRegisterMVP() {
    irp.setModel(irm);
    irm.setPresenter(irp);
    irp.setView(irv);
    irv.setPresenter(irp);
  }

  private void configurePanelNames(JPanel mainPanel, JPanel loginPanel, JPanel registerPanel) {
    mainPanel.setName("Landing");
    this.loginPanel = loginPanel;
    this.loginPanel.setName("Login");

    this.registerPanel = registerPanel;
    this.registerPanel.setName("Register");
  }

  public Main() {
    menuBar = new Menubar();
    menuBar.add(Box.createHorizontalGlue());
    setJMenuBar(menuBar);
    setSize(WIDTH, HEIGHT);

    initPageSetup();
    initLoginButtonUI();
    initRegisterButtonUI();
    initHomeButtonUI();

    // DP for database implementation
    Database.getInstance();

    // Add the button to the menu bar
    menuBar.add(loginButton);
    menuBar.add(registerButton);
    menuBar.add(homeButton);
    contentPane.add(container);
    cards.show(container, "Landing");
    setTitle("Same Page");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
    setLocationRelativeTo(null);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton)
      cards.show(container, "Login");
    if (e.getSource() == registerButton)
      cards.show(container, "Register");
    if (e.getSource() == homeButton) {
      if (User.getInstance().getLoginState() != State.GUEST && !checkCurrentCard().equals("Landing")) {
        setLandingPanel(new LandingPanel());
      }
      cards.show(container, "Landing");
    }
    if (e.getSource() == profileButton) {
      cards.show(container, "Profile");
    }
  }


  public JPanel getContainer() {
    return this.container;
  }

  public CardLayout getCard() {
    return this.cards;
  }

  /**
   * Adds the profile panel on successful login for users to view profile specific information
   */
  public void addProfilePanel() {
    profile= new ProfilePanel();
    JPanel profilePanel = profile.getView();
    profilePanel.setName("Profile");
    container.add(profile.getView(), profilePanel.getName());
    JMenuBar tempBar = new Menubar();
    tempBar.add(Box.createHorizontalGlue());
    setJMenuBar(tempBar);
    initProfileButtonUI();

    tempBar.add(homeButton);
    tempBar.add(profileButton);
  }

  /**
   * Checks the name of the current JPanel on gui and returns the name as a string
   * Used when a specific action is to be performed if a certain panel is shown on GUI
   */
  public String checkCurrentCard() {
    JPanel card = null;

    for (Component comp : container.getComponents()) {
      if (comp.isVisible() == true) {
        card = (JPanel) comp;
      }
    }

    return card.getName();
  }

}
