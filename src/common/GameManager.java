package common;

import client.Server;
import model.Player;
import model.Square;
import model.analyzer.ObstacleAnalyzer;
import model.analyzer.PathAnalyzer;
import model.characters.Team;
import model.graph.Graph;
import model.graph.GraphNode;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GameManager implements IConstants {
    private static final int DEFAULT_EDGE_WEIGHT = 1;
    private ServerManager serverManager;
    private Graph<Square> graph;
    private PathAnalyzer<Square> pathAnalyzer;
    private ObstacleAnalyzer obstacleAnalyzer;
    private ExecutorService threadPool;

    private boolean activeGame;
    private Vector<Player> players;
    //private ArrayList<> TODO arraylist movimientos
    
    private Runnable moveTeams;
    private Runnable fight;

    public GameManager(){
        graph = new Graph<>();
        pathAnalyzer = new PathAnalyzer<>();
        obstacleAnalyzer = new ObstacleAnalyzer();
        activeGame = false;
        serverManager = null;
        threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        createMovementRunnables();
        createGraph();
        deleteObstaclesEdges();
        checkIfGraphSolvable();
    }
    public GameManager(ServerManager pManager){
        this();
        serverManager = pManager;
    }
    protected void addPlayer(ArrayList<Team> pTeams, int[] pFlag){
        //TODO por cada movimiento se le setea a cada team
    }
    protected void isReady(){
        if(activeGame){
            this.threadPool.execute(this.moveTeams);
        }
        else{
            activeGame = true;
        }
    }

    private void run(){
        //Aqui se corre el juego cuando los dos estan ready
    	// if fightingTeams.isEmpty() { mete equipos ; threadPool.execute(this.fight); }
    	// else { mete equipos; }
        while(activeGame){
        	// threadPool.execute(this.moveTeams);
        	// threadPool.execute(this.fight);
        	
        }
    }
    
    private void createMovementRunnables() {
    	this.moveTeams = new Runnable() {
    		public void run() {
    			while (activeGame) {
	    			System.out.println("moving teams");
	    			// for (Team team : teams) { team.move(); 
	    			// chequea si alguien ganó. Si ganó activeGame = false;
	    			// chequea adyacencia; }
	    			// manda mensaje
	    			// mete equipos a lista de peleas si es necesario
	    			// threadPool.execute(this.fight);
    			}
    		}
    	};
    	
    	this.fight = new Runnable() {
    		public void run() {
    			// pide hilos con getFightRunnable para la pelea
    			// for (Team team : fightingTeams) {  }
    		}
    	};
    }
    
    private Runnable getFightRunnable(Team pTeam1, Team pTeam2) {
    	Runnable fight = new Runnable() {
    		public void run() {
    			System.out.println(pTeam1.toString() + " is fighting against " + pTeam2.toString());
    			// fight.sleep(attacker_sleep_time);
    			// pTeam1 atack pTeam2
    			// revisa si siguen peleando
    		}
    	};
    	return fight;
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
