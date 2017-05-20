package org.jgraph.graph;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.WeakHashMap;
import javax.swing.event.EventListenerList;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CompoundEdit;
import javax.swing.undo.UndoableEdit;
import org.jgraph.event.GraphLayoutCacheEvent;
import org.jgraph.event.GraphLayoutCacheEvent.GraphLayoutCacheChange;
import org.jgraph.event.GraphLayoutCacheListener;
import org.jgraph.event.GraphModelEvent.GraphModelChange;
import org.jgraph.util.RectUtils;

public class GraphLayoutCache
  implements CellMapper, Serializable
{
  protected boolean autoSizeOnValueChange = false;
  protected boolean showsExistingConnections = true;
  protected boolean showsChangedConnections = true;
  protected boolean showsInvisibleEditedCells = false;
  protected boolean showsInsertedCells = true;
  protected boolean showsInsertedConnections = true;
  protected boolean hidesExistingConnections = true;
  protected boolean hidesDanglingConnections = false;
  protected boolean remembersCellViews = true;
  protected boolean selectsAllInsertedCells = false;
  protected boolean selectsLocalInsertedCells = false;
  protected boolean movesChildrenOnExpand = true;
  protected boolean movesParentsOnCollapse = true;
  protected boolean resizesParentsOnCollapse = false;
  protected double collapseXScale = 1.0D;
  protected double collapseYScale = 1.0D;
  /**
   * @deprecated
   */
  protected boolean reconnectsEdgesToVisibleParent = false;
  protected EventListenerList listenerList = new EventListenerList();
  protected GraphModel graphModel;
  protected Map mapping = new Hashtable();
  protected transient Map hiddenMapping = new WeakHashMap();
  protected CellViewFactory factory = null;
  protected Set visibleSet = new HashSet();
  protected List roots = new ArrayList();
  protected PortView[] ports;
  protected boolean partial = false;
  protected boolean allAttributesLocal = false;
  protected Set localAttributes = new HashSet();
  
  public GraphLayoutCache()
  {
    this(new DefaultGraphModel(), new DefaultCellViewFactory());
  }
  
  public GraphLayoutCache(GraphModel paramGraphModel, CellViewFactory paramCellViewFactory)
  {
    this(paramGraphModel, paramCellViewFactory, false);
  }
  
  public GraphLayoutCache(GraphModel paramGraphModel, CellViewFactory paramCellViewFactory, boolean paramBoolean)
  {
    this(paramGraphModel, paramCellViewFactory, null, null, paramBoolean);
  }
  
  public GraphLayoutCache(GraphModel paramGraphModel, CellViewFactory paramCellViewFactory, CellView[] paramArrayOfCellView1, CellView[] paramArrayOfCellView2, boolean paramBoolean)
  {
    this.factory = paramCellViewFactory;
    this.partial = paramBoolean;
    int i;
    if (paramArrayOfCellView1 != null)
    {
      this.graphModel = paramGraphModel;
      for (i = 0; i < paramArrayOfCellView1.length; i++) {
        if (paramArrayOfCellView1[i] != null)
        {
          putMapping(paramArrayOfCellView1[i].getCell(), paramArrayOfCellView1[i]);
          if (paramBoolean) {
            this.visibleSet.add(paramArrayOfCellView1[i].getCell());
          }
        }
      }
      insertViews(paramArrayOfCellView1);
    }
    else
    {
      setModel(paramGraphModel);
    }
    if (paramArrayOfCellView2 != null) {
      for (i = 0; i < paramArrayOfCellView2.length; i++) {
        this.hiddenMapping.put(paramArrayOfCellView2[i].getCell(), paramArrayOfCellView2[i]);
      }
    }
  }
  
  public void addGraphLayoutCacheListener(GraphLayoutCacheListener paramGraphLayoutCacheListener)
  {
    this.listenerList.add(GraphLayoutCacheListener.class, paramGraphLayoutCacheListener);
  }
  
  public void removeGraphLayoutCacheListener(GraphLayoutCacheListener paramGraphLayoutCacheListener)
  {
    this.listenerList.remove(GraphLayoutCacheListener.class, paramGraphLayoutCacheListener);
  }
  
  public void cellViewsChanged(final CellView[] paramArrayOfCellView)
  {
    if (paramArrayOfCellView != null) {
      fireGraphLayoutCacheChanged(this, new GraphLayoutCacheEvent.GraphLayoutCacheChange()
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
        
        public Object getSource()
        {
          return this;
        }
        
        public Object[] getChanged()
        {
          return paramArrayOfCellView;
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
  
  protected void fireGraphLayoutCacheChanged(Object paramObject, GraphLayoutCacheEvent.GraphLayoutCacheChange paramGraphLayoutCacheChange)
  {
    Object[] arrayOfObject = this.listenerList.getListenerList();
    GraphLayoutCacheEvent localGraphLayoutCacheEvent = null;
    for (int i = arrayOfObject.length - 2; i >= 0; i -= 2) {
      if (arrayOfObject[i] == GraphLayoutCacheListener.class)
      {
        if (localGraphLayoutCacheEvent == null) {
          localGraphLayoutCacheEvent = new GraphLayoutCacheEvent(paramObject, paramGraphLayoutCacheChange);
        }
        ((GraphLayoutCacheListener)arrayOfObject[(i + 1)]).graphLayoutCacheChanged(localGraphLayoutCacheEvent);
      }
    }
  }
  
  public GraphLayoutCacheListener[] getGraphLayoutCacheListeners()
  {
    return (GraphLayoutCacheListener[])this.listenerList.getListeners(GraphLayoutCacheListener.class);
  }
  
  public void setFactory(CellViewFactory paramCellViewFactory)
  {
    this.factory = paramCellViewFactory;
  }
  
  public CellViewFactory getFactory()
  {
    return this.factory;
  }
  
  public void setModel(GraphModel paramGraphModel)
  {
    this.roots.clear();
    this.mapping.clear();
    this.hiddenMapping.clear();
    this.visibleSet.clear();
    this.graphModel = paramGraphModel;
    if (!isPartial())
    {
      Object[] arrayOfObject = DefaultGraphModel.getRoots(getModel());
      CellView[] arrayOfCellView = getMapping(arrayOfObject, true);
      insertViews(arrayOfCellView);
    }
    update();
  }
  
  public void update()
  {
    updatePorts();
    cellViewsChanged(getRoots());
  }
  
  public CellView[] getCellViews()
  {
    Collection localCollection = this.mapping.values();
    CellView[] arrayOfCellView = new CellView[localCollection.size()];
    localCollection.toArray(arrayOfCellView);
    return arrayOfCellView;
  }
  
  public static Rectangle2D getBounds(CellView[] paramArrayOfCellView)
  {
    if ((paramArrayOfCellView != null) && (paramArrayOfCellView.length > 0))
    {
      Rectangle2D localRectangle2D1 = paramArrayOfCellView[0] != null ? paramArrayOfCellView[0].getBounds() : null;
      Rectangle2D localRectangle2D2 = localRectangle2D1 != null ? (Rectangle2D)localRectangle2D1.clone() : null;
      for (int i = 1; i < paramArrayOfCellView.length; i++)
      {
        localRectangle2D1 = paramArrayOfCellView[i] != null ? paramArrayOfCellView[i].getBounds() : null;
        if (localRectangle2D1 != null) {
          if (localRectangle2D2 == null) {
            localRectangle2D2 = localRectangle2D1 != null ? (Rectangle2D)localRectangle2D1.clone() : null;
          } else {
            Rectangle2D.union(localRectangle2D2, localRectangle2D1, localRectangle2D2);
          }
        }
      }
      return localRectangle2D2;
    }
    return null;
  }
  
  public Object[] getCells(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    CellView[] arrayOfCellView = getCellViews();
    ArrayList localArrayList = new ArrayList(arrayOfCellView.length);
    GraphModel localGraphModel = getModel();
    for (int i = 0; i < arrayOfCellView.length; i++)
    {
      Object localObject = arrayOfCellView[i].getCell();
      boolean bool = localGraphModel.isEdge(localObject);
      if (((paramBoolean3) || (!localGraphModel.isPort(localObject))) && (((!paramBoolean3) && (!paramBoolean2)) || (((bool) && ((!paramBoolean4) || (!bool))) || ((arrayOfCellView[i].isLeaf()) || ((paramBoolean1) && (!arrayOfCellView[i].isLeaf())))))) {
        localArrayList.add(arrayOfCellView[i].getCell());
      }
    }
    return localArrayList.toArray();
  }
  
  public Map createNestedMap()
  {
    CellView[] arrayOfCellView = getCellViews();
    Hashtable localHashtable = new Hashtable();
    for (int i = 0; i < arrayOfCellView.length; i++) {
      localHashtable.put(arrayOfCellView[i].getCell(), new Hashtable((Map)arrayOfCellView[i].getAllAttributes().clone()));
    }
    return localHashtable;
  }
  
  public CellView[] getHiddenCellViews()
  {
    Collection localCollection = this.hiddenMapping.values();
    CellView[] arrayOfCellView = new CellView[localCollection.size()];
    localCollection.toArray(arrayOfCellView);
    return arrayOfCellView;
  }
  
  public synchronized void reload()
  {
    ArrayList localArrayList = new ArrayList();
    Hashtable localHashtable = new Hashtable(this.mapping);
    this.mapping.clear();
    Iterator localIterator = localHashtable.keySet().iterator();
    HashSet localHashSet = new HashSet(this.roots);
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      CellView localCellView1 = (CellView)localHashtable.get(localObject);
      CellView localCellView2 = getMapping(localObject, true);
      localCellView2.changeAttributes(this, localCellView1.getAttributes());
      if (localHashSet.contains(localCellView1)) {
        localArrayList.add(localCellView2);
      }
    }
    this.hiddenMapping.clear();
    this.roots = localArrayList;
  }
  
  public GraphModel getModel()
  {
    return this.graphModel;
  }
  
  public CellView[] getRoots()
  {
    CellView[] arrayOfCellView = new CellView[this.roots.size()];
    this.roots.toArray(arrayOfCellView);
    return arrayOfCellView;
  }
  
  public CellView[] getRoots(Rectangle2D paramRectangle2D)
  {
    ArrayList localArrayList = new ArrayList();
    CellView[] arrayOfCellView = getRoots();
    for (int i = 0; i < arrayOfCellView.length; i++) {
      if (arrayOfCellView[i].getBounds().intersects(paramRectangle2D)) {
        localArrayList.add(arrayOfCellView[i]);
      }
    }
    arrayOfCellView = new CellView[localArrayList.size()];
    localArrayList.toArray(arrayOfCellView);
    return arrayOfCellView;
  }
  
  public Object[] getVisibleCells(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      ArrayList localArrayList = new ArrayList(paramArrayOfObject.length);
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if (isVisible(paramArrayOfObject[i])) {
          localArrayList.add(paramArrayOfObject[i]);
        }
      }
      return localArrayList.toArray();
    }
    return null;
  }
  
  public PortView[] getPorts()
  {
    return this.ports;
  }
  
  protected void updatePorts()
  {
    Object[] arrayOfObject = DefaultGraphModel.getRoots(this.graphModel);
    List localList = DefaultGraphModel.getDescendants(this.graphModel, arrayOfObject);
    if (localList != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if (this.graphModel.isPort(localObject))
        {
          CellView localCellView = getMapping(localObject, false);
          if (localCellView != null)
          {
            localArrayList.add(localCellView);
            localCellView.refresh(this, this, false);
          }
        }
      }
      this.ports = new PortView[localArrayList.size()];
      localArrayList.toArray(this.ports);
    }
  }
  
  public void refresh(CellView[] paramArrayOfCellView, boolean paramBoolean)
  {
    if (paramArrayOfCellView != null) {
      for (int i = 0; i < paramArrayOfCellView.length; i++) {
        refresh(paramArrayOfCellView[i], paramBoolean);
      }
    }
  }
  
  public void refresh(CellView paramCellView, boolean paramBoolean)
  {
    if (paramCellView != null)
    {
      paramCellView.refresh(this, this, paramBoolean);
      CellView[] arrayOfCellView = paramCellView.getChildViews();
      for (int i = 0; i < arrayOfCellView.length; i++) {
        refresh(arrayOfCellView[i], paramBoolean);
      }
    }
  }
  
  public void update(CellView[] paramArrayOfCellView)
  {
    if (paramArrayOfCellView != null) {
      for (int i = 0; i < paramArrayOfCellView.length; i++) {
        update(paramArrayOfCellView[i]);
      }
    }
  }
  
  public void update(CellView paramCellView)
  {
    if (paramCellView != null)
    {
      paramCellView.update(this);
      CellView[] arrayOfCellView = paramCellView.getChildViews();
      for (int i = 0; i < arrayOfCellView.length; i++) {
        update(arrayOfCellView[i]);
      }
    }
  }
  
  public void graphChanged(GraphModelEvent.GraphModelChange paramGraphModelChange)
  {
    CellView[] arrayOfCellView = paramGraphModelChange.getViews(this);
    if (arrayOfCellView != null)
    {
      for (int i = 0; i < arrayOfCellView.length; i++) {
        if (arrayOfCellView[i] != null) {
          this.mapping.put(arrayOfCellView[i].getCell(), arrayOfCellView[i]);
        }
      }
      setVisibleImpl(getCells(arrayOfCellView), true);
    }
    Object[] arrayOfObject = paramGraphModelChange.getChanged();
    getMapping(paramGraphModelChange.getInserted(), true);
    arrayOfCellView = removeCells(paramGraphModelChange.getRemoved());
    paramGraphModelChange.putViews(this, arrayOfCellView);
    if (isPartial())
    {
      showCellsForChange(paramGraphModelChange);
      hideCellsForChange(paramGraphModelChange);
    }
    if ((arrayOfObject != null) && (arrayOfObject.length > 0)) {
      for (int j = 0; j < arrayOfObject.length; j++)
      {
        CellView localCellView = getMapping(arrayOfObject[j], false);
        if (localCellView != null)
        {
          localCellView.refresh(this, this, true);
          update(localCellView);
        }
      }
    }
    reloadRoots();
    refresh(getMapping(getContext(paramGraphModelChange), false), false);
    updatePorts();
  }
  
  protected void reloadRoots()
  {
    Object[] arrayOfObject = DefaultGraphModel.getAll(this.graphModel);
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < arrayOfObject.length; i++)
    {
      CellView localCellView = getMapping(arrayOfObject[i], false);
      if (localCellView != null)
      {
        localCellView.refresh(this, this, true);
        if (localCellView.getParentView() == null) {
          localArrayList.add(localCellView);
        }
      }
    }
    this.roots = localArrayList;
  }
  
  protected Object[] getContext(GraphModelEvent.GraphModelChange paramGraphModelChange)
  {
    return paramGraphModelChange.getContext();
  }
  
  protected void hideCellsForChange(GraphModelEvent.GraphModelChange paramGraphModelChange)
  {
    Object[] arrayOfObject1 = paramGraphModelChange.getRemoved();
    HashSet localHashSet = new HashSet();
    if (arrayOfObject1 != null) {
      for (int i = 0; i < arrayOfObject1.length; i++) {
        localHashSet.add(arrayOfObject1[i]);
      }
    }
    if ((this.hidesDanglingConnections) || (this.hidesExistingConnections))
    {
      Object[] arrayOfObject2 = paramGraphModelChange.getChanged();
      for (int j = 0; j < arrayOfObject2.length; j++)
      {
        CellView localCellView = getMapping(arrayOfObject2[j], false);
        if ((localCellView instanceof EdgeView))
        {
          EdgeView localEdgeView = (EdgeView)localCellView;
          Object localObject1 = localEdgeView.getSource() == null ? null : localEdgeView.getSource().getCell();
          Object localObject2 = localEdgeView.getTarget() == null ? null : localEdgeView.getTarget().getCell();
          Object localObject3 = this.graphModel.getSource(arrayOfObject2[j]);
          Object localObject4 = this.graphModel.getTarget(arrayOfObject2[j]);
          int k = (this.hidesExistingConnections) && (((localObject3 != null) && (!hasVisibleParent(localObject3, null))) || ((localObject4 != null) && (!hasVisibleParent(localObject4, null)))) ? 1 : 0;
          if (((this.hidesDanglingConnections) && ((localHashSet.contains(localObject1)) || (localHashSet.contains(localObject2)))) || (k != 0)) {
            setVisibleImpl(new Object[] { arrayOfObject2[j] }, false);
          }
        }
      }
    }
  }
  
  protected boolean hasVisibleParent(Object paramObject, Set paramSet)
  {
    boolean bool = false;
    do
    {
      bool = (paramSet == null) || (!paramSet.contains(paramObject)) ? isVisible(paramObject) : false;
      paramObject = getModel().getParent(paramObject);
    } while ((paramObject != null) && (!bool));
    return bool;
  }
  
  protected void showCellsForChange(GraphModelEvent.GraphModelChange paramGraphModelChange)
  {
    Object[] arrayOfObject = paramGraphModelChange.getInserted();
    Object localObject1;
    if ((arrayOfObject != null) && (this.showsInsertedConnections)) {
      for (int i = 0; i < arrayOfObject.length; i++) {
        if (!isVisible(arrayOfObject[i]))
        {
          localObject1 = this.graphModel.getSource(arrayOfObject[i]);
          Object localObject2 = this.graphModel.getTarget(arrayOfObject[i]);
          if (((localObject1 != null) || (localObject2 != null)) && (isVisible(localObject1)) && (isVisible(localObject2))) {
            setVisibleImpl(new Object[] { arrayOfObject[i] }, true);
          }
        }
      }
    }
    if (paramGraphModelChange.getConnectionSet() != null)
    {
      Set localSet = paramGraphModelChange.getConnectionSet().getChangedEdges();
      if ((localSet != null) && (this.showsChangedConnections))
      {
        localObject1 = localSet.toArray();
        for (int j = 0; j < localObject1.length; j++) {
          if (!isVisible(localObject1[j]))
          {
            Object localObject3 = this.graphModel.getSource(localObject1[j]);
            Object localObject4 = this.graphModel.getTarget(localObject1[j]);
            if (((localObject3 != null) || (localObject4 != null)) && (isVisible(localObject3)) && (isVisible(localObject4)) && (!isVisible(localObject1[j]))) {
              setVisibleImpl(new Object[] { localObject1[j] }, true);
            }
          }
        }
      }
    }
  }
  
  public void insertViews(CellView[] paramArrayOfCellView)
  {
    if (paramArrayOfCellView != null)
    {
      refresh(paramArrayOfCellView, true);
      for (int i = 0; i < paramArrayOfCellView.length; i++) {
        if ((paramArrayOfCellView[i] != null) && (getMapping(paramArrayOfCellView[i].getCell(), false) != null))
        {
          CellView localCellView = paramArrayOfCellView[i].getParentView();
          Object localObject = localCellView != null ? localCellView.getCell() : null;
          if ((!this.graphModel.isPort(paramArrayOfCellView[i].getCell())) && (localObject == null)) {
            this.roots.add(paramArrayOfCellView[i]);
          }
        }
      }
    }
  }
  
  public CellView[] removeCells(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      CellView[] arrayOfCellView = new CellView[paramArrayOfObject.length];
      HashSet localHashSet = null;
      for (int i = 0; i < paramArrayOfObject.length; i++)
      {
        arrayOfCellView[i] = removeMapping(paramArrayOfObject[i]);
        if (arrayOfCellView[i] != null)
        {
          arrayOfCellView[i].removeFromParent();
          if (localHashSet == null) {
            localHashSet = new HashSet();
          }
          localHashSet.add(arrayOfCellView[i]);
          this.visibleSet.remove(arrayOfCellView[i].getCell());
        }
      }
      if ((localHashSet != null) && (localHashSet.size() > 0))
      {
        i = this.roots.size() - localHashSet.size();
        if (i < 8) {
          i = 8;
        }
        ArrayList localArrayList = new ArrayList(i);
        Iterator localIterator = this.roots.iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          if (!localHashSet.contains(localObject)) {
            localArrayList.add(localObject);
          }
        }
        this.roots = localArrayList;
      }
      return arrayOfCellView;
    }
    return null;
  }
  
  public Object[] getCells(CellView[] paramArrayOfCellView)
  {
    if (paramArrayOfCellView != null)
    {
      Object[] arrayOfObject = new Object[paramArrayOfCellView.length];
      for (int i = 0; i < paramArrayOfCellView.length; i++) {
        if (paramArrayOfCellView[i] != null) {
          arrayOfObject[i] = paramArrayOfCellView[i].getCell();
        }
      }
      return arrayOfObject;
    }
    return null;
  }
  
  public CellView getMapping(Object paramObject, boolean paramBoolean)
  {
    if (paramObject == null) {
      return null;
    }
    CellView localCellView = (CellView)this.mapping.get(paramObject);
    if ((localCellView == null) && (paramBoolean) && (isVisible(paramObject)))
    {
      localCellView = (CellView)this.hiddenMapping.get(paramObject);
      if (localCellView != null)
      {
        putMapping(paramObject, localCellView);
        this.hiddenMapping.remove(paramObject);
      }
      else
      {
        localCellView = this.factory.createView(this.graphModel, paramObject);
        putMapping(paramObject, localCellView);
        localCellView.refresh(this, this, true);
        localCellView.update(this);
      }
    }
    return localCellView;
  }
  
  public CellView[] getMapping(Object[] paramArrayOfObject)
  {
    return getMapping(paramArrayOfObject, false);
  }
  
  public CellView[] getMapping(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    if (paramArrayOfObject != null)
    {
      CellView[] arrayOfCellView = new CellView[paramArrayOfObject.length];
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        arrayOfCellView[i] = getMapping(paramArrayOfObject[i], paramBoolean);
      }
      return arrayOfCellView;
    }
    return null;
  }
  
  public void putMapping(Object paramObject, CellView paramCellView)
  {
    if ((paramObject != null) && (paramCellView != null)) {
      this.mapping.put(paramObject, paramCellView);
    }
  }
  
  public CellView removeMapping(Object paramObject)
  {
    if (paramObject != null)
    {
      CellView localCellView = (CellView)this.mapping.remove(paramObject);
      return localCellView;
    }
    return null;
  }
  
  public boolean isVisible(Object paramObject)
  {
    return (!isPartial()) || (this.visibleSet.contains(paramObject)) || (paramObject == null);
  }
  
  public Set getVisibleSet()
  {
    return new HashSet(this.visibleSet);
  }
  
  public void setVisibleSet(Set paramSet)
  {
    this.visibleSet = paramSet;
  }
  
  public void setVisible(Object paramObject, boolean paramBoolean)
  {
    setVisible(new Object[] { paramObject }, paramBoolean);
  }
  
  public void setVisible(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    if (paramBoolean) {
      setVisible(paramArrayOfObject, null);
    } else {
      setVisible(null, paramArrayOfObject);
    }
  }
  
  public void setVisible(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    setVisible(paramArrayOfObject1, paramArrayOfObject2, null);
  }
  
  public void setVisible(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, ConnectionSet paramConnectionSet)
  {
    setVisible(paramArrayOfObject1, paramArrayOfObject2, null, paramConnectionSet);
  }
  
  public void setVisible(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Map paramMap, ConnectionSet paramConnectionSet)
  {
    GraphLayoutCacheEdit localGraphLayoutCacheEdit = new GraphLayoutCacheEdit(null, paramMap, paramArrayOfObject1, paramArrayOfObject2);
    localGraphLayoutCacheEdit.end();
    this.graphModel.edit(paramMap, paramConnectionSet, null, new UndoableEdit[] { localGraphLayoutCacheEdit });
  }
  
  protected Object[] addVisibleDependencies(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    if (paramArrayOfObject != null)
    {
      HashSet localHashSet;
      Object localObject1;
      Object localObject2;
      Object localObject3;
      if (paramBoolean)
      {
        localHashSet = new HashSet();
        for (int i = 0; i < paramArrayOfObject.length; i++)
        {
          localHashSet.add(paramArrayOfObject[i]);
          localHashSet.addAll(getPorts(paramArrayOfObject[i]));
          localObject1 = getParentPorts(this.graphModel.getSource(paramArrayOfObject[i]));
          if (localObject1 != null) {
            localHashSet.addAll((Collection)localObject1);
          }
          localObject1 = getParentPorts(this.graphModel.getTarget(paramArrayOfObject[i]));
          if (localObject1 != null) {
            localHashSet.addAll((Collection)localObject1);
          }
        }
        if (this.showsExistingConnections)
        {
          Set localSet = DefaultGraphModel.getEdges(getModel(), paramArrayOfObject);
          localObject1 = localSet.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = ((Iterator)localObject1).next();
            localObject3 = this.graphModel.getSource(localObject2);
            Object localObject4 = this.graphModel.getTarget(localObject2);
            if (((isVisible(localObject3)) || (localHashSet.contains(localObject3))) && ((isVisible(localObject4)) || (localHashSet.contains(localObject4)))) {
              localHashSet.add(localObject2);
            }
          }
        }
        localHashSet.removeAll(this.visibleSet);
        localHashSet.remove(null);
        return localHashSet.toArray();
      }
      if (this.hidesExistingConnections)
      {
        localHashSet = new HashSet();
        for (int j = 0; j < paramArrayOfObject.length; j++)
        {
          localHashSet.addAll(getPorts(paramArrayOfObject[j]));
          localHashSet.add(paramArrayOfObject[j]);
        }
        Iterator localIterator = DefaultGraphModel.getEdges(this.graphModel, paramArrayOfObject).iterator();
        while (localIterator.hasNext())
        {
          localObject1 = localIterator.next();
          localObject2 = this.graphModel.getSource(localObject1);
          localObject3 = this.graphModel.getTarget(localObject1);
          if (((localObject2 != null) && (!hasVisibleParent(localObject2, localHashSet))) || ((localObject3 != null) && (!hasVisibleParent(localObject3, localHashSet)))) {
            localHashSet.add(localObject1);
          }
        }
        localHashSet.remove(null);
        return localHashSet.toArray();
      }
    }
    return paramArrayOfObject;
  }
  
  public boolean setVisibleImpl(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    paramArrayOfObject = addVisibleDependencies(paramArrayOfObject, paramBoolean);
    if ((paramArrayOfObject != null) && (isPartial()))
    {
      boolean bool = false;
      CellView[] arrayOfCellView = new CellView[paramArrayOfObject.length];
      if (!paramBoolean) {
        arrayOfCellView = removeCells(paramArrayOfObject);
      }
      HashSet localHashSet1 = null;
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if (paramArrayOfObject[i] != null) {
          if (paramBoolean)
          {
            this.visibleSet.add(paramArrayOfObject[i]);
            arrayOfCellView[i] = getMapping(paramArrayOfObject[i], true);
          }
          else if (arrayOfCellView[i] != null)
          {
            if (localHashSet1 == null) {
              localHashSet1 = new HashSet(DefaultGraphModel.getRootsAsCollection(getModel()));
            }
            if ((localHashSet1.contains(arrayOfCellView[i].getCell())) && (this.remembersCellViews)) {
              this.hiddenMapping.put(arrayOfCellView[i].getCell(), arrayOfCellView[i]);
            }
            bool = true;
          }
        }
      }
      if (paramBoolean)
      {
        HashSet localHashSet2 = new HashSet();
        HashSet localHashSet3 = null;
        Object localObject2;
        Object localObject3;
        for (int j = 0; j < arrayOfCellView.length; j++) {
          if (arrayOfCellView[j] != null)
          {
            localObject2 = arrayOfCellView[j];
            localObject3 = AbstractCellView.getDescendantViews(new CellView[] { localObject2 });
            for (int k = 0; k < localObject3.length; k++)
            {
              if (localHashSet3 == null) {
                localHashSet3 = new HashSet();
              }
              localHashSet3.add(localObject3[k]);
            }
            ((CellView)localObject2).refresh(this, this, false);
            CellView localCellView = ((CellView)localObject2).getParentView();
            if (localCellView != null) {
              localHashSet2.add(localCellView);
            }
            bool = true;
          }
        }
        if ((localHashSet3 != null) && (localHashSet3.size() > 0))
        {
          localObject1 = new ArrayList();
          localObject2 = this.roots.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = ((Iterator)localObject2).next();
            if (!localHashSet3.contains(localObject3)) {
              ((List)localObject1).add(localObject3);
            }
          }
          this.roots = ((List)localObject1);
        }
        Object localObject1 = new CellView[localHashSet2.size()];
        localHashSet2.toArray((Object[])localObject1);
        refresh((CellView[])localObject1, true);
      }
      return bool;
    }
    return false;
  }
  
  protected Collection getParentPorts(Object paramObject)
  {
    for (Object localObject = this.graphModel.getParent(paramObject); localObject != null; localObject = this.graphModel.getParent(localObject)) {
      if (isVisible(localObject)) {
        return null;
      }
    }
    localObject = this.graphModel.getParent(paramObject);
    Collection localCollection = getPorts(localObject);
    localCollection.add(localObject);
    return localCollection;
  }
  
  protected Collection getPorts(Object paramObject)
  {
    LinkedList localLinkedList = new LinkedList();
    for (int i = 0; i < this.graphModel.getChildCount(paramObject); i++)
    {
      Object localObject = this.graphModel.getChild(paramObject, i);
      if (this.graphModel.isPort(localObject)) {
        localLinkedList.add(localObject);
      }
    }
    return localLinkedList;
  }
  
  public boolean isPartial()
  {
    return this.partial;
  }
  
  public boolean getPartial()
  {
    return isPartial();
  }
  
  public void valueForCellChanged(Object paramObject1, Object paramObject2)
  {
    Object localObject = null;
    if (isAutoSizeOnValueChange())
    {
      CellView localCellView = getMapping(paramObject1, false);
      if (localCellView != null)
      {
        AttributeMap localAttributeMap = localCellView.getAllAttributes();
        Rectangle2D localRectangle2D1 = GraphConstants.getBounds(localAttributeMap);
        Rectangle2D localRectangle2D2 = null;
        if (localRectangle2D1 != null) {
          localRectangle2D2 = localAttributeMap.createRect(localRectangle2D1.getX(), localRectangle2D1.getY(), 0.0D, 0.0D);
        } else {
          localRectangle2D2 = localAttributeMap.createRect(0.0D, 0.0D, 0.0D, 0.0D);
        }
        localObject = GraphConstants.createAttributes(new Object[] { paramObject1 }, new Object[] { "resize", "bounds" }, new Object[] { Boolean.TRUE, localRectangle2D2 });
      }
    }
    else
    {
      localObject = new Hashtable();
      ((Map)localObject).put(paramObject1, new Hashtable());
    }
    augmentNestedMapForValueChange((Map)localObject, paramObject1, paramObject2);
    edit((Map)localObject, null, null, null);
  }
  
  protected void augmentNestedMapForValueChange(Map paramMap, Object paramObject1, Object paramObject2)
  {
    Map localMap = (Map)paramMap.get(paramObject1);
    if (localMap != null) {
      GraphConstants.setValue(localMap, paramObject2);
    }
  }
  
  public void insert(Object[] paramArrayOfObject, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, UndoableEdit[] paramArrayOfUndoableEdit)
  {
    Object[] arrayOfObject = null;
    if ((isPartial()) && (this.showsInsertedCells))
    {
      localObject = DefaultGraphModel.getDescendants(this.graphModel, paramArrayOfObject);
      ((List)localObject).removeAll(this.visibleSet);
      if (!((List)localObject).isEmpty()) {
        arrayOfObject = ((List)localObject).toArray();
      }
    }
    Object localObject = createLocalEdit(paramArrayOfObject, paramMap, arrayOfObject, null);
    if (localObject != null) {
      paramArrayOfUndoableEdit = augment(paramArrayOfUndoableEdit, (UndoableEdit)localObject);
    }
    this.graphModel.insert(paramArrayOfObject, paramMap, paramConnectionSet, paramParentMap, paramArrayOfUndoableEdit);
  }
  
  public Object[] insertClones(Object[] paramArrayOfObject, Map paramMap1, Map paramMap2, ConnectionSet paramConnectionSet, ParentMap paramParentMap, double paramDouble1, double paramDouble2)
  {
    if (paramArrayOfObject != null)
    {
      if (paramConnectionSet != null) {
        paramConnectionSet = paramConnectionSet.clone(paramMap1);
      }
      if (paramParentMap != null) {
        paramParentMap = paramParentMap.clone(paramMap1);
      }
      if (paramMap2 != null)
      {
        paramMap2 = GraphConstants.replaceKeys(paramMap1, paramMap2);
        AttributeMap.translate(paramMap2.values(), paramDouble1, paramDouble2);
      }
      Object[] arrayOfObject = new Object[paramArrayOfObject.length];
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        arrayOfObject[i] = paramMap1.get(paramArrayOfObject[i]);
      }
      insert(arrayOfObject, paramMap2, paramConnectionSet, paramParentMap, null);
      return arrayOfObject;
    }
    return null;
  }
  
  public void insert(Object paramObject)
  {
    insert(new Object[] { paramObject });
  }
  
  public void insertEdge(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    insert(new Object[] { paramObject1 }, new Hashtable(), new ConnectionSet(paramObject1, paramObject2, paramObject3), new ParentMap());
  }
  
  public void insert(Object[] paramArrayOfObject)
  {
    insert(paramArrayOfObject, new Hashtable(), new ConnectionSet(), new ParentMap());
  }
  
  public void insert(Object[] paramArrayOfObject, Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap)
  {
    if (paramArrayOfObject != null)
    {
      if (paramMap == null) {
        paramMap = new Hashtable();
      }
      if (paramConnectionSet == null) {
        paramConnectionSet = new ConnectionSet();
      }
      if (paramParentMap == null) {
        paramParentMap = new ParentMap();
      }
      for (int i = 0; i < paramArrayOfObject.length; i++)
      {
        int j = getModel().getChildCount(paramArrayOfObject[i]);
        for (int k = 0; k < j; k++)
        {
          localObject1 = getModel().getChild(paramArrayOfObject[i], k);
          paramParentMap.addEntry(localObject1, paramArrayOfObject[i]);
          localObject2 = getModel().getAttributes(localObject1);
          if (localObject2 != null) {
            paramMap.put(localObject1, localObject2);
          }
        }
        Map localMap = (Map)paramMap.get(paramArrayOfObject[i]);
        Object localObject1 = getModel().getAttributes(paramArrayOfObject[i]);
        if (localMap != null) {
          ((Map)localObject1).putAll(localMap);
        }
        paramMap.put(paramArrayOfObject[i], localObject1);
        Object localObject2 = getModel().getSource(paramArrayOfObject[i]);
        if (localObject2 != null) {
          paramConnectionSet.connect(paramArrayOfObject[i], localObject2, true);
        }
        Object localObject3 = getModel().getTarget(paramArrayOfObject[i]);
        if (localObject3 != null) {
          paramConnectionSet.connect(paramArrayOfObject[i], localObject3, false);
        }
      }
      paramArrayOfObject = DefaultGraphModel.getDescendants(getModel(), paramArrayOfObject).toArray();
      insert(paramArrayOfObject, paramMap, paramConnectionSet, paramParentMap, null);
    }
  }
  
  public void insertGroup(Object paramObject, Object[] paramArrayOfObject)
  {
    if ((paramObject != null) && (paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      Hashtable localHashtable = new Hashtable();
      ArrayList localArrayList = new ArrayList(paramArrayOfObject.length + 1);
      if (!getModel().contains(paramObject)) {
        localArrayList.add(paramObject);
      }
      ParentMap localParentMap = new ParentMap();
      for (int i = 0; i < paramArrayOfObject.length; i++)
      {
        localParentMap.addEntry(paramArrayOfObject[i], paramObject);
        if (!getModel().contains(paramArrayOfObject[i]))
        {
          localArrayList.add(paramArrayOfObject[i]);
          AttributeMap localAttributeMap = getModel().getAttributes(paramArrayOfObject[i]);
          if (localAttributeMap != null) {
            localHashtable.put(paramArrayOfObject[i], localAttributeMap);
          }
        }
      }
      if (localArrayList.isEmpty()) {
        edit(localHashtable, null, localParentMap, null);
      } else {
        insert(localArrayList.toArray(), localHashtable, null, localParentMap);
      }
    }
  }
  
  public void remove(Object[] paramArrayOfObject)
  {
    this.graphModel.remove(paramArrayOfObject);
  }
  
  public void remove(Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      if (paramBoolean2)
      {
        Object[] arrayOfObject1 = DefaultGraphModel.getEdges(getModel(), paramArrayOfObject).toArray();
        Object[] arrayOfObject2 = new Object[paramArrayOfObject.length + arrayOfObject1.length];
        System.arraycopy(paramArrayOfObject, 0, arrayOfObject2, 0, paramArrayOfObject.length);
        System.arraycopy(arrayOfObject1, 0, arrayOfObject2, paramArrayOfObject.length, arrayOfObject1.length);
        paramArrayOfObject = arrayOfObject2;
      }
      if (paramBoolean1) {
        paramArrayOfObject = DefaultGraphModel.getDescendants(getModel(), paramArrayOfObject).toArray();
      }
      remove(paramArrayOfObject);
    }
  }
  
  public void hideCells(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      if (paramBoolean) {
        paramArrayOfObject = DefaultGraphModel.getDescendants(getModel(), paramArrayOfObject).toArray();
      }
      setVisible(paramArrayOfObject, false);
    }
  }
  
  public void showCells(Object[] paramArrayOfObject, boolean paramBoolean)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      if (paramBoolean) {
        paramArrayOfObject = DefaultGraphModel.getDescendants(getModel(), paramArrayOfObject).toArray();
      }
      setVisible(paramArrayOfObject, true);
    }
  }
  
  public Object[] ungroup(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      int i = 0;
      for (int j = 0; j < paramArrayOfObject.length; j++)
      {
        int k = 0;
        ArrayList localArrayList3 = new ArrayList();
        for (int m = 0; m < getModel().getChildCount(paramArrayOfObject[j]); m++)
        {
          Object localObject = getModel().getChild(paramArrayOfObject[j], m);
          if (!getModel().isPort(localObject))
          {
            localArrayList2.add(localObject);
            k = 1;
          }
          else
          {
            localArrayList3.add(localObject);
          }
        }
        if (k != 0)
        {
          localArrayList1.addAll(localArrayList3);
          localArrayList1.add(paramArrayOfObject[j]);
          i = 1;
        }
      }
      if (i != 0) {
        remove(localArrayList1.toArray());
      }
      return localArrayList2.toArray();
    }
    return null;
  }
  
  public void toggleCollapsedState(Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      Object localObject = paramArrayOfObject[i];
      CellView localCellView = getMapping(localObject, false);
      if (localCellView != null) {
        if ((localCellView.isLeaf()) && (!paramBoolean1)) {
          localArrayList1.add(localCellView.getCell());
        } else if ((!localCellView.isLeaf()) && (!paramBoolean2)) {
          localArrayList2.add(localCellView.getCell());
        }
      }
    }
    if ((!localArrayList2.isEmpty()) || (!localArrayList1.isEmpty())) {
      setCollapsedState(localArrayList2.toArray(), localArrayList1.toArray());
    }
  }
  
  public void collapse(Object[] paramArrayOfObject)
  {
    setCollapsedState(paramArrayOfObject, null);
  }
  
  public void expand(Object[] paramArrayOfObject)
  {
    setCollapsedState(null, paramArrayOfObject);
  }
  
  public void setCollapsedState(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    ConnectionSet localConnectionSet = new ConnectionSet();
    List localList = DefaultGraphModel.getDescendants(getModel(), paramArrayOfObject1);
    int j;
    int k;
    if (paramArrayOfObject1 != null)
    {
      for (int i = 0; i < paramArrayOfObject1.length; i++)
      {
        localList.remove(paramArrayOfObject1[i]);
        cellWillCollapse(paramArrayOfObject1[i]);
      }
      for (i = 0; i < paramArrayOfObject1.length; i++)
      {
        j = getModel().getChildCount(paramArrayOfObject1[i]);
        if (j > 0) {
          for (k = 0; k < j; k++)
          {
            Object localObject = getModel().getChild(paramArrayOfObject1[i], k);
            if (getModel().isPort(localObject)) {
              localList.remove(localObject);
            }
          }
        }
      }
    }
    HashSet localHashSet = new HashSet();
    if (paramArrayOfObject2 != null) {
      for (j = 0; j < paramArrayOfObject2.length; j++)
      {
        k = getModel().getChildCount(paramArrayOfObject2[j]);
        for (int m = 0; m < k; m++) {
          localHashSet.add(getModel().getChild(paramArrayOfObject2[j], m));
        }
      }
    }
    setVisible(localHashSet.toArray(), localList != null ? localList.toArray() : null, localConnectionSet);
  }
  
  protected Object getParentPort(Object paramObject, boolean paramBoolean)
  {
    Object localObject1 = getModel().getParent(paramBoolean ? DefaultGraphModel.getSourceVertex(getModel(), paramObject) : DefaultGraphModel.getTargetVertex(getModel(), paramObject));
    int i = getModel().getChildCount(localObject1);
    int j = paramBoolean ? i - 1 : 0;
    while ((j < getModel().getChildCount(localObject1)) && (j >= 0))
    {
      Object localObject2 = getModel().getChild(localObject1, j);
      if (getModel().isPort(localObject2)) {
        return localObject2;
      }
      j += (paramBoolean ? -1 : 1);
    }
    return null;
  }
  
  protected Object getChildPort(Object paramObject, boolean paramBoolean)
  {
    GraphModel localGraphModel = getModel();
    Object localObject1 = paramBoolean ? DefaultGraphModel.getSourceVertex(localGraphModel, paramObject) : DefaultGraphModel.getTargetVertex(localGraphModel, paramObject);
    int i = localGraphModel.getChildCount(localObject1);
    int j = paramBoolean ? i - 1 : 0;
    while ((j < i) && (j >= 0))
    {
      Object localObject2 = localGraphModel.getChild(localObject1, j);
      if ((!localGraphModel.isEdge(localObject2)) && (!localGraphModel.isPort(localObject2))) {
        for (int k = 0; k < localGraphModel.getChildCount(localObject2); k++)
        {
          Object localObject3 = localGraphModel.getChild(localObject2, k);
          if (localGraphModel.isPort(localObject3)) {
            return localObject3;
          }
        }
      }
      j += (paramBoolean ? -1 : 1);
    }
    return null;
  }
  
  public void edit(Map paramMap, ConnectionSet paramConnectionSet, ParentMap paramParentMap, UndoableEdit[] paramArrayOfUndoableEdit)
  {
    if ((paramMap != null) || (paramConnectionSet != null) || (paramParentMap != null) || (paramArrayOfUndoableEdit != null))
    {
      Object[] arrayOfObject = null;
      if ((isPartial()) && (this.showsInvisibleEditedCells))
      {
        localObject = new HashSet();
        if (paramMap != null) {
          ((Set)localObject).addAll(paramMap.keySet());
        }
        if (paramConnectionSet != null) {
          ((Set)localObject).addAll(paramConnectionSet.getChangedEdges());
        }
        if (paramParentMap != null) {
          ((Set)localObject).addAll(paramParentMap.getChangedNodes());
        }
        ((Set)localObject).removeAll(this.visibleSet);
        if (!((Set)localObject).isEmpty()) {
          arrayOfObject = ((Set)localObject).toArray();
        }
      }
      Object localObject = createLocalEdit(null, paramMap, arrayOfObject, null);
      if (localObject != null) {
        paramArrayOfUndoableEdit = augment(paramArrayOfUndoableEdit, (UndoableEdit)localObject);
      }
      this.graphModel.edit(paramMap, paramConnectionSet, paramParentMap, paramArrayOfUndoableEdit);
    }
  }
  
  public void edit(Map paramMap)
  {
    edit(paramMap, null, null, null);
  }
  
  public void edit(Object[] paramArrayOfObject, Map paramMap)
  {
    if ((paramMap != null) && (paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      Hashtable localHashtable = new Hashtable();
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        localHashtable.put(paramArrayOfObject[i], paramMap);
      }
      edit(localHashtable, null, null, null);
    }
  }
  
  public void editCell(Object paramObject, Map paramMap)
  {
    if ((paramMap != null) && (paramObject != null)) {
      edit(new Object[] { paramObject }, paramMap);
    }
  }
  
  protected UndoableEdit[] augment(UndoableEdit[] paramArrayOfUndoableEdit, UndoableEdit paramUndoableEdit)
  {
    if (paramUndoableEdit != null)
    {
      int i = paramArrayOfUndoableEdit != null ? paramArrayOfUndoableEdit.length + 1 : 1;
      UndoableEdit[] arrayOfUndoableEdit = new UndoableEdit[i];
      if (paramArrayOfUndoableEdit != null) {
        System.arraycopy(paramArrayOfUndoableEdit, 0, arrayOfUndoableEdit, 0, i - 2);
      }
      arrayOfUndoableEdit[(i - 1)] = paramUndoableEdit;
      return arrayOfUndoableEdit;
    }
    return paramArrayOfUndoableEdit;
  }
  
  public void toBack(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0)) {
      this.graphModel.toBack(paramArrayOfObject);
    }
  }
  
  public void toFront(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0)) {
      this.graphModel.toFront(paramArrayOfObject);
    }
  }
  
  protected GraphLayoutCacheEdit createLocalEdit(Object[] paramArrayOfObject1, Map paramMap, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3)
  {
    Object localObject1;
    if ((paramMap != null) && (!paramMap.isEmpty()) && ((!this.localAttributes.isEmpty()) || (isAllAttributesLocal())))
    {
      localObject1 = new Hashtable();
      Hashtable localHashtable1 = new Hashtable();
      Iterator localIterator1 = paramMap.entrySet().iterator();
      Object localObject2;
      while (localIterator1.hasNext())
      {
        Hashtable localHashtable2 = new Hashtable();
        localObject2 = (Map.Entry)localIterator1.next();
        Object localObject3 = ((Map.Entry)localObject2).getKey();
        Map localMap = (Map)((Map.Entry)localObject2).getValue();
        CellView localCellView = getMapping(localObject3, false);
        if (localCellView != null) {
          localMap = localCellView.getAllAttributes().diff(localMap);
        }
        Iterator localIterator2 = localMap.entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator2.next();
          Object localObject4 = localEntry.getKey();
          Object localObject5 = localEntry.getValue();
          boolean bool = isControlAttribute(localObject3, localObject4, localObject5);
          if ((isAllAttributesLocal()) || (bool) || (isLocalAttribute(localObject3, localObject4, localObject5)))
          {
            localHashtable2.put(localObject4, localObject5);
            if (!bool) {
              localIterator2.remove();
            }
          }
        }
        if (!localHashtable2.isEmpty()) {
          localHashtable1.put(localObject3, localHashtable2);
        }
        if (!localMap.isEmpty()) {
          ((Map)localObject1).put(localObject3, localMap);
        }
      }
      paramMap.clear();
      paramMap.putAll((Map)localObject1);
      if ((paramArrayOfObject2 != null) || (paramArrayOfObject3 != null) || (!localHashtable1.isEmpty()))
      {
        localObject2 = new GraphLayoutCacheEdit(paramArrayOfObject1, new Hashtable(localHashtable1), paramArrayOfObject2, paramArrayOfObject3);
        ((GraphLayoutCacheEdit)localObject2).end();
        return (GraphLayoutCacheEdit)localObject2;
      }
    }
    else if ((paramArrayOfObject2 != null) || (paramArrayOfObject3 != null))
    {
      localObject1 = new GraphLayoutCacheEdit(paramArrayOfObject1, null, paramArrayOfObject2, paramArrayOfObject3);
      ((GraphLayoutCacheEdit)localObject1).end();
      return (GraphLayoutCacheEdit)localObject1;
    }
    return null;
  }
  
  protected boolean isLocalAttribute(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return this.localAttributes.contains(paramObject2);
  }
  
  protected boolean isControlAttribute(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return ("removeAll".equals(paramObject2)) || ("removeAttributes".equals(paramObject2));
  }
  
  public boolean removeViewLocalAttribute(Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.localAttributes.contains(paramObject))
    {
      if (paramBoolean1)
      {
        copyRemovedViewValue(paramObject, paramBoolean1, paramBoolean2, this.mapping.values());
        copyRemovedViewValue(paramObject, paramBoolean1, paramBoolean2, this.hiddenMapping.values());
      }
      this.localAttributes.remove(paramObject);
      return true;
    }
    return false;
  }
  
  private void copyRemovedViewValue(Object paramObject, boolean paramBoolean1, boolean paramBoolean2, Collection paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      CellView localCellView = (CellView)localIterator.next();
      AttributeMap localAttributeMap1 = localCellView.getAttributes();
      if (localAttributeMap1.containsKey(paramObject))
      {
        if (paramBoolean1)
        {
          Object localObject1 = localCellView.getCell();
          AttributeMap localAttributeMap2 = this.graphModel.getAttributes(localObject1);
          if (localAttributeMap2 != null)
          {
            boolean bool = localAttributeMap2.containsKey(paramObject);
            if ((!paramBoolean2) || (!bool))
            {
              Object localObject2 = localAttributeMap1.get(paramObject);
              localAttributeMap2.put(paramObject, localObject2);
            }
          }
        }
        localAttributeMap1.remove(paramObject);
      }
    }
  }
  
  protected void cellExpanded(Object paramObject)
  {
    GraphModel localGraphModel = getModel();
    if ((this.movesChildrenOnExpand) && (!localGraphModel.isPort(paramObject)))
    {
      CellView localCellView1 = getMapping(paramObject, false);
      if (localCellView1 != null)
      {
        CellView localCellView2 = getMapping(localGraphModel.getParent(paramObject), false);
        if ((localCellView2 != null) && (DefaultGraphModel.isVertex(localGraphModel, localCellView2)))
        {
          Rectangle2D localRectangle2D1 = GraphConstants.getBounds(localCellView2.getAllAttributes());
          Rectangle2D localRectangle2D2 = localCellView2.getBounds();
          if ((localRectangle2D2 != null) && (localRectangle2D1 != null))
          {
            double d1 = localRectangle2D1.getX() - localRectangle2D2.getX();
            double d2 = localRectangle2D1.getY() - localRectangle2D2.getY();
            AttributeMap localAttributeMap = localCellView1.getAttributes();
            if (!localAttributeMap.contains("bounds")) {
              localAttributeMap = localGraphModel.getAttributes(localCellView1.getCell());
            }
            localAttributeMap.translate(d1, d2);
          }
        }
      }
    }
  }
  
  protected void cellWillCollapse(Object paramObject)
  {
    GraphModel localGraphModel = getModel();
    if (this.movesParentsOnCollapse)
    {
      CellView localCellView = getMapping(paramObject, false);
      if ((localCellView != null) && (!localCellView.isLeaf()))
      {
        AttributeMap localAttributeMap = localCellView.getAttributes();
        if ((!localAttributeMap.contains("bounds")) && (!this.localAttributes.contains("bounds"))) {
          localAttributeMap = localGraphModel.getAttributes(paramObject);
        }
        Rectangle2D localRectangle2D1 = GraphConstants.getBounds(localAttributeMap);
        Rectangle2D localRectangle2D2 = localCellView.getBounds();
        if ((this.resizesParentsOnCollapse) || (localRectangle2D1 == null) || (localRectangle2D1.equals(VertexView.defaultBounds)))
        {
          localRectangle2D1 = localAttributeMap.createRect(localRectangle2D2.getX(), localRectangle2D2.getY(), localRectangle2D2.getWidth() * this.collapseXScale, localRectangle2D2.getHeight() * this.collapseYScale);
          localAttributeMap.applyValue("bounds", localRectangle2D1);
        }
        else
        {
          localRectangle2D1.setFrame(localRectangle2D2.getX(), localRectangle2D2.getY(), localRectangle2D1.getWidth(), localRectangle2D1.getHeight());
        }
      }
    }
  }
  
  protected Map handleAttributes(Map paramMap)
  {
    Hashtable localHashtable = new Hashtable();
    CellView[] arrayOfCellView = new CellView[paramMap.size()];
    Iterator localIterator = paramMap.entrySet().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      CellView localCellView = getMapping(localEntry.getKey(), false);
      arrayOfCellView[i] = localCellView;
      i++;
      if ((localCellView != null) && (localCellView.getAttributes() != null))
      {
        Map localMap = (Map)localEntry.getValue();
        AttributeMap localAttributeMap = localCellView.getAttributes().applyMap(localMap);
        localCellView.refresh(this, this, false);
        localHashtable.put(localCellView.getCell(), localAttributeMap);
      }
    }
    update(arrayOfCellView);
    return localHashtable;
  }
  
  public static void translateViews(CellView[] paramArrayOfCellView, double paramDouble1, double paramDouble2)
  {
    for (int i = 0; i < paramArrayOfCellView.length; i++) {
      if ((paramArrayOfCellView[i] instanceof AbstractCellView)) {
        ((AbstractCellView)paramArrayOfCellView[i]).translate(paramDouble1, paramDouble2);
      }
    }
  }
  
  public List getNeighbours(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    GraphModel localGraphModel = getModel();
    Object[] arrayOfObject = paramBoolean1 ? DefaultGraphModel.getOutgoingEdges(localGraphModel, paramObject) : DefaultGraphModel.getEdges(localGraphModel, new Object[] { paramObject }).toArray();
    ArrayList localArrayList = new ArrayList(arrayOfObject.length);
    HashSet localHashSet = new HashSet(arrayOfObject.length + 8, 0.75F);
    for (int i = 0; i < arrayOfObject.length; i++) {
      if ((!paramBoolean2) || (isVisible(arrayOfObject[i])))
      {
        Object localObject = DefaultGraphModel.getOpposite(localGraphModel, arrayOfObject[i], paramObject);
        if ((localObject != null) && ((paramSet == null) || (!paramSet.contains(localObject))) && (!localHashSet.contains(localObject)) && ((!paramBoolean2) || (isVisible(localObject))))
        {
          localHashSet.add(localObject);
          localArrayList.add(localObject);
        }
      }
    }
    return localArrayList;
  }
  
  public List getOutgoingEdges(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    return getEdges(paramObject, paramSet, paramBoolean1, paramBoolean2, false);
  }
  
  public List getIncomingEdges(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    return getEdges(paramObject, paramSet, paramBoolean1, paramBoolean2, true);
  }
  
  protected List getEdges(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    GraphModel localGraphModel = getModel();
    Object[] arrayOfObject = DefaultGraphModel.getEdges(localGraphModel, paramObject, paramBoolean3);
    ArrayList localArrayList = new ArrayList(arrayOfObject.length);
    HashSet localHashSet = new HashSet(arrayOfObject.length);
    for (int i = 0; i < arrayOfObject.length; i++) {
      if (((paramSet == null) || (!paramSet.contains(arrayOfObject[i]))) && (!localHashSet.contains(arrayOfObject[i])) && ((!paramBoolean1) || (isVisible(arrayOfObject[i]))))
      {
        if ((paramBoolean2 == true) || (localGraphModel.getSource(arrayOfObject[i]) != localGraphModel.getTarget(arrayOfObject[i]))) {
          localArrayList.add(arrayOfObject[i]);
        }
        localHashSet.add(arrayOfObject[i]);
      }
    }
    return localArrayList;
  }
  
  public CellView[] getAllViews()
  {
    return getAllDescendants(getRoots());
  }
  
  public CellView[] getAllDescendants(CellView[] paramArrayOfCellView)
  {
    Stack localStack = new Stack();
    for (int i = 0; i < paramArrayOfCellView.length; i++) {
      if (paramArrayOfCellView[i] != null) {
        localStack.add(paramArrayOfCellView[i]);
      }
    }
    ArrayList localArrayList = new ArrayList();
    while (!localStack.isEmpty())
    {
      localObject1 = (CellView)localStack.pop();
      CellView[] arrayOfCellView = ((CellView)localObject1).getChildViews();
      for (int j = 0; j < arrayOfCellView.length; j++) {
        localStack.add(arrayOfCellView[j]);
      }
      localArrayList.add(localObject1);
      for (j = 0; j < this.graphModel.getChildCount(((CellView)localObject1).getCell()); j++)
      {
        Object localObject2 = this.graphModel.getChild(((CellView)localObject1).getCell(), j);
        if (this.graphModel.isPort(localObject2))
        {
          CellView localCellView = getMapping(localObject2, false);
          if (localCellView != null) {
            localStack.add(localCellView);
          }
        }
      }
    }
    Object localObject1 = new CellView[localArrayList.size()];
    localArrayList.toArray((Object[])localObject1);
    return (CellView[])localObject1;
  }
  
  public Map getHiddenMapping()
  {
    return this.hiddenMapping;
  }
  
  public void setShowsExistingConnections(boolean paramBoolean)
  {
    this.showsExistingConnections = paramBoolean;
  }
  
  public boolean isShowsExistingConnections()
  {
    return this.showsExistingConnections;
  }
  
  public void setShowsInsertedConnections(boolean paramBoolean)
  {
    this.showsInsertedConnections = paramBoolean;
  }
  
  public boolean isShowsInsertedConnections()
  {
    return this.showsInsertedConnections;
  }
  
  public void setHidesExistingConnections(boolean paramBoolean)
  {
    this.hidesExistingConnections = paramBoolean;
  }
  
  public boolean isHidesExistingConnections()
  {
    return this.hidesExistingConnections;
  }
  
  public void setHidesDanglingConnections(boolean paramBoolean)
  {
    this.hidesDanglingConnections = paramBoolean;
  }
  
  public boolean isHidesDanglingConnections()
  {
    return this.hidesDanglingConnections;
  }
  
  public void setRemembersCellViews(boolean paramBoolean)
  {
    this.remembersCellViews = paramBoolean;
  }
  
  public boolean isRemembersCellViews()
  {
    return this.remembersCellViews;
  }
  
  public void setHiddenSet(Map paramMap)
  {
    this.hiddenMapping = paramMap;
  }
  
  public Set getLocalAttributes()
  {
    return this.localAttributes;
  }
  
  public void setLocalAttributes(Set paramSet)
  {
    this.localAttributes = paramSet;
  }
  
  public boolean isAllAttributesLocal()
  {
    return this.allAttributesLocal;
  }
  
  public void setAllAttributesLocal(boolean paramBoolean)
  {
    this.allAttributesLocal = paramBoolean;
  }
  
  public boolean isAutoSizeOnValueChange()
  {
    return this.autoSizeOnValueChange;
  }
  
  public void setAutoSizeOnValueChange(boolean paramBoolean)
  {
    this.autoSizeOnValueChange = paramBoolean;
  }
  
  public boolean isSelectsAllInsertedCells()
  {
    return this.selectsAllInsertedCells;
  }
  
  public void setSelectsAllInsertedCells(boolean paramBoolean)
  {
    this.selectsAllInsertedCells = paramBoolean;
  }
  
  public boolean isSelectsLocalInsertedCells()
  {
    return this.selectsLocalInsertedCells;
  }
  
  public void setSelectsLocalInsertedCells(boolean paramBoolean)
  {
    this.selectsLocalInsertedCells = paramBoolean;
  }
  
  /**
   * @deprecated
   */
  public boolean isReconnectsEdgesToVisibleParent()
  {
    return this.reconnectsEdgesToVisibleParent;
  }
  
  /**
   * @deprecated
   */
  public void setReconnectsEdgesToVisibleParent(boolean paramBoolean)
  {
    this.reconnectsEdgesToVisibleParent = paramBoolean;
  }
  
  public boolean isShowsChangedConnections()
  {
    return this.showsChangedConnections;
  }
  
  public void setShowsChangedConnections(boolean paramBoolean)
  {
    this.showsChangedConnections = paramBoolean;
  }
  
  public boolean isMovesChildrenOnExpand()
  {
    return this.movesChildrenOnExpand;
  }
  
  public void setMovesChildrenOnExpand(boolean paramBoolean)
  {
    this.movesChildrenOnExpand = paramBoolean;
  }
  
  public boolean isShowsInvisibleEditedCells()
  {
    return this.showsInvisibleEditedCells;
  }
  
  public void setShowsInvisibleEditedCells(boolean paramBoolean)
  {
    this.showsInvisibleEditedCells = paramBoolean;
  }
  
  public double getCollapseXScale()
  {
    return this.collapseXScale;
  }
  
  public void setCollapseXScale(double paramDouble)
  {
    this.collapseXScale = paramDouble;
  }
  
  public double getCollapseYScale()
  {
    return this.collapseYScale;
  }
  
  public void setCollapseYScale(double paramDouble)
  {
    this.collapseYScale = paramDouble;
  }
  
  public boolean isMovesParentsOnCollapse()
  {
    return this.movesParentsOnCollapse;
  }
  
  public void setMovesParentsOnCollapse(boolean paramBoolean)
  {
    this.movesParentsOnCollapse = paramBoolean;
  }
  
  public boolean isResizesParentsOnCollapse()
  {
    return this.resizesParentsOnCollapse;
  }
  
  public void setResizesParentsOnCollapse(boolean paramBoolean)
  {
    this.resizesParentsOnCollapse = paramBoolean;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    Hashtable localHashtable = new Hashtable(this.hiddenMapping);
    paramObjectOutputStream.writeObject(localHashtable);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    Map localMap = (Map)paramObjectInputStream.readObject();
    this.hiddenMapping = new WeakHashMap(localMap);
  }
  
  public class GraphLayoutCacheEdit
    extends CompoundEdit
    implements GraphLayoutCacheEvent.GraphLayoutCacheChange
  {
    protected Object[] cells;
    protected Object[] previousCells = null;
    protected CellView[] context;
    protected CellView[] hidden;
    protected Map attributes;
    protected Map previousAttributes;
    protected Object[] visible;
    protected Object[] invisible;
    protected Rectangle2D dirtyRegion = null;
    protected Set changedCells = new HashSet();
    
    public GraphLayoutCacheEdit(Map paramMap)
    {
      this(null, paramMap, null, null);
      this.attributes = paramMap;
    }
    
    public GraphLayoutCacheEdit(Object[] paramArrayOfObject1, Map paramMap, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3)
    {
      this.attributes = paramMap;
      this.previousAttributes = paramMap;
      this.cells = paramArrayOfObject1;
      this.visible = paramArrayOfObject2;
      this.invisible = paramArrayOfObject3;
    }
    
    public Object getSource()
    {
      return GraphLayoutCache.this;
    }
    
    public boolean isSignificant()
    {
      return true;
    }
    
    public Object[] getChanged()
    {
      return this.changedCells.toArray();
    }
    
    public Object[] getInserted()
    {
      return this.invisible;
    }
    
    public Object[] getRemoved()
    {
      return this.visible;
    }
    
    public Object[] getContext()
    {
      return this.context;
    }
    
    public Map getAttributes()
    {
      return this.attributes;
    }
    
    public Map getPreviousAttributes()
    {
      return this.previousAttributes;
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
      GraphModel localGraphModel = GraphLayoutCache.this.getModel();
      this.changedCells.clear();
      if (this.hidden != null) {
        for (int i = 0; i < this.hidden.length; i++) {
          if (this.hidden[i] != null) {
            GraphLayoutCache.this.mapping.put(this.hidden[i].getCell(), this.hidden[i]);
          }
        }
      }
      if ((this.invisible != null) && (this.invisible.length > 0))
      {
        CellView[] arrayOfCellView1 = new CellView[this.invisible.length];
        arrayOfCellView1 = GraphLayoutCache.this.getMapping(this.invisible, true);
        Rectangle2D localRectangle2D1 = GraphLayoutCache.getBounds(arrayOfCellView1);
        this.dirtyRegion = RectUtils.union(this.dirtyRegion, localRectangle2D1);
      }
      if (!GraphLayoutCache.this.remembersCellViews) {
        this.hidden = GraphLayoutCache.this.getMapping(this.invisible);
      }
      boolean bool = GraphLayoutCache.this.setVisibleImpl(this.visible, true) | GraphLayoutCache.this.setVisibleImpl(this.invisible, false);
      int j;
      if (this.visible != null) {
        for (j = 0; j < this.visible.length; j++)
        {
          this.changedCells.add(this.visible[j]);
          if (this.cells == null) {
            GraphLayoutCache.this.cellExpanded(this.visible[j]);
          }
        }
      }
      if (this.invisible != null) {
        for (j = 0; j < this.invisible.length; j++) {
          this.changedCells.add(this.invisible[j]);
        }
      }
      Object[] arrayOfObject = this.visible;
      this.visible = this.invisible;
      this.invisible = arrayOfObject;
      if (this.attributes != null)
      {
        this.previousAttributes = this.attributes;
        this.changedCells.addAll(this.attributes.keySet());
      }
      if (bool) {
        GraphLayoutCache.this.updatePorts();
      }
      HashSet localHashSet1 = new HashSet();
      Iterator localIterator = this.changedCells.iterator();
      while (localIterator.hasNext()) {
        for (localObject = localGraphModel.getParent(localIterator.next()); localObject != null; localObject = localGraphModel.getParent(localObject)) {
          localHashSet1.add(localObject);
        }
      }
      this.changedCells.addAll(localHashSet1);
      Object localObject = DefaultGraphModel.getEdges(GraphLayoutCache.this.getModel(), this.changedCells.toArray());
      this.context = GraphLayoutCache.this.getMapping(((Set)localObject).toArray());
      HashSet localHashSet2 = new HashSet(this.changedCells);
      localHashSet2.addAll((Collection)localObject);
      CellView[] arrayOfCellView2 = GraphLayoutCache.this.getMapping(localHashSet2.toArray());
      Rectangle2D localRectangle2D2 = GraphLayoutCache.getBounds(arrayOfCellView2);
      this.dirtyRegion = RectUtils.union(this.dirtyRegion, localRectangle2D2);
      if (this.attributes != null) {
        this.attributes = GraphLayoutCache.this.handleAttributes(this.attributes);
      }
      GraphLayoutCache.this.refresh(GraphLayoutCache.this.getMapping(this.changedCells.toArray(), false), false);
      GraphLayoutCache.this.refresh(this.context, false);
      arrayOfObject = this.cells;
      this.cells = this.previousCells;
      this.previousCells = arrayOfObject;
      GraphLayoutCache.this.reloadRoots();
      GraphLayoutCache.this.fireGraphLayoutCacheChanged(GraphLayoutCache.this, this);
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphLayoutCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */