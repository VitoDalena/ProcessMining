package org.jgraph.event;

import java.awt.geom.Rectangle2D;
import java.util.EventObject;
import java.util.Map;

public class GraphLayoutCacheEvent
  extends EventObject
{
  protected GraphLayoutCacheChange change;
  
  public GraphLayoutCacheEvent(Object paramObject, GraphLayoutCacheChange paramGraphLayoutCacheChange)
  {
    super(paramObject);
    this.change = paramGraphLayoutCacheChange;
  }
  
  public GraphLayoutCacheChange getChange()
  {
    return this.change;
  }
  
  public static abstract interface GraphLayoutCacheChange
  {
    public abstract Object getSource();
    
    public abstract Object[] getChanged();
    
    public abstract Object[] getInserted();
    
    public abstract Object[] getRemoved();
    
    public abstract Map getAttributes();
    
    public abstract Map getPreviousAttributes();
    
    public abstract Rectangle2D getDirtyRegion();
    
    public abstract void setDirtyRegion(Rectangle2D paramRectangle2D);
    
    public abstract Object[] getContext();
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/event/GraphLayoutCacheEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */