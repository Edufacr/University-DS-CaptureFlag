package common;

public interface IConstants {
	public static final int WINDOW_WIDTH = 1400;
	public static final int WINDOW_HEIGHT = 800;
	public static final int GAME_PANEL_WIDTH = 1024;
	public static final int GAME_PANEL_HEIGHT = 800;
	public static final int GRID_HEIGHT = 25;
	public static final int GRID_WIDTH = 32;
	public static final int CELL_WIDTH = 32;
	public static final int CELL_HEIGHT = 32;
	
	public static final String WINDOW_NAME = "Capture the Flag";
	
	public static final String JSON_PATH = "maps/map.json";
	public static final String ARRAY_KEY = "obstacles";
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
}
