package model;

import common.IConstants;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonManager implements IConstants{
	private static JsonManager singleton;
	private JSONObject json;
	private ArrayList<int[]> coordinates;
	
	private JsonManager() {
		coordinates = new ArrayList<int[]>();
		try {
			json = new JSONObject(openJson());
			JSONArray jsonCoordinates = json.getJSONArray(ARRAY_KEY);
			
			JSONObject obstacle;
			for (int arrayIterator = 0; arrayIterator < jsonCoordinates.length(); arrayIterator++) {
				obstacle = jsonCoordinates.getJSONObject(arrayIterator);
				int[] obstacleCoordinates = {obstacle.getInt(FIRST_X), obstacle.getInt(SECOND_X), obstacle.getInt(FIRST_Y), obstacle.getInt(SECOND_Y)};
				coordinates.add(obstacleCoordinates);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static JsonManager getInstance() {
		if (singleton == null) {
			singleton = new JsonManager();
		}
		return singleton;
	}
	
	private String openJson() {
		String jsonString = "";
	    try {
	        BufferedReader bufferReader = new BufferedReader(new FileReader(JSON_PATH));
	        StringBuilder stringBuilder = new StringBuilder();
	        String line = bufferReader.readLine();
	        while (line != null) {
	        	stringBuilder.append(line);
	            line = bufferReader.readLine();
	        }
	        jsonString = stringBuilder.toString();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return jsonString;
	}
	
	public ArrayList<int[]> getCoordinates() {
		return this.coordinates;
	}
	
	public static void main(String[] args) {
		JsonManager jm = JsonManager.getInstance();
		for( int[] coordinates : jm.getCoordinates()) {
			for (int i = 0; i < coordinates.length; i++) {
				System.out.println(coordinates[i]);
			}
			System.out.println();
		}
	}
}
