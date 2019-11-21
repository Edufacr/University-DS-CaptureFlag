package common;

import client.Server;
import model.Player;
import model.Square;
import model.analyzer.ObstacleAnalyzer;
import model.analyzer.PathAnalyzer;
import model.characters.Team;
import model.graph.*;

import java.util.ArrayList;


public class GameManager implements IConstants {
    private static final int DEFAULT_EDGE_WEIGHT = 1;
    private ServerManager serverManager;
    private Graph<Square> graph;
    private PathAnalyzer<Square> pathAnalyzer;
    private ObstacleAnalyzer obstacleAnalyzer;

    private boolean activeGame;
    private ArrayList<Player> players;
    private ArrayList<IGraphPathGettable<Square>> pathGetterTypes;

    public GameManager(){
        graph = new Graph<>();
        pathAnalyzer = new PathAnalyzer<>();
        obstacleAnalyzer = new ObstacleAnalyzer();
        activeGame = false;
        serverManager = null;
        players = new ArrayList<>();
        createGraph();
        deleteObstaclesEdges();
        checkIfGraphSolvable();
    }
    private void generatePathGetterTypes(){
        pathGetterTypes = new ArrayList<IGraphPathGettable<Square>>();
        pathGetterTypes.add(new Dijkstra<Square>());
        pathGetterTypes.add(new Kruskal<Square>());
        pathGetterTypes.add(Warshall.getInstance());
    }
    public GameManager(ServerManager pManager){
        this();
        serverManager = pManager;
    }
    protected void addPlayer(ArrayList<Team> pTeams, int[] pFlag){
        for (int teamIndex = 0; teamIndex< pTeams.size();teamIndex++){
            pTeams.get(teamIndex).setPathGetter(pathGetterTypes.get(teamIndex));
        }
        Player player = new Player(pTeams,getNodeNum(pFlag[0],pFlag[1]));
        players.add(player);
    }
    protected void isReady(){
        if(activeGame){
            run();
        }
        else{
            activeGame = true;
        }
    }

    private void run(){
        //Aqui se corre el juego cuando los dos estan ready
        while(activeGame){

        }
    }
    private void checkIfGraphSolvable(){
        if(pathAnalyzer.analyzeGraph(graph,getPrimaryConnections())){
           serverManager = new ServerManager(this);
        }
        else {
            System.out.println(MAP_NOT_SOLVABLE_ERROR_MESSAGE);
        }
    }
    private ArrayList<Square> getPrimaryConnections(){
        ArrayList<Square> retList = new ArrayList<>(3);
        retList.add(graph.getNode(getNodeNum(OBJECTIVE_X,NORTH_OBJECTIVE_Y)).getContents());
        retList.add(graph.getNode(getNodeNum(OBJECTIVE_X,CENTER_OBJECTIVE_Y)).getContents());
        retList.add(graph.getNode(getNodeNum(OBJECTIVE_X,SOUTH_OBJECTIVE_Y)).getContents());
        return retList;
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
