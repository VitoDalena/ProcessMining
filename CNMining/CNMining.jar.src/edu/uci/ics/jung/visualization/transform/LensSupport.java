package edu.uci.ics.jung.visualization.transform;

import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

public abstract interface LensSupport
{
  public abstract void activate();
  
  public abstract void deactivate();
  
  public abstract void activate(boolean paramBoolean);
  
  public abstract LensTransformer getLensTransformer();
  
  public abstract ModalGraphMouse getGraphMouse();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/LensSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */