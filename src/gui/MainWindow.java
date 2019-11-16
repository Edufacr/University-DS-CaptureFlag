package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import common.IConstants;

public class MainWindow implements IConstants{
	
	private JFrame frame;
	private JPanel[][] cells;
	
	public MainWindow() {
		this.cells = new JPanel[GRID_WIDTH][GRID_HEIGHT];
		
		this.frame =  new JFrame(WINDOW_NAME);
		this.frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new GridLayout(GRID_WIDTH, GRID_HEIGHT));
		this.fillGrid();
		this.frame.setVisible(true);
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
				
				this.frame.add(current);
			}
		}
	}
	
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
	}
}
