package com.joshuacrotts.dijkstra;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import com.theta.graphic.ThetaGraphics;

public class GUIVertex extends Vertex {

  private Ellipse2D.Double ellipse;
  
  private final int WIDTH = 20;
  private final int HEIGHT = 20;
  
  private int x;
  private int y;
  
  private boolean isSelected;
  private boolean isStartVertex;
  private boolean isEndVertex;
  
  public GUIVertex(int id, int x, int y) {
    super(id);
    this.x = x;
    this.y = y;
    this.ellipse = new Ellipse2D.Double(x, y, WIDTH, HEIGHT);
  }
  
  /**
   * 
   */
  public void render() {
    Color old = ThetaGraphics.GFXContext.getColor();
    
    ThetaGraphics.GFXContext.setColor(Color.red);
    ThetaGraphics.GFXContext.fill(ellipse);
    
    // Color the start vertex green, and the end vertex orange.
    if (this.isStartVertex) {
      ThetaGraphics.GFXContext.setColor(Color.green);
      ThetaGraphics.GFXContext.draw(ellipse);
    } else if (this.isEndVertex) {
      ThetaGraphics.GFXContext.setColor(Color.orange);
      ThetaGraphics.GFXContext.draw(ellipse);
    }
    
    // If we have the vertex selected, remove 
    if (this.isSelected) {
      ThetaGraphics.GFXContext.setColor(Color.blue);
      ThetaGraphics.GFXContext.draw(ellipse);
    }
    
    // Draw the inner-label with the vertex identifier. 
    ThetaGraphics.GFXContext.setColor(Color.white);
    ThetaGraphics.GFXContext.drawString(this.getID() + "", x + WIDTH / 2 - 3, y + HEIGHT / 2 + 3);
    
    ThetaGraphics.GFXContext.setColor(old);
  }
  
  /**
   * 
   * @param mx
   * @param my
   */
  public void select(int mx, int my) {
    if (this.ellipse.contains(mx, my)) {
      this.isSelected = !this.isSelected;
    }
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public boolean isSelected() {
    return this.isSelected;
  }
  
  public void setSelected(boolean s) {
    this.isSelected = s;
  }
  
  public boolean isStart() {
    return this.isStartVertex;
  }
  
  public void setStart(boolean start) {
    this.isStartVertex = start;
  }
  
  public boolean isEnd() {
    return this.isEndVertex;
  }
  
  public void setEnd(boolean end) {
    this.isEndVertex = end;
  }
}
