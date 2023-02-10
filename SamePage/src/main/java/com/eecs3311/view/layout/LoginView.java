package com.eecs3311.view.layout;

import javax.swing.*;

import com.eecs3311.model.Member;
import com.eecs3311.presenter.Login.LoginPresenter;

import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ILoginView {

	// Creating components that will be used for the Login page
	private JTextField emailField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel email = new JLabel("Email: ");
	private JLabel password = new JLabel("Password: ");
	private JButton loginSubmit = new JButton("Login");
	private JLabel lblHeader = new JLabel("Login to SamePage");
	private JLabel loginStatus = new JLabel("status pending");

	/**
	 * Creates a GUI JFrame relating to the model. Invokes LoginPresenter on button
	 * click to check the user input parameters with registered SamePage accounts in
	 * database.
	 */
	public LoginView() {
		Member model = new Member();
		LoginPresenter presenter = new LoginPresenter(this, model);

		lblHeader.setHorizontalAlignment(JLabel.CENTER);
		lblHeader.setFont(new Font("Segoe print", Font.BOLD, 25));

		password.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

		emailField.setPreferredSize(new Dimension(150, emailField.getPreferredSize().height));
		passwordField.setPreferredSize(new Dimension(150, passwordField.getPreferredSize().height));

		// Creating a JPanel to use SprinLayout for organizing the component placement
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);

		// Adding components to the JPanel
		panel.add(lblHeader);
		panel.add(email);
		panel.add(emailField);
		panel.add(password);
		panel.add(passwordField);
		panel.add(loginSubmit);
		panel.add(loginStatus);

		// Used SpringLayout constraints to set location for components in the JPanel
		layout.putConstraint(SpringLayout.NORTH, lblHeader, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeader, 0, SpringLayout.HORIZONTAL_CENTER, panel);

		layout.putConstraint(SpringLayout.NORTH, email, 100, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, email, -50, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, emailField, 100, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, emailField, 50, SpringLayout.HORIZONTAL_CENTER, panel);

		layout.putConstraint(SpringLayout.NORTH, password, 130, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, password, -55, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, passwordField, 130, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordField, 105, SpringLayout.HORIZONTAL_CENTER,
				password);

		layout.putConstraint(SpringLayout.NORTH, loginSubmit, 180, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginSubmit, 0, SpringLayout.HORIZONTAL_CENTER, panel);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginStatus, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.SOUTH, loginStatus, -100, SpringLayout.SOUTH, panel);

		add(panel);

		// Set default information for the Java Application Window to ensure intended
		// size and functionality on close
		setTitle("Login Page");
		setSize(500, 400);
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
}