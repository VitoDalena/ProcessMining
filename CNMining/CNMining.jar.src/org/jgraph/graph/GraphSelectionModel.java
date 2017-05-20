package org.jgraph.graph;

import java.beans.PropertyChangeListener;
import org.jgraph.event.GraphSelectionListener;

public abstract interface GraphSelectionModel
{
  public static final int SINGLE_GRAPH_SELECTION = 1;
  public static final int MULTIPLE_GRAPH_SELECTION = 4;
  
  public abstract void setSelectionMode(int paramInt);
  
  public abstract void setChildrenSelectable(boolean paramBoolean);
  
  public abstract boolean isChildrenSelectable();
  
  public abstract int getSelectionMode();
  
  public abstract void setSelectionCell(Object paramObject);
  
  public abstract void setSelectionCells(Object[] paramArrayOfObject);
  
  public abstract void addSelectionCell(Object paramObject);
  
  public abstract void addSelectionCells(Object[] paramArrayOfObject);
  
  public abstract void removeSelectionCell(Object paramObject);
  
  public abstract void removeSelectionCells(Object[] paramArrayOfObject);
  
  public abstract Object[] getSelectables();
  
  public abstract Object getSelectionCell();
  
  public abstract Object[] getSelectionCells();
  
  public abstract int getSelectionCount();
  
  public abstract boolean isCellSelected(Object paramObject);
  
  public abstract boolean isChildrenSelected(Object paramObject);
  
  public abstract boolean isSelectionEmpty();
  
  public abstract void clearSelection();
  
  public abstract void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener);
  
  public abstract void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener);
  
  public abstract void addGraphSelectionListener(GraphSelectionListener paramGraphSelectionListener);
  
  public abstract void removeGraphSelectionListener(GraphSelectionListener paramGraphSelectionListener);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphSelectionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */