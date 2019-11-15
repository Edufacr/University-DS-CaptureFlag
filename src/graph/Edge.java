package graph;

import java.util.ArrayList;

public class Edge<T> implements Comparable <Edge<T>>{
	private ArrayList<GraphNode<T>> nodes;
	private int weight;
	
	public Edge(GraphNode<T> pNode1, GraphNode<T> pNode2, int pWeight) {
		this.nodes = new ArrayList<GraphNode<T>>();
		this.nodes.add(pNode1);
		this.nodes.add(pNode2);
		this.weight = pWeight;
	}
	
	public ArrayList<GraphNode<T>> getNodes(){
		return this.nodes;
	}
	
	public int getWeight() {
		return this.weight;
	}

	@Override
	public int compareTo(Edge<T> pOtherEdge) {
		if (this.weight < pOtherEdge.getWeight()) {
			return -1;
		} else if (this.weight > pOtherEdge.getWeight()) {
			return 1;
		}
		return 0;
	}
	
	
}
