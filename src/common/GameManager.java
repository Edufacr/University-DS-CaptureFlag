package common;

import model.Square;
import model.analyzer.ObstacleAnalyzer;
import model.analyzer.PathAnalyzer;
import model.graph.Graph;
import model.graph.GraphNode;

import java.util.ArrayList;


public class GameManager implements IConstants {
    private static final int DEFAULT_EDGE_WEIGHT = 1;
    private ServerManager serverManager;
    private Graph<Square> graph;
    private PathAnalyzer<Square> pathAnalyzer;
    private ObstacleAnalyzer obstacleAnalyzer;

    public GameManager(){
        graph = new Graph<>();
        pathAnalyzer = new PathAnalyzer<>();
        obstacleAnalyzer = new ObstacleAnalyzer();
        createGraph();
        deleteObstaclesEdges();
        checkIfGraphSolvable();
    }
    private void checkIfGraphSolvable(){
        //TODO Hay que ver como se maneja o por lo menos poner en IConstants las cosas
        if(pathAnalyzer.analyzeGraph(graph,getPrimaryConnections())){
            System.out.println("Graph is solvable");
        }
        else {
            System.out.println("ERROR: Graph is solvable");
        }
    }
    private ArrayList<Square> getPrimaryConnections(){
        //TODO Hay que ver donde se deciden donde se ponen las banderas
        return null;
    }

    private void deleteObstaclesEdges(){
        ArrayList<ArrayList<Integer>> obstacles = obstacleAnalyzer.getObstacleList();
        int nodeNum;
        for (ArrayList<Integer> coordinates:obstacles
             ) {
            nodeNum = getNodeNum(coordinates.get(0),coordinates.get(1));
            graph.removeEdges(graph.getNode(nodeNum));
        }
    }

    private int getNodeNum(int pX, int pY){
        return (pY*GRID_WIDTH)+pX;
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
        for (int nodeNum = 0; nodeNum < graph.getNodes().size() ;nodeNum++){
            linkGraphNode(nodeNum);
        }
    }
    private void linkGraphNode(int pNodeNum){
        GraphNode<Square> node = graph.getNode(pNodeNum);
        for (GraphNode<Square> linkingNode: getNodesToLink(pNodeNum)
             ) {
            graph.addEdge(node,linkingNode, DEFAULT_EDGE_WEIGHT);
        }

    }
    private ArrayList<GraphNode<Square>> getNodesToLink(int pNodeNum){
        ArrayList<GraphNode<Square>> retList = new ArrayList<>(3);
        retList.add(getBelowNode(pNodeNum));
        retList.add(getDiagonalNode(pNodeNum));
        retList.add(getNextNode(pNodeNum));
        return retList;
    }
    private GraphNode<Square> getDiagonalNode(int pNodeNum){
        return graph.getNode(pNodeNum+GRID_WIDTH+1);
    }
    private GraphNode<Square> getNextNode(int pNodeNum){
        return graph.getNode(pNodeNum+1);
    }
    private GraphNode<Square> getBelowNode(int pNodeNum){
        return  graph.getNode(pNodeNum+GRID_WIDTH);
    }

}
