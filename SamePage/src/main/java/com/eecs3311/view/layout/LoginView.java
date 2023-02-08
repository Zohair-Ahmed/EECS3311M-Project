package com.eecs3311.view.layout;

import javax.swing.*;

import com.eecs3311.model.Member;
import com.eecs3311.presenter.Login.LoginPresenter;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class LoginView extends JFrame implements ILoginView {

   
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JLabel email = new JLabel("Email:\n");
    private JLabel password = new JLabel("Password\n");
    private JLabel login = new JLabel("Login to SamePage");
    private JButton loginSubmit = new JButton("Login");
    private JLabel loginStatus = new JLabel("");

    public LoginView() {
    	
    	JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel email = new JLabel("Email:\n");
        JLabel password = new JLabel("Password\n");
        JLabel login = new JLabel("Login to SamePage");
        JButton loginSubmit = new JButton("Login");
        JLabel loginStatus = new JLabel("");
        
        email.setFont(mainFont);
        password.setFont(mainFont);
        login.setFont(mainFont);
        loginStatus.setFont(mainFont);
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        emailField.setBorder(border);
        passwordField.setBorder(border);
        
    	
    	
        JPanel formPanel = new JPanel();
        
        formPanel.add(login);
        login.setHorizontalAlignment(JLabel.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));
        mainPanel.add(formPanel, BorderLayout.NORTH);
//        mainPanel.add(email, BorderLayout.CENTER);
//        mainPanel.add(emailField, BorderLayout.CENTER);
//        mainPanel.add(password, BorderLayout.CENTER);
//        mainPanel.add(passwordField, BorderLayout.CENTER);
//
//        mainPanel.add(loginSubmit, BorderLayout.SOUTH);
//        mainPanel.add(loginStatus, BorderLayout.SOUTH);


        add(mainPanel);
        

        setTitle("Login Page");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        setVisible(true);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setLoginPerformed(ActionListener listener) {
        loginSubmit.addActionListener(listener);
    }

    public void loginStatus(String status) {
        loginStatus.setText(status);
    }


    public static void main(String[] args) {
        Member model = new Member();
        LoginView view = new LoginView();
        LoginPresenter presenter = new LoginPresenter(view, model);
        
        view.setVisible(true);
      }
}