package graph;

import java.util.ArrayList;

public class GraphNode<T> {
	private T contents;
	private boolean visited;
	private ArrayList<GraphNode<T>> adjacentNodes;
	private ArrayList<Integer> weights;
	private GraphNode<T> last;
	
	public GraphNode(T pContents) {
		this.contents = pContents;
		this.adjacentNodes = new ArrayList<GraphNode<T>>();
		this.weights = new ArrayList<Integer>();
		this.last = null;
	}

	public T getContents() {
		return this.contents;
	}

	public ArrayList<GraphNode<T>> getAdjacentNodes() {
		return this.adjacentNodes;
	}
	
	public ArrayList<Integer> getWeights(){
		return this.weights;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public void visit() {
		this.visited = true;
	}
	
	public void resetVisit() {
		this.visited = false;
	}
	
	public GraphNode<T> getLast(){
		return this.last;
	}

	public void setLast(GraphNode<T> pLast) {
		this.last = pLast;
	}
	
	public GraphNode<T> getAdjacent(T pValue) {
		for (GraphNode<T> node : this.adjacentNodes) {
			if (node.getContents().equals(pValue)) {
				return node;
			}
		}
		return null;
	}
	
	public int getWeight(T pValue) {
		GraphNode<T> node = this.getAdjacent(pValue);
		if (node != null) {
			int index = this.adjacentNodes.indexOf(node);
			return this.weights.get(index);
		}
		return 0;
	}
	
	public void addEdge(GraphNode<T> pNode, int pWeight) {
		if (!this.adjacentNodes.contains(pNode)) {
			this.adjacentNodes.add(pNode);
			this.weights.add(pWeight);
		}
	}
	
	
}