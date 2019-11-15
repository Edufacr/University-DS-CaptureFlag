package graph;

import java.util.ArrayList;
import java.util.Stack;

public class Kruskal <T>{
	private ArrayList<GraphNode<T>> path;
	private ArrayList<GraphNode<T>> nodes;
	private ArrayList<Edge<T>> edges;
	private ArrayList<Edge<T>> mst;
	private Stack<GraphNode<T>> stack;
	
	public Kruskal() {
		this.path = new ArrayList<GraphNode<T>>();
		this.nodes = null;
		this.edges = null;
		this.mst = new ArrayList<Edge<T>>();
		this.stack = new Stack<GraphNode<T>>();
	}
	
	public ArrayList<GraphNode<T>> getPath(Graph<T> pGraph, T pValue1, T pValue2) {
		// Calculates MST
		pGraph.clearVisits();
		this.nodes = pGraph.getNodes();
		this.edges = pGraph.getEdges();
		this.calculateMST(pGraph);
		
		// Gets nodes from parameter values
		GraphNode<T> start = pGraph.getNode(pValue1);
		GraphNode<T> end = pGraph.getNode(pValue2);
		
		// Prepares structures for generation of path from MST
		pGraph.clearVisits();
		this.stack.clear();
		this.stack.push(start);
		
		while (true) {
			if (this.stack.isEmpty()) {
				break;
			}
			GraphNode<T> current = this.stack.pop();
			current.visit();
			
			for (GraphNode<T> adjacentNode : current.getAdjacentNodes()) {
				if (adjacentNode.isVisited()) {
					continue;
				} else if (!this.connected(current, adjacentNode)) {
					adjacentNode.visit();
					adjacentNode.setLast(current);
					this.stack.push(adjacentNode);
				}
				
				if (adjacentNode.equals(end)) {
					this.stack.clear();
					break;
				}
			}
			
			this.path = this.generatePath(this.path, end);
			
		}
		
		
		return this.path;
	}
	
	private void calculateMST(Graph<T> pGraph) {
		Edge<T> edge;
		ArrayList<GraphNode<T>> edgeNodes;
		for (int edgeIndex = 0; edgeIndex < this.edges.size(); edgeIndex++) {
			edge = this.edges.get(edgeIndex);
			edgeNodes = edge.getNodes();
			if (edgeNodes.get(0).isVisited() && edgeNodes.get(1).isVisited()) {
				continue;
			}
			edgeNodes.get(0).visit();
			edgeNodes.get(1).visit();
			this.mst.add(edge);
			this.mst.add(this.edges.get(edgeIndex+1));
			
			if (this.visitMissing()) {
				continue;
			}
			break;
		}
	}
	
	private boolean visitMissing() {
		boolean visitStatus = false;
		for (GraphNode<T> node : this.nodes) {
			if (node.isVisited()) {
				continue;
			}
			visitStatus = true;
		}
		return visitStatus;
	}
	
	private boolean connected(GraphNode<T> pNode, GraphNode<T> pOtherNode) {
		Edge<T> graphEdge = pNode.getEdge(pOtherNode);
		if (this.mst.contains(graphEdge)) {
			return true;
		}
		return false;
	}
	
	private ArrayList<GraphNode<T>> generatePath(ArrayList<GraphNode<T>> pArray, GraphNode<T> pNode){
		if (pNode == null) {
			return pArray;
		} else {
			pArray.add(pNode);
			return generatePath(pArray, pNode.getLast());
		}
	}
}
