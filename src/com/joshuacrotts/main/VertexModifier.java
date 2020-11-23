package com.joshuacrotts.main;

import java.awt.event.KeyEvent;

import com.joshuacrotts.dijkstra.GUIVertex;
import com.joshuacrotts.dijkstra.Vertex;
import com.theta.input.Command;

public class VertexModifier {
  
  /** */
  private DijkstraRunner dijkstra;
  
  /** */
  private VertexSetStartCommand vssc;
  
  /** */
  private VertexSetEndCommand vsec;
  
  public VertexModifier(DijkstraRunner dijkstraRunner) {
    this.dijkstra = dijkstraRunner;
    this.vssc = new VertexSetStartCommand(dijkstraRunner);
    this.vsec = new VertexSetEndCommand(dijkstraRunner);
  }
  
  public void update() {
    for (Vertex v : this.dijkstra.getGraph().getVertices()) {
      GUIVertex gv = (GUIVertex) v;
      if (gv.isSelected()) {
        if (this.vssc.active) {
          gv.setStart(!gv.isStart());
          this.vssc.active = false;
        } 
        if (this.vsec.active) {
          gv.setEnd(!gv.isEnd());
          this.vsec.active = false;
        }
      }
    }
  }
  
  private class VertexSetStartCommand extends Command {
    
    private boolean active = false;
    
    public VertexSetStartCommand(DijkstraRunner dijkstraRunner) {
      this.bind(dijkstraRunner.getKeyboard(), KeyEvent.VK_S);
    }

    @Override
    public void pressed(float dt) {
      this.active = !this.active;
    }
  }
  
  private class VertexSetEndCommand extends Command {
    
    private boolean active = false;
    
    public VertexSetEndCommand(DijkstraRunner dijkstraRunner) {
      this.bind(dijkstraRunner.getKeyboard(), KeyEvent.VK_E);
    }

    @Override
    public void pressed(float dt) {
      this.active = !this.active;
    }
  }
}
