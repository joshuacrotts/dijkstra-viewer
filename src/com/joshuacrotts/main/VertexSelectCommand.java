package com.joshuacrotts.main;

import java.awt.event.KeyEvent;

import com.joshuacrotts.dijkstra.GUIVertex;
import com.joshuacrotts.dijkstra.Vertex;
import com.theta.input.Command;

public class VertexSelectCommand extends Command {

  /** Instance of the GUI with the Dijkstra object. */
  private DijkstraRunner dijkstra;

  /** Vertex to keep track of the source in a newly-connecting edge. */
  private GUIVertex pointOne;

  /** Vertex to keep track of the destination in a newly-connecting edge. */
  private GUIVertex pointTwo;

  public VertexSelectCommand(DijkstraRunner dijkstraRunner) {
    this.dijkstra = dijkstraRunner;
    this.bind(dijkstra.getKeyboard(), KeyEvent.VK_M);
  }

  @Override
  public void pressed(float dt) {
    int mx = this.dijkstra.getMouse().getMouseX();
    int my = this.dijkstra.getMouse().getMouseY();
    for (Vertex v : dijkstra.getGraph().getVertices()) {
      GUIVertex gv = (GUIVertex) v;
      gv.select(mx, my);

      // If the vertex is not selected (which also includes the vertex
      // being de-selected, we de-select it from the pointOne and pointTwo
      // vertices.
      if (!gv.isSelected()) {
        if (pointOne == gv) {
          pointOne = null;
        } else if (pointTwo == gv) {
          pointTwo = null;
        }
      }
      // If pointOne is null and we just selected a vertex, then assign
      // the current vertex to pointOne. Once pointOne != null, pointTwo is
      // assigned.
      if (pointOne == null && gv.isSelected()) {
        pointOne = gv;
      } else if (pointTwo == null && gv.isSelected() && v.getID() != pointOne.getID()) {
        pointTwo = gv;
      }

      // If both vertices are not null, we add an edge between the two and 
      // de-select them.
      if (pointOne != null && pointTwo != null) {
        this.dijkstra.getGraph().addGUIEdge(pointOne, pointTwo, true);
        this.pointTwo = this.pointOne = null;
        this.dijkstra.unselectAll();
      }
    }
  }
}
