package com.joshuacrotts.dijkstra;

/**
 *
 * @author joshuacrotts
 */
public class Edge {

  /** Source vertex used in the edge. */
  private Vertex source;
  
  /** Destination vertex used in the edge. */
  private Vertex destination;
  
  /** Distance that this edge uses. */
  private double distance;

  protected Edge(Vertex src, Vertex dest) {
    this(src, dest, 0);
  }
  
  protected Edge(Vertex src, Vertex dest, double distance) {
    this.source = src; 
    this.destination = dest;
    this.distance = distance;
    src.adjacencyList.add(this);
  }

  public Vertex getSource() {
    return this.source;
  }
  
  public Vertex getDestination() {
    return this.destination;
  }
  
  public double getDistance() {
    return this.distance;
  }
  
  /**
   * Sets the distance used for this edge. Note that if the edge is 
   * undirected, use the setEdgeDistance(...) method in Dijkstra.java.
   * 
   * @param distance
   * 
   * @return void.
   */
  public void setDirectedEdgeDistance(double distance) {
    this.distance = distance;
  }

  @Override
  public String toString() {
    return "Destination: " + destination + ", distance: " + distance;
  }
}
