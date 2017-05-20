package org.jgraph.graph;

import java.io.Serializable;
import java.util.List;

public abstract interface Edge
  extends GraphCell
{
  public abstract Object getSource();
  
  public abstract Object getTarget();
  
  public abstract void setSource(Object paramObject);
  
  public abstract void setTarget(Object paramObject);
  
  public static abstract interface Routing
    extends Serializable
  {
    public static final int NO_PREFERENCE = -1;
    
    public abstract List route(GraphLayoutCache paramGraphLayoutCache, EdgeView paramEdgeView);
    
    public abstract int getPreferredLineStyle(EdgeView paramEdgeView);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/Edge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */