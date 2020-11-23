package com.joshuacrotts.main;

import java.awt.event.MouseEvent;

import com.joshuacrotts.dijkstra.Edge;
import com.joshuacrotts.dijkstra.GUIEdge;
import com.joshuacrotts.dijkstra.GUIVertex;
import com.joshuacrotts.dijkstra.Vertex;
import com.theta.input.Command;

public class EdgeSelectCommand extends Command {

  /** */
  private DijkstraRunner dijkstra;

  public EdgeSelectCommand(DijkstraRunner dijkstraRunner) {
    this.dijkstra = dijkstraRunner;
    this.bind(dijkstra.getMouse(), MouseEvent.BUTTON3);
  }

  @Override
  public void pressed(float dt) {
    int mx = this.dijkstra.getMouse().getMouseX();
    int my = this.dijkstra.getMouse().getMouseY();
    for (Vertex v : dijkstra.getGraph().getVertices()) {
      GUIVertex gv = (GUIVertex) v;
      for (Edge e : gv.getAdjacencyList()) {
        GUIEdge ge = (GUIEdge) e;
        ge.select(mx, my);
        if (ge.isSelected()) 
          ge.setSelected(false);
      }
    }
  }
}
