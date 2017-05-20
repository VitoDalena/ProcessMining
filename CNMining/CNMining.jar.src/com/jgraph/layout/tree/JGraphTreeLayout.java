package com.jgraph.layout.tree;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphFacade.CellVisitor;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JGraphTreeLayout
  extends JGraphAbstractTreeLayout
{
  protected int alignment = 1;
  protected boolean combineLevelNodes = true;
  
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
          JGraphTreeLayout.StandardTreeNode localStandardTreeNode1 = JGraphTreeLayout.this.getTreeNode(paramAnonymousObject1);
          JGraphTreeLayout.StandardTreeNode localStandardTreeNode2 = JGraphTreeLayout.this.getTreeNode(paramAnonymousObject2);
          if (localStandardTreeNode1 != null)
          {
            localStandardTreeNode1.addChild(localStandardTreeNode2);
            localStandardTreeNode2.setParent(localStandardTreeNode1);
          }
        }
      });
      StandardTreeNode localStandardTreeNode = getTreeNode(paramJGraphFacade.getRootAt(i));
      layout(localStandardTreeNode);
      if (this.combineLevelNodes) {
        setLevelHeights(localStandardTreeNode);
      }
      if (this.positionMultipleTrees) {
        spaceMultipleTrees(localStandardTreeNode);
      }
      localStandardTreeNode.setPosition(null, 0.0D);
    }
  }
  
  protected StandardTreeNode getTreeNode(Object paramObject)
  {
    if (paramObject != null)
    {
      StandardTreeNode localStandardTreeNode = (StandardTreeNode)this.nodes.get(paramObject);
      if (localStandardTreeNode == null)
      {
        localStandardTreeNode = new StandardTreeNode(paramObject);
        this.nodes.put(paramObject, localStandardTreeNode);
      }
      return localStandardTreeNode;
    }
    return null;
  }
  
  protected void layout(StandardTreeNode paramStandardTreeNode)
  {
    if (paramStandardTreeNode.children.size() != 0)
    {
      Object localObject;
      if (paramStandardTreeNode.children.size() == 1)
      {
        localObject = (StandardTreeNode)paramStandardTreeNode.children.get(0);
        ((StandardTreeNode)localObject).setDepth(paramStandardTreeNode.getDepth() + 1);
        layout((StandardTreeNode)localObject);
        ((StandardTreeNode)localObject).leftContour.dx = ((((StandardTreeNode)localObject).width - paramStandardTreeNode.width) / 2.0D);
        ((StandardTreeNode)localObject).rightContour.dx = ((((StandardTreeNode)localObject).width - paramStandardTreeNode.width) / 2.0D);
        paramStandardTreeNode.leftContour.next = ((StandardTreeNode)localObject).leftContour;
        paramStandardTreeNode.rightContour.next = ((StandardTreeNode)localObject).rightContour;
      }
      else
      {
        localObject = paramStandardTreeNode.children.iterator();
        while (((Iterator)localObject).hasNext())
        {
          StandardTreeNode localStandardTreeNode = (StandardTreeNode)((Iterator)localObject).next();
          localStandardTreeNode.setDepth(paramStandardTreeNode.getDepth() + 1);
          layout(localStandardTreeNode);
        }
        join(paramStandardTreeNode);
      }
    }
  }
  
  protected void join(StandardTreeNode paramStandardTreeNode)
  {
    int i = 0;
    for (int j = 0; j < paramStandardTreeNode.children.size(); j++)
    {
      localObject = (StandardTreeNode)paramStandardTreeNode.children.get(j);
      for (k = j + 1; k < paramStandardTreeNode.children.size(); k++)
      {
        localStandardTreeNode2 = (StandardTreeNode)paramStandardTreeNode.children.get(k);
        m = distance(((StandardTreeNode)localObject).rightContour, localStandardTreeNode2.leftContour) / (k - j);
        i = Math.max(i, m);
      }
    }
    i = (int)(i + this.nodeDistance);
    if (paramStandardTreeNode.children.size() % 2 == 0) {
      j = (paramStandardTreeNode.children.size() / 2 - 1) * i + i / 2;
    } else {
      j = paramStandardTreeNode.children.size() / 2 * i;
    }
    Object localObject = paramStandardTreeNode.children.iterator();
    for (int k = 0; ((Iterator)localObject).hasNext(); k++) {
      ((StandardTreeNode)((Iterator)localObject).next()).x = (-j + k * i);
    }
    StandardTreeNode localStandardTreeNode1 = getLeftMostX(paramStandardTreeNode);
    StandardTreeNode localStandardTreeNode2 = getRightMostX(paramStandardTreeNode);
    paramStandardTreeNode.leftContour.next = localStandardTreeNode1.leftContour;
    paramStandardTreeNode.rightContour.next = localStandardTreeNode2.rightContour;
    StandardTreeNode localStandardTreeNode3;
    for (int m = 1; m < paramStandardTreeNode.children.size(); m++)
    {
      localStandardTreeNode3 = (StandardTreeNode)paramStandardTreeNode.children.get(m);
      merge(paramStandardTreeNode.leftContour.next, localStandardTreeNode3.leftContour, m * i + paramStandardTreeNode.width);
    }
    for (m = paramStandardTreeNode.children.size() - 2; m >= 0; m--)
    {
      localStandardTreeNode3 = (StandardTreeNode)paramStandardTreeNode.children.get(m);
      merge(paramStandardTreeNode.rightContour.next, localStandardTreeNode3.rightContour, m * i + paramStandardTreeNode.width);
    }
    i = (paramStandardTreeNode.children.size() - 1) * i / 2;
    paramStandardTreeNode.leftContour.next.dx += i - paramStandardTreeNode.width / 2.0D;
    paramStandardTreeNode.rightContour.next.dx += i - paramStandardTreeNode.width / 2.0D;
  }
  
  protected StandardTreeNode getLeftMostX(StandardTreeNode paramStandardTreeNode)
  {
    double d1 = Double.MAX_VALUE;
    int i = 0;
    Object localObject = null;
    Iterator localIterator = paramStandardTreeNode.getChildren();
    while (localIterator.hasNext())
    {
      StandardTreeNode localStandardTreeNode = (StandardTreeNode)localIterator.next();
      double d2 = localStandardTreeNode.x - localStandardTreeNode.getLeftWidth();
      if (d2 < d1)
      {
        localObject = localStandardTreeNode;
        d1 = d2;
      }
      i = 1;
    }
    if (localObject != null) {
      return (StandardTreeNode)localObject;
    }
    return i != 0 ? (StandardTreeNode)paramStandardTreeNode.children.get(0) : paramStandardTreeNode;
  }
  
  protected StandardTreeNode getRightMostX(StandardTreeNode paramStandardTreeNode)
  {
    double d1 = Double.MIN_VALUE;
    int i = 0;
    Object localObject = null;
    Iterator localIterator = paramStandardTreeNode.getChildren();
    while (localIterator.hasNext())
    {
      StandardTreeNode localStandardTreeNode = (StandardTreeNode)localIterator.next();
      double d2 = localStandardTreeNode.x + localStandardTreeNode.getRightWidth();
      if (d2 > d1)
      {
        localObject = localStandardTreeNode;
        d1 = d2;
      }
      i = 1;
    }
    if (localObject != null) {
      return (StandardTreeNode)localObject;
    }
    return i != 0 ? (StandardTreeNode)paramStandardTreeNode.children.get(0) : paramStandardTreeNode;
  }
  
  protected void merge(PolyLine paramPolyLine1, PolyLine paramPolyLine2, double paramDouble)
  {
    while (paramPolyLine1 != null)
    {
      if (paramPolyLine2.next == null) {
        return;
      }
      if (paramPolyLine1.next == null)
      {
        paramPolyLine2 = paramPolyLine2.next;
        break;
      }
      paramDouble += paramPolyLine1.dx - paramPolyLine2.dx;
      paramPolyLine1 = paramPolyLine1.next;
      paramPolyLine2 = paramPolyLine2.next;
    }
    paramPolyLine2.dx += -paramDouble;
    paramPolyLine1.next = paramPolyLine2;
  }
  
  protected int distance(PolyLine paramPolyLine1, PolyLine paramPolyLine2)
  {
    int i = 0;
    int j = 0;
    while ((paramPolyLine1 != null) && (paramPolyLine2 != null))
    {
      j = (int)(j + (paramPolyLine1.dx + paramPolyLine2.dx));
      if (j > 0)
      {
        i += j;
        j = 0;
      }
      paramPolyLine1 = paramPolyLine1.next;
      paramPolyLine2 = paramPolyLine2.next;
    }
    return i;
  }
  
  protected void setPosition(List paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      ((StandardTreeNode)localIterator.next()).setPosition(null, 0.0D);
    }
  }
  
  protected void setLevelHeights(StandardTreeNode paramStandardTreeNode)
  {
    List localList1 = paramStandardTreeNode.getNodesByLevel();
    double d = 0.0D;
    for (int i = 0; i < localList1.size(); i++)
    {
      List localList2 = (List)localList1.get(i);
      for (int j = 0; j < localList2.size(); j++) {
        d = Math.max(d, ((StandardTreeNode)localList2.get(j)).height);
      }
      for (j = 0; j < localList2.size(); j++) {
        ((StandardTreeNode)localList2.get(j)).levelheight = d;
      }
      d = 0.0D;
    }
  }
  
  protected void spaceMultipleTrees(StandardTreeNode paramStandardTreeNode)
  {
    Point2D localPoint2D = this.graph.getLocation(paramStandardTreeNode.cell);
    double d1 = 0.0D;
    double d2 = 0.0D;
    if (localPoint2D != null)
    {
      d1 = this.graph.getLocation(paramStandardTreeNode.cell).getX();
      d2 = this.graph.getLocation(paramStandardTreeNode.cell).getY();
    }
    int i;
    double d3;
    int j;
    if (this.orientation == 1)
    {
      i = paramStandardTreeNode.getLeftWidth();
      d3 = d1 - i;
      if (d3 < this.treeBoundary + this.treeDistance)
      {
        d1 = this.treeBoundary + this.treeDistance + i;
        this.graph.setLocation(paramStandardTreeNode.cell, d1, d2);
      }
      j = paramStandardTreeNode.getRightWidth();
      this.treeBoundary = (d1 + j);
    }
    if (this.orientation == 5)
    {
      i = paramStandardTreeNode.getRightWidth();
      d3 = d1 - i;
      if (d3 < this.treeBoundary + this.treeDistance)
      {
        d1 = this.treeBoundary + this.treeDistance + i;
        this.graph.setLocation(paramStandardTreeNode.cell, d1, d2);
      }
      j = paramStandardTreeNode.getLeftWidth();
      this.treeBoundary = (d1 + j);
    }
    if (this.orientation == 7)
    {
      i = paramStandardTreeNode.getLeftWidth();
      d3 = d2 - i;
      if (d3 < this.treeBoundary + this.treeDistance)
      {
        d2 = this.treeBoundary + this.treeDistance + i;
        this.graph.setLocation(paramStandardTreeNode.cell, d1, d2);
      }
      j = paramStandardTreeNode.getRightWidth();
      this.treeBoundary = (d2 + j);
    }
    if (this.orientation == 3)
    {
      i = paramStandardTreeNode.getRightWidth();
      d3 = d2 - i;
      if (d3 < this.treeBoundary + this.treeDistance)
      {
        d2 = this.treeBoundary + this.treeDistance + i;
        this.graph.setLocation(paramStandardTreeNode.cell, d1, d2);
      }
      j = paramStandardTreeNode.getLeftWidth();
      this.treeBoundary = (d2 + j + getRightMostX(paramStandardTreeNode).height);
    }
  }
  
  public int getAlignment()
  {
    return this.alignment;
  }
  
  public void setAlignment(int paramInt)
  {
    if ((paramInt != 1) && (paramInt != 0) && (paramInt != 3)) {
      throw new IllegalArgumentException("Alignment must be one of TOP, CENTER or BOTTOM");
    }
    this.alignment = paramInt;
  }
  
  public boolean isCombineLevelNodes()
  {
    return this.combineLevelNodes;
  }
  
  public void setCombineLevelNodes(boolean paramBoolean)
  {
    this.combineLevelNodes = paramBoolean;
  }
  
  public String toString()
  {
    return "Tree";
  }
  
  protected class PolyLine
  {
    double dx;
    PolyLine next;
    
    public PolyLine(double paramDouble)
    {
      this.dx = paramDouble;
    }
  }
  
  protected class StandardTreeNode
    extends JGraphAbstractTreeLayout.TreeNode
  {
    List children = new ArrayList();
    double levelheight;
    JGraphTreeLayout.PolyLine leftContour = new JGraphTreeLayout.PolyLine(JGraphTreeLayout.this, this.width / 2.0D);
    JGraphTreeLayout.PolyLine rightContour = new JGraphTreeLayout.PolyLine(JGraphTreeLayout.this, this.width / 2.0D);
    private int depth = 0;
    protected JGraphAbstractTreeLayout.TreeNode parent;
    
    public StandardTreeNode(Object paramObject)
    {
      super(paramObject);
    }
    
    public Iterator getChildren()
    {
      return this.children.iterator();
    }
    
    public int getLeftWidth()
    {
      int i = 0;
      JGraphTreeLayout.PolyLine localPolyLine = this.leftContour;
      int j = 0;
      while (localPolyLine != null)
      {
        j = (int)(j + localPolyLine.dx);
        if (j > 0)
        {
          i += j;
          j = 0;
        }
        localPolyLine = localPolyLine.next;
      }
      return i;
    }
    
    public int getRightWidth()
    {
      int i = 0;
      JGraphTreeLayout.PolyLine localPolyLine = this.rightContour;
      int j = 0;
      while (localPolyLine != null)
      {
        j = (int)(j + localPolyLine.dx);
        if (j > 0)
        {
          i += j;
          j = 0;
        }
        localPolyLine = localPolyLine.next;
      }
      return i;
    }
    
    public double getHeight()
    {
      if (this.children.isEmpty()) {
        return this.levelheight;
      }
      double d = 0.0D;
      Iterator localIterator = this.children.iterator();
      while (localIterator.hasNext()) {
        d = Math.max(d, ((StandardTreeNode)localIterator.next()).getHeight());
      }
      return d + JGraphTreeLayout.this.levelDistance + this.levelheight;
    }
    
    public void addChild(StandardTreeNode paramStandardTreeNode)
    {
      this.children.add(paramStandardTreeNode);
    }
    
    public void setPosition(Point2D paramPoint2D, double paramDouble)
    {
      double d = 0.0D;
      Object localObject = this.children.iterator();
      while (((Iterator)localObject).hasNext()) {
        d = Math.max(d, ((StandardTreeNode)((Iterator)localObject).next()).height);
      }
      localObject = JGraphTreeLayout.this.graph.getLocation(this.cell);
      if (localObject == null) {
        localObject = new Point2D.Double(0.0D, 0.0D);
      }
      if (paramPoint2D == null)
      {
        if ((JGraphTreeLayout.this.orientation == 7) || (JGraphTreeLayout.this.orientation == 3)) {
          ((Point2D)localObject).setLocation(((Point2D)localObject).getY(), ((Point2D)localObject).getX());
        }
        if ((JGraphTreeLayout.this.orientation == 1) || (JGraphTreeLayout.this.orientation == 7)) {
          paramPoint2D = new Point2D.Double(((Point2D)localObject).getX() + this.width / 2.0D, ((Point2D)localObject).getY() + this.height);
        } else if ((JGraphTreeLayout.this.orientation == 5) || (JGraphTreeLayout.this.orientation == 3)) {
          paramPoint2D = new Point2D.Double(((Point2D)localObject).getX() + this.width / 2.0D, ((Point2D)localObject).getY());
        }
        localIterator = this.children.iterator();
        while (localIterator.hasNext()) {
          ((StandardTreeNode)localIterator.next()).setPosition(paramPoint2D, d);
        }
        return;
      }
      if (JGraphTreeLayout.this.combineLevelNodes) {
        paramDouble = this.levelheight;
      }
      localObject = new Point2D.Double(this.width, this.height);
      if ((JGraphTreeLayout.this.orientation == 1) || (JGraphTreeLayout.this.orientation == 7)) {
        ((Point2D)localObject).setLocation(this.x + paramPoint2D.getX() - this.width / 2.0D, paramPoint2D.getY() + JGraphTreeLayout.this.levelDistance);
      } else {
        ((Point2D)localObject).setLocation(this.x + paramPoint2D.getX() - this.width / 2.0D, paramPoint2D.getY() - JGraphTreeLayout.this.levelDistance - this.levelheight);
      }
      if (JGraphTreeLayout.this.alignment == 0) {
        ((Point2D)localObject).setLocation(((Point2D)localObject).getX(), ((Point2D)localObject).getY() + (paramDouble - this.height) / 2.0D);
      } else if (JGraphTreeLayout.this.alignment == 3) {
        ((Point2D)localObject).setLocation(((Point2D)localObject).getX(), ((Point2D)localObject).getY() + paramDouble - this.height);
      }
      if ((JGraphTreeLayout.this.orientation == 7) || (JGraphTreeLayout.this.orientation == 3)) {
        ((Point2D)localObject).setLocation(((Point2D)localObject).getY(), ((Point2D)localObject).getX());
      }
      JGraphTreeLayout.this.graph.setLocation(this.cell, ((Point2D)localObject).getX(), ((Point2D)localObject).getY());
      if ((JGraphTreeLayout.this.orientation == 1) || (JGraphTreeLayout.this.orientation == 7)) {
        this.y = ((int)(paramPoint2D.getY() + JGraphTreeLayout.this.levelDistance + paramDouble));
      } else {
        this.y = ((int)(paramPoint2D.getY() - JGraphTreeLayout.this.levelDistance - paramDouble));
      }
      Iterator localIterator = this.children.iterator();
      while (localIterator.hasNext()) {
        ((StandardTreeNode)localIterator.next()).setPosition(new Point2D.Double(this.x + paramPoint2D.getX(), this.y), d);
      }
      if (JGraphTreeLayout.this.routeTreeEdges) {
        routeEdges(paramPoint2D);
      }
    }
    
    public List getNodesByLevel()
    {
      Object localObject1 = new ArrayList();
      Object localObject2 = this.children.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject3 = ((StandardTreeNode)((Iterator)localObject2).next()).getNodesByLevel();
        if (((List)localObject1).size() < ((List)localObject3).size())
        {
          Object localObject4 = localObject1;
          localObject1 = localObject3;
          localObject3 = localObject4;
        }
        for (int i = 0; i < ((List)localObject3).size(); i++) {
          ((List)((List)localObject1).get(i)).addAll((List)((List)localObject3).get(i));
        }
      }
      localObject2 = new ArrayList();
      ((ArrayList)localObject2).add(this);
      ((List)localObject1).add(0, localObject2);
      return (List)localObject1;
    }
    
    public int getDepth()
    {
      return this.depth;
    }
    
    public void setDepth(int paramInt)
    {
      this.depth = paramInt;
    }
    
    public JGraphAbstractTreeLayout.TreeNode getParent()
    {
      return this.parent;
    }
    
    public void setParent(JGraphAbstractTreeLayout.TreeNode paramTreeNode)
    {
      this.parent = paramTreeNode;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/tree/JGraphTreeLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */