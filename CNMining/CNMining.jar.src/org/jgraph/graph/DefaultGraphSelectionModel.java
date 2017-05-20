package org.jgraph.graph;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import javax.swing.event.EventListenerList;
import javax.swing.event.SwingPropertyChangeSupport;
import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;

public class DefaultGraphSelectionModel
  implements GraphSelectionModel, Cloneable, Serializable
{
  public static final String SELECTION_MODE_PROPERTY = "selectionMode";
  public static final int SELECTED = -1;
  public static final Integer UNSELECTED = new Integer(0);
  protected JGraph graph;
  protected SwingPropertyChangeSupport changeSupport;
  protected EventListenerList listenerList = new EventListenerList();
  protected int selectionMode;
  protected boolean childrenSelectable = true;
  protected Map cellStates = new Hashtable();
  protected Set selection = new LinkedHashSet();
  
  public DefaultGraphSelectionModel(JGraph paramJGraph)
  {
    this.graph = paramJGraph;
  }
  
  public void setSelectionMode(int paramInt)
  {
    int i = this.selectionMode;
    this.selectionMode = paramInt;
    if ((this.selectionMode != 4) && (this.selectionMode != 1)) {
      this.selectionMode = 4;
    }
    if ((i != this.selectionMode) && (this.changeSupport != null)) {
      this.changeSupport.firePropertyChange("selectionMode", new Integer(i), new Integer(this.selectionMode));
    }
  }
  
  public int getSelectionMode()
  {
    return this.selectionMode;
  }
  
  public void setChildrenSelectable(boolean paramBoolean)
  {
    this.childrenSelectable = paramBoolean;
  }
  
  public boolean isChildrenSelectable()
  {
    return this.childrenSelectable;
  }
  
  protected boolean isChildrenSelectable(Object paramObject)
  {
    AttributeMap localAttributeMap = this.graph.getModel().getAttributes(paramObject);
    if ((localAttributeMap != null) && (this.childrenSelectable)) {
      return GraphConstants.isChildrenSelectable(localAttributeMap);
    }
    return this.childrenSelectable;
  }
  
  public void setSelectionCell(Object paramObject)
  {
    if (paramObject == null) {
      setSelectionCells(null);
    } else {
      setSelectionCells(new Object[] { paramObject });
    }
  }
  
  public void setSelectionCells(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      if ((this.selectionMode == 1) && (paramArrayOfObject.length > 0)) {
        paramArrayOfObject = new Object[] { paramArrayOfObject[(paramArrayOfObject.length - 1)] };
      }
      this.cellStates.clear();
      Vector localVector = new Vector();
      LinkedHashSet localLinkedHashSet = new LinkedHashSet();
      Object localObject;
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if (paramArrayOfObject[i] != null)
        {
          this.selection.remove(paramArrayOfObject[i]);
          localVector.addElement(new CellPlaceHolder(paramArrayOfObject[i], !this.selection.remove(paramArrayOfObject[i])));
          select(localLinkedHashSet, paramArrayOfObject[i]);
          localObject = this.graph.getModel().getParent(paramArrayOfObject[i]);
          if (localObject != null) {
            localVector.addElement(new CellPlaceHolder(localObject, false));
          }
        }
      }
      Iterator localIterator = this.selection.iterator();
      while (localIterator.hasNext()) {
        for (localObject = localIterator.next(); localObject != null; localObject = this.graph.getModel().getParent(localObject)) {
          localVector.addElement(new CellPlaceHolder(localObject, false));
        }
      }
      this.selection = localLinkedHashSet;
      if (localVector.size() > 0) {
        notifyCellChange(localVector);
      }
    }
  }
  
  public void addSelectionCell(Object paramObject)
  {
    if (paramObject != null) {
      addSelectionCells(new Object[] { paramObject });
    }
  }
  
  public void addSelectionCells(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null) {
      if (this.selectionMode == 1)
      {
        setSelectionCells(paramArrayOfObject);
      }
      else
      {
        Vector localVector = new Vector();
        for (int i = 0; i < paramArrayOfObject.length; i++) {
          if (paramArrayOfObject[i] != null)
          {
            boolean bool = select(this.selection, paramArrayOfObject[i]);
            if (bool)
            {
              localVector.addElement(new CellPlaceHolder(paramArrayOfObject[i], true));
              Object localObject = this.graph.getModel().getParent(paramArrayOfObject[i]);
              if (localObject != null) {
                localVector.addElement(new CellPlaceHolder(localObject, false));
              }
            }
          }
        }
        if (localVector.size() > 0) {
          notifyCellChange(localVector);
        }
      }
    }
  }
  
  public void removeSelectionCell(Object paramObject)
  {
    if (paramObject != null) {
      removeSelectionCells(new Object[] { paramObject });
    }
  }
  
  public void removeSelectionCells(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      Vector localVector = new Vector();
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if (paramArrayOfObject[i] != null)
        {
          boolean bool = deselect(paramArrayOfObject[i]);
          if (bool)
          {
            localVector.addElement(new CellPlaceHolder(paramArrayOfObject[i], false));
            Object localObject = this.graph.getModel().getParent(paramArrayOfObject[i]);
            if (localObject != null) {
              localVector.addElement(new CellPlaceHolder(localObject, false));
            }
          }
        }
      }
      if (localVector.size() > 0) {
        notifyCellChange(localVector);
      }
    }
  }
  
  public Object[] getSelectables()
  {
    if (isChildrenSelectable())
    {
      ArrayList localArrayList = new ArrayList();
      Stack localStack = new Stack();
      GraphModel localGraphModel = this.graph.getModel();
      for (int i = 0; i < localGraphModel.getRootCount(); i++) {
        localStack.add(localGraphModel.getRootAt(i));
      }
      while (!localStack.isEmpty())
      {
        Object localObject = localStack.pop();
        AttributeMap localAttributeMap = this.graph.getAttributes(localObject);
        if ((!localGraphModel.isPort(localObject)) && ((localAttributeMap == null) || (GraphConstants.isSelectable(localAttributeMap)))) {
          localArrayList.add(localObject);
        }
        if (isChildrenSelectable(localObject)) {
          for (int j = 0; j < localGraphModel.getChildCount(localObject); j++) {
            localStack.add(localGraphModel.getChild(localObject, j));
          }
        }
      }
      return localArrayList.toArray();
    }
    return this.graph.getRoots();
  }
  
  public Object getSelectionCell()
  {
    if ((this.selection != null) && (this.selection.size() > 0)) {
      return this.selection.toArray()[0];
    }
    return null;
  }
  
  public Object[] getSelectionCells()
  {
    if (this.selection != null) {
      return this.selection.toArray();
    }
    return null;
  }
  
  public int getSelectionCount()
  {
    return this.selection == null ? 0 : this.selection.size();
  }
  
  public boolean isCellSelected(Object paramObject)
  {
    int i = getSelectedChildCount(paramObject);
    return i == -1;
  }
  
  public boolean isChildrenSelected(Object paramObject)
  {
    int i = getSelectedChildCount(paramObject);
    return i > 0;
  }
  
  public boolean isSelectionEmpty()
  {
    return this.selection.isEmpty();
  }
  
  public void clearSelection()
  {
    if (this.selection != null)
    {
      Vector localVector = new Vector();
      Iterator localIterator = this.cellStates.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        for (Object localObject = localEntry.getKey(); localObject != null; localObject = this.graph.getModel().getParent(localObject)) {
          localVector.addElement(new CellPlaceHolder(localObject, false));
        }
      }
      this.selection.clear();
      this.cellStates.clear();
      if (localVector.size() > 0) {
        notifyCellChange(localVector);
      }
    }
  }
  
  protected int getSelectedChildCount(Object paramObject)
  {
    if (paramObject != null)
    {
      Integer localInteger = (Integer)this.cellStates.get(paramObject);
      if (localInteger == null)
      {
        localInteger = UNSELECTED;
        this.cellStates.put(paramObject, localInteger);
      }
      return localInteger.intValue();
    }
    return 0;
  }
  
  protected void setSelectedChildCount(Object paramObject, int paramInt)
  {
    Integer localInteger = new Integer(paramInt);
    this.cellStates.put(paramObject, localInteger);
  }
  
  protected boolean select(Set paramSet, Object paramObject)
  {
    AttributeMap localAttributeMap = this.graph.getAttributes(paramObject);
    if ((!isCellSelected(paramObject)) && (this.graph.getGraphLayoutCache().isVisible(paramObject)) && ((localAttributeMap == null) || (GraphConstants.isSelectable(localAttributeMap))))
    {
      GraphModel localGraphModel = this.graph.getModel();
      for (Object localObject1 = localGraphModel.getParent(paramObject); localObject1 != null; localObject1 = localGraphModel.getParent(localObject1))
      {
        int i = getSelectedChildCount(localObject1);
        if (i == -1) {
          i = 0;
        }
        i++;
        setSelectedChildCount(localObject1, i);
        this.selection.remove(localObject1);
      }
      Object[] arrayOfObject = { paramObject };
      List localList = DefaultGraphModel.getDescendants(localGraphModel, arrayOfObject);
      localList.remove(paramObject);
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = localIterator.next();
        if ((localObject2 != null) && (!localGraphModel.isPort(localObject2)))
        {
          this.selection.remove(localObject2);
          this.cellStates.remove(localObject2);
        }
      }
      setSelectedChildCount(paramObject, -1);
      return paramSet.add(paramObject);
    }
    return false;
  }
  
  protected boolean deselect(Object paramObject)
  {
    if (isCellSelected(paramObject))
    {
      Object localObject = this.graph.getModel().getParent(paramObject);
      int i = 1;
      int j = -1;
      while ((localObject != null) && (j != 0))
      {
        int k = getSelectedChildCount(localObject);
        k += j;
        if ((k == 0) && (i != 0))
        {
          j = 0;
          k = -1;
          this.selection.add(localObject);
        }
        setSelectedChildCount(localObject, k);
        localObject = this.graph.getModel().getParent(localObject);
        i = 0;
      }
      this.cellStates.remove(paramObject);
      return this.selection.remove(paramObject);
    }
    return false;
  }
  
  public void addGraphSelectionListener(GraphSelectionListener paramGraphSelectionListener)
  {
    this.listenerList.add(GraphSelectionListener.class, paramGraphSelectionListener);
  }
  
  public void removeGraphSelectionListener(GraphSelectionListener paramGraphSelectionListener)
  {
    this.listenerList.remove(GraphSelectionListener.class, paramGraphSelectionListener);
  }
  
  protected void fireValueChanged(GraphSelectionEvent paramGraphSelectionEvent)
  {
    Object[] arrayOfObject = this.listenerList.getListenerList();
    for (int i = arrayOfObject.length - 2; i >= 0; i -= 2) {
      if (arrayOfObject[i] == GraphSelectionListener.class) {
        ((GraphSelectionListener)arrayOfObject[(i + 1)]).valueChanged(paramGraphSelectionEvent);
      }
    }
  }
  
  public EventListener[] getListeners(Class paramClass)
  {
    return this.listenerList.getListeners(paramClass);
  }
  
  public synchronized void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
  {
    if (this.changeSupport == null) {
      this.changeSupport = new SwingPropertyChangeSupport(this);
    }
    this.changeSupport.addPropertyChangeListener(paramPropertyChangeListener);
  }
  
  public synchronized void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
  {
    if (this.changeSupport == null) {
      return;
    }
    this.changeSupport.removePropertyChangeListener(paramPropertyChangeListener);
  }
  
  protected void notifyCellChange(Vector paramVector)
  {
    int i = paramVector.size();
    boolean[] arrayOfBoolean = new boolean[i];
    Object[] arrayOfObject = new Object[i];
    for (int j = 0; j < i; j++)
    {
      CellPlaceHolder localCellPlaceHolder = (CellPlaceHolder)paramVector.elementAt(j);
      arrayOfBoolean[j] = localCellPlaceHolder.isNew;
      arrayOfObject[j] = localCellPlaceHolder.cell;
    }
    GraphSelectionEvent localGraphSelectionEvent = new GraphSelectionEvent(this, arrayOfObject, arrayOfBoolean);
    fireValueChanged(localGraphSelectionEvent);
  }
  
  public Object clone()
    throws CloneNotSupportedException
  {
    DefaultGraphSelectionModel localDefaultGraphSelectionModel = (DefaultGraphSelectionModel)super.clone();
    localDefaultGraphSelectionModel.changeSupport = null;
    if (this.selection != null) {
      localDefaultGraphSelectionModel.selection = new LinkedHashSet(this.selection);
    }
    localDefaultGraphSelectionModel.listenerList = new EventListenerList();
    return localDefaultGraphSelectionModel;
  }
  
  protected class CellPlaceHolder
  {
    protected boolean isNew;
    protected Object cell;
    
    protected CellPlaceHolder(Object paramObject, boolean paramBoolean)
    {
      this.cell = paramObject;
      this.isNew = paramBoolean;
    }
    
    public Object getCell()
    {
      return this.cell;
    }
    
    public boolean isNew()
    {
      return this.isNew;
    }
    
    public void setCell(Object paramObject)
    {
      this.cell = paramObject;
    }
    
    public void setNew(boolean paramBoolean)
    {
      this.isNew = paramBoolean;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/DefaultGraphSelectionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */