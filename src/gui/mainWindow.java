package gui;

import javax.swing.*;
import common.IConstants;

public class mainWindow implements IConstants{
	
	private JFrame frame;
	
	public mainWindow() {
		this.frame =  new JFrame("Main");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
