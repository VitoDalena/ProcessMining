package org.jgraph.graph;

import java.util.Vector;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import org.jgraph.event.GraphLayoutCacheEvent.GraphLayoutCacheChange;

public class GraphUndoManager
  extends UndoManager
{
  public synchronized boolean canUndo(Object paramObject)
  {
    if (isInProgress())
    {
      UndoableEdit localUndoableEdit = editToBeUndone(paramObject);
      return (localUndoableEdit != null) && (localUndoableEdit.canUndo());
    }
    return super.canUndo();
  }
  
  public synchronized boolean canRedo(Object paramObject)
  {
    if (isInProgress())
    {
      UndoableEdit localUndoableEdit = editToBeRedone(paramObject);
      return (localUndoableEdit != null) && (localUndoableEdit.canRedo());
    }
    return super.canRedo();
  }
  
  public void undo(Object paramObject)
  {
    if ((paramObject == null) || (!isInProgress()))
    {
      super.undo();
    }
    else
    {
      UndoableEdit localUndoableEdit = editToBeUndone(paramObject);
      if (localUndoableEdit == null) {
        throw new CannotUndoException();
      }
      undoTo(localUndoableEdit);
    }
  }
  
  protected UndoableEdit editToBeUndone(Object paramObject)
  {
    UndoableEdit localUndoableEdit = null;
    Object localObject = null;
    do
    {
      localUndoableEdit = nextEditToBeUndone(localUndoableEdit);
      if ((localUndoableEdit instanceof GraphLayoutCacheEvent.GraphLayoutCacheChange)) {
        localObject = ((GraphLayoutCacheEvent.GraphLayoutCacheChange)localUndoableEdit).getSource();
      }
      if (!(localObject instanceof GraphLayoutCache)) {
        localObject = null;
      }
    } while ((localUndoableEdit != null) && (localObject != null) && (localObject != paramObject));
    return localUndoableEdit;
  }
  
  protected UndoableEdit nextEditToBeUndone(UndoableEdit paramUndoableEdit)
  {
    if (paramUndoableEdit == null) {
      return editToBeUndone();
    }
    int i = this.edits.indexOf(paramUndoableEdit) - 1;
    if (i >= 0) {
      return (UndoableEdit)this.edits.get(i);
    }
    return null;
  }
  
  public void redo(Object paramObject)
  {
    if ((paramObject == null) || (!isInProgress()))
    {
      super.redo();
    }
    else
    {
      UndoableEdit localUndoableEdit = editToBeRedone(paramObject);
      if (localUndoableEdit == null) {
        throw new CannotRedoException();
      }
      redoTo(localUndoableEdit);
    }
  }
  
  protected UndoableEdit editToBeRedone(Object paramObject)
  {
    UndoableEdit localUndoableEdit1 = nextEditToBeRedone(null);
    UndoableEdit localUndoableEdit2 = null;
    Object localObject = null;
    do
    {
      localUndoableEdit2 = localUndoableEdit1;
      localUndoableEdit1 = nextEditToBeRedone(localUndoableEdit1);
      if ((localUndoableEdit1 instanceof GraphLayoutCacheEvent.GraphLayoutCacheChange)) {
        localObject = ((GraphLayoutCacheEvent.GraphLayoutCacheChange)localUndoableEdit1).getSource();
      }
      if (!(localObject instanceof GraphLayoutCache)) {
        localObject = null;
      }
    } while ((localUndoableEdit1 != null) && (localObject != null) && (localObject != paramObject));
    return localUndoableEdit2;
  }
  
  protected UndoableEdit nextEditToBeRedone(UndoableEdit paramUndoableEdit)
  {
    if (paramUndoableEdit == null) {
      return editToBeRedone();
    }
    int i = this.edits.indexOf(paramUndoableEdit) + 1;
    if (i < this.edits.size()) {
      return (UndoableEdit)this.edits.get(i);
    }
    return null;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphUndoManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */