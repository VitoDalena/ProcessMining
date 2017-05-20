package org.jgraph.event;

import java.util.EventObject;
import org.jgraph.graph.CellView;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.ParentMap;

public class GraphModelEvent
  extends EventObject
{
  protected GraphModelChange change;
  
  public GraphModelEvent(Object paramObject, GraphModelChange paramGraphModelChange)
  {
    super(paramObject);
    this.change = paramGraphModelChange;
  }
  
  public GraphModelChange getChange()
  {
    return this.change;
  }
  
  public static abstract interface GraphModelChange
    extends GraphLayoutCacheEvent.GraphLayoutCacheChange
  {
    public abstract ConnectionSet getConnectionSet();
    
    public abstract ConnectionSet getPreviousConnectionSet();
    
    public abstract ParentMap getParentMap();
    
    public abstract ParentMap getPreviousParentMap();
    
    public abstract void putViews(GraphLayoutCache paramGraphLayoutCache, CellView[] paramArrayOfCellView);
    
    public abstract CellView[] getViews(GraphLayoutCache paramGraphLayoutCache);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/event/GraphModelEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */