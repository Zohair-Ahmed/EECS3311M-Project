package com.eecs3311.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.eecs3311.view.layout.LandingFrame;
import com.eecs3311.view.layout.LoginView;
import com.eecs3311.view.layout.RegisterFrame;

public class MenubarFrame extends JFrame implements ActionListener {

  JMenuBar menubar;
  JMenu home_menu;
  JMenu login_menu;
  JMenu register_menu;
  JMenu profile_menu;
  JMenuItem home_menu_item;
  JMenuItem login_menu_item;
  JMenuItem register_menu_item;

  private String state = "home";

  public MenubarFrame() {

    menubar = new JMenuBar();

    home_menu = new JMenu("Home");

    home_menu_item = new JMenuItem("Go Home");
    login_menu_item = new JMenuItem("Login Page");
    register_menu_item = new JMenuItem("Register Page");

    login_menu = new JMenu("Login");
    register_menu = new JMenu("Register");
    profile_menu = new JMenu("My Profile");

    home_menu.addActionListener(this);
    login_menu.addActionListener(this);
    register_menu.addActionListener(this);
    profile_menu.addActionListener(this);

    home_menu_item.addActionListener(this);
    login_menu_item.addActionListener(this);
    register_menu_item.addActionListener(this);

    home_menu.add(home_menu_item);
    login_menu.add(login_menu_item);
    register_menu.add(register_menu_item);

    setJMenuBar(menubar);
    menubar.add(home_menu);
    menubar.add(login_menu);
    menubar.add(register_menu);
    menubar.add(profile_menu);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == home_menu_item && !state.equals("home")) {
      state = "home";
      LandingFrame home = new LandingFrame();
      home.setVisible(true);
    } else if (e.getSource() == login_menu_item && !state.equals("login")) {
      state = "login";
      LoginView login = new LoginView();
      login.setVisible(true);
    } else if (e.getSource() == register_menu_item && !state.equals("register")) {
      state = "register";
      RegisterFrame frame = new RegisterFrame();
      frame.setVisible(true);
    }
  }

  public static void main(String[] args) {
    new MenubarFrame();
  }
}