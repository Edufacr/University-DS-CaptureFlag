package graph;

import java.util.ArrayList;

public class Kruskal <T>{
	private ArrayList<GraphNode<T>> path;
	private ArrayList<GraphNode<T>> nodes;
	private ArrayList<Edge<T>> edges;
	private ArrayList<Edge<T>> mst;
	
	public Kruskal() {
		this.path = new ArrayList<GraphNode<T>>();
		this.nodes = null;
		this.edges = null;
		this.mst = new ArrayList<Edge<T>>();
	}
	
	public ArrayList<GraphNode<T>> getPath(Graph<T> pGraph, T pValue1, T pValue2) {
		pGraph.clearVisits();
		this.nodes = pGraph.getNodes();
		this.edges = pGraph.getEdges();
		this.calculateMST(pGraph);
		
		System.out.println(mst);
		return null;
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
}
