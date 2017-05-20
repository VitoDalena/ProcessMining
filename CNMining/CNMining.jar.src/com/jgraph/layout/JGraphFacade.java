package com.jgraph.layout;

import com.jgraph.algebra.JGraphAlgebra;
import com.jgraph.algebra.JGraphUnionFind;
import com.jgraph.algebra.cost.JGraphCostFunction;
import com.jgraph.algebra.cost.JGraphDistanceCostFunction;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.AttributeMap.SerializablePoint2D;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.Edge.Routing;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.Port;

public class JGraphFacade
{
  protected boolean ignoresHiddenCells = true;
  protected boolean ignoresUnconnectedCells = true;
  protected boolean ignoresCellsInGroups = false;
  protected boolean directed;
  protected boolean edgePromotion = false;
  protected boolean ordered = false;
  protected transient JGraph graph = null;
  protected transient GraphLayoutCache graphLayoutCache = null;
  protected transient GraphModel model = null;
  protected transient Hashtable attributes = new Hashtable();
  protected transient Comparator order = new DefaultComparator();
  protected transient JGraphCostFunction distanceCostFunction;
  protected transient JGraphAlgebra algebra;
  protected transient List roots = new ArrayList();
  protected transient Set verticesFilter = null;
  protected transient List groupHierarchies = null;
  protected transient List<Integer> groupOrientations = null;
  protected double circleRadiusFactor = 1.0D;
  private static Logger logger = Logger.getLogger("com.jgraph.layout.JGraphFacade");
  
  public JGraphFacade(JGraph paramJGraph)
  {
    this(paramJGraph, null);
  }
  
  public JGraphFacade(JGraph paramJGraph, Object[] paramArrayOfObject)
  {
    this(paramJGraph, paramArrayOfObject, true, false, true, true);
  }
  
  public JGraphFacade(JGraph paramJGraph, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this(paramJGraph, paramArrayOfObject, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, new JGraphDistanceCostFunction(paramJGraph.getGraphLayoutCache()), JGraphAlgebra.getSharedInstance());
  }
  
  public JGraphFacade(JGraph paramJGraph, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, JGraphCostFunction paramJGraphCostFunction, JGraphAlgebra paramJGraphAlgebra)
  {
    this(paramJGraph == null ? null : paramJGraph.getModel(), paramJGraph == null ? null : paramJGraph.getGraphLayoutCache(), paramArrayOfObject, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramJGraphCostFunction, paramJGraphAlgebra);
    this.graph = paramJGraph;
  }
  
  public JGraphFacade(GraphLayoutCache paramGraphLayoutCache)
  {
    this(paramGraphLayoutCache, null, true, false, true, true, new JGraphDistanceCostFunction(paramGraphLayoutCache), JGraphAlgebra.getSharedInstance());
  }
  
  public JGraphFacade(GraphLayoutCache paramGraphLayoutCache, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, JGraphCostFunction paramJGraphCostFunction, JGraphAlgebra paramJGraphAlgebra)
  {
    this(paramGraphLayoutCache == null ? null : paramGraphLayoutCache.getModel(), paramGraphLayoutCache, paramArrayOfObject, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramJGraphCostFunction, paramJGraphAlgebra);
  }
  
  public JGraphFacade(GraphModel paramGraphModel, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, JGraphCostFunction paramJGraphCostFunction, JGraphAlgebra paramJGraphAlgebra)
  {
    this(paramGraphModel, null, paramArrayOfObject, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramJGraphCostFunction, paramJGraphAlgebra);
  }
  
