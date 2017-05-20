package edu.uci.ics.jung.visualization.control;

import java.awt.event.MouseEvent;

public abstract interface GraphMousePlugin
{
  public abstract int getModifiers();
  
  public abstract void setModifiers(int paramInt);
  
  public abstract boolean checkModifiers(MouseEvent paramMouseEvent);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/GraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */