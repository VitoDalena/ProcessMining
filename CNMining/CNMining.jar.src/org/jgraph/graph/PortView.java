package org.jgraph.graph;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class PortView
  extends AbstractCellView
{
  public static transient int SIZE = 6;
  public static transient PortRenderer renderer = new PortRenderer();
  public static boolean allowPortMagic = true;
  protected transient CellView lastParent;
  
  public PortView() {}
  
  public PortView(Object paramObject)
  {
    super(paramObject);
  }
  
  public CellView getParentView()
  {
    CellView localCellView = super.getParentView();
    if (localCellView == null) {
      localCellView = this.lastParent;
    } else {
      this.lastParent = localCellView;
    }
    return localCellView;
  }
  
  public Rectangle2D getBounds()
  {
    Point2D localPoint2D = getLocation();
    double d1 = 0.0D;
    double d2 = 0.0D;
    if (localPoint2D != null)
    {
      d1 = localPoint2D.getX();
      d2 = localPoint2D.getY();
    }
    Rectangle2D.Double localDouble = new Rectangle2D.Double(d1, d2, 0.0D, 0.0D);
    localDouble.setFrame(localDouble.getX() - getPortSize() / 2, localDouble.getY() - getPortSize() / 2, getPortSize(), getPortSize());
    return localDouble;
  }
  
  public CellViewRenderer getRenderer()
  {
    return renderer;
  }
  
  public CellHandle getHandle(GraphContext paramGraphContext)
  {
    return null;
  }
  
  public Point2D getLocation()
  {
    return getLocation(null, null);
  }
  
  public Point2D getLocation(EdgeView paramEdgeView)
  {
    return getLocation(paramEdgeView, null);
  }
  
  public Point2D getLocation(EdgeView paramEdgeView, Point2D paramPoint2D)
  {
    CellView localCellView = getParentView();
    Object localObject1 = null;
    if (localCellView != null)
    {
      Object localObject2 = null;
      Point2D localPoint2D = GraphConstants.getOffset(this.allAttributes);
      if ((paramEdgeView == null) && (localPoint2D == null)) {
        localObject1 = getCenterPoint(localCellView);
      }
      if (localPoint2D != null)
      {
        double d1 = localPoint2D.getX();
        double d2 = localPoint2D.getY();
        Rectangle2D localRectangle2D2 = localCellView.getBounds();
        boolean bool1 = GraphConstants.isAbsoluteX(this.allAttributes);
        boolean bool2 = GraphConstants.isAbsoluteY(this.allAttributes);
        if (!bool1) {
          d1 = d1 * (localRectangle2D2.getWidth() - 1.0D) / 1000.0D;
        }
        if (!bool2) {
          d2 = d2 * (localRectangle2D2.getHeight() - 1.0D) / 1000.0D;
        }
        localObject1 = localObject2 != null ? ((PortView)localObject2).getLocation(paramEdgeView, paramPoint2D) : new Point2D.Double(localRectangle2D2.getX(), localRectangle2D2.getY());
        localObject1 = new Point2D.Double(((Point2D)localObject1).getX() + d1, ((Point2D)localObject1).getY() + d2);
      }
      else if (paramEdgeView != null)
      {
        if (paramPoint2D == null) {
          return getCenterPoint(localCellView);
        }
        localObject1 = localCellView.getPerimeterPoint(paramEdgeView, (Point2D)localObject1, paramPoint2D);
        if ((shouldInvokePortMagic(paramEdgeView)) && (paramPoint2D != null))
        {
          Rectangle2D localRectangle2D1 = localCellView.getBounds();
          if ((paramPoint2D.getX() >= localRectangle2D1.getX()) && (paramPoint2D.getX() <= localRectangle2D1.getX() + localRectangle2D1.getWidth())) {
            ((Point2D)localObject1).setLocation(paramPoint2D.getX(), ((Point2D)localObject1).getY());
          } else if ((paramPoint2D.getY() >= localRectangle2D1.getY()) && (paramPoint2D.getY() <= localRectangle2D1.getY() + localRectangle2D1.getHeight())) {
            ((Point2D)localObject1).setLocation(((Point2D)localObject1).getX(), paramPoint2D.getY());
          }
          if (paramPoint2D.getX() < localRectangle2D1.getX()) {
            ((Point2D)localObject1).setLocation(localRectangle2D1.getX(), ((Point2D)localObject1).getY());
          } else if (paramPoint2D.getX() > localRectangle2D1.getX() + localRectangle2D1.getWidth()) {
            ((Point2D)localObject1).setLocation(localRectangle2D1.getX() + localRectangle2D1.getWidth(), ((Point2D)localObject1).getY());
          }
          if (paramPoint2D.getY() < localRectangle2D1.getY()) {
            ((Point2D)localObject1).setLocation(((Point2D)localObject1).getX(), localRectangle2D1.getY());
          } else if (paramPoint2D.getY() > localRectangle2D1.getY() + localRectangle2D1.getHeight()) {
            ((Point2D)localObject1).setLocation(((Point2D)localObject1).getX(), localRectangle2D1.getY() + localRectangle2D1.getHeight());
          }
        }
      }
    }
    return (Point2D)localObject1;
  }
  
  protected boolean shouldInvokePortMagic(EdgeView paramEdgeView)
  {
    return (allowPortMagic) && (!(getParentView() instanceof EdgeView)) && (paramEdgeView.getPointCount() > 2) && (GraphConstants.getLineStyle(paramEdgeView.getAllAttributes()) == 11);
  }
  
  public int getPortSize()
  {
    return SIZE;
  }
  
  public void setPortSize(int paramInt)
  {
    SIZE = paramInt;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/graph/PortView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */