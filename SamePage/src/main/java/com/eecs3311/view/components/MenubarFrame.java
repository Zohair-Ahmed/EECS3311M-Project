package com.eecs3311.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenubarFrame extends JFrame implements ActionListener {

  JMenuBar menubar;
  JMenu home_menu;
  JMenu login_menu;
  JMenu register_menu;
  JMenu profile_menu;

  public MenubarFrame() {
    menubar = new JMenuBar();

    home_menu = new JMenu("Home");
    login_menu = new JMenu("Login");
    register_menu = new JMenu("Register");
    profile_menu = new JMenu("My Profile");

    home_menu.addActionListener(this);
    login_menu.addActionListener(this);
    register_menu.addActionListener(this);
    profile_menu.addActionListener(this);

    setJMenuBar(menubar);
    menubar.add(home_menu);
    menubar.add(login_menu);
    menubar.add(register_menu);
    menubar.add(profile_menu);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == home_menu) {
      System.out.println("Home Screen");
    } else if (e.getSource() == login_menu) {
      System.out.println("Login Screen");
    } else if (e.getSource() == register_menu) {
      System.out.println("Register Screen");
    } else if (e.getSource() == profile_menu) {
      System.out.println("Profile Screen");
    }
  }

  public static void main(String[] args) {
    new MenubarFrame();
  }
}
