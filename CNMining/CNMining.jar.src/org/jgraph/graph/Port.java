package org.jgraph.graph;

import java.util.Iterator;

public abstract interface Port
  extends GraphCell
{
  public abstract Iterator edges();
  
  public abstract boolean addEdge(Object paramObject);
  
  public abstract boolean removeEdge(Object paramObject);
  
  public abstract Port getAnchor();
  
  public abstract void setAnchor(Port paramPort);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/Port.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */