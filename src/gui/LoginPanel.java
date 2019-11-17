package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.IConstants;

public class LoginPanel extends JPanel implements IConstants{
	private JLabel panelTitle;
	private JLabel email;
	private JLabel password;
	private JTextField emailEntry;
	private JTextField passwordEntry;
	
	public LoginPanel(JButton pButton) {
		super();
		super.setLayout(null);
		super.setBounds(LOGIN_PANEL_X, LOGIN_PANEL_Y, LOGIN_PANEL_WIDTH, LOGIN_PANEL_HEIGHT);
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		
		pButton.setBounds(LOGIN_BUTTON_X, LOGIN_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		this.panelTitle = new JLabel("Login");
		this.panelTitle.setLocation(20, 10);
		
		super.add(this.panelTitle);
		super.add(pButton);
	}
}
