package com.jgraph.layout.tree;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphFacade.CellVisitor;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class JGraphCompactTreeLayout
  extends JGraphAbstractTreeLayout
{
  /**
   * @deprecated
   */
  public static final int LEFT_TO_RIGHT = 7;
  /**
   * @deprecated
   */
  public static final int UP_TO_DOWN = 1;
  /**
   * @deprecated
   */
  public static final int DEFAULT_ORIENTATION = 7;
  
  public JGraphCompactTreeLayout()
  {
    this.orientation = 7;
    this.levelDistance = 30.0D;
    this.nodeDistance = 5.0D;
  }
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    super.run(paramJGraphFacade);
    for (int i = 0; i < paramJGraphFacade.getRootCount(); i++)
    {
      this.nodes.clear();
      paramJGraphFacade.dfs(paramJGraphFacade.getRootAt(i), new JGraphFacade.CellVisitor()
      {
        public void visit(Object paramAnonymousObject1, Object paramAnonymousObject2, Object paramAnonymousObject3, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          JGraphCompactTreeLayout.CompactTreeNode localCompactTreeNode1 = JGraphCompactTreeLayout.this.getTreeLayoutNode(paramAnonymousObject1);
          JGraphCompactTreeLayout.CompactTreeNode localCompactTreeNode2 = JGraphCompactTreeLayout.this.getTreeLayoutNode(paramAnonymousObject2);
          if (localCompactTreeNode1 != null) {
            if (paramAnonymousObject3 == null)
            {
              localCompactTreeNode1.child = localCompactTreeNode2;
            }
            else
            {
              JGraphCompactTreeLayout.CompactTreeNode localCompactTreeNode3 = JGraphCompactTreeLayout.this.getTreeLayoutNode(paramAnonymousObject3);
              localCompactTreeNode3.sibling = localCompactTreeNode2;
            }
          }
        }
      });
      layoutTree(getTreeLayoutNode(paramJGraphFacade.getRootAt(i)));
      dispatchResult(this.nodes.values());
    }
  }
  
  protected void layoutTree(CompactTreeNode paramCompactTreeNode)
  {
    layout(paramCompactTreeNode);
    Rectangle2D localRectangle2D = this.graph.getBounds(paramCompactTreeNode.getCell());
    double d1 = localRectangle2D.getX();
    double d2 = localRectangle2D.getY();
    switch (this.orientation)
    {
    case 7: 
      leftRightNodeLayout(paramCompactTreeNode, d1, d2);
      break;
    case 1: 
      upDownNodeLayout(paramCompactTreeNode, null, d1, d2);
      break;
    default: 
      leftRightNodeLayout(paramCompactTreeNode, d1, d2);
    }
  }
  
  protected CompactTreeNode getTreeLayoutNode(Object paramObject)
  {
    if (paramObject != null) {
      return getTreeLayoutNode(paramObject, true);
    }
    return null;
  }
  
  protected CompactTreeNode getTreeLayoutNode(Object paramObject, boolean paramBoolean)
  {
    CompactTreeNode localCompactTreeNode = (CompactTreeNode)this.nodes.get(paramObject);
    if ((localCompactTreeNode == null) && (paramBoolean))
    {
      localCompactTreeNode = new CompactTreeNode(paramObject);
      this.nodes.put(paramObject, localCompactTreeNode);
    }
    return localCompactTreeNode;
  }
  
  protected void dispatchResult(Collection paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      CompactTreeNode localCompactTreeNode = (CompactTreeNode)localIterator.next();
      this.graph.setLocation(localCompactTreeNode.getCell(), localCompactTreeNode.x, localCompactTreeNode.y);
    }
  }
  
  protected void layout(CompactTreeNode paramCompactTreeNode)
  {
    if (paramCompactTreeNode == null) {
      return;
    }
    for (CompactTreeNode localCompactTreeNode = paramCompactTreeNode.child; localCompactTreeNode != null; localCompactTreeNode = localCompactTreeNode.sibling) {
      layout(localCompactTreeNode);
    }
    if (paramCompactTreeNode.child != null) {
      attachParent(paramCompactTreeNode, join(paramCompactTreeNode));
    } else {
      layoutLeaf(paramCompactTreeNode);
    }
  }
  
  protected void attachParent(CompactTreeNode paramCompactTreeNode, double paramDouble)
  {
    double d1 = this.nodeDistance + this.levelDistance;
    double d3 = (paramDouble - paramCompactTreeNode.width) / 2.0D - this.nodeDistance;
    double d2 = d3 + paramCompactTreeNode.width + 2.0D * this.nodeDistance - paramDouble;
    paramCompactTreeNode.child.offsetX = (d1 + paramCompactTreeNode.height);
    paramCompactTreeNode.child.offsetY = d2;
    paramCompactTreeNode.contour.upperHead = new PolyLine(paramCompactTreeNode.height, 0.0D, new PolyLine(d1, d2, paramCompactTreeNode.contour.upperHead));
    paramCompactTreeNode.contour.lowerHead = new PolyLine(paramCompactTreeNode.height, 0.0D, new PolyLine(d1, d3, paramCompactTreeNode.contour.lowerHead));
  }
  
  protected void layoutLeaf(CompactTreeNode paramCompactTreeNode)
  {
    paramCompactTreeNode.contour.upperTail = new PolyLine(paramCompactTreeNode.height + 2.0D * this.nodeDistance, 0.0D, null);
    paramCompactTreeNode.contour.upperHead = paramCompactTreeNode.contour.upperTail;
    paramCompactTreeNode.contour.lowerTail = new PolyLine(0.0D, -paramCompactTreeNode.width - 2.0D * this.nodeDistance, null);
    paramCompactTreeNode.contour.lowerHead = new PolyLine(paramCompactTreeNode.height + 2.0D * this.nodeDistance, 0.0D, paramCompactTreeNode.contour.lowerTail);
  }
  
  protected double join(CompactTreeNode paramCompactTreeNode)
  {
    CompactTreeNode localCompactTreeNode = paramCompactTreeNode.child;
    paramCompactTreeNode.contour = localCompactTreeNode.contour;
    double d2;
    double d3 = d2 = localCompactTreeNode.width + 2.0D * this.nodeDistance;
    for (localCompactTreeNode = localCompactTreeNode.sibling; localCompactTreeNode != null; localCompactTreeNode = localCompactTreeNode.sibling)
    {
      double d1 = merge(paramCompactTreeNode.contour, localCompactTreeNode.contour);
      localCompactTreeNode.offsetY = (d1 + d2);
      localCompactTreeNode.offsetX = 0.0D;
      d2 = localCompactTreeNode.width + 2.0D * this.nodeDistance;
      d3 += d1 + d2;
    }
    return d3;
  }
  
  protected double merge(Polygon paramPolygon1, Polygon paramPolygon2)
  {
    double d3;
    double d2;
    double d1 = d2 = d3 = 0.0D;
    PolyLine localPolyLine2 = paramPolygon1.lowerHead;
    PolyLine localPolyLine1 = paramPolygon2.upperHead;
    while ((localPolyLine1 != null) && (localPolyLine2 != null))
    {
      double d4 = offset(d1, d2, localPolyLine1.dx, localPolyLine1.dy, localPolyLine2.dx, localPolyLine2.dy);
      d2 += d4;
      d3 += d4;
      if (d1 + localPolyLine1.dx <= localPolyLine2.dx)
      {
        d2 += localPolyLine1.dy;
        d1 += localPolyLine1.dx;
        localPolyLine1 = localPolyLine1.link;
      }
      else
      {
        d2 -= localPolyLine2.dy;
        d1 -= localPolyLine2.dx;
        localPolyLine2 = localPolyLine2.link;
      }
    }
    PolyLine localPolyLine3;
    if (localPolyLine1 != null)
    {
      localPolyLine3 = bridge(paramPolygon1.upperTail, 0.0D, 0.0D, localPolyLine1, d1, d2);
      paramPolygon1.upperTail = (localPolyLine3.link != null ? paramPolygon2.upperTail : localPolyLine3);
      paramPolygon1.lowerTail = paramPolygon2.lowerTail;
    }
    else
    {
      localPolyLine3 = bridge(paramPolygon2.lowerTail, d1, d2, localPolyLine2, 0.0D, 0.0D);
      if (localPolyLine3.link == null) {
        paramPolygon1.lowerTail = localPolyLine3;
      }
    }
    paramPolygon1.lowerHead = paramPolygon2.lowerHead;
    return d3;
  }
  
  protected double offset(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    if ((paramDouble5 <= paramDouble1) || (paramDouble1 + paramDouble3 <= 0.0D)) {
      return 0.0D;
    }
    double d3 = paramDouble5 * paramDouble4 - paramDouble3 * paramDouble6;
    double d2;
    double d1;
    if (d3 > 0.0D)
    {
      if (paramDouble1 < 0.0D)
      {
        d2 = paramDouble1 * paramDouble4;
        d1 = d2 / paramDouble3 - paramDouble2;
      }
      else if (paramDouble1 > 0.0D)
      {
        d2 = paramDouble1 * paramDouble6;
        d1 = d2 / paramDouble5 - paramDouble2;
      }
      else
      {
        d1 = -paramDouble2;
      }
    }
    else if (paramDouble5 < paramDouble1 + paramDouble3)
    {
      d2 = (paramDouble5 - paramDouble1) * paramDouble4;
      d1 = paramDouble6 - (paramDouble2 + d2 / paramDouble3);
    }
    else if (paramDouble5 > paramDouble1 + paramDouble3)
    {
      d2 = (paramDouble3 + paramDouble1) * paramDouble6;
      d1 = d2 / paramDouble5 - (paramDouble2 + paramDouble4);
    }
    else
    {
      d1 = paramDouble6 - (paramDouble2 + paramDouble4);
    }
    if (d1 > 0.0D) {
      return d1;
    }
    return 0.0D;
  }
  
  protected PolyLine bridge(PolyLine paramPolyLine1, double paramDouble1, double paramDouble2, PolyLine paramPolyLine2, double paramDouble3, double paramDouble4)
  {
    double d2 = paramDouble3 + paramPolyLine2.dx - paramDouble1;
    double d1;
    if (paramPolyLine2.dx == 0.0D)
    {
      d1 = paramPolyLine2.dy;
    }
    else
    {
      double d3 = d2 * paramPolyLine2.dy;
      d1 = d3 / paramPolyLine2.dx;
    }
    PolyLine localPolyLine = new PolyLine(d2, d1, paramPolyLine2.link);
    paramPolyLine1.link = new PolyLine(0.0D, paramDouble4 + paramPolyLine2.dy - d1 - paramDouble2, localPolyLine);
    return localPolyLine;
  }
  
  protected void branch(CompactTreeNode paramCompactTreeNode1, CompactTreeNode paramCompactTreeNode2, CompactTreeNode paramCompactTreeNode3)
  {
    unzip(paramCompactTreeNode1);
    paramCompactTreeNode2.parent = paramCompactTreeNode1;
    if (paramCompactTreeNode3 != null)
    {
      paramCompactTreeNode2.sibling = paramCompactTreeNode3.sibling;
      paramCompactTreeNode3.sibling = paramCompactTreeNode2;
    }
    else
    {
      paramCompactTreeNode2.sibling = paramCompactTreeNode1.child;
      paramCompactTreeNode1.child = paramCompactTreeNode2;
    }
    zip(paramCompactTreeNode1);
  }
  
  protected void unzip(CompactTreeNode paramCompactTreeNode)
  {
    if (paramCompactTreeNode.parent != null) {
      unzip(paramCompactTreeNode.parent);
    }
    if (paramCompactTreeNode.child != null) {}
  }
  
  protected void zip(CompactTreeNode paramCompactTreeNode)
  {
    if (paramCompactTreeNode.child != null) {
      attachParent(paramCompactTreeNode, join(paramCompactTreeNode));
    } else {
      layoutLeaf(paramCompactTreeNode);
    }
    if (paramCompactTreeNode.parent != null) {
      zip(paramCompactTreeNode.parent);
    }
  }
  
  protected void leftRightNodeLayout(CompactTreeNode paramCompactTreeNode, double paramDouble1, double paramDouble2)
  {
    paramCompactTreeNode.x += paramDouble1 + paramCompactTreeNode.offsetX;
    paramCompactTreeNode.y += paramDouble2 + paramCompactTreeNode.offsetY;
    CompactTreeNode localCompactTreeNode1 = paramCompactTreeNode.child;
    if (localCompactTreeNode1 != null)
    {
      leftRightNodeLayout(localCompactTreeNode1, paramCompactTreeNode.x, paramCompactTreeNode.y);
      CompactTreeNode localCompactTreeNode2 = localCompactTreeNode1.sibling;
      double d = paramCompactTreeNode.y + localCompactTreeNode1.offsetY;
      while (localCompactTreeNode2 != null)
      {
        leftRightNodeLayout(localCompactTreeNode2, paramCompactTreeNode.x + localCompactTreeNode1.offsetX, d);
        d += localCompactTreeNode2.offsetY;
        localCompactTreeNode2 = localCompactTreeNode2.sibling;
      }
    }
  }
  
  protected void upDownNodeLayout(CompactTreeNode paramCompactTreeNode1, CompactTreeNode paramCompactTreeNode2, double paramDouble1, double paramDouble2)
  {
    paramCompactTreeNode1.x += paramDouble1 + paramCompactTreeNode1.offsetY;
    paramCompactTreeNode1.y += paramDouble2 + paramCompactTreeNode1.offsetX;
    CompactTreeNode localCompactTreeNode1 = paramCompactTreeNode1.child;
    if (localCompactTreeNode1 != null)
    {
      upDownNodeLayout(localCompactTreeNode1, paramCompactTreeNode1, paramCompactTreeNode1.x, paramCompactTreeNode1.y);
      CompactTreeNode localCompactTreeNode2 = localCompactTreeNode1.sibling;
      double d = paramCompactTreeNode1.x + localCompactTreeNode1.offsetY;
      while (localCompactTreeNode2 != null)
      {
        upDownNodeLayout(localCompactTreeNode2, paramCompactTreeNode1, d, paramCompactTreeNode1.y + localCompactTreeNode1.offsetX);
        d += localCompactTreeNode2.offsetY;
        localCompactTreeNode2 = localCompactTreeNode2.sibling;
      }
    }
  }
  
  public void setOrientation(int paramInt)
  {
    if ((paramInt != 1) && (paramInt != 7)) {
      throw new IllegalArgumentException("Orientation must be NORTH (1), or WEST (7)");
    }
    this.orientation = paramInt;
  }
  
  public int getOrientation()
  {
    return this.orientation;
  }
  
  /**
   * @deprecated
   */
  public void setChildParentDistance(double paramDouble)
  {
    setLevelDistance(paramDouble);
  }
  
  /**
   * @deprecated
   */
  public double getChildParentDistance()
  {
    return getLevelDistance();
  }
  
  public double getNodeBorder()
  {
    return this.nodeDistance;
  }
  
  public void setNodeBorder(double paramDouble)
  {
    if (paramDouble < 0.0D) {
      paramDouble = 0.0D;
    }
    this.nodeDistance = paramDouble;
  }
  
  public String toString()
  {
    return "Compact Tree";
  }
  
  private static class PolyLine
  {
    final double dx;
    final double dy;
    PolyLine link;
    
    PolyLine(double paramDouble1, double paramDouble2, PolyLine paramPolyLine)
    {
      this.dx = paramDouble1;
      this.dy = paramDouble2;
      this.link = paramPolyLine;
    }
  }
  
  private static class Polygon
  {
    JGraphCompactTreeLayout.PolyLine lowerHead;
    JGraphCompactTreeLayout.PolyLine lowerTail;
    JGraphCompactTreeLayout.PolyLine upperHead;
    JGraphCompactTreeLayout.PolyLine upperTail;
  }
  
  protected class CompactTreeNode
    extends JGraphAbstractTreeLayout.TreeNode
  {
    CompactTreeNode parent;
    CompactTreeNode child;
    CompactTreeNode sibling;
    protected double offsetX = 0.0D;
    protected double offsetY = 0.0D;
    JGraphCompactTreeLayout.Polygon contour = new JGraphCompactTreeLayout.Polygon(null);
    
    public CompactTreeNode(Object paramObject)
    {
      super(paramObject);
    }
    
    public Object getCell()
    {
      return this.cell;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/tree/JGraphCompactTreeLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */