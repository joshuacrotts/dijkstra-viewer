package com.joshuacrotts.main;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JOptionPane;

import com.joshuacrotts.dijkstra.Edge;
import com.joshuacrotts.dijkstra.GUIEdge;
import com.joshuacrotts.dijkstra.GUIVertex;
import com.joshuacrotts.dijkstra.Vertex;
import com.theta.input.Command;

public class DijkstraSolverCommand extends Command {

  /** */
  private DijkstraRunner dijkstra;
  
  public DijkstraSolverCommand(DijkstraRunner dijkstraRunner) {
    this.dijkstra = dijkstraRunner;
    this.bind(this.dijkstra.getKeyboard(), KeyEvent.VK_Q);
  }
  
  /**
   * 
   * @param dt
   */
  public void pressed(float dt) {
    Vertex start = null;
    Vertex end = null;
    
    // First iterate through and find the start and end vertices.
    for (Vertex v : this.dijkstra.getGraph().getVertices()) {
      GUIVertex gv = (GUIVertex) v;
      for (Edge e : gv.getAdjacencyList()) {
        GUIEdge ge = (GUIEdge) e;
        if (gv.isStart() && start == null) {
          start = gv;
        }
        
        if (gv.isEnd() && end == null) {
          end = gv;
        }
        
        ge.setSelected(false);
        gv.setSelected(false);
      }
    }
    
    // Check to make sure there's one start and one end vertex.
    if (start == null || end == null) {
      JOptionPane.showMessageDialog(null, "Error - one vertex must be the start, and one must be the end.");
      return;
    }
    
    this.dijkstra.getGraph().computeDijkstra(start);
    List<Vertex> path = this.dijkstra.getGraph().getDijkstraPath(end);
    
    // Run through all vertices and check adjacency lists - once 
    // we find the path that is connected by the vertex and its next
    // one in line, we highlight it.
    for (int i = 0; i < path.size() - 1; i++) {
      GUIVertex v1 = (GUIVertex) path.get(i);
      GUIVertex v2 = (GUIVertex) path.get(i + 1);
      for (Edge e : v1.getAdjacencyList()) {
        GUIEdge ge = (GUIEdge) e; 
        if (ge.getSource() == v1 && ge.getDestination() == v2) {
          ge.setSelected(true);
          v1.setSelected(true);
          break;
        }
      }
      
      for (Edge e : v2.getAdjacencyList()) {
        GUIEdge ge = (GUIEdge) e; 
        if (ge.getSource() == v2 && ge.getDestination() == v1) {
          ge.setSelected(true);
          v2.setSelected(true);
          break;
        }
      }
    }
  }
}
