package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import common.IConstants;

public class MainWindow extends JFrame implements IConstants{

	private JPanel gamePanel;
	private JPanel[][] cells;
	
	public MainWindow() {
		super(WINDOW_NAME);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		super.setResizable(false);
		
		this.cells = new JPanel[GRID_WIDTH][GRID_HEIGHT];
		
		this.gamePanel =  new JPanel();
		this.gamePanel.setSize(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
		this.gamePanel.setLayout(new GridLayout(GRID_WIDTH, GRID_HEIGHT));
		this.fillGrid();
		super.add(this.gamePanel, BorderLayout.CENTER);
		super.setVisible(true);
		
	}
	
	public void fillGrid() {
		JPanel current;
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				cells[i][j] =  new JPanel();
				current = cells[i][j];
				current.setSize(CELL_WIDTH, CELL_HEIGHT);
				
				if ((i+j)%2!=0) {
					current.setBackground(Color.blue);
				} else {current.setBackground(Color.green);}
				
				this.gamePanel.add(current);
			}
		}
	}
	
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		System.out.println("A");
	}
}
