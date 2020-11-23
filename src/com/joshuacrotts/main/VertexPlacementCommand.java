package com.joshuacrotts.main;

import java.awt.event.MouseEvent;

import com.joshuacrotts.dijkstra.GUIVertex;
import com.theta.input.Command;

public class VertexPlacementCommand extends Command {

  /** */
  private DijkstraRunner dijkstra;
  
  public VertexPlacementCommand(DijkstraRunner dijkstraRunner) {
    this.dijkstra = dijkstraRunner;
    this.bind(dijkstra.getMouse(), MouseEvent.BUTTON1);
  }
  
  @Override
  public void pressed(float dt) {
    int mx = this.dijkstra.getMouse().getMouseX();
    int my = this.dijkstra.getMouse().getMouseY();
    this.dijkstra.getGraph().addVertex(new GUIVertex(dijkstra.getGraph().getVertices().size() + 1, mx, my));
  }
}
