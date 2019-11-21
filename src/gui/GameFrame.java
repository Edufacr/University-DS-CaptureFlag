package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import common.IConstants;
import model.JsonManager;

public class GameFrame extends JPanel implements IConstants{
	
	private JPanel[][] cells;
	private JsonManager mapLoader;
	private ArrayList<int[]> obstacleCoordinates;
	
	public GameFrame() {
		super();
		super.setBounds(GAME_PANEL_X, GAME_PANEL_Y, GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
		super.setLayout(null);
		
		this.cells = new JPanel[GRID_WIDTH][GRID_HEIGHT];
		this.fillGrid();
		this.loadObstacles();
		super.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void fillGrid() {
		JPanel current;
		for (int currentX = 0; currentX < GRID_WIDTH; currentX++) {
			for (int currentY = 0; currentY < GRID_HEIGHT; currentY++) {
				cells[currentX][currentY] =  new JPanel();
				current = cells[currentX][currentY];
				int cellX = CELL_WIDTH * currentX;
				int cellY = CELL_HEIGHT * currentY;
				current.setBounds(cellX, cellY, CELL_WIDTH, CELL_HEIGHT);
				current.setOpaque(false);
				super.add(current);
			}
		}
	}
	
	private void loadObstacles() {
		this.mapLoader = JsonManager.getInstance();
		this.obstacleCoordinates = this.mapLoader.getCoordinates();
		
		for (int[] coordinates : this.obstacleCoordinates) {
			this.paintRectangle(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		}
	}
	
	private void paintRectangle(int pCoordinateX1, int pCoordinateY1, int pCoordinateX2, int pCoordinateY2) {
		
		int width = (pCoordinateX2 - pCoordinateX1);
		width+=2;
		
		int height = (pCoordinateY2 - pCoordinateY1);
		height+=2;
		
	    JPanel panel = new JPanel();
	    panel.setBounds(pCoordinateX1, pCoordinateY1, width, height);
	    panel.setBackground(Color.BLACK);
	    super.add(panel);
	}
	
	public void updateTeamPositions(ArrayList<ArrayList<String>> pNewCoordinates) {
		
	}
}
