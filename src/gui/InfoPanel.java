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
	private ArrayList<JLabel> characterNames;
	private ArrayList<JPanel> characters;
	
	public InfoPanel(String pPlayerName, int pPositionX, int pPositionY) {
		super();
		super.setBounds(pPositionX, pPositionY, INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT);
		super.setBorder(BorderFactory.createLineBorder(Color.black));
		super.setLayout(null);
		this.playerName = new JLabel(pPlayerName);
		this.playerName.setBounds(PLAYER_NAME_X, PLAYER_NAME_Y, CHARACTER_INFO_WIDTH, CHARACTER_INFO_HEIGHT);
		
		this.colorIndex = 0;
		this.teamColors = new ArrayList<Color>();
		this.teamColors.add(Color.RED);
		this.teamColors.add(Color.CYAN);
		this.teamColors.add(Color.GREEN);
		
		super.add(this.playerName);
	}
	
	public void displayCharacters(ArrayList<Character> pCharacters) {
		this.initListeners();
		int yCoordinate = CHARACTER_INFO_Y;
		this.characterNames = new ArrayList<JLabel>();
		this.characters = new ArrayList<JPanel>();
		for (Character character : pCharacters) {
			JPanel characterPanel = new JPanel();
			JLabel characterName = new JLabel(character.toString());
			this.characterNames.add(characterName);
			this.characters.add(characterPanel);
			characterName.setLocation(CHARACTER_NAME_X, CHARACTER_NAME_Y);
			characterPanel.add(characterName);
			characterPanel.setBounds(CHARACTER_INFO_X, yCoordinate, CHARACTER_INFO_WIDTH, CHARACTER_INFO_HEIGHT);
			yCoordinate += CHARACTER_INFO_Y_ADJUST;
			characterPanel.addMouseListener(teamSelection);
			super.add(characterPanel);
		}
	}
	
	public void updateCharacters(ArrayList<ArrayList<String>> pCharacters) {
		pCharacters.remove(0);
		int characterNamesIndex = 0;
		for (ArrayList<String> characterInfo : pCharacters) {
			String updatedInfo = characterInfo.get(0) + ": " + characterInfo.get(1);
			this.characterNames.get(characterNamesIndex).setText(updatedInfo);
			characterNamesIndex++;
		}
	}
	
	public ArrayList<String> getTeamCompositions() {
		ArrayList<String> composition = new ArrayList<String>();
		for (JPanel character : this.characters) {
			if (character.getBackground() == Color.RED) {
				composition.add(TEAM_1);
			} else if (character.getBackground() == Color.CYAN) {
				composition.add(TEAM_2);
			} else {
				composition.add(TEAM_3);
			}
		}
		
		return composition;
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
