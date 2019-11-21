package common;

import client.Server;
import model.Player;
import model.Square;
import model.analyzer.ObstacleAnalyzer;
import model.analyzer.PathAnalyzer;
import model.characters.Team;
import model.graph.*;

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
    private Vector<Team> battleVector;
    private ArrayList<IGraphPathGettable<Square>> pathGetterTypes;
    private Runnable moveTeams;
    private Runnable fight;

    public GameManager(){
        graph = new Graph<>();
        pathAnalyzer = new PathAnalyzer<>();
        obstacleAnalyzer = new ObstacleAnalyzer();
        activeGame = false;
        serverManager = null;
        players = new Vector<>();
        battleVector = new Vector<>();
        threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        createMovementRunnables();
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
            //this.threadPool.execute(this.moveTeams);
            run();
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
            for (Player player:players
                 ) {
                for (Team team:player.getTeams()
                     ) {
                    moveTeam(team);
                    checkWin(team.getActualSquareNum());
                    checkBattle(team.getActualSquareNum());
                }
            }
        	// threadPool.execute(this.moveTeams);
        	// threadPool.execute(this.fight);
        	
        }
    }
    private void checkWin(int pActualSquareNum) {
        for (Player player:players
             ) {
            if(player.getFlagNodeNum() == pActualSquareNum){
                System.out.println("Ganó");
                System.out.println(player.getId());
                activeGame = false;
            }
        }
    }
    private void checkBattle(int pActualSquareNum){
        GraphNode<Square> actualNode = graph.getNode(pActualSquareNum);
        for (GraphNode<Square> adjacentNode: actualNode.getAdjacentNodes()
             ) {
            if(adjacentNode.getContents() != null){
                if(adjacentNode.getContents().getActualTeam().getPlayer() != actualNode.getContents().getActualTeam().getPlayer()){
                    sendToFight(adjacentNode.getContents().getActualTeam(),actualNode.getContents().getActualTeam());
                }
            }
        }
    }
    private void sendToFight(Team pTeam1, Team pTeam2){
        pTeam1.setCurrentObjective(pTeam2);
        pTeam2.setCurrentObjective(pTeam1);
        pTeam1.setOnBattle(true);
        pTeam2.setOnBattle(true);
        battleVector.add(pTeam1);
        battleVector.add(pTeam2);
        getFightRunnable(pTeam1,pTeam2).run();
    }
    private void moveTeam(Team pTeam){
        int actualNodeNum = pTeam.getActualSquareNum();
        int nextNodeNum = pTeam.getNextMove().getContents().getId();
        GraphNode<Square> actualnode =  graph.getNode(actualNodeNum);
        GraphNode<Square> nextnode =  graph.getNode(nextNodeNum);

        nextnode.getContents().setActualTeam((actualnode.getContents().getActualTeam()));
        actualnode.getContents().setActualTeam(null);
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
        retList.add(graph.getNode(getNodeNum(OBJECTIVE_X,NORTH_OBJECTIVE_Y_COORDINATE)).getContents());
        retList.add(graph.getNode(getNodeNum(OBJECTIVE_X,CENTER_OBJECTIVE_Y_COORDINATE)).getContents());
        retList.add(graph.getNode(getNodeNum(OBJECTIVE_X,SOUTH_OBJECTIVE_Y_COORDINATE)).getContents());
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

    public static void main(String[] args) {
        GameManager manager = new GameManager();
        int[] flag1 = new int[2];
        flag1[0] = OBJECTIVE_X_COORDINATE;
        flag1[1] = NORTH_OBJECTIVE_Y_COORDINATE;
        ArrayList<Team> char1 = new ArrayList<>();
        manager.addPlayer(char1,flag1);

        int[] flag2 = new int[2];
        flag2[0] = OBJECTIVE_X_COORDINATE;
        flag2[1] = NORTH_OBJECTIVE_Y_COORDINATE;
        ArrayList<Team> char2 = new ArrayList<>();
        manager.addPlayer(char2,flag2);

        manager.isReady();
        manager.isReady();


    }

}
