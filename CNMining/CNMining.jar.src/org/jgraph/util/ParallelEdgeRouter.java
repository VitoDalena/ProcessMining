package org.jgraph.util;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jgraph.JGraph;
import org.jgraph.graph.AbstractCellView;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultEdge.LoopRouting;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

public class ParallelEdgeRouter
  extends DefaultEdge.LoopRouting
{
  protected static final ParallelEdgeRouter sharedInstance = new ParallelEdgeRouter();
  private static double edgeSeparation = 10.0D;
  private static double edgeDeparture = 10.0D;
  
  public static ParallelEdgeRouter getSharedInstance()
  {
    return sharedInstance;
  }
  
  public List routeEdge(GraphLayoutCache paramGraphLayoutCache, EdgeView paramEdgeView)
  {
    ArrayList localArrayList = new ArrayList();
    CellView localCellView1 = paramEdgeView.getSource();
    CellView localCellView2 = paramEdgeView.getTarget();
    if (null == localCellView1) {
      localCellView1 = paramEdgeView.getSourceParentView();
    }
    if (null == localCellView2) {
      localCellView2 = paramEdgeView.getTargetParentView();
    }
    if ((null == localCellView1) || (null == localCellView2)) {
      return null;
    }
    if (localCellView1 == localCellView2) {
      return null;
    }
    List localList = paramEdgeView.getPoints();
    Object localObject1 = localList.get(0);
    Object localObject2 = localList.get(localList.size() - 1);
    localArrayList.add(localObject1);
    Object[] arrayOfObject = getParallelEdges(paramGraphLayoutCache, paramEdgeView, localCellView1, localCellView2);
    if (arrayOfObject == null) {
      return null;
    }
    if (arrayOfObject.length >= 2)
    {
      int i = 0;
      for (int j = 0; j < arrayOfObject.length; j++)
      {
        localObject3 = arrayOfObject[j];
        if (localObject3 == paramEdgeView.getCell()) {
          i = j + 1;
        }
      }
      Object localObject3 = paramEdgeView.getTarget() != null ? paramEdgeView.getPoint(paramEdgeView.getPointCount() - 1) : AbstractCellView.getCenterPoint(localCellView2);
      if (localObject3 == null) {
        localObject3 = AbstractCellView.getCenterPoint(localCellView2);
      }
      Point2D localPoint2D1;
      if ((paramEdgeView.getSource() == null) || (paramEdgeView.getSource().getParentView() == null)) {
        localPoint2D1 = localCellView1.getPerimeterPoint(paramEdgeView, AbstractCellView.getCenterPoint(localCellView1), (Point2D)localObject3);
      } else {
        localPoint2D1 = paramEdgeView.getSource().getParentView().getPerimeterPoint(paramEdgeView, AbstractCellView.getCenterPoint(paramEdgeView.getSource().getParentView()), (paramEdgeView.getTarget() != null) && (paramEdgeView.getTarget().getParentView() != null) ? AbstractCellView.getCenterPoint(paramEdgeView.getTarget().getParentView()) : AbstractCellView.getCenterPoint(localCellView2));
      }
      Point2D localPoint2D2;
      if ((paramEdgeView.getTarget() == null) || (paramEdgeView.getTarget().getParentView() == null)) {
        localPoint2D2 = localCellView2.getPerimeterPoint(paramEdgeView, AbstractCellView.getCenterPoint(localCellView2), localPoint2D1);
      } else {
        localPoint2D2 = paramEdgeView.getTarget().getParentView().getPerimeterPoint(paramEdgeView, AbstractCellView.getCenterPoint(paramEdgeView.getTarget().getParentView()), localPoint2D1);
      }
      if ((localPoint2D1 != null) && (localPoint2D2 != null))
      {
        double d1 = localPoint2D1.getY() - localPoint2D2.getY();
        double d2 = localPoint2D1.getX() - localPoint2D2.getX();
        if ((d1 == 0.0D) && (d2 == 0.0D)) {
          return null;
        }
        double d3 = 0.0D;
        if (d1 == 0.0D)
        {
          d3 = 1.5707963267948966D;
        }
        else if (d2 == 0.0D)
        {
          d3 = 0.0D;
        }
        else
        {
          d4 = d1 / d2;
          d3 = Math.atan(-1.0D / d4);
        }
        double d4 = Math.sqrt(d2 * d2 + d1 * d1);
        double d5 = d2 / d4;
        double d6 = d1 / d4;
        double d7 = Math.max(localCellView1.getBounds().getWidth(), localCellView1.getBounds().getHeight()) / 2.0D;
        double d8 = Math.max(localCellView2.getBounds().getWidth(), localCellView2.getBounds().getHeight()) / 2.0D;
        double d9 = (Math.sqrt(d2 * d2 + d1 * d1) - d7 - d8) / 2.0D + d7;
        double d10 = edgeDeparture + d7;
        double d11 = edgeDeparture + d8;
        double d12 = edgeSeparation * Math.floor(i / 2);
        if (0 == i % 2) {
          d12 = -d12;
        }
        double d13 = d12 * Math.cos(d3);
        double d14 = d12 * Math.sin(d3);
        double d15;
        double d17;
        if (d9 <= d10)
        {
          d15 = localPoint2D1.getX() - d5 * d9;
          d17 = localPoint2D1.getY() - d6 * d9;
          Point2D.Double localDouble1 = new Point2D.Double(d13 + d15, d14 + d17);
          localArrayList.add(localDouble1);
        }
        else
        {
          d15 = localPoint2D1.getX() - d5 * d10;
          d17 = localPoint2D1.getY() - d6 * d10;
          double d18 = localPoint2D2.getX() + d5 * d11;
          double d19 = localPoint2D2.getY() + d6 * d11;
          Point2D.Double localDouble2 = new Point2D.Double(d13 + d15, d14 + d17);
          Point2D.Double localDouble3 = new Point2D.Double(d13 + d18, d14 + d19);
          localArrayList.add(localDouble2);
          localArrayList.add(localDouble3);
        }
        Point2D localPoint2D3 = paramEdgeView.getLabelPosition();
        if (localPoint2D3 != null)
        {
          double d16 = localPoint2D3.getX();
          if (d16 == 500.0D)
          {
            AttributeMap localAttributeMap = paramEdgeView.getAllAttributes();
            if (localAttributeMap != null)
            {
              if (d1 < 0.0D) {
                d12 = -d12;
              }
              int k = getPreferredLineStyle(paramEdgeView);
              if (k == -1) {
                k = GraphConstants.getLineStyle(localAttributeMap);
              }
              if ((k == 12) || (k == 13)) {
                GraphConstants.setLabelPosition(localAttributeMap, new Point2D.Double(d16, d12 * d9 / 79.0D));
              } else {
                GraphConstants.setExactSegmentLabel(localAttributeMap, true);
              }
            }
          }
        }
      }
    }
    localArrayList.add(localObject2);
    return localArrayList;
  }
  
  public static double getEdgeSeparation()
  {
    return edgeSeparation;
  }
  
  public static void setEdgeSeparation(double paramDouble)
  {
    edgeSeparation = paramDouble;
  }
  
  public static double getEdgeDeparture()
  {
    return edgeDeparture;
  }
  
  public static void setEdgeDeparture(double paramDouble)
  {
    edgeDeparture = paramDouble;
  }
  
  protected Object[] getParallelEdges(GraphLayoutCache paramGraphLayoutCache, EdgeView paramEdgeView, CellView paramCellView1, CellView paramCellView2)
  {
    GraphModel localGraphModel = paramGraphLayoutCache.getModel();
    Object localObject1 = paramCellView1.getCell();
    Object localObject2 = paramCellView2.getCell();
    Object[] arrayOfObject1 = DefaultGraphModel.getRoots(localGraphModel);
    if (arrayOfObject1.length == 0) {
      return null;
    }
    Object[] arrayOfObject2 = { localObject1, localObject2 };
    arrayOfObject2 = DefaultGraphModel.order(localGraphModel, arrayOfObject2);
    if ((arrayOfObject2 == null) || (arrayOfObject2.length < 2)) {
      return null;
    }
    localObject1 = arrayOfObject2[0];
    localObject2 = arrayOfObject2[1];
    while ((localGraphModel.getParent(localObject1) != null) && (!paramGraphLayoutCache.isVisible(localObject1))) {
      localObject1 = localGraphModel.getParent(localObject1);
    }
    while ((localGraphModel.getParent(localObject2) != null) && (!paramGraphLayoutCache.isVisible(localObject2))) {
      localObject2 = localGraphModel.getParent(localObject2);
    }
    List localList = DefaultGraphModel.getDescendants(localGraphModel, new Object[] { localObject1 });
    ArrayList localArrayList1 = new ArrayList();
    localArrayList1.add(localObject1);
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      localObject3 = localIterator1.next();
      if ((DefaultGraphModel.isVertex(localGraphModel, localObject3)) && (!paramGraphLayoutCache.isVisible(localObject3))) {
        localArrayList1.add(localObject3);
      }
    }
    Object localObject3 = DefaultGraphModel.getDescendants(localGraphModel, new Object[] { localObject2 });
    ArrayList localArrayList2 = new ArrayList();
    localArrayList2.add(localObject2);
    localIterator1 = ((List)localObject3).iterator();
    while (localIterator1.hasNext())
    {
      localObject4 = localIterator1.next();
      if ((DefaultGraphModel.isVertex(localGraphModel, localObject4)) && (!paramGraphLayoutCache.isVisible(localObject4))) {
        localArrayList2.add(localObject4);
      }
    }
    if ((localArrayList1.size() == 1) && (localArrayList2.size() == 1)) {
      return DefaultGraphModel.getEdgesBetween(localGraphModel, localObject1, localObject2, false);
    }
    Object localObject4 = null;
    Iterator localIterator2 = localArrayList1.iterator();
    while (localIterator2.hasNext())
    {
      Object localObject5 = localIterator2.next();
      Iterator localIterator3 = localArrayList2.iterator();
      while (localIterator3.hasNext())
      {
        Object localObject6 = localIterator3.next();
        Object[] arrayOfObject3 = DefaultGraphModel.getEdgesBetween(localGraphModel, localObject5, localObject6, false);
        if (arrayOfObject3.length > 0) {
          if (localObject4 == null)
          {
            localObject4 = arrayOfObject3;
          }
          else
          {
            Object[] arrayOfObject4 = new Object[arrayOfObject3.length + localObject4.length];
            System.arraycopy(localObject4, 0, arrayOfObject4, 0, localObject4.length);
            System.arraycopy(arrayOfObject3, 0, arrayOfObject4, localObject4.length, arrayOfObject3.length);
            localObject4 = arrayOfObject4;
          }
        }
      }
    }
    return (Object[])localObject4;
  }
  
  /**
   * @deprecated
   */
  public static void setGraph(JGraph paramJGraph) {}
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/util/ParallelEdgeRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */