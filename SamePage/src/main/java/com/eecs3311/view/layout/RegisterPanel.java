package com.eecs3311.view.layout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.eecs3311.view.IPanelView;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPasswordField;

public class RegisterPanel implements IPanelView {

	private JPanel containerPanel = new JPanel();

	private JLabel lblUserCheck;
	private JLabel lblEmailCheck;
	private JLabel lblPassCheck;
	private JLabel lblConfCheck;
	private JLabel lblTermsCheck;

	private JTextField tfUsername;
	private JTextField tfEmail;
	private JPasswordField tfPassword;
	private JPasswordField tfConfirmPass;
	private JCheckBox cbTerms;
	private JLabel lblConfirmation;

	/**
	 * Create the GUI frame. Plan to Refactor in Itr2
	 */
	public RegisterPanel() {
		initComponents();
	}

	// Reads individual fields and displays error icon if they don't have input
	private void CheckFields(JTextField input, JLabel label) {
		if (input.getText().equals("")) {
			label.setText("*");
		} else {
			label.setText("");
		}
	}

	// Reads that all fields have some input and sends confirmation message,
	// otherwise gives error
	private void ValidateFields() {
		if (!tfUsername.getText().equals("") && !tfEmail.getText().equals("")
				&& !tfPassword.getText().equals("") && !tfConfirmPass.getText().equals("")
				&& cbTerms.isSelected()) {
			lblUserCheck.setText("");
			lblEmailCheck.setText("");
			lblPassCheck.setText("");
			lblConfCheck.setText("");
			lblConfirmation.setText("Confirmation Sent!");
		} else {
			lblConfirmation.setText("Please enter all valid credentials!");
		}
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

	@Override
	public void initComponents() {
		// setResizable(false);

		// setTitle("Register");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 486);
		// containerPanel = new JPanel();
		// containerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// setContentPane(containerPanel);
		containerPanel.setLayout(null);

		// Register label
		JLabel lblRegisterHere = new JLabel("Register Here");
		lblRegisterHere.setFont(new Font("Segoe print", Font.BOLD, 25));
		lblRegisterHere.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRegisterHere.setBounds(140, 6, 203, 56);
		containerPanel.add(lblRegisterHere);

		// Username label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(62, 89, 80, 16);
		containerPanel.add(lblUsername);

		// * username label
		lblUserCheck = new JLabel("");
		lblUserCheck.setForeground(new Color(255, 26, 10));
		lblUserCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblUserCheck.setBounds(391, 115, 33, 16);
		containerPanel.add(lblUserCheck);

		// Username text field
		tfUsername = new JTextField();
		tfUsername.setBounds(62, 110, 331, 26);
		containerPanel.add(tfUsername);
		tfUsername.setColumns(10);

		// Email label
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(62, 159, 80, 16);
		containerPanel.add(lblEmail);

		// * email label
		lblEmailCheck = new JLabel("");
		lblEmailCheck.setForeground(new Color(255, 26, 10));
		lblEmailCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblEmailCheck.setBounds(391, 181, 33, 16);
		containerPanel.add(lblEmailCheck);

		// Email text field
		tfEmail = new JTextField();
		tfEmail.setBounds(62, 176, 331, 26);
		containerPanel.add(tfEmail);
		tfEmail.setColumns(10);

		// Password label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(62, 225, 80, 16);
		containerPanel.add(lblPassword);

		// * password label
		lblPassCheck = new JLabel("");
		lblPassCheck.setForeground(new Color(255, 25, 9));
		lblPassCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPassCheck.setBounds(391, 247, 33, 16);
		containerPanel.add(lblPassCheck);

		// Password text field
		tfPassword = new JPasswordField();
		tfPassword.setBounds(62, 242, 331, 26);
		containerPanel.add(tfPassword);
		tfPassword.setColumns(10);

		// Confirm Password label
		JLabel lblConfirmPass = new JLabel("Confirm Password:");
		lblConfirmPass.setBounds(62, 290, 127, 16);
		containerPanel.add(lblConfirmPass);

		// * confirm password label
		lblConfCheck = new JLabel("");
		lblConfCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfCheck.setForeground(new Color(255, 25, 9));
		lblConfCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblConfCheck.setBounds(391, 313, 33, 16);
		containerPanel.add(lblConfCheck);

		// Confirm password text field
		tfConfirmPass = new JPasswordField();
		tfConfirmPass.setBounds(62, 308, 331, 26);
		containerPanel.add(tfConfirmPass);
		tfConfirmPass.setColumns(10);

		// T&C Checkbox
		cbTerms = new JCheckBox("I have read the terms and conditions");
		cbTerms.setBounds(83, 346, 273, 23);
		containerPanel.add(cbTerms);

		// * t&c label
		lblTermsCheck = new JLabel("");
		lblTermsCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblTermsCheck.setForeground(new Color(255, 25, 9));
		lblTermsCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTermsCheck.setBounds(348, 350, 33, 16);
		containerPanel.add(lblTermsCheck);

		// Message label
		lblConfirmation = new JLabel("");
		lblConfirmation.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 11));
		lblConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmation.setBounds(62, 414, 324, 16);
		containerPanel.add(lblConfirmation);

		JButton btnRegister = new JButton("Register");
		// Logic for checking all required fields have valid input upon clicking
		// register button
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckFields(tfUsername, lblUserCheck);
				CheckFields(tfEmail, lblEmailCheck);
				CheckFields(tfPassword, lblPassCheck);
				CheckFields(tfConfirmPass, lblConfCheck);
				if (!cbTerms.isSelected()) {
					lblTermsCheck.setText("*");
				} else {
					lblTermsCheck.setText("");
				}

				ValidateFields();
			}
		});
		btnRegister.setBounds(166, 373, 117, 29);
		containerPanel.add(btnRegister);
	}
}
