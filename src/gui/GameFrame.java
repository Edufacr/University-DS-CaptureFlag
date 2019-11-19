package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import common.IConstants;

public class GameFrame extends JPanel implements IConstants{
	
	private JPanel[][] cells;
	
	public GameFrame() {
		super();
		super.setBounds(GAME_PANEL_X, GAME_PANEL_Y, GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
		super.setLayout(null);
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.cells = new JPanel[GRID_WIDTH][GRID_HEIGHT];
		this.fillGrid();
		this.cells[3][4].setBackground(Color.red);
	}
	
	private void fillGrid() {
		int cellWidth = GAME_PANEL_WIDTH / GRID_WIDTH;
		int cellHeight = GAME_PANEL_HEIGHT / GRID_HEIGHT;
		JPanel current;
		for (int i = 0; i < GRID_HEIGHT; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
				cells[j][i] =  new JPanel();
				current = cells[j][i];
				int x = cellWidth * j;
				int y = cellHeight * i;
				current.setBounds(x, y, cellWidth, cellHeight);
				if ((i+j)%2==0) {
					current.setBackground(Color.BLACK);
				} else {current.setBackground(Color.gray);}
				super.add(current);
			}
		}
	}
}
