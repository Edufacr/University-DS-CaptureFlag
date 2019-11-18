package gui;

import java.awt.Color;
import java.awt.Font;

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
		
		this.panelTitle = new JLabel(LOGIN_PANEL_TITLE);
		this.email = new JLabel(EMAIL_ENTRY_LABEL);
		this.password = new JLabel(PASSWORD_ENTRY_LABEL);
		this.emailEntry = new JTextField();
		this.passwordEntry = new JTextField();
		
		Font customFont = new Font(Font.SANS_SERIF, Font.BOLD, TITLE_FONT_SIZE);
		this.panelTitle.setFont(customFont);
		
		this.panelTitle.setBounds(LOGIN_TITLE_X, LOGIN_TITLE_Y, LOGIN_TITLE_WIDTH, LOGIN_TITLE_HEIGHT);
		this.email.setBounds(EMAIL_LABEL_X, EMAIL_LABEL_Y, EMAIL_LABEL_WIDTH, EMAIL_LABEL_HEIGHT);
		this.emailEntry.setBounds(EMAIL_ENTRY_X, EMAIL_ENTRY_Y, EMAIL_ENTRY_WIDTH, EMAIL_ENTRY_HEIGHT);
		this.password.setBounds(PASSWORD_LABEL_X, PASSWORD_LABEL_Y, PASSWORD_LABEL_WIDTH, PASSWORD_LABEL_HEIGHT);
		this.passwordEntry.setBounds(PASSWORD_ENTRY_X, PASSWORD_ENTRY_Y, PASSWORD_ENTRY_WIDTH, PASSWORD_ENTRY_HEIGHT);
		
		super.add(this.panelTitle);
		super.add(this.email);
		super.add(this.emailEntry);
		super.add(this.password);
		super.add(this.passwordEntry);
		super.add(pButton);
	}
	
	public String getEmail() {
		return this.emailEntry.getText();
	}
	
	public String getPassword() {
		return this.passwordEntry.getText();
	}
}
