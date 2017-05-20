package org.jgraph.graph;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import javax.swing.event.EventListenerList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelEvent.GraphModelChange;
import org.jgraph.event.GraphModelListener;

public class DefaultGraphModel
  extends UndoableEditSupport
  implements Serializable, GraphModel
{
  protected transient EventListenerList listenerList = new EventListenerList();
  protected transient Iterator emptyIterator = new EmptyIterator();
  protected List roots = null;
  protected boolean asksAllowsChildren = false;
  protected boolean removeEmptyGroups = true;
  protected AttributeMap attributes = null;
  protected transient int updateLevel = 0;
  protected transient Set transAddedCells = null;
  protected transient Set transRemovedCells = null;
  protected transient Map transEditAttrs = null;
  protected transient ConnectionSet transEditCS = null;
  protected transient ParentMap transEditPM = null;
  
  public DefaultGraphModel()
  {
    this(null, null);
  }
  
  public DefaultGraphModel(List paramList, AttributeMap paramAttributeMap)
  {
    if (paramList != null) {
      this.roots = paramList;
    } else {
      this.roots = new ArrayList();
    }
    if (paramAttributeMap != null) {
      this.attributes = paramAttributeMap;
    } else {
      this.attributes = new AttributeMap();
    }
  }
  
  public DefaultGraphModel(List paramList, AttributeMap paramAttributeMap, ConnectionSet paramConnectionSet)
  {
    this(paramList, paramAttributeMap);
    handleConnectionSet(paramConnectionSet);
  }
  
  public List getRoots()
  {
    return this.roots;
  }
  
  public int getRootCount()
  {
    return this.roots.size();
  }
  
  public Object getRootAt(int paramInt)
  {
    return this.roots.get(paramInt);
  }
  
  public int getIndexOfRoot(Object paramObject)
  {
    return this.roots.indexOf(paramObject);
  }
  
  public boolean contains(Object paramObject)
  {
    Object localObject = null;
    while ((localObject = getParent(paramObject)) != null) {
      paramObject = localObject;
    }
    return this.roots.contains(paramObject);
  }
  
  public AttributeMap getAttributes(Object paramObject)
  {
    if ((paramObject instanceof GraphCell)) {
      return ((GraphCell)paramObject).getAttributes();
    }
    if (paramObject == null) {
      return this.attributes;
    }
    return null;
  }
  
  public Object getValue(Object paramObject)
  {
    if ((paramObject instanceof DefaultMutableTreeNode)) {
      return ((DefaultMutableTreeNode)paramObject).getUserObject();
    }
    return null;
  }
  
  public Map getAttributes()
  {
    return getAttributes(null);
  }
  
  public Object getSource(Object paramObject)
  {
    if ((paramObject instanceof Edge)) {
      return ((Edge)paramObject).getSource();
    }
    return null;
  }
  
  public Object getTarget(Object paramObject)
  {
    if ((paramObject instanceof Edge)) {
      return ((Edge)paramObject).getTarget();
    }
    return null;
  }
  
  public boolean acceptsSource(Object paramObject1, Object paramObject2)
  {
    return true;
  }
  
  public boolean acceptsTarget(Object paramObject1, Object paramObject2)
  {
    return true;
  }
  
  public Iterator edges(Object paramObject)
  {
    if ((paramObject instanceof Port)) {
      return ((Port)paramObject).edges();
    }
    return this.emptyIterator;
  }
  
  public boolean isEdge(Object paramObject)
  {
    return paramObject instanceof Edge;
  }
  
  public boolean isPort(Object paramObject)
  {
    return paramObject instanceof Port;
  }
  
  public ConnectionSet getConnectionSet()
  {
    return ConnectionSet.create(this, getAll(this), false);
  }
  
  public Map cloneCells(Object[] paramArrayOfObject)
  {
    Hashtable localHashtable = new Hashtable();
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      localHashtable.put(paramArrayOfObject[i], cloneCell(paramArrayOfObject[i]));
    }
    Iterator localIterator = localHashtable.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = localEntry.getValue();
      Object localObject2 = localEntry.getKey();
      Object localObject3 = getParent(localObject2);
      if (localObject3 != null) {
        localObject3 = localHashtable.get(localObject3);
      }
      if (localObject3 != null) {
        ((DefaultMutableTreeNode)localObject3).add((DefaultMutableTreeNode)localObject1);
      }
      if ((localObject1 instanceof Port))
      {
        Port localPort = ((Port)localObject1).getAnchor();
        if (localPort != null) {
          ((Port)localObject1).setAnchor((Port)localHashtable.get(localPort));
        }
      }
    }
    return localHashtable;
  }
  
  protected void setParent(Object paramObject1, Object paramObject2)
  {
    if (((paramObject1 instanceof DefaultMutableTreeNode)) && ((paramObject2 instanceof DefaultMutableTreeNode)))
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject2;
      localDefaultMutableTreeNode.add((DefaultMutableTreeNode)paramObject1);
    }
  }
  
  protected Object cloneCell(Object paramObject)
  {
    if ((paramObject instanceof DefaultGraphCell))
    {
      DefaultGraphCell localDefaultGraphCell1 = (DefaultGraphCell)paramObject;
      DefaultGraphCell localDefaultGraphCell2 = (DefaultGraphCell)localDefaultGraphCell1.clone();
      localDefaultGraphCell2.setUserObject(cloneUserObject(localDefaultGraphCell1.getUserObject()));
      return localDefaultGraphCell2;
    }
    return paramObject;
  }
  
  protected Object cloneUserObject(Object paramObject)
  {
    return paramObject;
  }
  
  public Object getParent(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof TreeNode))) {
      return ((TreeNode)paramObject).getParent();
    }
    return null;
  }
  
  public int getIndexOfChild(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return -1;
    }
    return ((TreeNode)paramObject1).getIndex((TreeNode)paramObject2);
  }
  
  public Object getChild(Object paramObject, int paramInt)
  {
    if ((paramObject instanceof TreeNode)) {
      return ((TreeNode)paramObject).getChildAt(paramInt);
    }
    return null;
  }
  
  public int getChildCount(Object paramObject)
  {
    if ((paramObject instanceof TreeNode)) {
      return ((TreeNode)paramObject).getChildCount();
    }
    return 0;
  }
  
  public boolean isLeaf(Object paramObject)
  {
    if ((this.asksAllowsChildren) && ((paramObject instanceof TreeNode))) {
      return !((TreeNode)paramObject).getAllowsChildren();
    }
    return ((TreeNode)paramObject).isLeaf();
  }
  
  public void insert(Object[] paramArrayOfObject, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, UndoableEdit[] paramArrayOfUndoableEdit)
  {
    if (this.updateLevel > 0)
    {
      updateTransaction(paramArrayOfObject, null, paramMap, paramConnectionSet, paramParentMap);
    }
    else
    {
      GraphModelEdit localGraphModelEdit = createEdit(paramArrayOfObject, null, paramMap, paramConnectionSet, paramParentMap, paramArrayOfUndoableEdit);
      if (localGraphModelEdit != null)
      {
        localGraphModelEdit.execute();
        if (paramArrayOfUndoableEdit != null) {
          for (int i = 0; i < paramArrayOfUndoableEdit.length; i++) {
            if ((paramArrayOfUndoableEdit[i] instanceof GraphLayoutCache.GraphLayoutCacheEdit)) {
              ((GraphLayoutCache.GraphLayoutCacheEdit)paramArrayOfUndoableEdit[i]).execute();
            }
          }
        }
        postEdit(localGraphModelEdit);
      }
    }
  }
  
  public void remove(Object[] paramArrayOfObject)
  {
    if (this.updateLevel > 0)
    {
      updateTransaction(null, paramArrayOfObject, null, null, null);
    }
    else
    {
      GraphModelEdit localGraphModelEdit = createRemoveEdit(paramArrayOfObject);
      if (localGraphModelEdit != null)
      {
        localGraphModelEdit.execute();
        postEdit(localGraphModelEdit);
      }
    }
  }
  
  public void edit(Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, UndoableEdit[] paramArrayOfUndoableEdit)
  {
    edit(null, null, paramMap, paramConnectionSet, paramParentMap, paramArrayOfUndoableEdit);
  }
  
  public void edit(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, UndoableEdit[] paramArrayOfUndoableEdit)
  {
    if (this.updateLevel > 0)
    {
      updateTransaction(paramArrayOfObject1, paramArrayOfObject2, paramMap, paramConnectionSet, paramParentMap);
    }
    else if (((paramArrayOfObject1 == null) || (paramArrayOfObject1.length == 0)) && ((paramArrayOfObject2 == null) || (paramArrayOfObject2.length == 0)) && ((paramMap == null) || (paramMap.isEmpty())) && ((paramConnectionSet == null) || (paramConnectionSet.isEmpty())) && (paramParentMap == null) && (paramArrayOfUndoableEdit != null) && (paramArrayOfUndoableEdit.length == 1))
    {
      if ((paramArrayOfUndoableEdit[0] instanceof GraphLayoutCache.GraphLayoutCacheEdit)) {
        ((GraphLayoutCache.GraphLayoutCacheEdit)paramArrayOfUndoableEdit[0]).execute();
      }
      postEdit(paramArrayOfUndoableEdit[0]);
    }
    else
    {
      GraphModelEdit localGraphModelEdit = createEdit(paramArrayOfObject1, paramArrayOfObject2, paramMap, paramConnectionSet, paramParentMap, paramArrayOfUndoableEdit);
      if (localGraphModelEdit != null)
      {
        localGraphModelEdit.execute();
        if (paramArrayOfUndoableEdit != null) {
          for (int i = 0; i < paramArrayOfUndoableEdit.length; i++) {
            if ((paramArrayOfUndoableEdit[i] instanceof GraphLayoutCache.GraphLayoutCacheEdit)) {
              ((GraphLayoutCache.GraphLayoutCacheEdit)paramArrayOfUndoableEdit[i]).execute();
            }
          }
        }
        postEdit(localGraphModelEdit);
      }
    }
  }
  
  public synchronized void execute(ExecutableChange paramExecutableChange) {}
  
  public int getUpdateLevel()
  {
    return this.updateLevel;
  }
  
  public void beginUpdate()
  {
    this.updateLevel += 1;
    if (this.updateLevel == 1)
    {
      this.transEditAttrs = new Hashtable();
      this.transEditCS = new ConnectionSet();
      this.transEditPM = new ParentMap();
      this.transAddedCells = new HashSet();
      this.transRemovedCells = new HashSet();
    }
  }
  
  public void endUpdate()
  {
    this.updateLevel -= 1;
    if (this.updateLevel == 0)
    {
      GraphModelEdit localGraphModelEdit = createEdit(this.transAddedCells.toArray(), this.transRemovedCells.toArray(), this.transEditAttrs, this.transEditCS, this.transEditPM, null);
      if (localGraphModelEdit != null)
      {
        localGraphModelEdit.execute();
        postEdit(localGraphModelEdit);
      }
    }
  }
  
  protected void updateTransaction(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap)
  {
    int i;
    if ((paramArrayOfObject1 != null) && (paramArrayOfObject1.length > 0)) {
      for (i = 0; i < paramArrayOfObject1.length; i++) {
        if (this.transRemovedCells.contains(paramArrayOfObject1[i])) {
          this.transRemovedCells.remove(paramArrayOfObject1[i]);
        } else {
          this.transAddedCells.add(paramArrayOfObject1[i]);
        }
      }
    }
    if ((paramArrayOfObject2 != null) && (paramArrayOfObject2.length > 0)) {
      for (i = 0; i < paramArrayOfObject2.length; i++) {
        if (this.transAddedCells.contains(paramArrayOfObject2[i])) {
          this.transAddedCells.remove(paramArrayOfObject2[i]);
        } else {
          this.transRemovedCells.add(paramArrayOfObject2[i]);
        }
      }
    }
    if (paramMap != null) {
      GraphConstants.merge(paramMap, this.transEditAttrs);
    }
    Object localObject1;
    Object localObject2;
    if (paramConnectionSet != null)
    {
      localObject1 = this.transEditCS.getConnections();
      ((Set)localObject1).addAll(paramConnectionSet.getConnections());
      this.transEditCS.setConnections((Set)localObject1);
      localObject2 = this.transEditCS.getEdges();
      ((Set)localObject2).addAll(paramConnectionSet.getEdges());
      this.transEditCS.setEdges((Set)localObject2);
    }
    if (paramParentMap != null)
    {
      localObject1 = paramParentMap.entries();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ParentMap.Entry)((Iterator)localObject1).next();
        this.transEditPM.addEntry(((ParentMap.Entry)localObject2).getChild(), ((ParentMap.Entry)localObject2).getParent());
      }
    }
  }
  
  public void toBack(Object[] paramArrayOfObject)
  {
    GraphModelLayerEdit localGraphModelLayerEdit = createLayerEdit(paramArrayOfObject, -2);
    if (localGraphModelLayerEdit != null)
    {
      localGraphModelLayerEdit.execute();
      postEdit(localGraphModelLayerEdit);
    }
  }
  
  public void toFront(Object[] paramArrayOfObject)
  {
    GraphModelLayerEdit localGraphModelLayerEdit = createLayerEdit(paramArrayOfObject, -1);
    if (localGraphModelLayerEdit != null)
    {
      localGraphModelLayerEdit.execute();
      postEdit(localGraphModelLayerEdit);
    }
  }
  
  protected GraphModelLayerEdit createLayerEdit(Object[] paramArrayOfObject, int paramInt)
  {
    return new GraphModelLayerEdit(paramArrayOfObject, paramInt);
  }
  
  protected GraphModelEdit createRemoveEdit(Object[] paramArrayOfObject)
  {
    ConnectionSet localConnectionSet = ConnectionSet.create(this, paramArrayOfObject, true);
    ParentMap localParentMap = ParentMap.create(this, paramArrayOfObject, true, false);
    GraphModelEdit localGraphModelEdit = createEdit(null, paramArrayOfObject, null, localConnectionSet, localParentMap, null);
    if (localGraphModelEdit != null) {
      localGraphModelEdit.end();
    }
    return localGraphModelEdit;
  }
  
  protected GraphModelEdit createEdit(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, UndoableEdit[] paramArrayOfUndoableEdit)
  {
    GraphModelEdit localGraphModelEdit = new GraphModelEdit(paramArrayOfObject1, paramArrayOfObject2, paramMap, paramConnectionSet, paramParentMap);
    if (localGraphModelEdit != null)
    {
      if (paramArrayOfUndoableEdit != null) {
        for (int i = 0; i < paramArrayOfUndoableEdit.length; i++) {
          localGraphModelEdit.addEdit(paramArrayOfUndoableEdit[i]);
        }
      }
      localGraphModelEdit.end();
    }
    return localGraphModelEdit;
  }
  
  protected Object[] handleInsert(Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = null;
    if (paramArrayOfObject != null)
    {
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if (getParent(paramArrayOfObject[i]) == null) {
          this.roots.add(paramArrayOfObject[i]);
        }
      }
      arrayOfObject = getDescendants(this, paramArrayOfObject).toArray();
    }
    return arrayOfObject;
  }
  
  protected Object[] handleRemove(Object[] paramArrayOfObject)
  {
    HashSet localHashSet1 = new HashSet();
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      HashSet localHashSet2 = new HashSet(this.roots);
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if ((getParent(paramArrayOfObject[i]) == null) && (localHashSet2.contains(paramArrayOfObject[i]))) {
          localHashSet1.add(paramArrayOfObject[i]);
        }
      }
      if (localHashSet1.size() > 0)
      {
        i = this.roots.size() - localHashSet1.size();
        if (i < 8) {
          i = 8;
        }
        ArrayList localArrayList = new ArrayList(i);
        Iterator localIterator = this.roots.iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          if (!localHashSet1.contains(localObject)) {
            localArrayList.add(localObject);
          }
        }
        this.roots = localArrayList;
      }
    }
    return localHashSet1.toArray();
  }
  
  protected ParentMap handleParentMap(ParentMap paramParentMap)
  {
    if (paramParentMap != null)
    {
      ParentMap localParentMap = new ParentMap();
      HashSet localHashSet1 = null;
      HashSet localHashSet2 = null;
      Iterator localIterator = paramParentMap.entries();
      Object localObject1;
      Object localObject2;
      while (localIterator.hasNext())
      {
        ParentMap.Entry localEntry = (ParentMap.Entry)localIterator.next();
        localObject1 = localEntry.getChild();
        localObject2 = localEntry.getParent();
        localParentMap.addEntry(localObject1, getParent(localObject1));
        if (localObject2 == null)
        {
          if ((localObject1 instanceof MutableTreeNode)) {
            ((MutableTreeNode)localObject1).removeFromParent();
          }
        }
        else if (((localObject2 instanceof DefaultMutableTreeNode)) && ((localObject1 instanceof MutableTreeNode))) {
          ((DefaultMutableTreeNode)localObject2).add((MutableTreeNode)localObject1);
        }
        if (localHashSet1 == null) {
          localHashSet1 = new HashSet(this.roots);
        }
        boolean bool = localHashSet1.contains(localObject1);
        if ((localObject2 == null) && (!bool))
        {
          localHashSet1.add(localObject1);
          this.roots.add(localObject1);
        }
        else if ((localObject2 != null) && (bool))
        {
          if (localHashSet2 == null) {
            localHashSet2 = new HashSet();
          }
          localHashSet1.remove(localObject1);
          localHashSet2.add(localObject1);
        }
      }
      if ((localHashSet2 != null) && (localHashSet2.size() > 0))
      {
        int i = this.roots.size() - localHashSet2.size();
        if (i < 8) {
          i = 8;
        }
        localObject1 = new ArrayList(i);
        localObject2 = this.roots.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Object localObject3 = ((Iterator)localObject2).next();
          if (!localHashSet2.contains(localObject3)) {
            ((List)localObject1).add(localObject3);
          }
        }
        this.roots = ((List)localObject1);
      }
      return localParentMap;
    }
    return null;
  }
  
  protected Map handleAttributes(Map paramMap)
  {
    if (paramMap != null)
    {
      Hashtable localHashtable = new Hashtable(paramMap.size());
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Object localObject1 = localEntry.getKey();
        Map localMap = (Map)localEntry.getValue();
        Object localObject2 = null;
        AttributeMap localAttributeMap = getAttributes(localObject1);
        if (localAttributeMap != null)
        {
          localObject2 = localAttributeMap.applyMap(localMap);
          localHashtable.put(localObject1, localObject2);
        }
        else
        {
          localObject2 = new Hashtable(2);
        }
        Object localObject3 = localMap.get("value");
        Object localObject4;
        if (localObject3 != null)
        {
          localObject4 = valueForCellChanged(localObject1, localObject3);
          if (localObject4 != null) {
            GraphConstants.setValue((Map)localObject2, localObject4);
          } else {
            GraphConstants.setRemoveAttributes((Map)localObject2, new Object[] { "value" });
          }
        }
        else
        {
          localObject4 = GraphConstants.getRemoveAttributes(localMap);
          if ((localObject4 != null) && (localObject4.length > 0)) {
            for (int i = 0; i < localObject4.length; i++) {
              if (localObject4[i] == "value")
              {
                Object localObject5 = valueForCellChanged(localObject1, null);
                if (localObject5 != null) {
                  GraphConstants.setValue((Map)localObject2, localObject5);
                }
              }
            }
          }
        }
      }
      return localHashtable;
    }
    return null;
  }
  
  public Object valueForCellChanged(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 instanceof DefaultMutableTreeNode))
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject1;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      localDefaultMutableTreeNode.setUserObject(paramObject2);
      return localObject;
    }
    return null;
  }
  
  protected ConnectionSet handleConnectionSet(ConnectionSet paramConnectionSet)
  {
    if (paramConnectionSet != null)
    {
      ConnectionSet localConnectionSet = new ConnectionSet();
      Iterator localIterator = paramConnectionSet.connections();
      while (localIterator.hasNext())
      {
        ConnectionSet.Connection localConnection = (ConnectionSet.Connection)localIterator.next();
        Object localObject = localConnection.getEdge();
        if (localConnection.isSource()) {
          localConnectionSet.connect(localObject, getSource(localObject), true);
        } else {
          localConnectionSet.connect(localObject, getTarget(localObject), false);
        }
        handleConnection(localConnection, false);
      }
      localIterator = paramConnectionSet.connections();
      while (localIterator.hasNext()) {
        handleConnection((ConnectionSet.Connection)localIterator.next(), true);
      }
      return localConnectionSet;
    }
    return null;
  }
  
  protected void handleConnection(ConnectionSet.Connection paramConnection, boolean paramBoolean)
  {
    Object localObject1 = paramConnection.getEdge();
    Object localObject2 = paramConnection.isSource() ? getSource(localObject1) : paramBoolean ? paramConnection.getPort() : getTarget(localObject1);
    connect(localObject1, localObject2, paramConnection.isSource(), paramBoolean);
  }
  
  protected void connect(Object paramObject1, Object paramObject2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramObject2 instanceof Port)) {
      if (paramBoolean2) {
        ((Port)paramObject2).addEdge(paramObject1);
      } else if (paramBoolean1 ? getTarget(paramObject1) != paramObject2 : getSource(paramObject1) != paramObject2) {
        ((Port)paramObject2).removeEdge(paramObject1);
      }
    }
    if (!paramBoolean2) {
      paramObject2 = null;
    }
    if ((paramObject1 instanceof Edge)) {
      if (paramBoolean1) {
        ((Edge)paramObject1).setSource(paramObject2);
      } else {
        ((Edge)paramObject1).setTarget(paramObject2);
      }
    }
  }
  
  public void addGraphModelListener(GraphModelListener paramGraphModelListener)
  {
    this.listenerList.add(GraphModelListener.class, paramGraphModelListener);
  }
  
  public void removeGraphModelListener(GraphModelListener paramGraphModelListener)
  {
    this.listenerList.remove(GraphModelListener.class, paramGraphModelListener);
  }
  
  public void cellsChanged(final Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null) {
      fireGraphChanged(this, new GraphModelEvent.GraphModelChange()
      {
        public Object[] getInserted()
        {
          return null;
        }
        
        public Object[] getRemoved()
        {
          return null;
        }
        
        public Map getPreviousAttributes()
        {
          return null;
        }
        
        public ConnectionSet getConnectionSet()
        {
          return null;
        }
        
        public ConnectionSet getPreviousConnectionSet()
        {
          return null;
        }
        
        public ParentMap getParentMap()
        {
          return null;
        }
        
        public ParentMap getPreviousParentMap()
        {
          return null;
        }
        
        public void putViews(GraphLayoutCache paramAnonymousGraphLayoutCache, CellView[] paramAnonymousArrayOfCellView) {}
        
        public CellView[] getViews(GraphLayoutCache paramAnonymousGraphLayoutCache)
        {
          return null;
        }
        
        public Object getSource()
        {
          return this;
        }
        
        public Object[] getChanged()
        {
          return paramArrayOfObject;
        }
        
        public Map getAttributes()
        {
          return null;
        }
        
        public Object[] getContext()
        {
          return null;
        }
        
        public Rectangle2D getDirtyRegion()
        {
          return null;
        }
        
        public void setDirtyRegion(Rectangle2D paramAnonymousRectangle2D) {}
      });
    }
  }
  
  protected void fireGraphChanged(Object paramObject, GraphModelEvent.GraphModelChange paramGraphModelChange)
  {
    Object[] arrayOfObject = this.listenerList.getListenerList();
    GraphModelEvent localGraphModelEvent = null;
    for (int i = arrayOfObject.length - 2; i >= 0; i -= 2) {
      if (arrayOfObject[i] == GraphModelListener.class)
      {
        if (localGraphModelEvent == null) {
          localGraphModelEvent = new GraphModelEvent(paramObject, paramGraphModelChange);
        }
        ((GraphModelListener)arrayOfObject[(i + 1)]).graphChanged(localGraphModelEvent);
      }
    }
  }
  
  public GraphModelListener[] getGraphModelListeners()
  {
    return (GraphModelListener[])this.listenerList.getListeners(GraphModelListener.class);
  }
  
  public static Object cloneCell(GraphModel paramGraphModel, Object paramObject)
  {
    Map localMap = paramGraphModel.cloneCells(getDescendants(paramGraphModel, new Object[] { paramObject }).toArray());
    return localMap.get(paramObject);
  }
  
  public static Object[] cloneCell(GraphModel paramGraphModel, Object[] paramArrayOfObject)
  {
    Map localMap = paramGraphModel.cloneCells(getDescendants(paramGraphModel, paramArrayOfObject).toArray());
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      paramArrayOfObject[i] = localMap.get(paramArrayOfObject[i]);
    }
    return paramArrayOfObject;
  }
  
  public static void setSourcePort(GraphModel paramGraphModel, Object paramObject1, Object paramObject2)
  {
    paramGraphModel.edit(null, new ConnectionSet(paramObject1, paramObject2, true), null, null);
  }
  
  public static void setTargetPort(GraphModel paramGraphModel, Object paramObject1, Object paramObject2)
  {
    paramGraphModel.edit(null, new ConnectionSet(paramObject1, paramObject2, false), null, null);
  }
  
  public static Object getSourceVertex(GraphModel paramGraphModel, Object paramObject)
  {
    if (paramGraphModel != null) {
      return paramGraphModel.getParent(paramGraphModel.getSource(paramObject));
    }
    return null;
  }
  
  public static Object getTargetVertex(GraphModel paramGraphModel, Object paramObject)
  {
    if (paramGraphModel != null) {
      return paramGraphModel.getParent(paramGraphModel.getTarget(paramObject));
    }
    return null;
  }
  
  /**
   * @deprecated
   */
  public static Object getUserObject(Object paramObject)
  {
    if ((paramObject instanceof DefaultMutableTreeNode)) {
      return ((DefaultMutableTreeNode)paramObject).getUserObject();
    }
    return null;
  }
  
  public static boolean isGroup(GraphModel paramGraphModel, Object paramObject)
  {
    for (int i = 0; i < paramGraphModel.getChildCount(paramObject); i++) {
      if (!paramGraphModel.isPort(paramGraphModel.getChild(paramObject, i))) {
        return true;
      }
    }
    return false;
  }
  
  public static Object[] getAll(GraphModel paramGraphModel)
  {
    return getDescendants(paramGraphModel, getRoots(paramGraphModel)).toArray();
  }
  
  public static Object[] getRoots(GraphModel paramGraphModel)
  {
    Object[] arrayOfObject = null;
    if (paramGraphModel != null) {
      if ((paramGraphModel instanceof DefaultGraphModel))
      {
        arrayOfObject = ((DefaultGraphModel)paramGraphModel).getRoots().toArray();
      }
      else
      {
        arrayOfObject = new Object[paramGraphModel.getRootCount()];
        for (int i = 0; i < arrayOfObject.length; i++) {
          arrayOfObject[i] = paramGraphModel.getRootAt(i);
        }
      }
    }
    return arrayOfObject;
  }
  
  public static Collection getRootsAsCollection(GraphModel paramGraphModel)
  {
    Object localObject = null;
    if (paramGraphModel != null) {
      if ((paramGraphModel instanceof DefaultGraphModel))
      {
        localObject = ((DefaultGraphModel)paramGraphModel).getRoots();
      }
      else
      {
        localObject = new LinkedHashSet(paramGraphModel.getRootCount());
        for (int i = 0; i < ((Collection)localObject).size(); i++) {
          ((Collection)localObject).add(paramGraphModel.getRootAt(i));
        }
      }
    }
    return (Collection)localObject;
  }
  
  public static Object[] getRoots(GraphModel paramGraphModel, Object[] paramArrayOfObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramArrayOfObject != null) {
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if (paramGraphModel.getParent(paramArrayOfObject[i]) == null) {
          localArrayList.add(paramArrayOfObject[i]);
        }
      }
    }
    return localArrayList.toArray();
  }
  
  public static Object[] getTopmostCells(GraphModel paramGraphModel, Object[] paramArrayOfObject)
  {
    HashSet localHashSet = new HashSet();
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      localHashSet.add(paramArrayOfObject[i]);
    }
    ArrayList localArrayList = new ArrayList();
    for (int j = 0; j < paramArrayOfObject.length; j++) {
      if (!hasAncestorIn(paramGraphModel, localHashSet, paramArrayOfObject[j])) {
        localArrayList.add(paramArrayOfObject[j]);
      }
    }
    return localArrayList.toArray();
  }
  
  public static boolean hasAncestorIn(GraphModel paramGraphModel, Set paramSet, Object paramObject)
  {
    for (Object localObject = paramGraphModel.getParent(paramObject); localObject != null; localObject = paramGraphModel.getParent(localObject)) {
      if (paramSet.contains(localObject)) {
        return true;
      }
    }
    return false;
  }
  
  public static List getDescendants(GraphModel paramGraphModel, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      Stack localStack = new Stack();
      for (int i = paramArrayOfObject.length - 1; i >= 0; i--) {
        localStack.add(paramArrayOfObject[i]);
      }
      LinkedList localLinkedList = new LinkedList();
      while (!localStack.isEmpty())
      {
        Object localObject = localStack.pop();
        for (int j = paramGraphModel.getChildCount(localObject) - 1; j >= 0; j--) {
          localStack.add(paramGraphModel.getChild(localObject, j));
        }
        if (localObject != null) {
          localLinkedList.add(localObject);
        }
      }
      return localLinkedList;
    }
    return null;
  }
  
  public static Object[] order(GraphModel paramGraphModel, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      HashSet localHashSet = new HashSet();
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        localHashSet.add(paramArrayOfObject[i]);
      }
      Stack localStack = new Stack();
      for (int j = paramGraphModel.getRootCount() - 1; j >= 0; j--) {
        localStack.add(paramGraphModel.getRootAt(j));
      }
      LinkedList localLinkedList = new LinkedList();
      while (!localStack.isEmpty())
      {
        Object localObject = localStack.pop();
        for (int k = paramGraphModel.getChildCount(localObject) - 1; k >= 0; k--) {
          localStack.add(paramGraphModel.getChild(localObject, k));
        }
        if (localHashSet.remove(localObject)) {
          localLinkedList.add(localObject);
        }
      }
      return localLinkedList.toArray();
    }
    return null;
  }
  
  public static Set getEdges(GraphModel paramGraphModel, Object[] paramArrayOfObject)
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    if (paramArrayOfObject != null)
    {
      int i = (int)(paramArrayOfObject.length * 1.33D) + 1;
      HashSet localHashSet = new HashSet(i, 0.75F);
      for (int j = 0; j < paramArrayOfObject.length; j++) {
        localHashSet.add(paramArrayOfObject[j]);
      }
      List localList = getDescendants(paramGraphModel, paramArrayOfObject);
      Iterator localIterator1 = localList.iterator();
      while (localIterator1.hasNext()) {
        localHashSet.add(localIterator1.next());
      }
      if (localHashSet != null)
      {
        Iterator localIterator2 = localHashSet.iterator();
        while (localIterator2.hasNext())
        {
          Object localObject = localIterator2.next();
          Iterator localIterator3 = paramGraphModel.edges(localObject);
          while (localIterator3.hasNext()) {
            localLinkedHashSet.add(localIterator3.next());
          }
        }
        for (int k = 0; k < paramArrayOfObject.length; k++) {
          localLinkedHashSet.remove(paramArrayOfObject[k]);
        }
      }
    }
    return localLinkedHashSet;
  }
  
  public static Object getOpposite(GraphModel paramGraphModel, Object paramObject1, Object paramObject2)
  {
    boolean bool = paramGraphModel.isPort(paramObject2);
    Object localObject1 = bool ? paramGraphModel.getSource(paramObject1) : getSourceVertex(paramGraphModel, paramObject1);
    Object localObject2 = bool ? paramGraphModel.getTarget(paramObject1) : getTargetVertex(paramGraphModel, paramObject1);
    if (paramObject2 == localObject1) {
      return localObject2;
    }
    if (paramObject2 == localObject2) {
      return localObject1;
    }
    List localList = getDescendants(paramGraphModel, new Object[] { paramObject2 });
    if (localList.contains(paramGraphModel.getSource(paramObject1))) {
      return localObject2;
    }
    return localObject1;
  }
  
  public static boolean containsEdgeBetween(GraphModel paramGraphModel, Object paramObject1, Object paramObject2)
  {
    Object[] arrayOfObject = getEdgesBetween(paramGraphModel, paramObject1, paramObject2, false);
    return (arrayOfObject != null) && (arrayOfObject.length > 0);
  }
  
  public static Object[] getEdgesBetween(GraphModel paramGraphModel, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    boolean bool1 = paramGraphModel.isPort(paramObject1);
    boolean bool2 = paramGraphModel.isPort(paramObject2);
    ArrayList localArrayList = new ArrayList();
    Set localSet = getEdges(paramGraphModel, new Object[] { paramObject1 });
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = bool1 ? paramGraphModel.getSource(localObject1) : getSourceVertex(paramGraphModel, localObject1);
      Object localObject3 = bool2 ? paramGraphModel.getTarget(localObject1) : getTargetVertex(paramGraphModel, localObject1);
      if (((localObject2 == paramObject1) && (localObject3 == paramObject2)) || ((!paramBoolean) && (localObject2 == paramObject2) && (localObject3 == paramObject1))) {
        localArrayList.add(localObject1);
      }
    }
    return localArrayList.toArray();
  }
  
  public static Object[] getOutgoingEdges(GraphModel paramGraphModel, Object paramObject)
  {
    return getEdges(paramGraphModel, paramObject, false);
  }
  
  public static Object[] getIncomingEdges(GraphModel paramGraphModel, Object paramObject)
  {
    return getEdges(paramGraphModel, paramObject, true);
  }
  
  public static Object[] getEdges(GraphModel paramGraphModel, Object paramObject, boolean paramBoolean)
  {
    Set localSet = getEdges(paramGraphModel, new Object[] { paramObject });
    ArrayList localArrayList = new ArrayList(localSet.size());
    Iterator localIterator = localSet.iterator();
    List localList = getDescendants(paramGraphModel, new Object[] { paramObject });
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (paramBoolean)
      {
        if (localList.contains(paramGraphModel.getTarget(localObject))) {
          localArrayList.add(localObject);
        }
      }
      else if (localList.contains(paramGraphModel.getSource(localObject))) {
        localArrayList.add(localObject);
      }
    }
    return localArrayList.toArray();
  }
  
  public static boolean isVertex(GraphModel paramGraphModel, Object paramObject)
  {
    return (paramObject != null) && (!paramGraphModel.isEdge(paramObject)) && (!paramGraphModel.isPort(paramObject));
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.listenerList = new EventListenerList();
    this.emptyIterator = new EmptyIterator();
  }
  
  public boolean isRemoveEmptyGroups()
  {
    return this.removeEmptyGroups;
  }
  
  public void setRemoveEmptyGroups(boolean paramBoolean)
  {
    this.removeEmptyGroups = paramBoolean;
  }
  
  public static class EmptyIterator
    implements Iterator, Serializable
  {
    public boolean hasNext()
    {
      return false;
    }
    
    public Object next()
    {
      return null;
    }
    
    public void remove() {}
  }
  
  public class GraphModelLayerEdit
    extends AbstractUndoableEdit
    implements GraphModelEvent.GraphModelChange
  {
    public static final int FRONT = -1;
    public static final int BACK = -2;
    protected Object changeSource;
    protected transient Object[] cells;
    protected transient int[] next;
    protected transient int[] prev;
    protected int layer;
    protected Object[] changed;
    
    public GraphModelLayerEdit(Object[] paramArrayOfObject, int paramInt)
    {
      this.cells = paramArrayOfObject;
      this.layer = paramInt;
      this.next = new int[paramArrayOfObject.length];
      this.prev = new int[paramArrayOfObject.length];
      updateNext();
      HashSet localHashSet = new HashSet();
      for (int i = 0; i < paramArrayOfObject.length; i++)
      {
        Object localObject = DefaultGraphModel.this.getParent(paramArrayOfObject[i]);
        if (localObject == null) {
          localObject = paramArrayOfObject[i];
        }
        localHashSet.add(localObject);
      }
      this.changed = localHashSet.toArray();
    }
    
    protected void updateNext()
    {
      for (int i = 0; i < this.next.length; i++) {
        this.next[i] = this.layer;
      }
    }
    
    public Object getSource()
    {
      return DefaultGraphModel.this;
    }
    
    public Object[] getChanged()
    {
      return this.changed;
    }
    
    public Object[] getInserted()
    {
      return null;
    }
    
    public Object[] getRemoved()
    {
      return null;
    }
    
    public Object[] getContext()
    {
      return null;
    }
    
    public Map getAttributes()
    {
      return null;
    }
    
    public Map getPreviousAttributes()
    {
      return null;
    }
    
    public ConnectionSet getConnectionSet()
    {
      return null;
    }
    
    public ConnectionSet getPreviousConnectionSet()
    {
      return null;
    }
    
    public ParentMap getParentMap()
    {
      return null;
    }
    
    public ParentMap getPreviousParentMap()
    {
      return null;
    }
    
    public Rectangle2D getDirtyRegion()
    {
      return null;
    }
    
    public void setDirtyRegion(Rectangle2D paramRectangle2D) {}
    
    public void addImplicitEdit(UndoableEdit paramUndoableEdit) {}
    
    public CellView[] getViews(GraphLayoutCache paramGraphLayoutCache)
    {
      return null;
    }
    
    public void putViews(GraphLayoutCache paramGraphLayoutCache, CellView[] paramArrayOfCellView) {}
    
    public void redo()
      throws CannotRedoException
    {
      super.redo();
      updateNext();
      execute();
    }
    
    public void undo()
      throws CannotUndoException
    {
      super.undo();
      execute();
    }
    
    public void execute()
    {
      for (int i = 0; i < this.cells.length; i++)
      {
        List localList = getParentList(this.cells[i]);
        if (localList != null)
        {
          this.prev[i] = localList.indexOf(this.cells[i]);
          if (this.prev[i] >= 0)
          {
            localList.remove(this.prev[i]);
            int j = this.next[i];
            if (j == -1) {
              j = localList.size();
            } else if (j == -2) {
              j = 0;
            }
            localList.add(j, this.cells[i]);
            this.next[i] = this.prev[i];
          }
        }
      }
      updateListeners();
    }
    
    protected void updateListeners()
    {
      DefaultGraphModel.this.fireGraphChanged(DefaultGraphModel.this, this);
    }
    
    protected List getParentList(Object paramObject)
    {
      List localList = null;
      if ((paramObject instanceof DefaultMutableTreeNode))
      {
        TreeNode localTreeNode = ((DefaultMutableTreeNode)paramObject).getParent();
        if ((localTreeNode instanceof DefaultGraphCell)) {
          localList = ((DefaultGraphCell)localTreeNode).getChildren();
        } else {
          localList = DefaultGraphModel.this.roots;
        }
      }
      return localList;
    }
  }
  
  public class GraphModelEdit
    extends CompoundEdit
    implements GraphModelEvent.GraphModelChange
  {
    protected Object[] insert;
    protected Object[] changed;
    protected Object[] remove;
    protected Object[] context;
    protected Object[] inserted;
    protected Object[] removed;
    protected Map attributes;
    protected Map previousAttributes;
    protected ParentMap parentMap;
    protected ParentMap previousParentMap;
    protected Rectangle2D dirtyRegion = null;
    protected ConnectionSet connectionSet;
    protected ConnectionSet previousConnectionSet;
    protected Map cellViews = new Hashtable();
    
    public GraphModelEdit(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap)
    {
      this.insert = paramArrayOfObject1;
      this.remove = paramArrayOfObject2;
      this.connectionSet = paramConnectionSet;
      this.attributes = paramMap;
      this.parentMap = paramParentMap;
      this.previousAttributes = null;
      this.previousConnectionSet = paramConnectionSet;
      this.previousParentMap = paramParentMap;
      if (paramParentMap != null)
      {
        Hashtable localHashtable = new Hashtable();
        Iterator localIterator = paramParentMap.entries();
        while (localIterator.hasNext())
        {
          ParentMap.Entry localEntry = (ParentMap.Entry)localIterator.next();
          Object localObject1 = localEntry.getChild();
          if (!DefaultGraphModel.this.isPort(localObject1))
          {
            Object localObject2 = DefaultGraphModel.this.getParent(localObject1);
            Object localObject3 = localEntry.getParent();
            if (localObject2 != localObject3)
            {
              changeChildCount(localHashtable, localObject2, -1);
              changeChildCount(localHashtable, localObject3, 1);
            }
          }
        }
        handleEmptyGroups(filterParents(localHashtable, 0));
      }
    }
    
    public Object[] filterParents(Map paramMap, int paramInt)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (((localEntry.getValue() instanceof Integer)) && (((Integer)localEntry.getValue()).intValue() == paramInt)) {
          localArrayList.add(localEntry.getKey());
        }
      }
      return localArrayList.toArray();
    }
    
    protected void changeChildCount(Map paramMap, Object paramObject, int paramInt)
    {
      if (paramObject != null)
      {
        Integer localInteger = (Integer)paramMap.get(paramObject);
        if (localInteger == null) {
          localInteger = new Integer(DefaultGraphModel.this.getChildCount(paramObject));
        }
        int i = localInteger.intValue() + paramInt;
        paramMap.put(paramObject, new Integer(i));
      }
    }
    
    protected void handleEmptyGroups(Object[] paramArrayOfObject)
    {
      if ((DefaultGraphModel.this.removeEmptyGroups) && (paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
      {
        if (this.remove == null) {
          this.remove = new Object[0];
        }
        Object[] arrayOfObject = new Object[this.remove.length + paramArrayOfObject.length];
        System.arraycopy(this.remove, 0, arrayOfObject, 0, this.remove.length);
        System.arraycopy(paramArrayOfObject, 0, arrayOfObject, this.remove.length, paramArrayOfObject.length);
        this.remove = arrayOfObject;
      }
    }
    
    public boolean isSignificant()
    {
      return true;
    }
    
    public Object getSource()
    {
      return DefaultGraphModel.this;
    }
    
    public Object[] getChanged()
    {
      return this.changed;
    }
    
    public Object[] getContext()
    {
      return this.context;
    }
    
    public Object[] getInserted()
    {
      return this.inserted;
    }
    
    public Object[] getRemoved()
    {
      return this.removed;
    }
    
    public Map getPreviousAttributes()
    {
      return this.previousAttributes;
    }
    
    public Map getAttributes()
    {
      return this.attributes;
    }
    
    public ConnectionSet getConnectionSet()
    {
      return this.connectionSet;
    }
    
    public ConnectionSet getPreviousConnectionSet()
    {
      return this.previousConnectionSet;
    }
    
    public ParentMap getParentMap()
    {
      return this.parentMap;
    }
    
    public ParentMap getPreviousParentMap()
    {
      return this.previousParentMap;
    }
    
    public Rectangle2D getDirtyRegion()
    {
      return this.dirtyRegion;
    }
    
    public void setDirtyRegion(Rectangle2D paramRectangle2D)
    {
      this.dirtyRegion = paramRectangle2D;
    }
    
    public void redo()
      throws CannotRedoException
    {
      super.redo();
      execute();
    }
    
    public void undo()
      throws CannotUndoException
    {
      super.undo();
      execute();
    }
    
    public void execute()
    {
      HashSet localHashSet = new HashSet();
      if (this.attributes != null) {
        localHashSet.addAll(this.attributes.keySet());
      }
      if (this.parentMap != null) {
        localHashSet.addAll(this.parentMap.getChangedNodes());
      }
      if (this.connectionSet != null) {
        localHashSet.addAll(this.connectionSet.getChangedEdges());
      }
      if (this.remove != null) {
        for (int i = 0; i < this.remove.length; i++) {
          localHashSet.remove(this.remove[i]);
        }
      }
      this.changed = localHashSet.toArray();
      Set localSet = DefaultGraphModel.getEdges(DefaultGraphModel.this, this.changed);
      this.context = localSet.toArray();
      this.inserted = this.insert;
      this.removed = this.remove;
      this.remove = DefaultGraphModel.this.handleInsert(this.inserted);
      this.previousParentMap = this.parentMap;
      this.parentMap = DefaultGraphModel.this.handleParentMap(this.parentMap);
      if (this.parentMap != null) {
        localHashSet.addAll(this.parentMap.getChangedNodes());
      }
      this.previousConnectionSet = this.connectionSet;
      this.connectionSet = DefaultGraphModel.this.handleConnectionSet(this.connectionSet);
      this.insert = DefaultGraphModel.this.handleRemove(this.removed);
      this.previousAttributes = this.attributes;
      this.attributes = DefaultGraphModel.this.handleAttributes(this.attributes);
      this.changed = localHashSet.toArray();
      DefaultGraphModel.this.fireGraphChanged(DefaultGraphModel.this, this);
    }
    
    public void putViews(GraphLayoutCache paramGraphLayoutCache, CellView[] paramArrayOfCellView)
    {
      if ((paramGraphLayoutCache != null) && (paramArrayOfCellView != null)) {
        this.cellViews.put(paramGraphLayoutCache, paramArrayOfCellView);
      }
    }
    
    public CellView[] getViews(GraphLayoutCache paramGraphLayoutCache)
    {
      return (CellView[])this.cellViews.get(paramGraphLayoutCache);
    }
    
    public String toString()
    {
      String str = new String();
      int i;
      if (this.inserted != null)
      {
        str = str + "Inserted:\n";
        for (i = 0; i < this.inserted.length; i++) {
          str = str + "  " + this.inserted[i] + "\n";
        }
      }
      else
      {
        str = str + "None inserted\n";
      }
      if (this.removed != null)
      {
        str = str + "Removed:\n";
        for (i = 0; i < this.removed.length; i++) {
          str = str + "  " + this.removed[i] + "\n";
        }
      }
      else
      {
        str = str + "None removed\n";
      }
      if ((this.changed != null) && (this.changed.length > 0))
      {
        str = str + "Changed:\n";
        for (i = 0; i < this.changed.length; i++) {
          str = str + "  " + this.changed[i] + "\n";
        }
      }
      else
      {
        str = str + "None changed\n";
      }
      if (this.parentMap != null) {
        str = str + this.parentMap.toString();
      } else {
        str = str + "No parent map\n";
      }
      return str;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/DefaultGraphModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */