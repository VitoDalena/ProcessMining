package com.jgraph.layout.tree;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphFacade.CellVisitor;
import com.jgraph.layout.JGraphLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JGraphRadialTreeLayout
  implements JGraphLayout
{
  private static final double TWO_PI = 6.283185307179586D;
  protected Object virtualRootCell = new Object();
  protected boolean moveRoots = false;
  protected double angleOffset = 0.5D;
  protected boolean autoRadius = false;
  protected double minradiusx = 80.0D;
  protected double minradiusy = 80.0D;
  protected double maxradiusx = 1000.0D;
  protected double maxradiusy = 1000.0D;
  protected double radiusx = 100.0D;
  protected double radiusy = 100.0D;
  protected double rootx;
  protected double rooty;
  protected transient Map nodes = new Hashtable();
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    if (paramJGraphFacade.getRootCount() == 0) {
      paramJGraphFacade.findTreeRoots();
    }
    this.nodes.clear();
    for (int i = 0; i < paramJGraphFacade.getRootCount(); i++) {
      paramJGraphFacade.dfs(paramJGraphFacade.getRootAt(i), new JGraphFacade.CellVisitor()
      {
        public void visit(Object paramAnonymousObject1, Object paramAnonymousObject2, Object paramAnonymousObject3, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          if (!JGraphRadialTreeLayout.this.nodes.keySet().contains(paramAnonymousObject2))
          {
            JGraphRadialTreeLayout.TreeNode localTreeNode1 = JGraphRadialTreeLayout.this.getTreeNode(paramAnonymousObject1);
            JGraphRadialTreeLayout.TreeNode localTreeNode2 = JGraphRadialTreeLayout.this.getTreeNode(paramAnonymousObject2);
            if (localTreeNode1 != null) {
              localTreeNode1.children.add(localTreeNode2);
            }
          }
        }
      });
    }
    Object localObject1 = paramJGraphFacade.getRootCount() == 1 ? paramJGraphFacade.getRootAt(0) : null;
    TreeNode localTreeNode = getTreeNode(localObject1);
    if (null == localTreeNode) {
      return;
    }
    double d = localTreeNode.getDepth();
    Object localObject2;
    if (paramJGraphFacade.getRootCount() == 1)
    {
      localObject2 = paramJGraphFacade.getLocation(paramJGraphFacade.getRootAt(0));
      this.rootx = ((int)((Point2D)localObject2).getX());
      this.rooty = ((int)((Point2D)localObject2).getY());
    }
    else
    {
      localObject2 = paramJGraphFacade.getGraphBounds();
      if (localObject2 != null)
      {
        this.rootx = ((int)((Rectangle2D)localObject2).getX() + ((Rectangle2D)localObject2).getWidth() / 2.0D);
        this.rooty = ((int)((Rectangle2D)localObject2).getY() + ((Rectangle2D)localObject2).getHeight() / 2.0D);
      }
    }
    if (this.autoRadius)
    {
      this.radiusx = Math.min(this.maxradiusx, Math.max(this.minradiusx, this.rootx / d));
      this.radiusy = Math.min(this.maxradiusx, Math.max(this.minradiusy, this.rooty / d));
    }
    layoutTree0(paramJGraphFacade, localTreeNode);
  }
  
  public TreeNode getTreeNode(Object paramObject)
  {
    if (paramObject == null) {
      paramObject = this.virtualRootCell;
    }
    if (paramObject != null)
    {
      TreeNode localTreeNode = (TreeNode)this.nodes.get(paramObject);
      if (localTreeNode == null)
      {
        localTreeNode = new TreeNode(paramObject);
        this.nodes.put(paramObject, localTreeNode);
      }
      return localTreeNode;
    }
    return null;
  }
  
  private void layoutTree0(JGraphFacade paramJGraphFacade, TreeNode paramTreeNode)
  {
    paramTreeNode.angle = 0.0D;
    paramTreeNode.x = this.rootx;
    paramTreeNode.y = this.rooty;
    paramTreeNode.rightBisector = 0.0D;
    paramTreeNode.rightTangent = 0.0D;
    paramTreeNode.leftBisector = 6.283185307179586D;
    paramTreeNode.leftTangent = 6.283185307179586D;
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramTreeNode);
    layoutTreeN(paramJGraphFacade, 1, localArrayList);
  }
  
  private void layoutTreeN(JGraphFacade paramJGraphFacade, int paramInt, List paramList)
  {
    int i = paramJGraphFacade.getRootCount() > 1 ? 1 : 0;
    double d2 = 0.0D;
    Object localObject1 = null;
    Object localObject2 = null;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = paramList.iterator();
    while (localIterator1.hasNext())
    {
      TreeNode localTreeNode1 = (TreeNode)localIterator1.next();
      List localList = localTreeNode1.getChildren();
      double d4 = localTreeNode1.rightLimit();
      double d5 = (localTreeNode1.leftLimit() - d4) / localList.size();
      Iterator localIterator2 = localList.iterator();
      for (double d1 = this.angleOffset; localIterator2.hasNext(); d1 += 1.0D)
      {
        TreeNode localTreeNode2 = (TreeNode)localIterator2.next();
        Object localObject3 = localTreeNode2.getCell();
        localTreeNode2.angle = (d4 + d1 * d5);
        if ((this.moveRoots) || (paramInt > i))
        {
          localTreeNode2.x = (this.rootx + paramInt * this.radiusx * Math.cos(localTreeNode2.angle));
          localTreeNode2.y = (this.rooty + paramInt * this.radiusy * Math.sin(localTreeNode2.angle));
          paramJGraphFacade.setLocation(localObject3, localTreeNode2.x, localTreeNode2.y);
        }
        if (localTreeNode2.hasChildren())
        {
          localArrayList.add(localTreeNode2);
          if (null == localObject1) {
            localObject1 = localTreeNode2;
          }
          double d6 = localTreeNode2.angle - d2;
          localTreeNode2.rightBisector = (localTreeNode2.angle - d6 / 2.0D);
          if (null != localObject2) {
            ((TreeNode)localObject2).leftBisector = localTreeNode2.rightBisector;
          }
          double d7 = paramInt / (paramInt + 1.0D);
          double d8 = 2.0D * Math.asin(d7);
          localTreeNode2.leftTangent = (localTreeNode2.angle + d8);
          localTreeNode2.rightTangent = (localTreeNode2.angle - d8);
          d2 = localTreeNode2.angle;
          localObject2 = localTreeNode2;
        }
      }
    }
    if (null != localObject1)
    {
      double d3 = 6.283185307179586D - ((TreeNode)localObject2).angle;
      ((TreeNode)localObject1).rightBisector = ((((TreeNode)localObject1).angle - d3) / 2.0D);
      if (((TreeNode)localObject1).rightBisector < 0.0D) {
        ((TreeNode)localObject2).leftBisector = (((TreeNode)localObject1).rightBisector + 6.283185307179586D + 6.283185307179586D);
      } else {
        ((TreeNode)localObject2).leftBisector = (((TreeNode)localObject1).rightBisector + 6.283185307179586D);
      }
    }
    if (localArrayList.size() > 0) {
      layoutTreeN(paramJGraphFacade, paramInt + 1, localArrayList);
    }
  }
  
  public double getRadiusx()
  {
    return this.radiusx;
  }
  
  public void setRadiusx(double paramDouble)
  {
    this.radiusx = paramDouble;
  }
  
  public double getRadiusy()
  {
    return this.radiusy;
  }
  
  public void setRadiusy(double paramDouble)
  {
    this.radiusy = paramDouble;
  }
  
  public double getAngleOffset()
  {
    return this.angleOffset;
  }
  
  public void setAngleOffset(double paramDouble)
  {
    this.angleOffset = paramDouble;
  }
  
  public boolean isAutoRadius()
  {
    return this.autoRadius;
  }
  
  public void setAutoRadius(boolean paramBoolean)
  {
    this.autoRadius = paramBoolean;
  }
  
  public boolean isMoveRoots()
  {
    return this.moveRoots;
  }
  
  public void setMoveRoots(boolean paramBoolean)
  {
    this.moveRoots = paramBoolean;
  }
  
  public double getMaxradiusx()
  {
    return this.maxradiusx;
  }
  
  public void setMaxradiusx(double paramDouble)
  {
    this.maxradiusx = paramDouble;
  }
  
  public double getMaxradiusy()
  {
    return this.maxradiusy;
  }
  
  public void setMaxradiusy(double paramDouble)
  {
    this.maxradiusy = paramDouble;
  }
  
  public double getMinradiusx()
  {
    return this.minradiusx;
  }
  
  public void setMinradiusx(double paramDouble)
  {
    this.minradiusx = paramDouble;
  }
  
  public double getMinradiusy()
  {
    return this.minradiusy;
  }
  
  public void setMinradiusy(double paramDouble)
  {
    this.minradiusy = paramDouble;
  }
  
  public String toString()
  {
    return "Radialtree";
  }
  
  private static class TreeNode
  {
    private Object cell;
    private List children = new ArrayList();
    public double angle;
    public double x;
    public double y;
    public double rightBisector;
    public double leftBisector;
    public double rightTangent;
    public double leftTangent;
    
    TreeNode(Object paramObject)
    {
      this.cell = paramObject;
    }
    
    public int getDepth()
    {
      int i = 1;
      Iterator localIterator = this.children.iterator();
      while (localIterator.hasNext())
      {
        TreeNode localTreeNode = (TreeNode)localIterator.next();
        int j = localTreeNode.getDepth();
        if (j >= i) {
          i = j + 1;
        }
      }
      return i;
    }
    
    public Object getCell()
    {
      return this.cell;
    }
    
    public void addChild(TreeNode paramTreeNode)
    {
      this.children.add(paramTreeNode);
    }
    
    public List getChildren()
    {
      return this.children;
    }
    
    public boolean hasChildren()
    {
      return this.children.size() > 0;
    }
    
    public double leftLimit()
    {
      return Math.min(normalize(this.leftBisector), this.leftTangent);
    }
    
    public double rightLimit()
    {
      return Math.max(normalize(this.rightBisector), this.rightTangent);
    }
    
    private double normalize(double paramDouble)
    {
      return paramDouble;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/tree/JGraphRadialTreeLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */