package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import common.IConstants;

public class mainWindow implements IConstants{
	
	private JFrame frame;
	private Hashtable<int[], JPanel> cells;
	
	public mainWindow() {
		this.cells = new Hashtable<int[], JPanel>();
		this.frame =  new JFrame("Main");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(30, 30));
		this.fillGrid();
		
		frame.setVisible(true);
	}
	
	public void fillGrid() {
		int[] key;;
		JPanel current;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; i < 30; i++) {
				key = new int[] {i,j};
				cells.put(key, new JPanel());
				current = cells.get(key);
				current.setSize(30, 20);
				
				if (i%2==0) {
					if (j%2==0) {
						current.setBackground(Color.blue);
					} else {current.setBackground(Color.green);}
				} else {
					if (j%2==0) {
						current.setBackground(Color.green);
					} else {current.setBackground(Color.blue);}
				}
				
				this.frame.add(current);
				current.setVisible(true);
			}
		}
	}
	
	public static void main(String[] args) {
		mainWindow window = new mainWindow();
	}
}
