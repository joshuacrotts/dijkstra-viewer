package com.joshuacrotts.dijkstra;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author joshuacrotts
 */
public class Vertex implements Comparable<Vertex> {

  /** Adjacency list of edges that connect to this vertex.*/
  protected List<Edge> adjacencyList;
  
  /** Vertex used in dijkstra algorithm to find the linking one.*/
  protected Vertex previousVertex;
  
  /** Distance from the source vertex that this vertex is. */
  protected double distanceFromSource = Double.MAX_VALUE;
  
  /** Identifier of this vertex. */
  private int id;
  
  public Vertex(int id) {
    this.id = id;
    this.previousVertex = null;
    this.adjacencyList = new LinkedList<>();
  }

  @Override
  public int compareTo(Vertex v) {
    return Double.compare(v.distanceFromSource, this.distanceFromSource);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    if (this == o) {
      return true;
    }

    Vertex otherVertex = (Vertex) o;
    return this.id == otherVertex.id;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + Objects.hashCode(this.id);
    return hash;
  }

  @Override
  public String toString() {
    return "ID=" + this.id;
  }
  
  public int getID() {
    return this.id;
  }
  
  public List<Edge> getAdjacencyList() {
    return this.adjacencyList;
  }
}
