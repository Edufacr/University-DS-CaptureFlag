package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import common.IConstants;
import model.JsonManager;

public class MainWindow extends JFrame implements IConstants{

	private GameFrame gameFrame;
	private InfoPanel player1Info;
	private InfoPanel player2Info;
	private LoginPanel loginPanel;
	private JButton ready;
	private ActionListener playerReady;
	private JButton loginButton;
	private ActionListener login;
	
	private MouseAdapter gamePanelListener;
	
	private JsonManager mapLoader;
	private ArrayList<int[]> obstacleCoordinates;
	
	public MainWindow() {
		// Initializes main window JFrame
		super(WINDOW_NAME);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		super.setLayout(null);
		super.setResizable(false);
		
		this.createListeners();
		
		// Initializes game panels
		this.gameFrame = new GameFrame();
		this.gameFrame.addMouseListener(this.gamePanelListener);
		this.player1Info = new InfoPanel(PLAYER_1, PLAYER_1_INFO_X, PLAYER_1_INFO_Y);
		this.player2Info = new InfoPanel(PLAYER_2, PLAYER_2_INFO_X, PLAYER_2_INFO_Y);
		
		//Initializes buttons
		this.ready = new JButton(READY_BUTTON_TEXT);
		this.ready.setBounds(READY_BUTTON_X, READY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		this.ready.addActionListener(this.playerReady);
		
		
		// Initializes login panel
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
        
        this.gamePanelListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	System.out.println(e.getX() + ", " + e.getY());
                gameFrame.getComponentAt(e.getX(), e.getY()).setBackground(Color.blue);
                validate();
                repaint();
            }
        };
        
	}
	
	private void initGameArea() {
		this.loginPanel.getEmail();
		this.loginPanel.getPassword();
		super.remove(this.loginButton);
		super.remove(this.loginPanel);
		this.loadObstacles();
		super.add(this.player1Info);
		super.add(this.gameFrame);
		super.add(this.player2Info);
		super.add(this.ready);
		super.validate();
		super.repaint();
	}
	
	private void loadObstacles() {
		this.mapLoader = JsonManager.getInstance();
		this.obstacleCoordinates = this.mapLoader.getCoordinates();
		
		
		for (int[] coordinates : this.obstacleCoordinates) {
			this.gameFrame.findComponentAt(coordinates[0], coordinates[1]).setBackground(Color.red);
			this.gameFrame.findComponentAt(coordinates[0], coordinates[3]).setBackground(Color.red);
			this.gameFrame.findComponentAt(coordinates[2], coordinates[1]).setBackground(Color.red);
			this.gameFrame.findComponentAt(coordinates[2], coordinates[3]).setBackground(Color.red);
		}
		super.validate();
		super.repaint();
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}
}
