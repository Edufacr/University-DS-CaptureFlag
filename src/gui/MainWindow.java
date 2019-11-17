package gui;

import javax.swing.JFrame;
import common.IConstants;

public class MainWindow extends JFrame implements IConstants{

	private GameFrame gameFrame;
	private InfoPanel player1Info;
	private InfoPanel player2Info;
	
	public MainWindow() {
		// Initializes main window JFrame
		super(WINDOW_NAME);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		super.setLayout(null);
		super.setResizable(false);
		
		// Initializes panels
		this.gameFrame = new GameFrame();
		this.player1Info = new InfoPanel(0, 0);
		this.player2Info = new InfoPanel(1204, 0);
		
		//Adds componentss and sets JFrame visible
		super.add(this.player1Info);
		super.add(this.gameFrame);
		super.add(this.player2Info);
		super.setVisible(true);
		
	}
	
	
	
	public static void main(String[] args) {
		new MainWindow();
	}
}
