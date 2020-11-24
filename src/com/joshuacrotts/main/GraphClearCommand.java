package com.joshuacrotts.main;

import java.awt.event.KeyEvent;

import com.joshuacrotts.dijkstra.Edge;
import com.joshuacrotts.dijkstra.GUIEdge;
import com.joshuacrotts.dijkstra.GUIVertex;
import com.joshuacrotts.dijkstra.Vertex;
import com.theta.input.Command;

public class GraphClearCommand extends Command {

  /** */
  private DijkstraRunner dijkstra;
  
  public GraphClearCommand(DijkstraRunner dijkstraRunner) {
    this.dijkstra = dijkstraRunner;
    this.bind(dijkstra.getKeyboard(), KeyEvent.VK_C);
  }
  
  @Override
  public void pressed(float dt) {
    for (Vertex v : this.dijkstra.getGraph().getVertices()) {
      GUIVertex gv = (GUIVertex) v;
      for (Edge e : gv.getAdjacencyList()) {
        GUIEdge ge = (GUIEdge) e;
        ge.setSelected(false);
        gv.setSelected(false);
      }
    }
  }
}
