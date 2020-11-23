package com.joshuacrotts.dijkstra;

import java.awt.Color;
import java.awt.geom.Line2D;

import javax.swing.JOptionPane;

import com.theta.graphic.ThetaGraphics;

public class GUIEdge extends Edge {

  /** */
  private GUIDijkstra dijkstra;
  
  /** */
  private Line2D.Double line;
  
  /** */
  private boolean isSelected;
  
  protected GUIEdge(GUIDijkstra dijkstraRunner, GUIVertex src, GUIVertex dest, double distance) {
    super(src, dest, distance);
    this.line = new Line2D.Double(src.getX() + 10, src.getY() + 10, dest.getX() + 10, dest.getY() + 10);
    this.dijkstra = dijkstraRunner;
  }
  
  protected GUIEdge(GUIDijkstra dijkstraRunner, GUIVertex src, GUIVertex dest) {
    this(dijkstraRunner, src, dest, 0);
  }
  
  /**
   * 
   * @param mx
   * @param my
   */
  public void select(int mx, int my) {
    double dis = this.line.ptSegDist(mx, my);
    if (dis < 2) {
      this.isSelected = !this.isSelected;
    }
    
    // If we currently have the edge selected, then prompt for a new
    // distance.
    if (this.isSelected) {
      String prompt = String.format("Enter distance for edge {%d->%d}: ", 
                                    this.getSource().getID(), this.getDestination().getID());
      double edgeDistance = Double.parseDouble(JOptionPane.showInputDialog(prompt));
      this.dijkstra.setUndirectedEdgeDistance(this, edgeDistance);
    }
  }
  
  /**
   * 
   * 
   * @param void.
   * 
   * @return void.
   */
  public void render() {
    Color old = ThetaGraphics.GFXContext.getColor();
    
    // Draw the line itself.
    ThetaGraphics.GFXContext.setColor(this.isSelected ? Color.pink : Color.green);
    ThetaGraphics.GFXContext.draw(this.line);
    
    // Draw the text displaying the edge weight.
    int cx = (int) (this.line.x1 + this.line.x2) / 2;
    int cy = (int) (this.line.y1 + this.line.y2) / 2;
    ThetaGraphics.GFXContext.setColor(Color.WHITE);
    ThetaGraphics.GFXContext.drawString(this.getDistance() + "", cx, cy);
    
    ThetaGraphics.GFXContext.setColor(old);
  }
  
  public boolean isSelected() {
    return this.isSelected;
  }
  
  public void setSelected(boolean s) {
    this.isSelected = s;
  }
}
