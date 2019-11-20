package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.IConstants;

import model.characters.Character;

public class InfoPanel extends JPanel implements IConstants {
	
	private JLabel playerName;
	private JLabel playerScore;
	private ArrayList<Color> teamColors;
	private int colorIndex;
	private MouseAdapter teamSelection;
	
	public InfoPanel(String pPlayerName, int pPositionX, int pPositionY) {
		super();
		super.setBounds(pPositionX, pPositionY, INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT);
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.playerName = new JLabel(pPlayerName);
		this.playerName.setLocation(PLAYER_NAME_X, PLAYER_NAME_Y);
		
		this.colorIndex = 0;
		this.teamColors = new ArrayList<Color>();
		this.teamColors.add(Color.RED);
		this.teamColors.add(Color.BLUE);
		this.teamColors.add(Color.GREEN);
		
		super.add(this.playerName);
	}
	
	public void displayCharacters(ArrayList<Character> pCharacters) {
		this.initListeners();
		int y = 40;
		for (Character character : pCharacters) {
			JPanel characterPanel = new JPanel();
			JLabel characterName = new JLabel(character.toString());
			characterName.setLocation(10, 10);
			characterPanel.add(characterName);
			characterPanel.setBounds(10, y, 180, 30);
			y += 30;
			characterPanel.addMouseListener(teamSelection);
			super.add(characterPanel);
		}
	}
	
	private void initListeners() {
		this.teamSelection = new MouseAdapter() {
			 @Override
	         public void mouseClicked(MouseEvent e) {
				 ((JComponent) e.getSource()).setBackground(getNextColor());
			 }
		};
	}
	
	
	private Color getNextColor() {
		Color color = this.teamColors.get(colorIndex);
		if (colorIndex < this.teamColors.size()-1) {
			this.colorIndex++;
		} else { this.colorIndex = 0;}
		return color;
	}
}
