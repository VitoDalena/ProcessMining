package com.jgraph.layout.graph;

import com.jgraph.layout.organic.JGraphOrganicLayout;
import java.awt.geom.Rectangle2D;

/**
 * @deprecated
 */
public class JGraphAnnealingLayout
  extends JGraphOrganicLayout
{
  /**
   * @deprecated
   */
  public JGraphAnnealingLayout() {}
  
  public JGraphAnnealingLayout(Rectangle2D paramRectangle2D)
  {
    super(paramRectangle2D);
  }
  
  public String toString()
  {
    return "Annealing";
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/graph/JGraphAnnealingLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */