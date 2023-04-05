package com.eecs3311.view.Register;

import com.eecs3311.presenter.Register.IRegisterPresenter;
import com.eecs3311.view.IPanelView;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;


public class RegisterPanel implements IRegisterPanelView, IPanelView, ActionListener {

	private JPanel containerPanel = new JPanel();

	private IRegisterPresenter registerPresenter;

	// Input labels
	private JLabel lblEmail = new JLabel("Email:");
	private JLabel lblUsername = new JLabel("Username:");
	private JLabel lblPassword = new JLabel("Password:");
	private JLabel lblConfirmPass = new JLabel("Confirm Password:");
	private JLabel lblTermsAndConditions = new JLabel("");

	// Register button
	private JButton btnRegister;

	// Label used to output feedback from registration
	private JLabel lblConfirmation = new JLabel("");

	// Input fields
	private JTextField tfUsername;
	private JTextField tfEmail;
	private JPasswordField tfPassword;
	private JPasswordField tfConfirmPass;
	private JCheckBox cbTerms;

	// Layout variables
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private String usernamePattern = "^[a-zA-Z0-9_-]{3,16}$";


	/**
	 * Create the GUI frame. Plan to Refactor in Itr2
	 */
	public RegisterPanel() {
		containerPanel.setLayout(new GridBagLayout());
		initComponents();
	}

	@Override
	public void initComponents() {
		// Initializing text labels to be displayed on the register panel
		initRegisterLabel();
		initEmailLabel();
		initUsernameLabel();
		initPasswordLabel();
		initConfirmPasswordLabel();
		initTermsAndConditionsLabel();
		initMessageLabel();

//		// Initializing input fields to be displayed on the register panel
		initEmailTextField();
		initUsernameTextField();
		initPasswordTextField();
		initConfirmPasswordField();
		initTermsAndConditionsCheckbox();
		initRegisterButton();
	}

	// Register label
	private void initRegisterLabel() {
		JLabel lblRegisterHere = new JLabel("Register Here");
		JLabel filler1 = new JLabel("");
		JLabel filler2 = new JLabel("");
		lblRegisterHere.setFont(new Font("Futura", Font.BOLD, 25));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 5, 35, 55);
		containerPanel.add(filler1, c);
		c.gridx = 1;
		containerPanel.add(lblRegisterHere, c);
		c.gridx = 2;
		containerPanel.add(filler2, c);
	}

	// Initializing Email label
	private void initEmailLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 5, 0);
		c.gridy = 1;
		containerPanel.add(lblEmail, c);
	}

	// Initializing the Email text field for input
	private void initEmailTextField() {
		tfEmail = new JTextField();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 20, 0);
		c.gridy = 2;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		containerPanel.add(tfEmail, c);
	}


	// Initializing username label
	private void initUsernameLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 5, 0);
		c.gridy = 3;
		containerPanel.add(lblUsername, c);
	}

	// Initializing username text field
	private void initUsernameTextField() {
		tfUsername = new JTextField();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 20, 0);
		c.gridy = 4;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		containerPanel.add(tfUsername, c);
	}

	// Initializing password label
	private void initPasswordLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 5, 0);
		c.gridy = 5;
		containerPanel.add(lblPassword, c);
	}

	// Initializing the password text box
	private void initPasswordTextField() {
		tfPassword = new JPasswordField();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 20, 0);
		c.gridy = 6;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		containerPanel.add(tfPassword, c);
	}

	private void initConfirmPasswordLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 5, 0);
		c.gridy = 7;
		containerPanel.add(lblConfirmPass, c);
	}

	// Initializing the confirm password field
	private void initConfirmPasswordField() {
		tfConfirmPass = new JPasswordField();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 20, 0);
		c.gridy = 8;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		containerPanel.add(tfConfirmPass, c);
	}

	// Initializing the t&c'c checkbox input, needs to be clicked to pass the
	private void initTermsAndConditionsCheckbox() {
		cbTerms = new JCheckBox("I have read the terms and conditions");
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 5, 0);
		c.gridx = 0;
		c.gridy = 9;
		containerPanel.add(cbTerms, c);
	}

	private void initTermsAndConditionsLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 25, 0);
		c.gridx = 1;
		c.gridy = 9;
		containerPanel.add(lblTermsAndConditions, c);
	}

	// Initializing register button to give feedback on click
	private void initRegisterButton() {
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(this);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 35, 0, 0);
		c.gridx = 1;
		c.gridy = 10;
		c.anchor = GridBagConstraints.WEST;
		containerPanel.add(btnRegister, c);
	}

	// Initializing status message label
	private void initMessageLabel() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(25, 0, 0, 40);
		c.gridx = 1;
		c.gridy = 11;
		c.anchor = GridBagConstraints.WEST;
		containerPanel.add(lblConfirmation, c);
	}

	public void actionPerformed(ActionEvent e) {
		// Logic for checking all required fields have valid input upon clicking
		if (e.getSource() == btnRegister) {
			String email = tfEmail.getText().trim();
			String username = tfUsername.getText().trim();

			CheckFields(tfUsername, lblUsername);
			CheckFields(tfEmail, lblEmail);
			CheckFields(tfPassword, lblPassword);
			CheckFields(tfConfirmPass, lblConfirmPass);

			// Validate email address
			if (!Pattern.matches(emailPattern, email)) {
				JOptionPane.showMessageDialog(containerPanel, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Validate username
			if (!Pattern.matches(usernamePattern, username)) {
				JOptionPane.showMessageDialog(containerPanel, "Username must be 3-16 characters long and can only contain letters, numbers, hyphens, and underscores", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!cbTerms.isSelected())
				cbTerms.setForeground(new Color(255, 25, 9));
			else
				cbTerms.setForeground(new Color(0, 0, 0));

			boolean fieldCheck = ValidateFields();
			if (fieldCheck == true)
				this.registerPresenter.updateModelFromView(tfUsername.getText(), tfEmail.getText(), new String(tfPassword.getPassword()));
		}
	}

	// Reads individual fields and displays error icon if they don't have input
	private void CheckFields(JTextField input, JLabel label) {
		if (input.getText().equals("")) {
			label.setText(label.getText().substring(0,label.getText().indexOf(":")+1)+""+" *");
			label.setForeground(new Color(255, 25, 9));
			label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		} else {
			label.setForeground(new Color(0, 0, 0));
			label.setText(label.getText().substring(0,label.getText().indexOf(":")+1));
		}
	}

	// Reads that all fields have some input and sends confirmation message,
	// otherwise gives error
	private boolean ValidateFields() {
		if (!tfUsername.getText().equals("") && !tfEmail.getText().equals("") && !tfPassword.getText().equals("") && !tfConfirmPass.getText().equals("") && cbTerms.isSelected()) {
			lblUsername.setText("Username:");
			lblEmail.setText("Email:");
			lblPassword.setText("Password:");
			lblConfirmPass.setText("Confirm Password:");
			if (!tfPassword.getText().equals(tfConfirmPass.getText())) {
					lblConfirmation.setText("Passwords don't match!");
					lblPassword.setText(lblPassword.getText().substring(0,lblPassword.getText().indexOf(":")+1)+""+" *");
					lblPassword.setForeground(new Color(255, 25, 9));
					lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
					lblConfirmPass.setText(lblConfirmPass.getText().substring(0,lblConfirmPass.getText().indexOf(":")+1)+""+" *");
					lblConfirmPass.setForeground(new Color(255, 25, 9));
					lblConfirmPass.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
					return false;
			}
			return true;
		} else {
			lblConfirmation.setText("Please enter all valid credentials!");
			return false;
		}
	}

	public void updateRegisterStatus(String status) {
		lblConfirmation.setText(status);
	}

	@Override
	public JPanel getView() {
		return this.containerPanel;
	}

	// Implement for future release
	@Override
	public JPanel getParentContainer() {
		return null;
	}

	// Implement for future release
	@Override
	public void setParentContainer(JPanel parent) {
	}

	// Methods used to create connection between Presenter and View 
	@Override
	public IRegisterPresenter getPresenter() {
		return this.registerPresenter;
	}

	@Override
	public void setPresenter(IRegisterPresenter irp) {
		this.registerPresenter = irp;
	}
}