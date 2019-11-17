package gui;

import java.awt.Color;

import javax.swing.JPanel;

import common.IConstants;

public class InfoPanel extends JPanel implements IConstants {
	
	public InfoPanel(int pPositionX, int pPositionY) {
		super();
		super.setBounds(pPositionX, pPositionY, INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT);
		super.setBackground(Color.red);
	}
}
