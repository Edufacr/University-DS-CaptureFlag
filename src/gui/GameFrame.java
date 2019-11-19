package gui;

import java.awt.Color;
import java.awt.GridLayout;
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
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.cells = new JPanel[GRID_WIDTH][GRID_HEIGHT];
		this.fillGrid();
		this.loadObstacles();
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
				if ((currentX+currentY)%2==0) {
					current.setBackground(Color.BLACK);
				} else {current.setBackground(Color.gray);}
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
		
		int width = (pCoordinateX2 - pCoordinateX1) / CELL_WIDTH;
		width += 2;
		int yCoordinate = pCoordinateY1 - CELL_HEIGHT;
		
		do {
			int xCoordinate = pCoordinateX1;
			yCoordinate += CELL_HEIGHT;
			for (int lineCount = 0; lineCount < width; lineCount++) {
				this.findComponentAt(xCoordinate, yCoordinate).setBackground(Color.red);
				xCoordinate += CELL_WIDTH;
			}
		} while (yCoordinate < pCoordinateY2);
	}
}
