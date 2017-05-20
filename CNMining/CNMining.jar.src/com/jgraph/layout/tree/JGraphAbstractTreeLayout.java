package com.jgraph.layout.tree;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class JGraphAbstractTreeLayout
  implements JGraphLayout
{
  protected transient Map nodes = new Hashtable();
  protected int orientation = 1;
  protected double levelDistance = 30.0D;
  protected double nodeDistance = 20.0D;
  protected boolean positionMultipleTrees = false;
  protected double treeDistance = 30.0D;
  protected double treeBoundary;
  protected Point2D oldOrigin;
  protected JGraphFacade graph;
  protected boolean routeTreeEdges = false;
  protected double[] lowerLevelValues;
  protected double[] upperLevelValues;
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    this.graph = paramJGraphFacade;
    this.treeBoundary = (-this.treeDistance);
    this.oldOrigin = paramJGraphFacade.getGraphOrigin();
    if (paramJGraphFacade.getRootCount() == 0) {
      paramJGraphFacade.findTreeRoots();
    }
  }
  
  public void setOrientation(int paramInt)
  {
    if ((paramInt != 1) && (paramInt != 3) && (paramInt != 5) && (paramInt != 7)) {
      throw new IllegalArgumentException("Orientation must be one of NORTH (1), EAST (3), SOUTH (5) or WEST (7)");
    }
    this.orientation = paramInt;
  }
  
  public void setLevelDistance(double paramDouble)
  {
    this.levelDistance = paramDouble;
  }
  
  public void setNodeDistance(int paramInt)
  {
    this.nodeDistance = paramInt;
  }
  
  public double getLevelDistance()
  {
    return this.levelDistance;
  }
  
  public double getNodeDistance()
  {
    return this.nodeDistance;
  }
  
  public int getOrientation()
  {
    return this.orientation;
  }
  
  public boolean isPositionMultipleTrees()
  {
    return this.positionMultipleTrees;
  }
  
  public void setPositionMultipleTrees(boolean paramBoolean)
  {
    this.positionMultipleTrees = paramBoolean;
  }
  
  public double getTreeDistance()
  {
    return this.treeDistance;
  }
  
  public void setTreeDistance(int paramInt)
  {
    this.treeDistance = paramInt;
  }
  
  public boolean getRouteTreeEdges()
  {
    return this.routeTreeEdges;
  }
  
  public void setRouteTreeEdges(boolean paramBoolean)
  {
    this.routeTreeEdges = paramBoolean;
  }
  
  protected class TreeNode
  {
    protected double width;
    protected double height;
    protected double x = 0.0D;
    protected double y = 0.0D;
    protected Object cell;
    
    public TreeNode(Object paramObject)
    {
      this.cell = paramObject;
      Rectangle2D localRectangle2D = JGraphAbstractTreeLayout.this.graph.getBounds(paramObject);
      if (localRectangle2D != null) {
        if ((JGraphAbstractTreeLayout.this.orientation == 1) || (JGraphAbstractTreeLayout.this.orientation == 5))
        {
          this.width = localRectangle2D.getWidth();
          this.height = localRectangle2D.getHeight();
        }
        else
        {
          this.width = localRectangle2D.getHeight();
          this.height = localRectangle2D.getWidth();
        }
      }
    }
    
    protected void routeEdges(Point2D paramPoint2D)
    {
      List localList1 = JGraphAbstractTreeLayout.this.graph.getIncomingEdges(this.cell, null, true, false);
      if ((localList1 != null) && (localList1.size() > 0))
      {
        Object localObject = localList1.get(0);
        List localList2 = JGraphAbstractTreeLayout.this.graph.getPoints(localObject);
        ArrayList localArrayList = new ArrayList(4);
        localArrayList.add(localList2.get(0));
        int i = (JGraphAbstractTreeLayout.this.orientation == 1) || (JGraphAbstractTreeLayout.this.orientation == 5) ? 1 : 0;
        int j = (JGraphAbstractTreeLayout.this.orientation == 1) || (JGraphAbstractTreeLayout.this.orientation == 7) ? 1 : 0;
        double d = j != 0 ? -JGraphAbstractTreeLayout.this.levelDistance / 2.0D : JGraphAbstractTreeLayout.this.levelDistance / 2.0D;
        Point2D.Double localDouble1 = new Point2D.Double(paramPoint2D.getX(), (paramPoint2D.getY() + this.y) / 2.0D + d);
        Point2D.Double localDouble2 = new Point2D.Double(paramPoint2D.getX() + this.x, (paramPoint2D.getY() + this.y) / 2.0D + d);
        localArrayList.add(localDouble1);
        localArrayList.add(localDouble2);
        localArrayList.add(localList2.get(localList2.size() - 1));
        JGraphAbstractTreeLayout.this.graph.setPoints(localObject, localArrayList);
        JGraphAbstractTreeLayout.this.graph.disableRouting(localObject);
      }
    }
    
    public double getWidth()
    {
      return this.width;
    }
    
    public void setWidth(double paramDouble)
    {
      this.width = paramDouble;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/tree/JGraphAbstractTreeLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */