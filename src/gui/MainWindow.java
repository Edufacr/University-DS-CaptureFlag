package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import common.ClientManager;
import common.IConstants;
import model.analyzer.ObstacleAnalyzer;
import model.characters.Archer;
import model.characters.Marine;
import model.characters.Puncher;

public class MainWindow extends JFrame implements IConstants, Observer {

	private ClientManager clientManager;

	private GameFrame gameFrame;
	private InfoPanel player1Info;
	private InfoPanel player2Info;
	private LoginPanel loginPanel;
	private JButton readyButton;
	private ActionListener playerReady;
	private JButton loginButton;
	private ActionListener login;
	private int currentFlagY;
	private ArrayList<Integer> flagLocation;
	private ArrayList<Integer> team1Objective;
	private ArrayList<Integer> team2Objective;
	private ArrayList<Integer> team3Objective;
	private int objectiveSelectionIndex;
	private boolean ready;
	
	private MouseAdapter gamePanelListener;
	
	public MainWindow() {
		// Initializes main window JFrame
		super(WINDOW_NAME);
		clientManager = new ClientManager(this);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		super.setLayout(null);
		super.setResizable(false);
		
		this.flagLocation = new ArrayList<Integer>();
		this.flagLocation.add(FLAG_X_COORDINATE);
		this.flagLocation.add(NORTH_FLAG_Y_COORDINATE);
		
		this.team1Objective = new ArrayList<Integer>();
		this.team2Objective = new ArrayList<Integer>();
		this.team3Objective = new ArrayList<Integer>();
		
		this.team1Objective.add(OBJECTIVE_X_COORDINATE);
		this.team2Objective.add(OBJECTIVE_X_COORDINATE);
		this.team3Objective.add(OBJECTIVE_X_COORDINATE);
		
		this.team1Objective.add(NORTH_OBJECTIVE_Y_COORDINATE);
		this.team2Objective.add(NORTH_OBJECTIVE_Y_COORDINATE);
		this.team3Objective.add(NORTH_OBJECTIVE_Y_COORDINATE);
		
		this.objectiveSelectionIndex = 0;
		
		this.createListeners();
		
		// Initializes game panels
		this.gameFrame = new GameFrame();
		this.gameFrame.addMouseListener(this.gamePanelListener);
		this.player1Info = new InfoPanel(PLAYER_1, PLAYER_1_INFO_X, PLAYER_1_INFO_Y);
		this.player2Info = new InfoPanel(PLAYER_2, PLAYER_2_INFO_X, PLAYER_2_INFO_Y);
		
		//Initializes buttons
		this.readyButton = new JButton(READY_BUTTON_TEXT);
		this.readyButton.setBounds(READY_BUTTON_X, READY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
		this.readyButton.addActionListener(this.playerReady);
		
		
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
        		if (ready) {
        			return;
        		}

        		ready = true;
        		ArrayList<ArrayList<Integer>> coordinates = new ArrayList<ArrayList<Integer>>();
        		coordinates.add(flagLocation);
        		coordinates.add(team1Objective);
        		coordinates.add(team2Objective);
        		coordinates.add(team3Objective);
        		clientManager.setPlayerTactics(coordinates, player1Info.getTeamCompositions());
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
                
                int xCoordinate = e.getX();
                int yCoordinate = e.getY();
                System.out.println(xCoordinate + ", " + yCoordinate);
                
                if ((xCoordinate >= FLAG_MIN_X) && (xCoordinate <= FLAG_MAX_X)) {
                	gameFrame.getComponentAt(FLAG_X, currentFlagY).setBackground(getBackground());
                	if ((yCoordinate >= NORTH_SELECTION_MIN_Y) && (yCoordinate <= NORTH_SELECTION_MAX_Y)) {
                		flagLocation.set(1, NORTH_FLAG_Y_COORDINATE);
                		currentFlagY = NORTH_FLAG_Y;
                	} else if((yCoordinate >= CENTER_SELECTION_MIN_Y) && (yCoordinate <= CENTER_SELECTION_MAX_Y)) {
                		flagLocation.set(1, CENTER_FLAG_Y_COORDINATE);
                		currentFlagY = CENTER_FLAG_Y;
                	} else if((yCoordinate >= SOUTH_SELECTION_MIN_Y) && (yCoordinate <= SOUTH_SELECTION_MAX_Y)) {
                		flagLocation.set(1, CENTER_FLAG_Y_COORDINATE);
                		currentFlagY = SOUTH_FLAG_Y;
                	}
                	
                	((JComponent) gameFrame.getComponentAt(FLAG_X, currentFlagY)).setOpaque(true);
                    gameFrame.getComponentAt(FLAG_X, currentFlagY).setBackground(Color.RED);
                	
                } else if((xCoordinate >= OBJECTIVE_MIN_X) && (xCoordinate <= OBJECTIVE_MAX_X)) {
                	if ((yCoordinate >= NORTH_SELECTION_MIN_Y) && (yCoordinate <= NORTH_SELECTION_MAX_Y)) {
                		updateObjective(NORTH_OBJECTIVE_Y_COORDINATE);
                	} else if((yCoordinate >= CENTER_SELECTION_MIN_Y) && (yCoordinate <= CENTER_SELECTION_MAX_Y)) {
                		updateObjective(CENTER_OBJECTIVE_Y_COORDINATE);
                	} else if((yCoordinate >= SOUTH_SELECTION_MIN_Y) && (yCoordinate <= SOUTH_SELECTION_MAX_Y)) {
                		updateObjective(SOUTH_OBJECTIVE_Y_COORDINATE);
                	}
                	
                	objectiveSelectionIndex++;
                }
            }
        };
        
	}
	
	private void initGameArea() {
		this.loginPanel.getEmail();
		this.loginPanel.getPassword();
		
		ArrayList<model.characters.Character> chars = new ArrayList<model.characters.Character>();
		chars.add(new Marine());
		chars.add(new Marine());
		chars.add(new Puncher());
		chars.add(new Puncher());
		chars.add(new Puncher());
		chars.add(new Puncher());
		chars.add(new Puncher());
		chars.add(new Puncher());
		chars.add(new Archer());
		chars.add(new Archer());
		chars.add(new Archer());
		player1Info.displayCharacters(chars);
		validate();
		repaint();
		
		super.remove(this.loginButton);
		super.remove(this.loginPanel);
		super.add(this.player1Info);
		super.add(this.gameFrame);
		super.add(this.player2Info);
		super.add(this.readyButton);
		super.validate();
		super.repaint();
	}
	
	private void updateObjective(int pLocation) {
		switch (this.objectiveSelectionIndex) {
		case 0:
			this.team1Objective.set(1, pLocation);
			break;
		case 1:
			this.team2Objective.set(1, pLocation);
			break;
		case 2:
			this.team3Objective.set(1, pLocation);
			break;
		default:
			return;
		}
	}
	
	@Override
	public void update(Observable observable, Object pObject) {
		ArrayList<ArrayList<String>> message = (ArrayList<ArrayList<String>>) pObject;
		
		if (message.get(0).get(0) == UPDATE_POSITION_KEY.toString()) {
			this.gameFrame.updateTeamPositions(message);
		} else if (message.get(0).get(0) == UPDATE_HP_KEY.toString()) {
			this.player1Info.updateCharacters(message);
		}
	}
	
	public static void main(String[] args) {
		ObstacleAnalyzer o = new ObstacleAnalyzer();
		System.out.println(o.getObstacleList());
		new MainWindow();
	}
}
