package graph;

import java.util.ArrayList;
import java.util.Hashtable;

public class Dijkstra <T>{
	private Hashtable<GraphNode<T>, Integer> set;
	private ArrayList<T> path;
	
	public Dijkstra() {
		this.path = new ArrayList<T>();
		this.set = new Hashtable<GraphNode<T>, Integer>();
	}
	
	public void calculateDijkstra(Graph<T> pGraph, T pValue1, T pValue2){
		if (pGraph.contains(pValue1) && pGraph.contains(pValue2)) {
			GraphNode<T> node1 = pGraph.getNode(pValue1);
			GraphNode<T> node2 = pGraph.getNode(pValue2);
			
			for (GraphNode<T> node : pGraph.getNodes()){
				set.put(node, Integer.MAX_VALUE);
			}
			
			
			
		}
	}
}
