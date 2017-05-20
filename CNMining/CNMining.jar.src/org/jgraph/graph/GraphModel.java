package org.jgraph.graph;

import java.util.Iterator;
import java.util.Map;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoableEdit;
import org.jgraph.event.GraphModelListener;

public abstract interface GraphModel
{
  public abstract int getRootCount();
  
  public abstract Object getRootAt(int paramInt);
  
  public abstract int getIndexOfRoot(Object paramObject);
  
  public abstract boolean contains(Object paramObject);
  
  public abstract AttributeMap getAttributes(Object paramObject);
  
  public abstract Object getValue(Object paramObject);
  
  public abstract Object getSource(Object paramObject);
  
  public abstract Object getTarget(Object paramObject);
  
  public abstract boolean acceptsSource(Object paramObject1, Object paramObject2);
  
  public abstract boolean acceptsTarget(Object paramObject1, Object paramObject2);
  
  public abstract Iterator edges(Object paramObject);
  
  public abstract boolean isEdge(Object paramObject);
  
  public abstract boolean isPort(Object paramObject);
  
  public abstract Object getParent(Object paramObject);
  
  public abstract int getIndexOfChild(Object paramObject1, Object paramObject2);
  
  public abstract Object getChild(Object paramObject, int paramInt);
  
  public abstract int getChildCount(Object paramObject);
  
  public abstract boolean isLeaf(Object paramObject);
  
  public abstract void insert(Object[] paramArrayOfObject, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, UndoableEdit[] paramArrayOfUndoableEdit);
  
  public abstract void remove(Object[] paramArrayOfObject);
  
  public abstract void edit(Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, UndoableEdit[] paramArrayOfUndoableEdit);
  
  public abstract void beginUpdate();
  
  public abstract void endUpdate();
  
  public abstract void execute(ExecutableChange paramExecutableChange);
  
  public abstract Map cloneCells(Object[] paramArrayOfObject);
  
  public abstract Object valueForCellChanged(Object paramObject1, Object paramObject2);
  
  public abstract void toBack(Object[] paramArrayOfObject);
  
  public abstract void toFront(Object[] paramArrayOfObject);
  
  public abstract void addGraphModelListener(GraphModelListener paramGraphModelListener);
  
  public abstract void removeGraphModelListener(GraphModelListener paramGraphModelListener);
  
  public abstract void addUndoableEditListener(UndoableEditListener paramUndoableEditListener);
  
  public abstract void removeUndoableEditListener(UndoableEditListener paramUndoableEditListener);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */