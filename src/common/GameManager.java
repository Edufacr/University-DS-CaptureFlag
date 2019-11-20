package common;

import model.Square;
import model.analyzer.ObstacleAnalyzer;
import model.analyzer.PathAnalyzer;
import model.graph.Graph;


public class GameManager implements IConstants {
    private Graph<Square> graph;
    private PathAnalyzer<Square> pathAnalyzer;
    private ObstacleAnalyzer obstacleAnalyzer;

    public GameManager(){
        graph = new Graph<>();
        pathAnalyzer = new PathAnalyzer<>();
        obstacleAnalyzer = new ObstacleAnalyzer();
    }

    private void createGraph(){
        int numOfNodes = GRID_WIDTH * GRID_HEIGHT;
        fillGraph(numOfNodes);
        linkGraphNodes();
    }

    private void fillGraph(int pNumOfNodes){
        Square square;
        for (int nodeNum = 0; nodeNum < pNumOfNodes ;nodeNum++){
            square = new Square(nodeNum);
            graph.addNode(square);
        }
    }

    private void linkGraphNodes(){
        Graph<Square> actualNode;
        Graph<Square> nextNode;
        Graph<Square> belowNode;
        Graph<Square> diagonalNode;
        for (int nodeNum = 0; nodeNum < graph.getNodes().size() ;nodeNum++){

        }
    }
}
