package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;

public class Graph<T> {
	private ArrayList<GraphNode<T>> nodes;
	private HashMap<T, GraphNode<T>> directory;
	
	public Graph() {
		this.nodes = new ArrayList<GraphNode<T>>();
		this.directory = new HashMap<T, GraphNode<T>>();
	}
	
	public void addNode(T pValue) {
		if (!directory.containsKey(pValue)) {
			GraphNode<T> node = new GraphNode<T>(pValue);
			nodes.add(node);
			directory.put(pValue, node);
		}
	}
	public void addNode(GraphNode<T> pNode){
		if(directory.containsKey(pNode.getContents())){
			return;
		}
		else{
			nodes.add(pNode);
			directory.put(pNode.getContents(),pNode);
		}
	}
	
	public void clearVisits() {
		for (GraphNode<T> node : this.nodes) {
			node.resetVisit();
		}
	}
	
	public void clear() {
		this.nodes.clear();
		this.directory.clear();
	}
	
	public void addEdge(T pValue1, T pValue2, int pWeight) {
		if (this.directory.containsKey(pValue1) && this.directory.containsKey(pValue2)) {
			GraphNode<T> node1 = directory.get(pValue1);
			GraphNode<T> node2 = directory.get(pValue2);
			node1.addEdge(node2, pWeight);
			node2.addEdge(node1, pWeight);
		}
	}
	
	public int getWeight(T pValue1, T pValue2) {
		if (this.directory.containsKey(pValue1) && this.directory.containsKey(pValue2)) {
			GraphNode<T> node1 = directory.get(pValue1);
			return node1.getWeight(pValue2);
		}
		return 0;
	}
	
	public ArrayList<T> getPath(T pValue1, T pValue2){
		if (this.directory.containsKey(pValue1) && this.directory.containsKey(pValue2)) {
			GraphNode<T> node1 = directory.get(pValue1);
			GraphNode<T> node2 = directory.get(pValue2);
			ArrayList<T> invPath = new ArrayList<T>();
			ArrayDeque<GraphNode<T>> queue = new ArrayDeque<GraphNode<T>>();
			
			queue.addLast(node1);
			
			while(!queue.isEmpty()) {
				GraphNode<T> current = queue.removeFirst();
				current.visit();
				for (GraphNode<T> adjNode : current.getAdjacentNodes()) {
					if (!adjNode.isVisited()) {
						adjNode.visit();
						adjNode.setLast(current);
						queue.addLast(adjNode);
					}
					if (adjNode.equals(node2)) {
						queue.clear();
						break;
					}
				}
			}
			invPath = generatePath(invPath, node2);
			ArrayList<T> path = new ArrayList<T>();
			for (int invPathIndex = invPath.size() - 1; invPathIndex >= 0; invPathIndex--) {
				path.add(invPath.get(invPathIndex));
			}
			return path;
		}
		return null;
	}
	
	private ArrayList<T> generatePath(ArrayList<T> pArray, GraphNode<T> pNode){
		if (pNode == null) {
			return pArray;
		} else {
			pArray.add(pNode.getContents());
			return generatePath(pArray, pNode.getLast());
		}
	}
	
	public void print() {
		for (GraphNode<T> node : this.nodes) {
			System.out.println("Node: " + node.getContents());
			System.out.print("Adjacent: ");
			for (GraphNode<T> adjNode : node.getAdjacentNodes()) {
				System.out.print("" + adjNode.getContents() + " ");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public boolean contains(T pValue) {
		if (this.directory.containsKey(pValue)) {
			return true;
		}
		return false;
	}
	
	GraphNode<T> getNode(T pValue){
		if (this.directory.containsKey(pValue)) {
			return this.directory.get(pValue);
		}
		return null;
	}
	
	ArrayList<GraphNode<T>> getNodes(){
		return this.nodes;
	}
	
	int getSize() {
		return this.nodes.size();
	}

	boolean areAdjacent(GraphNode<T> pNode1, GraphNode<T> pNode2 ){
		return pNode1.getAdjacentNodes().contains(pNode2);
	}

	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		Dijkstra d = new Dijkstra();
		
		g.addNode("A");
		g.addNode("B");
		g.addNode("C");
		g.addNode("D");
		g.addNode("E");
		
		g.addEdge("A", "C", 5);
		g.addEdge("A", "D", 7);
		g.addEdge("B", "C", 1);
		g.addEdge("B", "E", 3);
		
		System.out.println(d.calculateDijkstra(g, "A", "B"));
		
		
		
	}
}
