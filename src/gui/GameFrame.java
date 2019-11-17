package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.IConstants;

public class GameFrame extends JPanel implements IConstants{
	
	private JPanel[][] cells;
	
	public GameFrame() {
		super();
		super.setBounds(INFO_PANEL_WIDTH+10, 5, GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
		super.setLayout(new GridLayout(GRID_WIDTH, GRID_HEIGHT));
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.cells = new JPanel[GRID_WIDTH][GRID_HEIGHT];
		this.fillGrid();
	}
	
	private void fillGrid() {
		JPanel current;
		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_HEIGHT; j++) {
				cells[i][j] =  new JPanel();
				current = cells[i][j];
				current.setSize(CELL_WIDTH, CELL_HEIGHT);

				super.add(current);
			}
		}
	}
}
