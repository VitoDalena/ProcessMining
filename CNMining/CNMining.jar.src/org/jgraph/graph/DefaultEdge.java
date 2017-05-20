package org.jgraph.graph;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class DefaultEdge
  extends DefaultGraphCell
  implements Edge
{
  protected Object source;
  protected Object target;
  
  public DefaultEdge()
  {
    this(null);
  }
  
  public DefaultEdge(Object paramObject)
  {
    this(paramObject, null);
  }
  
  public DefaultEdge(Object paramObject, AttributeMap paramAttributeMap)
  {
    super(paramObject, paramAttributeMap);
  }
  
  public Object getSource()
  {
    return this.source;
  }
  
  public Object getTarget()
  {
    return this.target;
  }
  
  public void setSource(Object paramObject)
  {
    this.source = paramObject;
  }
  
  public void setTarget(Object paramObject)
  {
    this.target = paramObject;
  }
  
  public Object clone()
  {
    DefaultEdge localDefaultEdge = (DefaultEdge)super.clone();
    localDefaultEdge.source = null;
    localDefaultEdge.target = null;
    return localDefaultEdge;
  }
  
  public static class DefaultRouting
    extends DefaultEdge.LoopRouting
  {
    protected List routeEdge(GraphLayoutCache paramGraphLayoutCache, EdgeView paramEdgeView)
    {
      ArrayList localArrayList = new ArrayList();
      int i = paramEdgeView.getPointCount();
      Point2D localPoint2D = paramEdgeView.getPoint(0);
      localArrayList.add(localPoint2D);
      if ((paramEdgeView.getSource() instanceof PortView))
      {
        localArrayList.set(0, paramEdgeView.getSource());
        localPoint2D = ((PortView)paramEdgeView.getSource()).getLocation();
      }
      else if (paramEdgeView.getSource() != null)
      {
        localObject1 = paramEdgeView.getSource().getBounds();
        localPoint2D = paramEdgeView.getAttributes().createPoint(((Rectangle2D)localObject1).getCenterX(), ((Rectangle2D)localObject1).getCenterY());
      }
      Object localObject1 = paramEdgeView.getPoint(i - 1);
      CellView localCellView = paramEdgeView.getTarget();
      Object localObject2;
      if ((localCellView instanceof PortView))
      {
        localObject1 = ((PortView)localCellView).getLocation();
      }
      else if (localCellView != null)
      {
        localObject2 = localCellView.getBounds();
        localObject1 = paramEdgeView.getAttributes().createPoint(((Rectangle2D)localObject2).getCenterX(), ((Rectangle2D)localObject2).getCenterY());
      }
      if ((localPoint2D != null) && (localObject1 != null))
      {
        double d1 = Math.abs(localPoint2D.getX() - ((Point2D)localObject1).getX());
        double d2 = Math.abs(localPoint2D.getY() - ((Point2D)localObject1).getY());
        double d3 = localPoint2D.getX() + (((Point2D)localObject1).getX() - localPoint2D.getX()) / 2.0D;
        double d4 = localPoint2D.getY() + (((Point2D)localObject1).getY() - localPoint2D.getY()) / 2.0D;
        localObject2 = new Point2D[2];
        Rectangle2D localRectangle2D1 = null;
        Rectangle2D localRectangle2D2 = null;
        if ((paramEdgeView.getTarget() != null) && (paramEdgeView.getTarget().getParentView() != null) && (paramEdgeView.getSource() != null) && (paramEdgeView.getSource().getParentView() != null))
        {
          localRectangle2D1 = paramEdgeView.getTarget().getParentView().getBounds();
          localRectangle2D2 = paramEdgeView.getSource().getParentView().getBounds();
        }
        if ((localRectangle2D1 != null) && (localRectangle2D2 != null))
        {
          if (d1 > d2)
          {
            localObject2[0] = paramEdgeView.getAttributes().createPoint(d3, localPoint2D.getY());
            localObject2[1] = paramEdgeView.getAttributes().createPoint(d3, ((Point2D)localObject1).getY());
            if ((localRectangle2D1.contains(localObject2[0])) || (localRectangle2D2.contains(localObject2[0])) || (localRectangle2D1.contains(localObject2[1])) || (localRectangle2D2.contains(localObject2[1])))
            {
              localObject2[0] = paramEdgeView.getAttributes().createPoint(localPoint2D.getX(), d4);
              localObject2[1] = paramEdgeView.getAttributes().createPoint(((Point2D)localObject1).getX(), d4);
            }
          }
          else
          {
            localObject2[0] = paramEdgeView.getAttributes().createPoint(localPoint2D.getX(), d4);
            localObject2[1] = paramEdgeView.getAttributes().createPoint(((Point2D)localObject1).getX(), d4);
            if ((localRectangle2D1.contains(localObject2[0])) || (localRectangle2D2.contains(localObject2[0])) || (localRectangle2D1.contains(localObject2[1])) || (localRectangle2D2.contains(localObject2[1])))
            {
              localObject2[0] = paramEdgeView.getAttributes().createPoint(d3, localPoint2D.getY());
              localObject2[1] = paramEdgeView.getAttributes().createPoint(d3, ((Point2D)localObject1).getY());
            }
          }
          for (int j = 0; j < localObject2.length; j++) {
            if ((!localRectangle2D1.contains(localObject2[j])) && (!localRectangle2D2.contains(localObject2[j]))) {
              localArrayList.add(localObject2[j]);
            }
          }
        }
        if (localCellView != null) {
          localArrayList.add(localCellView);
        } else {
          localArrayList.add(localObject1);
        }
        return localArrayList;
      }
      return null;
    }
  }
  
  public static class LoopRouting
    implements Edge.Routing
  {
    public List route(GraphLayoutCache paramGraphLayoutCache, EdgeView paramEdgeView)
    {
      if (paramEdgeView.isLoop()) {
        return routeLoop(paramGraphLayoutCache, paramEdgeView);
      }
      return routeEdge(paramGraphLayoutCache, paramEdgeView);
    }
    
    protected List routeLoop(GraphLayoutCache paramGraphLayoutCache, EdgeView paramEdgeView)
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramEdgeView.getSource());
      CellView localCellView = paramEdgeView.getSource() != null ? paramEdgeView.getSource().getParentView() : paramEdgeView.getSourceParentView();
      if (localCellView != null)
      {
        Point2D localPoint2D = AbstractCellView.getCenterPoint(localCellView);
        Rectangle2D localRectangle2D = localCellView.getBounds();
        double d1 = localRectangle2D.getWidth();
        double d2 = localRectangle2D.getHeight() / 2.0D;
        double d3 = Math.min(20.0D, Math.max(10.0D, d1 / 8.0D));
        double d4 = Math.min(30.0D, Math.max(12.0D, Math.max(d3 + 4.0D, d2 / 2.0D)));
        localArrayList.add(paramEdgeView.getAttributes().createPoint(localPoint2D.getX() - d3, localPoint2D.getY() - d2 - d4 * 1.2D));
        localArrayList.add(paramEdgeView.getAttributes().createPoint(localPoint2D.getX(), localPoint2D.getY() - d2 - 1.5D * d4));
        localArrayList.add(paramEdgeView.getAttributes().createPoint(localPoint2D.getX() + d3, localPoint2D.getY() - d2 - d4 * 1.2D));
        localArrayList.add(paramEdgeView.getTarget());
        return localArrayList;
      }
      return null;
    }
    
    protected List routeEdge(GraphLayoutCache paramGraphLayoutCache, EdgeView paramEdgeView)
    {
      return null;
    }
    
    public int getPreferredLineStyle(EdgeView paramEdgeView)
    {
      if (paramEdgeView.isLoop()) {
        return getLoopStyle();
      }
      return getEdgeStyle();
    }
    
    protected int getLoopStyle()
    {
      return 12;
    }
    
    protected int getEdgeStyle()
    {
      return -1;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/DefaultEdge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */