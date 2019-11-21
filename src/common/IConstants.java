package common;

public interface IConstants {
	public static final String WINDOW_NAME = "Capture the Flag";
	public static final int WINDOW_WIDTH = 1460;
	public static final int WINDOW_HEIGHT = 900;
	
	public static final int GAME_PANEL_X = 218;
	public static final int GAME_PANEL_Y = 15;
	public static final int GAME_PANEL_WIDTH = 1024;
	public static final int GAME_PANEL_HEIGHT = 800;
	
	public static final int GRID_HEIGHT = 25;
	public static final int GRID_WIDTH = 32;
	public static final int CELL_WIDTH = 32;
	public static final int CELL_HEIGHT = 32;

	public static final String READY_BUTTON_TEXT = "Ready";
	public static final int READY_BUTTON_X = 690;
	public static final int READY_BUTTON_Y = 825;

	public static final int FLAG_MAX_X = 200;
	public static final int FLAG_MIN_X = 0;
	public static final int OBJECTIVE_MAX_X = 1024;
	public static final int OBJECTIVE_MIN_X = 800;
	public static final int NORTH_SELECTION_MAX_Y = 260;
	public static final int NORTH_SELECTION_MIN_Y = 0;
	public static final int CENTER_SELECTION_MAX_Y = 521;
	public static final int CENTER_SELECTION_MIN_Y = 261;
	public static final int SOUTH_SELECTION_MAX_Y = 800;
	public static final int SOUTH_SELECTION_MIN_Y = 521;
	
	public static final int NORTH = 0;
	public static final int CENTER = 1;
	public static final int SOUTH = 2;
	
	public static final int FLAG_X = 50;
	public static final int NORTH_FLAG_Y = 90;
	public static final int CENTER_FLAG_Y = 360;
	public static final int SOUTH_FLAG_Y = 690;
	
	public static final int FLAG_X_COORDINATE = 1;
	public static final int NORTH_FLAG_Y_COORDINATE = 2;
	public static final int CENTER_FLAG_Y_COORDINATE = 11;
	public static final int SOUTH_FLAG_Y_COORDINATE = 21;
	
	public static final int OBJECTIVE_X = 970;
	public static final int NORTH_OBJECTIVE_Y = 90;
	public static final int CENTER_OBJECTIVE_Y = 360;
	public static final int SOUTH_OBJECTIVE_Y = 690;
	
	public static final int OBJECTIVE_X_COORDINATE = 30;
	public static final int NORTH_OBJECTIVE_Y_COORDINATE = 2;
	public static final int CENTER_OBJECTIVE_Y_COORDINATE = 11;
	public static final int SOUTH_OBJECTIVE_Y_COORDINATE = 21;
	
	
	public static final String LOGIN_BUTTON_TEXT = "Login";
	public static final int LOGIN_BUTTON_X = 200;
	public static final int LOGIN_BUTTON_Y = 240;
	
	public static final int LOGIN_PANEL_X = 400;
	public static final int LOGIN_PANEL_Y = 300;
	public static final int LOGIN_PANEL_WIDTH = 500;
	public static final int LOGIN_PANEL_HEIGHT = 300;
	
	public static final String LOGIN_PANEL_TITLE= "Login";
	public static final int TITLE_FONT_SIZE = 20;
	public static final int LOGIN_TITLE_X = 220;
	public static final int LOGIN_TITLE_Y = 20;
	public static final int LOGIN_TITLE_WIDTH = 80;
	public static final int LOGIN_TITLE_HEIGHT = 30;
	
	public static final String EMAIL_ENTRY_LABEL = "Email:";
	public static final int EMAIL_LABEL_X = 50;
	public static final int EMAIL_LABEL_Y = 70;
	public static final int EMAIL_LABEL_WIDTH = 100;
	public static final int EMAIL_LABEL_HEIGHT = 30;
	public static final int EMAIL_ENTRY_X = 160;
	public static final int EMAIL_ENTRY_Y = 80;
	public static final int EMAIL_ENTRY_WIDTH = 250;
	public static final int EMAIL_ENTRY_HEIGHT = 30;
	
	public static final String PASSWORD_ENTRY_LABEL = "Password:";
	public static final int PASSWORD_LABEL_X = 50;
	public static final int PASSWORD_LABEL_Y = 130;
	public static final int PASSWORD_LABEL_WIDTH = 100;
	public static final int PASSWORD_LABEL_HEIGHT = 30;
	public static final int PASSWORD_ENTRY_X = 160;
	public static final int PASSWORD_ENTRY_Y = 130;
	public static final int PASSWORD_ENTRY_WIDTH = 250;
	public static final int PASSWORD_ENTRY_HEIGHT = 30;
	
	
	public static final int BUTTON_WIDTH = 100;
	public static final int BUTTON_HEIGHT = 40;
	
	
	
	public static final int INFO_PANEL_WIDTH = 188;
	public static final int INFO_PANEL_HEIGHT = 800;
	
	public static final String PLAYER_1 = "Player 1";
	public static final int PLAYER_1_INFO_X = 20;
	public static final int PLAYER_1_INFO_Y = 15;
	public static final String PLAYER_2 = "Player 2";
	public static final int PLAYER_2_INFO_X = 1252;
	public static final int PLAYER_2_INFO_Y = 15;
	public static final int PLAYER_NAME_X = 70;
	public static final int PLAYER_NAME_Y = 20;
	
	public static final int CHARACTER_NAME_X = 0;
	public static final int CHARACTER_NAME_Y = 0;
	public static final int CHARACTER_INFO_X = 30;
	public static final int CHARACTER_INFO_Y = 60;
	public static final int CHARACTER_INFO_Y_ADJUST = 30;
	public static final int CHARACTER_INFO_WIDTH = 100;
	public static final int CHARACTER_INFO_HEIGHT = 30;
	
	
	public static final String JSON_PATH = "maps/mapa3.json";
	public static final String ARRAY_KEY = "obstaculos";
	public static final String FIRST_X = "x1";
	public static final String SECOND_X = "x2";
	public static final String FIRST_Y = "y1";
	public static final String SECOND_Y = "y2";
	
	public static final int CHARACTER_HP = 100;
	
	public static final int MARINE_ATTACK_TIME = 2000;
	public static final int MARINE_ATTACK_POWER = 80;
	public static final int PUNCHER_ATTACK_TIME = 1000;
	public static final int PUNCHER_ATTACK_POWER = 20;
	public static final int ARCHER_ATTACK_TIME = 1500;
	public static final int ARCHER_ATTACK_POWER = 40;

	//Messages
	//SERVER --> CLIENT ---> GUI
	public static final int UPDATE_POSITION = 0;
	public static final int UPDATE_HP = 1;
	public static final int UPDATE_FLAG_POSITION = 2;
	public static final int UPDATE_TEAM = 3;
}
