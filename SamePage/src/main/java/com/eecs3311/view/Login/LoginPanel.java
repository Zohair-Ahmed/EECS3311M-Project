package com.eecs3311.view.Login;

import javax.swing.*;

import com.eecs3311.presenter.Login.ILoginPresenter;
import com.eecs3311.view.IPanelView;
import com.eecs3311.view.layout.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPanel implements ILoginPanelView, IPanelView, ActionListener {

	// Creating components that will be used for the Login page
	private JPanel panel = new JPanel();
	private JTextField emailField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel email = new JLabel("Email: ");
	private JLabel password = new JLabel("Password: ");
	private JButton loginSubmit = new JButton("Login");
	private JLabel lblHeader = new JLabel("Login");
	private JLabel loginStatus = new JLabel("status pending");
	private SpringLayout layout = new SpringLayout();
	private Main main;
	private Timer timer;


	private ILoginPresenter loginPresenter;

	// Constructor for an instance of the Login page
	public LoginPanel() {
		panel.setLayout(new GridBagLayout());
		initComponents();
		//Create time to  allow login to switch to landing page
		timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Switch to the landing frame panel
				main.getCard().show(main.getContainer(), "Landing");
				main.addProfilePanel();
			}
		});
		timer.setRepeats(false); // Only perform the action once
		timer.stop(); // Stop the timer initially
	}

	@Override
	public void initComponents() {
		initLoginLabel();
		initEmailLabel();
		initEmailTextField();
		initPasswordLabel();
		initPasswordTextField();
		initLoginButton();
		initStatusLabel();
	}

	// Initializing the login header
	public void initLoginLabel() {
		JLabel filler1 = new JLabel("");
		JLabel filler2 = new JLabel("");
		GridBagConstraints c = new GridBagConstraints();
		lblHeader.setFont(new Font("Futura", Font.BOLD, 25));
		c.insets = new Insets(0, 35, 35, 55);
		panel.add(filler1, c);
		c.gridx = 1;
		panel.add(lblHeader, c);
		c.gridx = 2;
		panel.add(filler2, c);
	}

	// Initializing email label above the user text field
	public void initEmailLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 5, 0);
		c.gridy = 1;
		panel.add(email, c);
	}

	// Email text field where user can enter their email
	public void initEmailTextField() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 20, 0);
		c.gridy = 2;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(emailField, c);
	}

	// Initializing password label
	private void initPasswordLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 5, 0);
		c.gridy = 3;
		panel.add(password, c);
	}

	// Initializing password label
	private void initPasswordTextField() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 20, 0);
		c.gridy = 4;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(passwordField, c);
	}

	// Initializing login button to give feedback on click
	private void initLoginButton() {
		loginSubmit.addActionListener(this);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 35, 0, 0);
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		panel.add(loginSubmit, c);
	}

	// Initializing status message label design
	private void initStatusLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25, 0, 0, 40);
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.EAST;
		panel.add(loginStatus, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginSubmit) {
			this.loginPresenter.updateModelFromView(getEmail(), getPassword());
		}
	}

	public String getEmail() {
		return emailField.getText();
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	public void updateLoginStatus(String status) {
		loginStatus.setText(status);

		if (checkLogin()) {
			// Start the timer if the login is successful
			timer.start();
		}
	}

	@Override
	public JPanel getView() {
		return panel;
	}

	@Override
	public JPanel getParentContainer() {
		return null;
	}

	@Override
	public void setParentContainer(JPanel parent) {
	}

	@Override
	public ILoginPresenter getPresenter() {
		return this.loginPresenter;
	}

	@Override
	public void setPresenter(ILoginPresenter ilp) {
		this.loginPresenter = ilp;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public boolean checkLogin() {
		if (loginStatus.getText().contains("Successfully Logged in")) {
			return true;
		}

		else {
			return false;
		}
	}



}