  public JGraphFacade(GraphModel paramGraphModel, GraphLayoutCache paramGraphLayoutCache, Object[] paramArrayOfObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, JGraphCostFunction paramJGraphCostFunction, JGraphAlgebra paramJGraphAlgebra)
  {
    this.model = paramGraphModel;
    this.graphLayoutCache = paramGraphLayoutCache;
    if (paramGraphModel == null) {
      throw new RuntimeException("GraphModel not available in JGraphFacade");
    }
    this.ignoresHiddenCells = paramBoolean1;
    if (paramGraphLayoutCache == null) {
      paramBoolean1 = false;
    }
    this.ignoresCellsInGroups = paramBoolean2;
    this.ignoresUnconnectedCells = paramBoolean3;
    this.directed = paramBoolean4;
    this.distanceCostFunction = paramJGraphCostFunction;
    this.algebra = paramJGraphAlgebra;
    if (paramArrayOfObject != null) {
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        if (isVertex(paramArrayOfObject[i])) {
          this.roots.add(paramArrayOfObject[i]);
        }
      }
    }
    setLoggerLevel(Level.OFF);
  }
  
  public void run(JGraphLayout paramJGraphLayout, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      int[] arrayOfInt = new int[2];
      HashSet localHashSet = new HashSet(getVertices());
      determineLayoutHierarchies(1);
      Set localSet = this.verticesFilter;
      Object[] arrayOfObject = this.groupHierarchies.toArray();
      for (int i = 0; i < arrayOfObject.length; i++)
      {
        this.verticesFilter = ((Set)arrayOfObject[i]);
        paramJGraphLayout.run(this);
      }
      this.graphLayoutCache.setVisible(localHashSet.toArray(), true);
      this.verticesFilter = localSet;
    }
    else
    {
      paramJGraphLayout.run(this);
    }
    fixParallelEdges(15.0D);
  }
  
  public void run(JGraphHierarchicalLayout paramJGraphHierarchicalLayout, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      HashSet localHashSet = new HashSet(getVertices());
      determineLayoutHierarchies(paramJGraphHierarchicalLayout.getOrientation());
      Set localSet = this.verticesFilter;
      Object[] arrayOfObject = this.groupHierarchies.toArray();
      for (int i = 0; i < arrayOfObject.length; i++)
      {
        this.verticesFilter = ((Set)arrayOfObject[i]);
        paramJGraphHierarchicalLayout.setOrientation(((Integer)this.groupOrientations.get(i)).intValue());
        paramJGraphHierarchicalLayout.run(this);
      }
      this.graphLayoutCache.setVisible(localHashSet.toArray(), true);
      this.verticesFilter = localSet;
    }
    else
    {
      paramJGraphHierarchicalLayout.run(this);
    }
    fixParallelEdges(15.0D);
  }
  
  protected void fixParallelEdges(double paramDouble)
  {
    ArrayList localArrayList1 = new ArrayList(getEdges());
    Iterator localIterator = localArrayList1.iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      List localList = getPoints(localObject1);
      if (localList.size() == 2)
      {
        Object localObject2 = getSource(localObject1);
        Object localObject3 = getTarget(localObject1);
        Object localObject4 = getSourcePort(localObject1);
        Object localObject5 = getTargetPort(localObject1);
        Object[] arrayOfObject = getEdgesBetween(localObject4, localObject5, false);
        if ((arrayOfObject.length != 1) || (localObject4 == localObject5))
        {
          Rectangle2D localRectangle2D1 = getBounds(localObject2);
          Rectangle2D localRectangle2D2 = getBounds(localObject3);
          Object localObject6 = GraphConstants.getOffset(((Port)localObject4).getAttributes());
          if (localObject6 == null) {
            localObject6 = new Point2D.Double(localRectangle2D1.getCenterX(), localRectangle2D1.getCenterY());
          }
          Object localObject7 = GraphConstants.getOffset(((Port)localObject5).getAttributes());
          if (localObject7 == null) {
            localObject7 = new Point2D.Double(localRectangle2D2.getCenterX(), localRectangle2D2.getCenterY());
          }
          double d1;
          double d2;
          if (localObject4 == localObject5)
          {
            assert (((Point2D)localObject6).equals(localObject7));
            d1 = ((Point2D)localObject6).getX();
            d2 = ((Point2D)localObject6).getY();
            for (int i = 2; i < arrayOfObject.length + 2; i++)
            {
              ArrayList localArrayList2 = new ArrayList(5);
              localArrayList2.add(new Point2D.Double(d1 - (paramDouble + i * paramDouble), d2));
              localArrayList2.add(new Point2D.Double(d1 - (paramDouble + i * paramDouble), d2 - (paramDouble + i * paramDouble)));
              localArrayList2.add(new Point2D.Double(d1, d2 - (2.0D * paramDouble + i * paramDouble)));
              localArrayList2.add(new Point2D.Double(d1 + (paramDouble + i * paramDouble), d2 - (paramDouble + i * paramDouble)));
              localArrayList2.add(new Point2D.Double(d1 + paramDouble, d2 - (paramDouble / 2.0D + i * paramDouble)));
              setPoints(arrayOfObject[(i - 2)], localArrayList2);
            }
          }
          else
          {
            d1 = ((Point2D)localObject6).getX() - ((Point2D)localObject7).getX();
            d2 = ((Point2D)localObject6).getY() - ((Point2D)localObject7).getY();
            double d3 = ((Point2D)localObject7).getX() + d1 / 2.0D;
            double d4 = ((Point2D)localObject7).getY() + d2 / 2.0D;
            double d5 = Math.sqrt(d1 * d1 + d2 * d2);
            for (int j = 0; j < arrayOfObject.length; j++)
            {
              ArrayList localArrayList3 = new ArrayList(3);
              double d6 = 2 * j - (arrayOfObject.length - 1);
              if (getSourcePort(arrayOfObject[j]) == localObject4)
              {
                localArrayList3.add(localObject6);
                localArrayList3.add(localObject7);
              }
              else
              {
                localArrayList3.add(localObject7);
                localArrayList3.add(localObject6);
              }
              if (d6 != 0.0D)
              {
                d6 /= 2.0D;
                double d7 = d3 + d6 * paramDouble * d2 / d5;
                double d8 = d4 - d6 * paramDouble * d1 / d5;
                localArrayList3.add(1, new Point2D.Double(d7, d8));
              }
              setPoints(arrayOfObject[j], localArrayList3);
            }
          }
        }
      }
    }
  }
  
  public void resetControlPoints()
  {
    resetControlPoints(false, null);
  }
  
  public void resetControlPoints(boolean paramBoolean, Edge.Routing paramRouting)
  {
    Iterator localIterator = getEdges().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (isMoveable(localObject))
      {
        Map localMap = getAttributes(localObject);
        List localList = GraphConstants.getPoints(localMap);
        if ((localList != null) && (localList.size() > 2))
        {
          ArrayList localArrayList = new ArrayList();
          localArrayList.add(localList.get(0));
          localArrayList.add(localList.get(localList.size() - 1));
          GraphConstants.setPoints(localMap, localArrayList);
        }
        if (paramBoolean) {
          GraphConstants.setRouting(localMap, paramRouting);
        }
      }
    }
  }
  
  public boolean isVertex(Object paramObject)
  {
    if ((this.verticesFilter != null) && (!this.verticesFilter.contains(paramObject))) {
      return false;
    }
    if (DefaultGraphModel.isVertex(this.model, paramObject))
    {
      Object localObject;
      if (this.ignoresUnconnectedCells)
      {
        localObject = getEdges(paramObject);
        if ((localObject == null) || (localObject.length == 0)) {
          return false;
        }
        if ((this.ignoresHiddenCells) && (this.graphLayoutCache != null))
        {
          int i = 0;
          for (int j = 0; j < localObject.length; j++) {
            i = (i != 0) || (this.graphLayoutCache.isVisible(localObject[j])) ? 1 : 0;
          }
          if (i == 0) {
            return false;
          }
        }
      }
      if ((this.ignoresHiddenCells) && (this.graphLayoutCache != null))
      {
        localObject = this.graphLayoutCache.getMapping(paramObject, false);
        if (localObject != null)
        {
          if (this.ignoresCellsInGroups) {
            return ((CellView)localObject).getParentView() == null;
          }
          return true;
        }
        return false;
      }
      return (!this.ignoresCellsInGroups) || (this.model.getParent(paramObject) == null);
    }
    return false;
  }
  
  public boolean isEdge(Object paramObject)
  {
    if ((this.model.getSource(paramObject) == null) || (this.model.getTarget(paramObject) == null)) {
      return false;
    }
    if ((this.ignoresHiddenCells) && (this.graphLayoutCache != null))
    {
      if (!this.model.isEdge(paramObject)) {
        return false;
      }
      CellView localCellView = this.graphLayoutCache.getMapping(paramObject, false);
      if (localCellView != null)
      {
        if (this.ignoresCellsInGroups) {
          return (localCellView.isLeaf()) && (localCellView.getParentView() == null);
        }
        return localCellView.isLeaf();
      }
      return false;
    }
    if ((this.ignoresCellsInGroups) && (this.model.getParent(paramObject) != null)) {
      return false;
    }
    return this.model.isEdge(paramObject);
  }
  
  public List getNeighbours(Object paramObject, boolean paramBoolean)
  {
    return getNeighbours(paramObject, null, paramBoolean);
  }
  
  public List getNeighbours(Object paramObject, Set paramSet, boolean paramBoolean)
  {
    return getNeighbours(paramObject, paramSet, paramBoolean, false);
  }
  
  public List getNeighbours(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    LinkedList localLinkedList = new LinkedList();
    Object localObject1;
    if ((this.graphLayoutCache != null) && (this.graphLayoutCache.isPartial()) && (this.edgePromotion))
    {
      localObject1 = getHiddenChildren(paramObject, paramBoolean2);
      Iterator localIterator1 = ((Set)localObject1).iterator();
      HashSet localHashSet = new HashSet();
      while (localIterator1.hasNext())
      {
        Object localObject2 = localIterator1.next();
        List localList = this.graphLayoutCache.getNeighbours(localObject2, paramSet, this.directed, false);
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
        {
          Object localObject3 = localIterator2.next();
          if (!((Set)localObject1).contains(localObject3))
          {
            while ((this.model.getParent(localObject3) != null) && (!this.graphLayoutCache.isVisible(localObject3))) {
              localObject3 = this.model.getParent(localObject3);
            }
            if (this.graphLayoutCache.isVisible(localObject3)) {
              localHashSet.add(localObject3);
            }
          }
        }
      }
      localLinkedList.addAll(localHashSet);
    }
    else
    {
      localObject1 = this.graphLayoutCache.getNeighbours(paramObject, paramSet, this.directed, this.ignoresHiddenCells);
      localLinkedList.addAll((Collection)localObject1);
    }
    if ((paramBoolean1) && (this.order != null)) {
      Collections.sort(localLinkedList, this.order);
    }
    return localLinkedList;
  }
  
  private Set getHiddenChildren(Object paramObject, boolean paramBoolean)
  {
    List localList = DefaultGraphModel.getDescendants(this.model, new Object[] { paramObject });
    HashSet localHashSet = new HashSet();
    localHashSet.add(paramObject);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((DefaultGraphModel.isVertex(this.model, localObject)) && ((paramBoolean) || (!this.graphLayoutCache.isVisible(localObject)))) {
        localHashSet.add(localObject);
      }
    }
    return localHashSet;
  }
  
  public double getLength(Object paramObject)
  {
    return this.distanceCostFunction.getCost(paramObject);
  }
  
  public double getDistance(Object paramObject1, Object paramObject2, int paramInt)
  {
    Object[] arrayOfObject = getPath(paramObject1, paramObject2, paramInt, this.distanceCostFunction);
    return this.algebra.sum(arrayOfObject, this.distanceCostFunction);
  }
  
  public Object[] getPath(Object paramObject1, Object paramObject2, int paramInt, JGraphCostFunction paramJGraphCostFunction)
  {
    return this.algebra.getShortestPath(this.model, paramObject1, paramObject2, paramJGraphCostFunction, paramInt, isDirected());
  }
  
  public JGraphUnionFind getConnectionComponents(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    return this.algebra.getConnectionComponents(this.model, paramArrayOfObject1, paramArrayOfObject2);
  }
  
  public Object[] getMinimumSpanningTree(Object[] paramArrayOfObject, JGraphCostFunction paramJGraphCostFunction)
  {
    return this.algebra.getMinimumSpanningTree(this.model, paramArrayOfObject, paramJGraphCostFunction, this.directed);
  }
  
  public Collection getVertices()
  {
    return getCells(getAll(), false, false);
  }
  
  public Collection getUnconnectedVertices(boolean paramBoolean)
  {
    List localList = getAll();
    Object localObject1 = null;
    if ((paramBoolean) && (this.order != null)) {
      localObject1 = new TreeSet(this.order);
    } else {
      localObject1 = new LinkedHashSet();
    }
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      if (DefaultGraphModel.isVertex(this.model, localObject2))
      {
        Object[] arrayOfObject = getEdges(localObject2);
        if ((arrayOfObject == null) || (arrayOfObject.length == 0)) {
          ((Set)localObject1).add(localObject2);
        }
      }
    }
    return (Collection)localObject1;
  }
  
  public Collection getEdges()
  {
    return getCells(getAll(), true, false);
  }
  
  public Object[] getEdges(Object paramObject)
  {
    return DefaultGraphModel.getEdges(this.model, new Object[] { paramObject }).toArray();
  }
  
  public Object[] getEdges(Object paramObject, boolean paramBoolean)
  {
    return DefaultGraphModel.getEdges(this.model, paramObject, paramBoolean);
  }
  
  public Object getSource(Object paramObject)
  {
    Object localObject = null;
    localObject = DefaultGraphModel.getSourceVertex(this.model, paramObject);
    if ((localObject != null) && (!isVertex(localObject)))
    {
      if (this.edgePromotion) {
        while ((this.model.getParent(localObject) != null) && (!isVertex(localObject))) {
          localObject = this.model.getParent(localObject);
        }
      }
      return null;
      if (isVertex(localObject)) {
        return localObject;
      }
      return null;
    }
    return localObject;
  }
  
  public Object getTarget(Object paramObject)
  {
    Object localObject = null;
    localObject = DefaultGraphModel.getTargetVertex(this.model, paramObject);
    if ((localObject != null) && (!isVertex(localObject)))
    {
      if (this.edgePromotion) {
        while ((this.model.getParent(localObject) != null) && (!isVertex(localObject))) {
          localObject = this.model.getParent(localObject);
        }
      }
      return null;
      if (isVertex(localObject)) {
        return localObject;
      }
      return null;
    }
    return localObject;
  }
  
  public Object getSourcePort(Object paramObject)
  {
    Object localObject = null;
    localObject = this.model.getSource(paramObject);
    return localObject;
  }
  
  public Object getTargetPort(Object paramObject)
  {
    Object localObject = null;
    localObject = this.model.getTarget(paramObject);
    return localObject;
  }
  
  protected List getAll()
  {
    return DefaultGraphModel.getDescendants(this.model, DefaultGraphModel.getRoots(this.model));
  }
  
  protected Collection getCells(Collection paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    if ((paramBoolean2) && (this.order != null)) {
      localObject1 = new TreeSet(this.order);
    } else {
      localObject1 = new LinkedHashSet();
    }
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      if ((paramBoolean1) && (isEdge(localObject2)) && (getSource(localObject2) != null) && (getTarget(localObject2) != null)) {
        ((Set)localObject1).add(localObject2);
      }
      if ((!paramBoolean1) && (isVertex(localObject2))) {
        ((Set)localObject1).add(localObject2);
      }
    }
    return (Collection)localObject1;
  }
  
  public Object getCellView(Object paramObject)
  {
    if (this.graphLayoutCache != null)
    {
      CellView localCellView = this.graphLayoutCache.getMapping(paramObject, false);
      return localCellView;
    }
    return null;
  }
  
  public Collection getVertices(Collection paramCollection, boolean paramBoolean)
  {
    return getCells(paramCollection, false, paramBoolean);
  }
  
  public List getOutgoingEdges(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    return this.graphLayoutCache.getOutgoingEdges(paramObject, paramSet, paramBoolean1, paramBoolean2);
  }
  
  public List getIncomingEdges(Object paramObject, Set paramSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    LinkedList localLinkedList = new LinkedList();
    Object localObject1;
    if ((this.graphLayoutCache != null) && (this.graphLayoutCache.isPartial()) && (this.edgePromotion))
    {
      localObject1 = getHiddenChildren(paramObject, false);
      Iterator localIterator1 = ((Set)localObject1).iterator();
      HashSet localHashSet = new HashSet();
      while (localIterator1.hasNext())
      {
        Object localObject2 = localIterator1.next();
        List localList = this.graphLayoutCache.getIncomingEdges(localObject2, paramSet, false, paramBoolean2);
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
        {
          Object localObject3 = localIterator2.next();
          Object localObject4 = this.model.getSource(localObject3);
          Object localObject5 = null;
          if (DefaultGraphModel.isVertex(this.model, localObject4)) {
            localObject5 = localObject4;
          } else {
            localObject5 = this.model.getParent(localObject4);
          }
          if (!((Set)localObject1).contains(localObject5)) {
            if ((this.graphLayoutCache.isVisible(localObject5)) && (paramBoolean1)) {
              localHashSet.add(localObject3);
            } else if (this.graphLayoutCache.isVisible(localObject5)) {
              localHashSet.add(localObject3);
            }
          }
        }
      }
      localLinkedList.addAll(localHashSet);
    }
    else
    {
      localObject1 = this.graphLayoutCache.getIncomingEdges(paramObject, paramSet, paramBoolean1, paramBoolean2);
      localLinkedList.addAll((Collection)localObject1);
    }
    return localLinkedList;
  }
  
  /**
   * @deprecated
   */
  public Map createNestedMap(Map paramMap)
  {
    Map localMap = createNestedMap(false, false);
    return GraphConstants.merge(paramMap, localMap);
  }
  
  public Map createNestedMap(boolean paramBoolean1, boolean paramBoolean2)
  {
    return createNestedMap(paramBoolean1, paramBoolean2 ? new Point2D.Double(0.0D, 0.0D) : null);
  }
  
  public Map createNestedMap(boolean paramBoolean, Point2D paramPoint2D)
  {
    Rectangle2D localRectangle2D = getCellBounds();
    if (localRectangle2D == null) {
      return null;
    }
    if (paramPoint2D != null) {
      translateCells(getAttributes().keySet(), -localRectangle2D.getX() + paramPoint2D.getX(), -localRectangle2D.getY() + paramPoint2D.getY());
    } else if (((this.graph == null) || (!this.graph.isMoveBelowZero())) && ((localRectangle2D.getX() < 0.0D) || (localRectangle2D.getY() < 0.0D))) {
      scale(getAttributes().keySet(), 1.0D, 1.0D, localRectangle2D.getX() < 0.0D ? Math.abs(localRectangle2D.getX()) : 0.0D, localRectangle2D.getY() < 0.0D ? Math.abs(localRectangle2D.getY()) : 0.0D);
    }
    Hashtable localHashtable1 = new Hashtable();
    Iterator localIterator = getAttributes().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = localEntry.getKey();
      Hashtable localHashtable2 = new Hashtable((Map)localEntry.getValue());
      if ((!paramBoolean) && (this.graph != null)) {
        this.graph.snap(GraphConstants.getBounds(localHashtable2));
      }
      localHashtable1.put(localObject, localHashtable2);
    }
    return localHashtable1;
  }
  
  public List getComponents()
  {
    LinkedList localLinkedList = new LinkedList();
    Object[] arrayOfObject = getVertices().toArray();
    for (int i = 0; i < arrayOfObject.length; i++)
    {
      int j = 1;
      Iterator localIterator1 = localLinkedList.iterator();
      while ((j != 0) && (localIterator1.hasNext())) {
        if (((Set)localIterator1.next()).contains(arrayOfObject[i])) {
          j = 0;
        }
      }
      if (j != 0)
      {
        Stack localStack = new Stack();
        localStack.push(arrayOfObject[i]);
        HashSet localHashSet = new HashSet();
        while (!localStack.isEmpty())
        {
          Object localObject = localStack.pop();
          if (!localHashSet.contains(localObject))
          {
            localHashSet.add(localObject);
            boolean bool = isDirected();
            setDirected(false);
            Iterator localIterator2 = getNeighbours(localObject, localHashSet, false).iterator();
            setDirected(bool);
            while (localIterator2.hasNext()) {
              localStack.push(localIterator2.next());
            }
          }
        }
        localLinkedList.add(localHashSet);
      }
    }
    return localLinkedList;
  }
  
  public double norm(Point2D paramPoint2D)
  {
    double d1 = paramPoint2D.getX();
    double d2 = paramPoint2D.getY();
    double d3 = Math.sqrt(d1 * d1 + d2 * d2);
    return d3;
  }
  
  public Hashtable getAttributes()
  {
    return this.attributes;
  }
  
  public void setAttributes(Hashtable paramHashtable)
  {
    this.attributes = paramHashtable;
  }
  
  public Map getAttributes(Object paramObject)
  {
    AttributeMap localAttributeMap = (AttributeMap)getAttributes().get(paramObject);
    if (localAttributeMap == null)
    {
      CellView localCellView = null;
      Rectangle2D localRectangle2D = null;
      if (this.graphLayoutCache != null) {
        localCellView = this.graphLayoutCache.getMapping(paramObject, false);
      }
      if (localCellView != null)
      {
        localAttributeMap = localCellView.getAllAttributes();
        localRectangle2D = (Rectangle2D)localCellView.getBounds().clone();
      }
      if (localAttributeMap == null) {
        localAttributeMap = this.model.getAttributes(paramObject);
      }
      if (localAttributeMap != null)
      {
        localAttributeMap = (AttributeMap)localAttributeMap.clone();
        if (localRectangle2D != null) {
          GraphConstants.setBounds(localAttributeMap, localRectangle2D);
        }
        getAttributes().put(paramObject, localAttributeMap);
      }
    }
    return localAttributeMap;
  }
  
  public boolean isMoveable(Object paramObject)
  {
    return GraphConstants.isMoveable(getAttributes(paramObject));
  }
  
  public void setAttributes(Object paramObject, Map paramMap)
  {
    getAttributes().put(paramObject, paramMap);
  }
  
  public Rectangle2D getBounds(List paramList)
  {
    Rectangle2D localRectangle2D1 = null;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Rectangle2D localRectangle2D2 = getBounds(localIterator.next());
      if (localRectangle2D2 != null) {
        if (localRectangle2D1 == null) {
          localRectangle2D1 = (Rectangle2D)localRectangle2D2.clone();
        } else {
          Rectangle2D.union(localRectangle2D1, localRectangle2D2, localRectangle2D1);
        }
      }
    }
    return localRectangle2D1;
  }
  
  public Rectangle2D getGraphBounds()
  {
    return GraphLayoutCache.getBounds(this.graphLayoutCache.getCellViews());
  }
  
  public Point2D getGraphOrigin()
  {
    Object[] arrayOfObject = DefaultGraphModel.getRoots(this.model);
    if ((arrayOfObject != null) && (arrayOfObject.length > 0))
    {
      Rectangle2D localRectangle2D1 = null;
      Rectangle2D localRectangle2D2 = null;
      for (int i = 0; i < arrayOfObject.length; i++)
      {
        Object localObject;
        if (this.graphLayoutCache != null)
        {
          localObject = this.graphLayoutCache.getMapping(arrayOfObject[i], false);
          if (localObject != null) {
            localRectangle2D2 = ((CellView)localObject).getBounds();
          }
        }
        else if (this.model != null)
        {
          localObject = this.model.getAttributes(arrayOfObject[i]);
          if (localObject != null) {
            localRectangle2D2 = GraphConstants.getBounds((Map)localObject);
          }
        }
        if (localRectangle2D2 != null) {
          if (localRectangle2D1 == null) {
            localRectangle2D1 = localRectangle2D2 != null ? (Rectangle2D)localRectangle2D2.clone() : null;
          } else {
            Rectangle2D.union(localRectangle2D1, localRectangle2D2, localRectangle2D1);
          }
        }
      }
      if (localRectangle2D1 != null) {
        return new Point2D.Double(Math.max(0.0D, localRectangle2D1.getX()), Math.max(0.0D, localRectangle2D1.getY()));
      }
    }
    return null;
  }
  
  public Rectangle2D getCellBounds()
  {
    Rectangle2D localRectangle2D1 = null;
    Hashtable localHashtable = getAttributes();
    HashSet localHashSet = new HashSet(localHashtable.keySet());
    Iterator localIterator = localHashSet.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      Rectangle2D localRectangle2D2 = getBounds(localObject);
      if (localRectangle2D2 != null) {
        if (localRectangle2D1 == null) {
          localRectangle2D1 = (Rectangle2D)localRectangle2D2.clone();
        } else {
          Rectangle2D.union(localRectangle2D1, localRectangle2D2, localRectangle2D1);
        }
      }
    }
    return localRectangle2D1;
  }
  
  public void translateCells(Collection paramCollection, double paramDouble1, double paramDouble2)
  {
    scale(paramCollection, 1.0D, 1.0D, paramDouble1, paramDouble2);
  }
  
  public void scale(Rectangle2D paramRectangle2D)
  {
    Rectangle2D localRectangle2D = getCellBounds();
    double d1 = paramRectangle2D.getWidth() / localRectangle2D.getWidth();
    double d2 = paramRectangle2D.getHeight() / localRectangle2D.getHeight();
    double d3 = paramRectangle2D.getX() - localRectangle2D.getX();
    double d4 = paramRectangle2D.getY() - localRectangle2D.getY();
    scale(getAttributes().keySet(), d1, d2, d3, d4);
  }
  
  public void scale(Collection paramCollection, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    Iterator localIterator1 = paramCollection.iterator();
    while (localIterator1.hasNext())
    {
      Object localObject1 = localIterator1.next();
      Point2D localPoint2D1 = getLocation(localObject1);
      if (localPoint2D1 != null)
      {
        localPoint2D1.setLocation((localPoint2D1.getX() + paramDouble3) * paramDouble1, (localPoint2D1.getY() + paramDouble4) * paramDouble2);
        setLocation(localObject1, localPoint2D1.getX(), localPoint2D1.getY(), false);
      }
      if (isEdge(localObject1))
      {
        List localList = getPoints(localObject1);
        if (localList != null)
        {
          Iterator localIterator2 = localList.iterator();
          while (localIterator2.hasNext())
          {
            Object localObject2 = localIterator2.next();
            if ((localObject2 instanceof Point2D))
            {
              Point2D localPoint2D2 = (Point2D)localObject2;
              localPoint2D2.setLocation((localPoint2D2.getX() + paramDouble3) * paramDouble1, (localPoint2D2.getY() + paramDouble4) * paramDouble2);
            }
          }
        }
      }
    }
  }
  
  public void randomize(Collection paramCollection, int paramInt1, int paramInt2)
  {
    Random localRandom = new Random();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      if ((paramInt1 > 0) && (paramInt2 > 0))
      {
        int i = localRandom.nextInt(paramInt1);
        int j = localRandom.nextInt(paramInt2);
        setLocation(localIterator.next(), i, j);
      }
    }
  }
  
  public void tilt(Collection paramCollection, int paramInt1, int paramInt2)
  {
    Random localRandom = new Random();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      int i = localRandom.nextInt(paramInt1);
      int j = localRandom.nextInt(paramInt2);
      translate(localIterator.next(), i, j);
    }
  }
  
  public void circle(Collection paramCollection)
  {
    Dimension localDimension = getMaxSize(paramCollection);
    double d1 = Math.max(localDimension.width, localDimension.height);
    Object[] arrayOfObject = paramCollection.toArray();
    double d2 = arrayOfObject.length * d1 / 3.141592653589793D * this.circleRadiusFactor;
    double d3 = 6.283185307179586D / paramCollection.size();
    for (int i = 0; i < arrayOfObject.length; i++) {
      setLocation(arrayOfObject[i], d2 + d2 * Math.sin(i * d3), d2 + d2 * Math.cos(i * d3));
    }
  }
  
  public Rectangle2D getBounds(Object paramObject)
  {
    Map localMap = getAttributes(paramObject);
    if (isEdge(paramObject))
    {
      Object localObject1 = GraphConstants.getBounds(localMap);
      localObject2 = GraphConstants.getPoints(localMap);
      if (localObject2 != null)
      {
        localObject3 = ((List)localObject2).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          Object localObject4 = ((Iterator)localObject3).next();
          if ((localObject4 instanceof Point2D)) {
            if (localObject1 == null) {
              localObject1 = new Rectangle2D.Double(((Point2D)localObject4).getX(), ((Point2D)localObject4).getY(), 1.0D, 1.0D);
            } else {
              ((Rectangle2D)localObject1).add((Point2D)localObject4);
            }
          }
        }
      }
      setBounds(paramObject, (Rectangle2D)localObject1);
      return (Rectangle2D)localObject1;
    }
    int i = this.model.getChildCount(paramObject);
    Object localObject2 = null;
    Object localObject3 = null;
    int j = 0;
    Object localObject6;
    for (int k = 0; k < i; k++)
    {
      localObject6 = this.model.getChild(paramObject, k);
      if ((paramObject != localObject6) && (DefaultGraphModel.isVertex(this.model, localObject6)))
      {
        AttributeMap localAttributeMap = (AttributeMap)getAttributes().get(localObject6);
        Rectangle2D localRectangle2D;
        if (localAttributeMap != null)
        {
          j = 1;
          localRectangle2D = (Rectangle2D)GraphConstants.getBounds(localAttributeMap).clone();
          if (localObject2 == null) {
            localObject2 = localRectangle2D;
          } else {
            localObject2 = ((Rectangle2D)localObject2).createUnion(localRectangle2D);
          }
        }
        else
        {
          localRectangle2D = (Rectangle2D)getBounds(localObject6).clone();
          if (localObject3 == null) {
            localObject3 = localRectangle2D;
          } else {
            localObject3 = ((Rectangle2D)localObject3).createUnion(localRectangle2D);
          }
        }
      }
    }
    if (j != 0)
    {
      Object localObject5 = null;
      if ((localObject2 != null) && (localObject3 != null))
      {
        localObject6 = ((Rectangle2D)localObject2).createUnion((Rectangle2D)localObject3);
        localObject5 = localObject6;
      }
      else if ((localObject2 == null) && (localObject3 != null))
      {
        localObject5 = localObject3;
      }
      else if ((localObject2 != null) && (localObject3 == null))
      {
        localObject5 = localObject2;
      }
      int m = GraphConstants.getInset(localMap);
      if (m != 0) {
        ((Rectangle2D)localObject5).setFrame(((Rectangle2D)localObject5).getX() - m, ((Rectangle2D)localObject5).getY() - m, ((Rectangle2D)localObject5).getWidth() + m * 2, ((Rectangle2D)localObject5).getHeight() + m * 2);
      }
      setBounds(paramObject, (Rectangle2D)localObject5);
      return (Rectangle2D)localObject5;
    }
    return GraphConstants.getBounds(localMap);
  }
  
  public void setBounds(Map paramMap)
  {
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Rectangle2D localRectangle2D = GraphConstants.getBounds((Map)localEntry.getValue());
        if (localRectangle2D != null) {
          setBounds(localEntry.getKey(), (Rectangle2D)localRectangle2D.clone());
        }
      }
    }
  }
  
  public void setBounds(Object paramObject, Rectangle2D paramRectangle2D)
  {
    Map localMap = getAttributes(paramObject);
    GraphConstants.setBounds(localMap, paramRectangle2D);
  }
  
  public double[][] getLocations(Object[] paramArrayOfObject)
  {
    double[][] arrayOfDouble = new double[paramArrayOfObject.length][2];
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      Point2D localPoint2D = getLocation(paramArrayOfObject[i]);
      arrayOfDouble[i][0] = localPoint2D.getX();
      arrayOfDouble[i][1] = localPoint2D.getY();
    }
    return arrayOfDouble;
  }
  
  public double[][] getBounds(Object[] paramArrayOfObject)
  {
    double[][] arrayOfDouble = new double[paramArrayOfObject.length][4];
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      Rectangle2D localRectangle2D = getBounds(paramArrayOfObject[i]);
      arrayOfDouble[i][0] = localRectangle2D.getX();
      arrayOfDouble[i][1] = localRectangle2D.getY();
      arrayOfDouble[i][2] = localRectangle2D.getWidth();
      arrayOfDouble[i][3] = localRectangle2D.getHeight();
    }
    return arrayOfDouble;
  }
  
  public Point2D getLocation(Object paramObject)
  {
    Rectangle2D localRectangle2D = getBounds(paramObject);
    if (localRectangle2D != null) {
      return new Point2D.Double(localRectangle2D.getX(), localRectangle2D.getY());
    }
    return null;
  }
  
  public void setLocations(Object[] paramArrayOfObject, double[][] paramArrayOfDouble)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfDouble != null) && (paramArrayOfObject.length == paramArrayOfDouble.length)) {
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        setLocation(paramArrayOfObject[i], paramArrayOfDouble[i][0], paramArrayOfDouble[i][1], true);
      }
    }
  }
  
  public void setBounds(Object[] paramArrayOfObject, double[][] paramArrayOfDouble)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfDouble != null) && (paramArrayOfObject.length == paramArrayOfDouble.length)) {
      for (int i = 0; i < paramArrayOfObject.length; i++) {
        setBounds(paramArrayOfObject[i], new Rectangle2D.Double(paramArrayOfDouble[i][0], paramArrayOfDouble[i][1], paramArrayOfDouble[i][2], paramArrayOfDouble[i][3]));
      }
    }
  }
  
  public void setLocation(Object paramObject, double paramDouble1, double paramDouble2)
  {
    setLocation(paramObject, paramDouble1, paramDouble2, true);
  }
  
  public void setLocation(Object paramObject, double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    if (paramObject != null)
    {
      Object localObject1 = getBounds(paramObject);
      if ((isMoveable(paramObject)) && (localObject1 != null)) {
        if (paramBoolean)
        {
          double d1 = paramDouble1 - ((Rectangle2D)localObject1).getX();
          double d2 = paramDouble2 - ((Rectangle2D)localObject1).getY();
          int i = this.model.getChildCount(paramObject);
          for (int j = 0; j < i; j++)
          {
            Object localObject2 = this.model.getChild(paramObject, j);
            if (paramObject != localObject2) {
              translate(localObject2, d1, d2);
            }
          }
          ((Rectangle2D)localObject1).setFrame(paramDouble1, paramDouble2, ((Rectangle2D)localObject1).getWidth(), ((Rectangle2D)localObject1).getHeight());
        }
        else
        {
          ((Rectangle2D)localObject1).setFrame(paramDouble1, paramDouble2, ((Rectangle2D)localObject1).getWidth(), ((Rectangle2D)localObject1).getHeight());
        }
      }
      if (localObject1 == null)
      {
        localObject1 = new Rectangle2D.Double(paramDouble1, paramDouble2, 0.0D, 0.0D);
        setBounds(paramObject, (Rectangle2D)localObject1);
      }
    }
  }
  
  public void translate(Object paramObject, double paramDouble1, double paramDouble2)
  {
    Rectangle2D localRectangle2D = getBounds(paramObject);
    if ((isMoveable(paramObject)) && (localRectangle2D != null))
    {
      int i = this.model.getChildCount(paramObject);
      int j = 0;
      for (int k = 0; k < i; k++)
      {
        Object localObject = this.model.getChild(paramObject, k);
        if ((DefaultGraphModel.isVertex(this.model, localObject)) && (paramObject != localObject))
        {
          translate(localObject, paramDouble1, paramDouble2);
          j = 1;
        }
        else if ((this.model.isEdge(localObject)) && (paramObject != localObject))
        {
          j = 1;
        }
      }
      if (j == 0) {
        if (DefaultGraphModel.isVertex(this.model, paramObject))
        {
          localRectangle2D.setFrame(localRectangle2D.getX() + paramDouble1, localRectangle2D.getY() + paramDouble2, localRectangle2D.getWidth(), localRectangle2D.getHeight());
        }
        else
        {
          ArrayList localArrayList = new ArrayList(1);
          localArrayList.add(paramObject);
          scale(localArrayList, 1.0D, 1.0D, paramDouble1, paramDouble2);
        }
      }
    }
  }
  
  public GraphModel getModel()
  {
    return this.model;
  }
  
  public GraphLayoutCache getCache()
  {
    return this.graphLayoutCache;
  }
  
  public Dimension getMaxSize(Collection paramCollection)
  {
    Dimension localDimension = new Dimension(0, 0);
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Dimension2D localDimension2D = getSize(localIterator.next());
      if (localDimension2D != null) {
        localDimension.setSize(Math.max(localDimension.getWidth(), localDimension2D.getWidth()), Math.max(localDimension.getHeight(), localDimension2D.getHeight()));
      }
    }
    return localDimension;
  }
  
  public void setSize(Object paramObject, double paramDouble1, double paramDouble2)
  {
    Rectangle2D localRectangle2D = getBounds(paramObject);
    localRectangle2D.setFrame(localRectangle2D.getX(), localRectangle2D.getY(), paramDouble1, paramDouble2);
  }
  
  public Dimension2D getSize(Object paramObject)
  {
    Rectangle2D localRectangle2D = getBounds(paramObject);
    return new Dimension((int)localRectangle2D.getWidth(), (int)localRectangle2D.getHeight());
  }
  
  public List getPoints(Object paramObject)
  {
    Map localMap = getAttributes(paramObject);
    Object localObject = GraphConstants.getPoints(localMap);
    if (localObject == null)
    {
      localObject = new ArrayList(4);
      ((List)localObject).add(new AttributeMap.SerializablePoint2D(10.0D, 10.0D));
      ((List)localObject).add(new AttributeMap.SerializablePoint2D(20.0D, 20.0D));
    }
    return (List)localObject;
  }
  
  public void setPoints(Object paramObject, List paramList)
  {
    Map localMap = getAttributes(paramObject);
    GraphConstants.setPoints(localMap, paramList);
  }
  
  public void disableRouting(Object paramObject)
  {
    Map localMap = getAttributes(paramObject);
    GraphConstants.setRemoveAttributes(localMap, new Object[] { "routing" });
  }
  
  public Object[] getEdgesBetween(Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    if ((this.graphLayoutCache != null) && (this.graphLayoutCache.isPartial()) && (this.edgePromotion))
    {
      Set localSet1 = getHiddenChildren(paramObject1, true);
      Set localSet2 = getHiddenChildren(paramObject2, true);
      if ((localSet1.size() == 1) && (localSet2.size() == 1)) {
        return DefaultGraphModel.getEdgesBetween(this.model, paramObject1, paramObject2, paramBoolean);
      }
      Object localObject1 = null;
      Iterator localIterator1 = localSet1.iterator();
      while (localIterator1.hasNext())
      {
        Object localObject2 = localIterator1.next();
        Iterator localIterator2 = localSet2.iterator();
        while (localIterator2.hasNext())
        {
          Object localObject3 = localIterator2.next();
          Object[] arrayOfObject1 = DefaultGraphModel.getEdgesBetween(this.model, localObject2, localObject3, paramBoolean);
          if (arrayOfObject1.length > 0) {
            if (localObject1 == null)
            {
              localObject1 = arrayOfObject1;
            }
            else
            {
              Object[] arrayOfObject2 = new Object[arrayOfObject1.length + localObject1.length];
              System.arraycopy(localObject1, 0, arrayOfObject2, 0, localObject1.length);
              System.arraycopy(arrayOfObject1, 0, arrayOfObject2, localObject1.length, arrayOfObject1.length);
              localObject1 = arrayOfObject2;
            }
          }
        }
      }
      return (Object[])localObject1;
    }
    return DefaultGraphModel.getEdgesBetween(this.model, paramObject1, paramObject2, paramBoolean);
  }
  
  protected void determineLayoutHierarchies(int paramInt)
  {
    int i = 0;
    if (this.model != null)
    {
      this.groupHierarchies = new ArrayList();
      this.groupOrientations = new ArrayList();
      LinkedHashSet localLinkedHashSet = null;
      Object[] arrayOfObject = DefaultGraphModel.getRoots(this.model);
      for (int j = 0; j < arrayOfObject.length; j++) {
        if (DefaultGraphModel.isVertex(this.model, arrayOfObject[j]))
        {
          populateGroupHierarchies(arrayOfObject[j]);
          if (localLinkedHashSet == null) {
            localLinkedHashSet = new LinkedHashSet();
          }
          localLinkedHashSet.add(arrayOfObject[j]);
        }
      }
      if (localLinkedHashSet != null)
      {
        this.groupHierarchies.add(localLinkedHashSet);
        this.groupOrientations.add(Integer.valueOf(paramInt));
      }
    }
  }
  
  protected void populateGroupHierarchies(Object paramObject)
  {
    LinkedHashSet localLinkedHashSet = null;
    int i = GraphConstants.getOrientation(this.model.getAttributes(paramObject));
    if (paramObject != null) {
      for (int j = 0; j < this.model.getChildCount(paramObject); j++)
      {
        Object localObject = this.model.getChild(paramObject, j);
        if (DefaultGraphModel.isVertex(this.model, localObject))
        {
          if (localLinkedHashSet == null) {
            localLinkedHashSet = new LinkedHashSet();
          }
          localLinkedHashSet.add(localObject);
          populateGroupHierarchies(localObject);
        }
      }
    }
    if (this.groupHierarchies == null)
    {
      this.groupHierarchies = new ArrayList();
      this.groupOrientations = new ArrayList();
    }
    if (localLinkedHashSet != null)
    {
      this.groupHierarchies.add(localLinkedHashSet);
      this.groupOrientations.add(Integer.valueOf(i));
    }
  }
  
  public int getRootCount()
  {
    return this.roots.size();
  }
  
  public Object getRootAt(int paramInt)
  {
    return this.roots.get(paramInt);
  }
  
  public boolean isRoot(Object paramObject)
  {
    return this.roots.contains(paramObject);
  }
  
  public List getRoots()
  {
    return this.roots;
  }
  
  public void setRoots(List paramList)
  {
    this.roots = paramList;
  }
  
  public boolean isDirected()
  {
    return this.directed;
  }
  
  public void setDirected(boolean paramBoolean)
  {
    this.directed = paramBoolean;
  }
  
  public Comparator getOrder()
  {
    return this.order;
  }
  
  public void setOrder(Comparator paramComparator)
  {
    this.order = paramComparator;
  }
  
  public boolean IsIgnoresCellsInGroups()
  {
    return this.ignoresCellsInGroups;
  }
  
  public void setIgnoresCellsInGroups(boolean paramBoolean)
  {
    this.ignoresCellsInGroups = paramBoolean;
  }
  
  public boolean isIgnoresHiddenCells()
  {
    return this.ignoresHiddenCells;
  }
  
  public void setIgnoresHiddenCells(boolean paramBoolean)
  {
    if (this.graphLayoutCache != null) {
      this.ignoresHiddenCells = paramBoolean;
    } else {
      this.ignoresHiddenCells = false;
    }
  }
  
  public boolean isIgnoresUnconnectedCells()
  {
    return this.ignoresUnconnectedCells;
  }
  
  public void setIgnoresUnconnectedCells(boolean paramBoolean)
  {
    this.ignoresUnconnectedCells = paramBoolean;
  }
  
  public boolean isEdgePromotion()
  {
    return this.edgePromotion;
  }
  
  public void setEdgePromotion(boolean paramBoolean)
  {
    this.edgePromotion = paramBoolean;
  }
  
  public Set getVerticesFilter()
  {
    return this.verticesFilter;
  }
  
  public void setVerticesFilter(Set paramSet)
  {
    this.verticesFilter = paramSet;
  }
  
  public List getGroupHierarchies()
  {
    return this.groupHierarchies;
  }
  
  public void setGroupHierarchies(List paramList)
  {
    this.groupHierarchies = paramList;
  }
  
  public double getCircleRadiusFactor()
  {
    return this.circleRadiusFactor;
  }
  
  public void setCircleRadiusFactor(double paramDouble)
  {
    this.circleRadiusFactor = paramDouble;
  }
  
  public void dfs(Object paramObject, CellVisitor paramCellVisitor)
  {
    if (isVertex(paramObject)) {
      dfs(null, paramObject, null, paramCellVisitor, new HashSet(), 0, 0);
    }
  }
  
  public void dfs(Object paramObject1, Object paramObject2, Object paramObject3, CellVisitor paramCellVisitor, Set paramSet, int paramInt1, int paramInt2)
  {
    if ((paramObject2 != null) && (!paramSet.contains(paramObject2)))
    {
      paramSet.add(paramObject2);
      paramCellVisitor.visit(paramObject1, paramObject2, paramObject3, paramInt1, paramInt2);
      paramInt2 = 0;
      Object localObject1 = null;
      Iterator localIterator = getNeighbours(paramObject2, paramSet, this.ordered).iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = localIterator.next();
        if ((isVertex(localObject2)) && (!isRoot(localObject2)))
        {
          dfs(paramObject2, localObject2, localObject1, paramCellVisitor, paramSet, paramInt1 + 1, paramInt2);
          localObject1 = localObject2;
          paramInt2++;
        }
      }
    }
  }
  
  public void dfs(Object paramObject1, Object paramObject2, Object paramObject3, CellVisitor paramCellVisitor, Set paramSet1, Set paramSet2, int paramInt1, int paramInt2)
  {
    if (paramObject2 != null)
    {
      if (paramObject1 != null) {
        paramSet2.add(paramObject1);
      }
      paramCellVisitor.visit(paramObject1, paramObject2, paramObject3, paramInt1, paramInt2);
      if (!paramSet1.contains(paramObject2))
      {
        paramSet1.add(paramObject2);
        paramInt2 = 0;
        Object localObject1 = null;
        Iterator localIterator = getNeighbours(paramObject2, paramSet1, true).iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = localIterator.next();
          if ((isVertex(localObject2)) && (!isRoot(localObject2)))
          {
            dfs(paramObject2, localObject2, localObject1, paramCellVisitor, paramSet1, new HashSet(paramSet2), paramInt1 + 1, paramInt2);
            localObject1 = localObject2;
            paramInt2++;
          }
        }
      }
    }
  }
  
  public void bfs(Object paramObject, CellVisitor paramCellVisitor)
  {
    int i = 1;
    int j = 0;
    int k = 0;
    Stack localStack = new Stack();
    localStack.push(paramObject);
    HashSet localHashSet = new HashSet();
    while (!localStack.isEmpty())
    {
      Object localObject1 = localStack.pop();
      if (!localHashSet.contains(localObject1))
      {
        localHashSet.add(paramObject);
        paramCellVisitor.visit(null, localObject1, null, k, 0);
        Iterator localIterator = getNeighbours(localObject1, localHashSet, true).iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = localIterator.next();
          if (!localHashSet.contains(localObject2))
          {
            localStack.push(localObject2);
            j++;
          }
        }
      }
      i--;
      i--;
      if (i <= 0)
      {
        i = j;
        j = 0;
        k++;
      }
    }
  }
  
  public void findTreeRoots()
  {
    Object[] arrayOfObject = getCells(getAll(), false, false).toArray();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    Object localObject = null;
    for (int j = 0; j < arrayOfObject.length; j++)
    {
      int k = getIncomingEdges(arrayOfObject[j], null, true, false).size();
      int m = getOutgoingEdges(arrayOfObject[j], null, true, false).size();
      if (k == 0) {
        localArrayList.add(arrayOfObject[j]);
      }
      int n = m - k;
      if (n >= i)
      {
        localObject = arrayOfObject[j];
        i = n;
      }
    }
    if ((localArrayList.isEmpty()) && (localObject != null)) {
      localArrayList.add(localObject);
    }
    this.roots = localArrayList;
  }
  
  public boolean isOrdered()
  {
    return this.ordered;
  }
  
  public void setOrdered(boolean paramBoolean)
  {
    this.ordered = paramBoolean;
  }
  
  public void setLoggerLevel(Level paramLevel)
  {
    try
    {
      logger.setLevel(paramLevel);
    }
    catch (SecurityException localSecurityException) {}
  }
  
  public class DefaultComparator
    implements Comparator
  {
    public DefaultComparator() {}
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      Object localObject1 = JGraphFacade.this.model.getParent(paramObject1);
      Object localObject2 = JGraphFacade.this.model.getParent(paramObject2);
      int i = localObject1 == null ? JGraphFacade.this.model.getIndexOfRoot(paramObject1) : JGraphFacade.this.model.getIndexOfChild(localObject1, paramObject1);
      int j = localObject2 == null ? JGraphFacade.this.model.getIndexOfRoot(paramObject2) : JGraphFacade.this.model.getIndexOfChild(localObject2, paramObject2);
      return new Integer(i).compareTo(new Integer(j));
    }
  }
  
  public static abstract interface CellVisitor
  {
    public abstract void visit(Object paramObject1, Object paramObject2, Object paramObject3, int paramInt1, int paramInt2);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/JGraphFacade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */