package model.analyzer;

import common.IConstants;
import model.JsonManager;

import java.util.ArrayList;

public class obstacleAnalyzer implements IConstants {
    private static final int X1 = 0;
    private static final int Y1 = 1;
    private static final int X2 = 2;
    private static final int Y2 = 3;
    private ArrayList<int[]> obstacleCoordinates;

    public obstacleAnalyzer(){
        JsonManager jsonManager = JsonManager.getInstance();
        obstacleCoordinates = jsonManager.getCoordinates();
    }

    public ArrayList<int[]> getObstacleList(){
        ArrayList<int[]> gridXYList = new ArrayList<>();
        int[] gridCoordinates;

        int[] squareCoordinates = new int[4];
        for (int gridY = 0; gridY < GRID_HEIGHT; gridY++){
            squareCoordinates[Y1] = squareCoordinates[Y2] = CELL_HEIGHT * gridY;
            squareCoordinates[Y2] += CELL_HEIGHT;
            for(int gridX =0; gridX<GRID_WIDTH;gridX++){
                squareCoordinates[X1] = squareCoordinates[X2] = CELL_WIDTH * gridX;
                squareCoordinates[X2] += CELL_WIDTH;
                if(isObstacle(squareCoordinates)){
                    gridCoordinates = new int[2];
                    gridCoordinates[X1] = gridX;
                    gridCoordinates[Y1] = gridY;
                    gridXYList.add(gridCoordinates);
                }
            }
        }
        return gridXYList;
    }

    private boolean isObstacle(int[] pSquareCoordinates, int[] pObstacleCoordinates){
        boolean atX = areBetweenLimits(pSquareCoordinates[X2],pSquareCoordinates[X1],pObstacleCoordinates[X1],pObstacleCoordinates[X2]);
        boolean atY = areBetweenLimits(pSquareCoordinates[Y2],pSquareCoordinates[Y1],pObstacleCoordinates[Y1],pObstacleCoordinates[Y2]);
        return atX && atY;
    }
    private boolean areBetweenLimits(int pMax, int pMin, int pCoordinate1, int pCoordinate2){
        boolean coordinateBelowMin = pMin > pCoordinate1 && pMin > pCoordinate2;
        boolean coordinateAboveMax = pMax < pCoordinate1 && pMax < pCoordinate2;
        return !(coordinateAboveMax || coordinateBelowMin);
    }

    private boolean isObstacle(int[] pSquareCoordinates){
        for (int[] obstacleCoordinates: obstacleCoordinates
             ) {
            if(isObstacle(pSquareCoordinates,obstacleCoordinates)){
                return true;
            }
        }
        return false;
    }
}
