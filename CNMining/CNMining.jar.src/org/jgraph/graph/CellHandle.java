package org.jgraph.graph;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract interface CellHandle
{
  public abstract void paint(Graphics paramGraphics);
  
  public abstract void overlay(Graphics paramGraphics);
  
  public abstract void mouseMoved(MouseEvent paramMouseEvent);
  
  public abstract void mousePressed(MouseEvent paramMouseEvent);
  
  public abstract void mouseDragged(MouseEvent paramMouseEvent);
  
  public abstract void mouseReleased(MouseEvent paramMouseEvent);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/CellHandle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */