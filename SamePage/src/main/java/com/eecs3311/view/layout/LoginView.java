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
    	
    	// Lines 28 - 108 is a organized version of the Login Page however the login functionality does not work, on click of button the login status does not change
    	
    	JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel email = new JLabel("Email:\n");
        JLabel password = new JLabel("Password\n");
    	JLabel lblHeader = new JLabel("Login to SamePage");
        JButton loginSubmit = new JButton("Login");
        JLabel loginStatus = new JLabel("Status");
        
        email.setFont(mainFont);
        password.setFont(mainFont);
        login.setFont(mainFont);
        loginStatus.setFont(mainFont);
        
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        emailField.setBorder(border);
        passwordField.setBorder(border);
        
    	
    	
//        JPanel formPanel = new JPanel();
//        
//        formPanel.add(login);
//        login.setHorizontalAlignment(JLabel.CENTER);
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//        mainPanel.setBackground(new Color(128, 128, 255));
//        mainPanel.add(formPanel, BorderLayout.NORTH);
////        mainPanel.add(email, BorderLayout.CENTER);
////        mainPanel.add(emailField, BorderLayout.CENTER);
////        mainPanel.add(password, BorderLayout.CENTER);
////        mainPanel.add(passwordField, BorderLayout.CENTER);
////
////        mainPanel.add(loginSubmit, BorderLayout.SOUTH);
////        mainPanel.add(loginStatus, BorderLayout.SOUTH);
//
//
//        add(mainPanel);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        
        constraints.gridx = 1;
        constraints.gridy = -2;
        panel.add(lblHeader, constraints);
	    lblHeader.setHorizontalAlignment(JLabel.CENTER);


        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Email:"), constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(loginSubmit, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(loginStatus, constraints);
        
        emailField.setPreferredSize(new Dimension(200, emailField.getPreferredSize().height));
        passwordField.setPreferredSize(new Dimension(200, passwordField.getPreferredSize().height));
        
        add(panel);
        
        // Lines 110 - 136 is the implementation for a working version of the Login Page however the layout is unorganized
	
//	private JTextField emailField = new JTextField(10);
//	  private JPasswordField passwordField = new JPasswordField(10);
//	  private JButton loginSubmit = new JButton("Login");
//	  private JLabel lblHeader = new JLabel("Login to SamePage");
//	  private JLabel loginStatus = new JLabel("status pending");
//
//	  public LoginView() {
//	    setTitle("Login");
//	    setSize(300, 150);
//	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    setLocationRelativeTo(null);
//
//	    JPanel panel = new JPanel();
//	    panel.setLayout(new GridLayout(3, 2, 10, 10));
//	    panel.add(new JLabel("Email:"));
//	    panel.add(emailField);
//	    panel.add(new JLabel("Password:"));
//	    panel.add(passwordField);
//	    panel.add(new JPanel());
//	    panel.add(loginSubmit);
//	    panel.add(loginStatus);
//
//	    add(lblHeader, BorderLayout.NORTH);
//	    add(panel, BorderLayout.CENTER);
//
//	    lblHeader.setHorizontalAlignment(JLabel.CENTER);
//	    loginStatus.setHorizontalAlignment(JLabel.CENTER);
////        

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
        System.out.println("Login window opened");
        
        view.setVisible(true);
      }
}