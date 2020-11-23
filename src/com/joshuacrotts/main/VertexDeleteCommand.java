package com.joshuacrotts.main;

import java.awt.event.KeyEvent;

import com.joshuacrotts.dijkstra.GUIVertex;
import com.joshuacrotts.dijkstra.Vertex;
import com.theta.input.Command;

public class VertexDeleteCommand extends Command {

  /** */
  private DijkstraRunner dijkstra;

  public VertexDeleteCommand(DijkstraRunner dijkstraRunner) {
    this.dijkstra = dijkstraRunner;
    this.bind(dijkstra.getKeyboard(), KeyEvent.VK_D);
  }

  @Override
  public void pressed(float dt) {
    for (Vertex v : dijkstra.getGraph().getVertices()) {
      GUIVertex gv = (GUIVertex) v;
      if (gv.isSelected()) {
        gv.setSelected(false);
        this.dijkstra.getGraph().removeVertex(gv);
        break;
      }
    }
  }

  @Override
  public void released(float dt) {

  }
}
