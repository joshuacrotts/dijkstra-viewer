package com.joshuacrotts.dijkstra;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This is a small (and hopefully portable) implementation of Dijkstra's
 * algorithm.
 *
 * @author Joshua Crotts
 */
public class Dijkstra {

  /** Set of vertices for this instance/graph of Dijkstra. */
  private final Set<Vertex> vertices = new HashSet<>();

  /**
   * Performs Dijkstra's algorithm starting at the provided source vertex. All
   * shortest paths to each vertex are computed.
   *
   * @param source vertex to start at.
   * 
   * @return void.
   */
  public void computeDijkstra(Vertex source) {
    source.distanceFromSource = 0;

    PriorityQueue<Vertex> heap = new PriorityQueue<>();
    heap.add(source);

    while (!heap.isEmpty()) {
      Vertex u = heap.poll();

      /* Visit all adjacencies. */
      for (Edge e : u.adjacencyList) {
        Vertex v = e.getDestination();
        double edgeWeight = e.getDistance();

        double distanceFromU = u.distanceFromSource + edgeWeight;
        if (distanceFromU < v.distanceFromSource) {
          heap.remove(u);
          v.distanceFromSource = distanceFromU;
          v.previousVertex = u;
          heap.add(v);
        }
      }
    }
  }

  /**
   * Returns the Dijkstra shortest path from the source vertex to the target
   * vertex. This assumes that computeDijkstra() has previously been called.
   * 
   * @param target - destination vertex.
   * 
   * @return LinkedList<> of vertices in the path.
   */
  public LinkedList<Vertex> getDijkstraPath(Vertex target) {
    LinkedList<Vertex> path = new LinkedList<>();

    /* If there is no path, just return nothing. */
    if (target.previousVertex == null) {
      return path;
    }

    /*
     * Otherwise, construct the path backwards, then reverse it. We use a stack
     * because of its natural reversed order.
     */
    for (Vertex v = target; v != null; v = v.previousVertex) {
      path.add(v);
    }

    Collections.reverse(path);
    return path;
  }

  /**
   * Adds a vertex with no edge to the graph. This should really only be used if
   * you have a reference to the Vertex somewhere else.
   * 
   * @param src vertex to add.
   * 
   * @return void.
   */
  public void addVertex(Vertex src) {
    this.vertices.add(src);
  }

  /**
   * Adds an edge to the graph.
   * 
   * @param src      source vertex.
   * @param dest     destination vertex.
   * @param distance used for edge.
   * @param boolean  determining if edge is undirected or not.
   * 
   * @return void.
   */
  public void addEdge(Vertex src, Vertex dest, double distance, boolean isUndirected) {
    this.vertices.add(src);
    this.vertices.add(dest);
    new Edge(src, dest, distance);

    if (isUndirected) {
      this.addEdge(dest, src, distance, false);
    }
  }

  /**
   * Adds an edge to the graph. No distance is applied here.
   * 
   * @param src          source vertex
   * @param dest         destination vertex.
   * @param isUndirected boolean determining if edge is undirected.
   * 
   * @return void.
   */
  public void addEdge(Vertex src, Vertex dest, boolean isUndirected) {
    this.vertices.add(src);
    this.vertices.add(dest);
    new Edge(src, dest);

    if (isUndirected) {
      this.addEdge(dest, src, false);
    }
  }
  
  /**
   * 
   * @param e
   */
  public void setUndirectedEdgeDistance(Edge e, double distance) {
    for (Vertex v : this.vertices) {
      for (Edge edge : v.adjacencyList) {
        if (edge == e || (edge.getSource() == e.getDestination() && edge.getDestination() == e.getSource())) {
          edge.setDirectedEdgeDistance(distance);
        }
      }
    }
  }

  /**
   * Removes a vertex from the graph. This may need more testing!
   * 
   * @param src vertex to remove.
   * 
   * @return void.
   */
  public void removeVertex(Vertex src) {
    src.adjacencyList.clear();
    for (Vertex v : this.vertices) {
      for (int e = 0; e < v.getAdjacencyList().size(); e++) {
        Edge edge = v.getAdjacencyList().get(e);
        if (edge.getSource() == src || edge.getDestination() == src) {
          v.getAdjacencyList().remove(edge);
          e--;
        }
      }
    }

    this.vertices.remove(src);
  }

  /**
   * Clears all edges from the graph.
   * 
   * @param void.
   * 
   * @return void.
   */
  public void clearEdges() {
    for (Vertex v : this.vertices) {
      v.adjacencyList.clear();
      v.distanceFromSource = 0;
    }

    this.vertices.clear();
  }
  
  /**
   * Prints the graph vertices (with their adjacency lists) to standard output.
   * 
   * @param void.
   * 
   * @return void.
   */
  public void printGraph() {
    for (Vertex v : this.vertices) {
      System.out.printf("Vertex ID={%d}\t ==> {", v.getID());
      for (int i = 0; i < v.adjacencyList.size(); i++) {
        Edge e = v.adjacencyList.get(i);
        System.out.printf("%s, ", e.toString());
      }
      System.out.printf("}\n");
    }
  }

  public Set<Vertex> getVertices() {
    return this.vertices;
  }
}
