package graph;

public class Warshall<T> {
    private Graph<T> copy;
    public Warshall(){
        copy = null;
    }
    public void calculateWarshall(Graph<T> pGraph){
        copy = duplicateGraph(pGraph);
        for (GraphNode<T> linkNode:pGraph.getNodes()
             ) {
            for (GraphNode<T> sourceNode:pGraph.getNodes()
            ) {
                for (GraphNode<T> endNode:pGraph.getNodes()
                ) {
                    if (copy.areAdjacent(sourceNode,endNode)){
                        continue;
                    }
                    if(copy.areAdjacent(sourceNode,linkNode) && copy.areAdjacent(linkNode,endNode)){
                        addConnection(sourceNode,endNode,linkNode);
                    }
                }
            }
        }
        copy.print();
    }
    private void addConnection(GraphNode<T> pSourceNode, GraphNode<T> pEndNode,GraphNode<T> pLinkNode){
        GraphNode<T> newNode = new GraphNode<T>(pEndNode.getContents());
        newNode.setLast(pLinkNode);
        pSourceNode.addEdge(newNode,0);
    }
    private Graph<T> duplicateGraph(Graph<T> pGraph){
        return null;
        /*Graph<T> graph = new Graph<T>();
        for (GraphNode<T> node:pGraph.getNodes()
             ) {
            graph.addNode(node.getContents());
        }

            for (GraphNode<T> adjacent:node.getAdjacentNodes()
                 ) {
                graph.addEdge(node.getContents(),);
            }
        }
        graph.add
        return null;*/
    }

    public static void main(String[] args) {
        Graph<Integer> g1 = new Graph<Integer>();
        g1.addNode(1);
        g1.addNode(2);
        g1.addNode(3);
        g1.addEdge(1,2,0);
        g1.addEdge(2,3,0);
        g1.addEdge(3,2,0);
    }
}
