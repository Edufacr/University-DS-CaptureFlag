package graph;

import java.util.ArrayList;

public class Dijkstra <T>{
	private ArrayList<T> path;
	
	public Dijkstra() {
		this.path = new ArrayList<T>();
	}
	
	public void calculateDijkstra(Graph pGraph, T pValue1, T pValue2){
		if (pGraph.contains(pValue1) && pGraph.contains(pValue2)) {
			GraphNode<T> node1 = pGraph.getNode(pValue1);
			GraphNode<T> node2 = pGraph.getNode(pValue2);
			
		}
	}
}
