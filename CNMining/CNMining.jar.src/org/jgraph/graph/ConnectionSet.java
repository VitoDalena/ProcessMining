package org.jgraph.graph;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ConnectionSet
  implements Serializable
{
  protected Set connections = new HashSet();
  protected Set edges = new HashSet();
  
  public static ConnectionSet create(GraphModel paramGraphModel, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    ConnectionSet localConnectionSet = new ConnectionSet();
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      Object localObject1 = paramArrayOfObject[i];
      if (paramGraphModel.isEdge(localObject1)) {
        if (paramBoolean) {
          localConnectionSet.disconnect(localObject1);
        } else {
          localConnectionSet.connect(localObject1, paramGraphModel.getSource(localObject1), paramGraphModel.getTarget(localObject1));
        }
      }
      Iterator localIterator = paramGraphModel.edges(localObject1);
      while (localIterator.hasNext())
      {
        Object localObject2 = localIterator.next();
        if (paramGraphModel.getSource(localObject2) == localObject1) {
          connect(localConnectionSet, localObject2, localObject1, true, paramBoolean);
        } else if (paramGraphModel.getTarget(localObject2) == localObject1) {
          connect(localConnectionSet, localObject2, localObject1, false, paramBoolean);
        }
      }
    }
    return localConnectionSet;
  }
  
  public ConnectionSet() {}
  
  public ConnectionSet(Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    connect(paramObject1, paramObject2, paramBoolean);
  }
  
  public ConnectionSet(Set paramSet)
  {
    setConnections(paramSet);
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      Connection localConnection = (Connection)localIterator.next();
      this.edges.add(localConnection.getEdge());
    }
  }
  
  public ConnectionSet(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    connect(paramObject1, paramObject2, paramObject3);
  }
  
  protected static void connect(ConnectionSet paramConnectionSet, Object paramObject1, Object paramObject2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean2) {
      paramConnectionSet.disconnect(paramObject1, paramBoolean1);
    } else {
      paramConnectionSet.connect(paramObject1, paramObject2, paramBoolean1);
    }
  }
  
  public void addConnections(CellView[] paramArrayOfCellView)
  {
    for (int i = 0; i < paramArrayOfCellView.length; i++) {
      if ((paramArrayOfCellView[i] instanceof EdgeView))
      {
        EdgeView localEdgeView = (EdgeView)paramArrayOfCellView[i];
        Object localObject1 = localEdgeView.getCell();
        CellView localCellView1 = localEdgeView.getSource();
        CellView localCellView2 = localEdgeView.getTarget();
        Object localObject2 = null;
        if (localCellView1 != null) {
          localObject2 = localCellView1.getCell();
        }
        Object localObject3 = null;
        if (localCellView2 != null) {
          localObject3 = localCellView2.getCell();
        }
        connect(localObject1, localObject2, localObject3);
      }
    }
  }
  
  public void connect(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    connect(paramObject1, paramObject2, true);
    connect(paramObject1, paramObject3, false);
  }
  
  public void connect(Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    Connection localConnection = new Connection(paramObject1, paramObject2, paramBoolean);
    this.connections.remove(localConnection);
    this.connections.add(localConnection);
    this.edges.add(paramObject1);
  }
  
  public void disconnect(Object paramObject)
  {
    disconnect(paramObject, true);
    disconnect(paramObject, false);
  }
  
  public void disconnect(Object paramObject, boolean paramBoolean)
  {
    this.connections.add(new Connection(paramObject, null, paramBoolean));
    this.edges.add(paramObject);
  }
  
  public boolean isEmpty()
  {
    return this.connections.isEmpty();
  }
  
  public int size()
  {
    return this.connections.size();
  }
  
  public Iterator connections()
  {
    return this.connections.iterator();
  }
  
  /**
   * @deprecated
   */
  public Set getChangedEdges()
  {
    return this.edges;
  }
  
  public Object getPort(Object paramObject, boolean paramBoolean)
  {
    if (this.edges.contains(paramObject))
    {
      Iterator localIterator = this.connections.iterator();
      while (localIterator.hasNext())
      {
        Connection localConnection = (Connection)localIterator.next();
        if ((localConnection.getEdge() == paramObject) && (localConnection.isSource() == paramBoolean)) {
          return localConnection.getPort();
        }
      }
    }
    return null;
  }
  
  public ConnectionSet clone(Map paramMap)
  {
    ConnectionSet localConnectionSet = new ConnectionSet();
    Iterator localIterator = connections();
    while (localIterator.hasNext())
    {
      Connection localConnection = (Connection)localIterator.next();
      Object localObject1 = paramMap.get(localConnection.getEdge());
      Object localObject2 = localConnection.getPort();
      if (localObject2 != null) {
        localObject2 = paramMap.get(localObject2);
      }
      if ((localObject1 != null) && (localObject2 != null)) {
        localConnectionSet.connect(localObject1, localObject2, localConnection.isSource());
      } else if (localObject1 != null) {
        localConnectionSet.connect(localObject1, localConnection.getPort(), localConnection.isSource());
      }
    }
    return localConnectionSet;
  }
  
  public Set getConnections()
  {
    return this.connections;
  }
  
  public Set getEdges()
  {
    return this.edges;
  }
  
  public void setConnections(Set paramSet)
  {
    this.connections = paramSet;
  }
  
  public void setEdges(Set paramSet)
  {
    this.edges = paramSet;
  }
  
  public static class Connection
    implements Serializable
  {
    protected Object edge;
    protected Object port;
    protected boolean isSource;
    
    public Connection() {}
    
    public Connection(Object paramObject1, Object paramObject2, boolean paramBoolean)
    {
      this.edge = paramObject1;
      this.port = paramObject2;
      this.isSource = paramBoolean;
    }
    
    public Object getEdge()
    {
      return this.edge;
    }
    
    public Object getPort()
    {
      return this.port;
    }
    
    public boolean isSource()
    {
      return this.isSource;
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof Connection))
      {
        Connection localConnection = (Connection)paramObject;
        return (localConnection.getEdge().equals(this.edge)) && (localConnection.isSource() == this.isSource);
      }
      return false;
    }
    
    public int hashCode()
    {
      return this.edge.hashCode();
    }
    
    public void setEdge(Object paramObject)
    {
      this.edge = paramObject;
    }
    
    public void setSource(boolean paramBoolean)
    {
      this.isSource = paramBoolean;
    }
    
    public void setPort(Object paramObject)
    {
      this.port = paramObject;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/ConnectionSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */