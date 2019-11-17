package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import common.IConstants;

public class MainWindow extends JFrame implements IConstants{

	private GameFrame gameFrame;
	private InfoPanel player1Info;
	private InfoPanel player2Info;
	private JButton ready;
	private ActionListener playerReady;
	
	public MainWindow() {
		// Initializes main window JFrame
		super(WINDOW_NAME);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		super.setLayout(null);
		super.setResizable(false);
		
		// Initializes panels
		this.gameFrame = new GameFrame();
		this.player1Info = new InfoPanel(PLAYER_1, 5, 5);
		this.player2Info = new InfoPanel(PLAYER_2, 1227, 5);
		
		//Initializes button
		this.createListeners();
		this.ready = new JButton(BUTTON_TEXT);
		this.ready.setBounds(BUTTON_X, BUTTON_Y, 100, 40);
		this.ready.addActionListener(this.playerReady);
		
		
		//Adds componentss and sets JFrame visible
		super.add(this.player1Info);
		super.add(this.gameFrame);
		super.add(this.player2Info);
		super.add(this.ready);
		super.setVisible(true);
	}
	
	public void createListeners() {
		this.playerReady = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println(BUTTON_TEXT);
			};
        };
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}
}
