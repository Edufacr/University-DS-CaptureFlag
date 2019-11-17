package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.IConstants;

public class InfoPanel extends JPanel implements IConstants {
	
	private JLabel playerName;
	private JLabel playerScore;
	
	public InfoPanel(String pPlayerName, int pPositionX, int pPositionY) {
		super();
		super.setBounds(pPositionX, pPositionY, INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT);
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.playerName = new JLabel(pPlayerName);
		this.playerName.setLocation(20, 20);
		super.add(this.playerName);
	}
}
