package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import common.IConstants;

public class MainWindow extends JFrame implements IConstants{

	private GameFrame gameFrame;
	private InfoPanel player1Info;
	private InfoPanel player2Info;
	private LoginPanel loginPanel;
	private JButton ready;
	private ActionListener playerReady;
	private JButton loginButton;
	private ActionListener login;
	
	public MainWindow() {
		// Initializes main window JFrame
		super(WINDOW_NAME);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		super.setLayout(null);
		super.setResizable(false);
		
		// Initializes game panels
		this.gameFrame = new GameFrame();
		this.player1Info = new InfoPanel(PLAYER_1, 5, 5);
		this.player2Info = new InfoPanel(PLAYER_2, 1227, 5);
		
		//Initializes buttons
		this.createListeners();
		this.ready = new JButton(READY_BUTTON_TEXT);
		this.ready.setBounds(READY_BUTTON_X, READY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		this.ready.addActionListener(this.playerReady);
		
		this.loginButton = new JButton(LOGIN_BUTTON_TEXT);
		this.loginButton.addActionListener(this.login);
		
		this.loginPanel = new LoginPanel(this.loginButton);
		
		//Adds componentss and sets JFrame visible
		super.add(this.loginPanel);
		super.setVisible(true);
	}
	
	public void createListeners() {
		this.playerReady = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println(READY_BUTTON_TEXT); // Temporary test
			};
        };
        
        this.login = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		initGameArea();
        	};
        };
	}
	
	private void initGameArea() {
		this.loginButton.setVisible(false);
		this.loginPanel.setVisible(false);
		super.add(this.player1Info);
		super.add(this.gameFrame);
		super.add(this.player2Info);
		super.add(this.ready);
		super.validate();
		super.repaint();
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}
}
