package org.jgraph.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jgraph.JGraph;

public class GraphContext
  implements CellMapper
{
  public static boolean PREVIEW_EDGE_GROUPS = false;
  protected JGraph graph;
  protected transient GraphLayoutCache graphLayoutCache;
  protected Object[] cells;
  protected Set allCells;
  protected Set cellSet;
  protected int cellCount;
  protected Map views = new Hashtable();
  
  public GraphContext(JGraph paramJGraph, Object[] paramArrayOfObject)
  {
    GraphModel localGraphModel = paramJGraph.getModel();
    this.allCells = new HashSet(DefaultGraphModel.getDescendants(localGraphModel, paramArrayOfObject));
    this.graphLayoutCache = paramJGraph.getGraphLayoutCache();
    this.graph = paramJGraph;
    this.cells = paramArrayOfObject;
    this.cellSet = new HashSet();
    Iterator localIterator = this.allCells.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (this.graphLayoutCache.isVisible(localObject))
      {
        this.cellSet.add(localObject);
        if (!localGraphModel.isPort(localObject)) {
          this.cellCount += 1;
        }
      }
    }
  }
  
  public boolean isEmpty()
  {
    return (this.cells == null) || (this.cells.length == 0);
  }
  
  public int getDescendantCount()
  {
    return this.cellCount;
  }
  
  public JGraph getGraph()
  {
    return this.graph;
  }
  
  public Object[] getCells()
  {
    return this.cells;
  }
  
  public boolean contains(Object paramObject)
  {
    return this.cellSet.contains(paramObject);
  }
  
  public CellView[] createTemporaryCellViews()
  {
    CellView[] arrayOfCellView = new CellView[this.cells.length];
    for (int i = 0; i < this.cells.length; i++) {
      arrayOfCellView[i] = getMapping(this.cells[i], true);
    }
    return arrayOfCellView;
  }
  
  public CellView[] createTemporaryPortViews()
  {
    GraphModel localGraphModel = this.graph.getModel();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.allCells.iterator();
    while (localIterator.hasNext())
    {
      localObject = localIterator.next();
      if ((localGraphModel.isPort(localObject)) && (this.graph.getGraphLayoutCache().isVisible(localObject))) {
        localArrayList.add(getMapping(localObject, true));
      }
    }
    Object localObject = new CellView[localArrayList.size()];
    localArrayList.toArray((Object[])localObject);
    return (CellView[])localObject;
  }
  
  public CellView[] createTemporaryContextViews()
  {
    return createTemporaryContextViews(this.cellSet);
  }
  
  public CellView[] createTemporaryContextViews(Set paramSet)
  {
    Object[] arrayOfObject = paramSet.toArray();
    ArrayList localArrayList = new ArrayList();
    Set localSet = DefaultGraphModel.getEdges(this.graph.getModel(), arrayOfObject);
    do
    {
      localObject1 = localSet.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = ((Iterator)localObject1).next();
        CellView localCellView1 = this.graphLayoutCache.getMapping(localObject2, false);
        if ((!paramSet.contains(localObject2)) && (this.graphLayoutCache.isVisible(localObject2)) && (localCellView1 != null) && ((PREVIEW_EDGE_GROUPS) || (localCellView1.isLeaf())))
        {
          CellView localCellView2 = createMapping(localObject2);
          localArrayList.add(localCellView2);
          CellView[] arrayOfCellView = localCellView2.getChildViews();
          for (int i = 0; i < arrayOfCellView.length; i++) {
            arrayOfCellView[i] = createMapping(arrayOfCellView[i].getCell());
          }
          localCellView2.refresh(this.graph.getGraphLayoutCache(), this, false);
        }
      }
      localSet = DefaultGraphModel.getEdges(this.graph.getModel(), localSet.toArray());
    } while (!localSet.isEmpty());
    Object localObject1 = new CellView[localArrayList.size()];
    localArrayList.toArray((Object[])localObject1);
    return (CellView[])localObject1;
  }
  
  public CellView getMapping(Object paramObject, boolean paramBoolean)
  {
    if (paramObject != null)
    {
      CellView localCellView = (CellView)this.views.get(paramObject);
      if (localCellView != null) {
        return localCellView;
      }
      if ((contains(paramObject)) || ((this.graph.getModel().isPort(paramObject)) && (paramBoolean) && (this.graph.getGraphLayoutCache().isVisible(paramObject)))) {
        return createMapping(paramObject);
      }
      return this.graphLayoutCache.getMapping(paramObject, false);
    }
    return null;
  }
  
  public CellView createMapping(Object paramObject)
  {
    CellView localCellView1 = this.graphLayoutCache.getFactory().createView(this.graph.getModel(), paramObject);
    putMapping(paramObject, localCellView1);
    localCellView1.refresh(this.graph.getGraphLayoutCache(), this, true);
    CellView localCellView2 = this.graphLayoutCache.getMapping(paramObject, false);
    if (localCellView2 != null)
    {
      localCellView1.changeAttributes(this.graphLayoutCache, (AttributeMap)localCellView2.getAttributes().clone());
      localCellView1.refresh(this.graph.getGraphLayoutCache(), this, false);
    }
    return localCellView1;
  }
  
  public ConnectionSet disconnect(CellView[] paramArrayOfCellView)
  {
    ConnectionSet localConnectionSet = new ConnectionSet();
    for (int i = 0; i < paramArrayOfCellView.length; i++) {
      if ((paramArrayOfCellView[i] instanceof EdgeView))
      {
        EdgeView localEdgeView = (EdgeView)paramArrayOfCellView[i];
        CellView localCellView = localEdgeView.getSource();
        if (GraphConstants.isDisconnectable(localEdgeView.getAllAttributes()))
        {
          if ((localCellView != null) && (GraphConstants.isDisconnectable(localCellView.getParentView().getAllAttributes())) && (!contains(localCellView.getCell())))
          {
            localEdgeView.setSource(null);
            localConnectionSet.disconnect(localEdgeView.getCell(), true);
          }
          localCellView = localEdgeView.getTarget();
          if ((localCellView != null) && (GraphConstants.isDisconnectable(localCellView.getParentView().getAllAttributes())) && (!contains(localCellView.getCell())))
          {
            localEdgeView.setTarget(null);
            localConnectionSet.disconnect(localEdgeView.getCell(), false);
          }
        }
      }
    }
    return localConnectionSet;
  }
  
  public void putMapping(Object paramObject, CellView paramCellView)
  {
    this.views.put(paramObject, paramCellView);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/GraphContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */