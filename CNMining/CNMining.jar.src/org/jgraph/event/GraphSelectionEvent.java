package org.jgraph.event;

import java.util.EventObject;

public class GraphSelectionEvent
  extends EventObject
{
  protected Object[] cells;
  protected boolean[] areNew;
  
  public GraphSelectionEvent(Object paramObject, Object[] paramArrayOfObject, boolean[] paramArrayOfBoolean)
  {
    super(paramObject);
    this.cells = paramArrayOfObject;
    this.areNew = paramArrayOfBoolean;
  }
  
  public Object[] getCells()
  {
    int i = this.cells.length;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(this.cells, 0, arrayOfObject, 0, i);
    return arrayOfObject;
  }
  
  public Object getCell()
  {
    return this.cells[0];
  }
  
  public boolean isAddedCell()
  {
    return this.areNew[0];
  }
  
  public boolean isAddedCell(Object paramObject)
  {
    for (int i = this.cells.length - 1; i >= 0; i--) {
      if (this.cells[i].equals(paramObject)) {
        return this.areNew[i];
      }
    }
    throw new IllegalArgumentException("cell is not a cell identified by the GraphSelectionEvent");
  }
  
  public boolean isAddedCell(int paramInt)
  {
    if ((this.cells == null) || (paramInt < 0) || (paramInt >= this.cells.length)) {
      throw new IllegalArgumentException("index is beyond range of added cells identified by GraphSelectionEvent");
    }
    return this.areNew[paramInt];
  }
  
  public Object cloneWithSource(Object paramObject)
  {
    return new GraphSelectionEvent(paramObject, this.cells, this.areNew);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/event/GraphSelectionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */