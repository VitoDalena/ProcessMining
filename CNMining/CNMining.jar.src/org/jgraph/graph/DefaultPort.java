package org.jgraph.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DefaultPort
  extends DefaultGraphCell
  implements Port
{
  protected HashSet edges = new HashSet(4, 0.75F);
  protected Port anchor;
  
  public DefaultPort()
  {
    this(null, null);
  }
  
  public DefaultPort(Object paramObject)
  {
    this(paramObject, null);
  }
  
  public DefaultPort(Object paramObject, Port paramPort)
  {
    super(paramObject);
    setAllowsChildren(false);
    this.anchor = paramPort;
  }
  
  public Iterator edges()
  {
    return this.edges.iterator();
  }
  
  public boolean addEdge(Object paramObject)
  {
    return this.edges.add(paramObject);
  }
  
  public boolean removeEdge(Object paramObject)
  {
    return this.edges.remove(paramObject);
  }
  
  public Set getEdges()
  {
    return new HashSet(this.edges);
  }
  
  public void setEdges(Set paramSet)
  {
    this.edges = new HashSet(paramSet);
  }
  
  public Port getAnchor()
  {
    return this.anchor;
  }
  
  public void setAnchor(Port paramPort)
  {
    this.anchor = paramPort;
  }
  
  public Object clone()
  {
    DefaultPort localDefaultPort = (DefaultPort)super.clone();
    localDefaultPort.edges = new HashSet();
    return localDefaultPort;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/DefaultPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */