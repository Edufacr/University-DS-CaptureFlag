package model.graph;

import model.Square;

import java.util.ArrayList;

public interface IGraphPathGettable<T> {
    public ArrayList<GraphNode<T>> getPath(Graph<T> pGraph, T pStartContent,T pEndContent);
}

