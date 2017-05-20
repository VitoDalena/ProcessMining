package com.jgraph.layout.graph;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;

public class JGraphSimpleLayout
  implements JGraphLayout
{
  public static final int TYPE_CIRCLE = 0;
  public static final int TYPE_TILT = 1;
  public static final int TYPE_RANDOM = 2;
  protected int type = 0;
  protected int maxx;
  protected int maxy;
  
  public JGraphSimpleLayout(int paramInt)
  {
    this(paramInt, 20, 20);
  }
  
  public JGraphSimpleLayout(int paramInt1, int paramInt2, int paramInt3)
  {
    this.type = paramInt1;
    this.maxx = paramInt2;
    this.maxy = paramInt3;
  }
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    switch (this.type)
    {
    case 0: 
      paramJGraphFacade.circle(paramJGraphFacade.getVertices());
      break;
    case 1: 
      paramJGraphFacade.tilt(paramJGraphFacade.getVertices(), this.maxx, this.maxy);
      break;
    case 2: 
      paramJGraphFacade.randomize(paramJGraphFacade.getVertices(), this.maxx, this.maxy);
    }
  }
  
  public int getMaxx()
  {
    return this.maxx;
  }
  
  public void setMaxx(int paramInt)
  {
    this.maxx = paramInt;
  }
  
  public int getMaxy()
  {
    return this.maxy;
  }
  
  public void setMaxy(int paramInt)
  {
    this.maxy = paramInt;
  }
  
  public String toString()
  {
    switch (this.type)
    {
    case 0: 
      return "Circle";
    case 1: 
      return "Tilt";
    case 2: 
      return "Random";
    }
    return "Unknown";
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/graph/JGraphSimpleLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */