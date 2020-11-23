package com.joshuacrotts.dijkstra;

public class GUIDijkstra extends Dijkstra {

  public GUIDijkstra() {
    super();
  }
  
  /**
   * Adds an GUI edge to the graph.
   * 
   * @param src      source vertex.
   * @param dest     destination vertex.
   * @param distance used for edge.
   * @param boolean  determining if edge is undirected or not.
   * 
   * @return void.
   */
  public void addGUIEdge(GUIVertex src, GUIVertex dest, double distance, boolean isUndirected) {
    this.getVertices().add(src);
    this.getVertices().add(dest);
    new GUIEdge(this, src, dest, distance);

    if (isUndirected) {
      this.addGUIEdge(dest, src, distance, false);
    }
  }

  /**
   * Adds an GUI edge to the graph. No distance is applied here.
   * 
   * @param src          source vertex
   * @param dest         destination vertex.
   * @param isUndirected boolean determining if edge is undirected.
   * 
   * @return void.
   */
  public void addGUIEdge(GUIVertex src, GUIVertex dest, boolean isUndirected) {
    this.getVertices().add(src);
    this.getVertices().add(dest);
    new GUIEdge(this, src, dest);

    if (isUndirected) {
      this.addGUIEdge(dest, src, false);
    }
  }
}
