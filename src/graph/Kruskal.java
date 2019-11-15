package graph;

import java.util.ArrayList;

public class Kruskal <T>{
	private ArrayList<GraphNode<T>> path;
	private ArrayList<GraphNode<T>> nodes;
	private ArrayList<Edge<GraphNode<T>>> edges;
	
	public ArrayList<GraphNode<T>> getPath(Graph<T> pGraph, T pValue1, T pValue2) {
		this.calculateMST(pGraph);
		return null;
	}
	
	private void calculateMST(Graph<T> pGraph) {
		this.buildEdges(pGraph);
		return;
	}
	
	private void buildEdges(Graph<T> pGraph) {
		//Collections.sort(this.edges);
		return;
	}
}
