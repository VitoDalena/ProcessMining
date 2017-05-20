package edu.uci.ics.jung.visualization.control;

import java.awt.event.MouseEvent;

public abstract interface GraphMouseListener<V>
{
  public abstract void graphClicked(V paramV, MouseEvent paramMouseEvent);
  
  public abstract void graphPressed(V paramV, MouseEvent paramMouseEvent);
  
  public abstract void graphReleased(V paramV, MouseEvent paramMouseEvent);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/GraphMouseListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */