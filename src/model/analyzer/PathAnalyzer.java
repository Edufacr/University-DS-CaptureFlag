package model.analyzer;

import model.graph.Graph;
import model.graph.GraphNode;
import model.graph.Warshall;

import java.util.ArrayList;

public class PathAnalyzer<T> {
    private Warshall<T> warshall;

    public PathAnalyzer(){
        //warshall = Warshall.getInstance();
        warshall = new Warshall<>();

    }

    public boolean analyzeGraph(Graph<T> pGraph,ArrayList<T> pPrimaryConnectionsContent){
        warshall.calculateWarshall(pGraph);
        Graph<T> graph = warshall.getConnectionGraph();
        ArrayList<GraphNode<T>> primaryConnections = generatePrimaryConnections(pPrimaryConnectionsContent, graph);
        for (GraphNode<T> startNode: primaryConnections
             ) {
            for (GraphNode<T> endNode: primaryConnections
            ) {
                if(!(startNode.equals(endNode)||graph.areAdjacent(startNode,endNode))){
                    return false;
                }
            }

        }
        return true;
    }

    private ArrayList<GraphNode<T>> generatePrimaryConnections(ArrayList<T> pPrimaryConnectionsContent,Graph<T> pGraph){
        ArrayList<GraphNode<T>> retList = new ArrayList<>();
        for (T nodeContent:pPrimaryConnectionsContent
             ) {
            retList.add(pGraph.getNode(nodeContent)); //***Se puede poner una condicion de if null
        }
        return retList;
    }


}
