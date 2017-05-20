package com.jgraph.layout.tree;

import com.jgraph.layout.JGraphFacade;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrganizationalChart
  extends JGraphTreeLayout
{
  protected int vertexDepthOrientationSwitch = 1;
  protected int childrenLimitOrientationSwitch = 6;
  protected Set horizontalParentsSet = null;
  protected int verticalEdgeLeftInset = 15;
  protected int verticalEdgeRightInset = 15;
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    super.run(paramJGraphFacade);
  }
  
  protected JGraphTreeLayout.StandardTreeNode getTreeNode(Object paramObject)
  {
    if (paramObject != null)
    {
      OrganizationalTreeNode localOrganizationalTreeNode = (OrganizationalTreeNode)this.nodes.get(paramObject);
      if (localOrganizationalTreeNode == null)
      {
        localOrganizationalTreeNode = new OrganizationalTreeNode(paramObject);
        this.nodes.put(paramObject, localOrganizationalTreeNode);
      }
      return localOrganizationalTreeNode;
    }
    return null;
  }
  
  protected void layout(JGraphTreeLayout.StandardTreeNode paramStandardTreeNode)
  {
    if (paramStandardTreeNode.children.size() != 0) {
      if (((OrganizationalTreeNode)paramStandardTreeNode).verticalStyleChildren)
      {
        int i = -1;
        int j = -1;
        double d1 = 0.0D;
        Object localObject2 = paramStandardTreeNode.children.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          j++;
          JGraphTreeLayout.StandardTreeNode localStandardTreeNode2 = (JGraphTreeLayout.StandardTreeNode)((Iterator)localObject2).next();
          localStandardTreeNode2.setDepth(paramStandardTreeNode.getDepth() + 1);
          double d2 = localStandardTreeNode2.width;
          if (d2 > d1)
          {
            d1 = d2;
            i = j;
          }
        }
        if (i >= 0)
        {
          localObject2 = (JGraphTreeLayout.StandardTreeNode)paramStandardTreeNode.children.get(i);
          ((JGraphTreeLayout.StandardTreeNode)localObject2).leftContour.dx = 0.0D;
          ((JGraphTreeLayout.StandardTreeNode)localObject2).rightContour.dx = ((((JGraphTreeLayout.StandardTreeNode)localObject2).width - paramStandardTreeNode.width) / 2.0D + this.verticalEdgeLeftInset + this.verticalEdgeRightInset);
          paramStandardTreeNode.leftContour.next = ((JGraphTreeLayout.StandardTreeNode)localObject2).leftContour;
          paramStandardTreeNode.rightContour.next = ((JGraphTreeLayout.StandardTreeNode)localObject2).rightContour;
        }
        else
        {
          localObject2 = (JGraphTreeLayout.StandardTreeNode)paramStandardTreeNode.children.get(0);
          ((JGraphTreeLayout.StandardTreeNode)localObject2).leftContour.dx = ((((JGraphTreeLayout.StandardTreeNode)localObject2).width - paramStandardTreeNode.width) / 2.0D);
          ((JGraphTreeLayout.StandardTreeNode)localObject2).rightContour.dx = ((((JGraphTreeLayout.StandardTreeNode)localObject2).width - paramStandardTreeNode.width) / 2.0D);
          paramStandardTreeNode.leftContour.next = ((JGraphTreeLayout.StandardTreeNode)localObject2).leftContour;
          paramStandardTreeNode.rightContour.next = ((JGraphTreeLayout.StandardTreeNode)localObject2).rightContour;
        }
      }
      else
      {
        Object localObject1;
        if (paramStandardTreeNode.children.size() == 1)
        {
          localObject1 = (JGraphTreeLayout.StandardTreeNode)paramStandardTreeNode.children.get(0);
          ((JGraphTreeLayout.StandardTreeNode)localObject1).setDepth(paramStandardTreeNode.getDepth() + 1);
          layout((JGraphTreeLayout.StandardTreeNode)localObject1);
          ((JGraphTreeLayout.StandardTreeNode)localObject1).leftContour.dx = ((((JGraphTreeLayout.StandardTreeNode)localObject1).width - paramStandardTreeNode.width) / 2.0D);
          ((JGraphTreeLayout.StandardTreeNode)localObject1).rightContour.dx = ((((JGraphTreeLayout.StandardTreeNode)localObject1).width - paramStandardTreeNode.width) / 2.0D);
          paramStandardTreeNode.leftContour.next = ((JGraphTreeLayout.StandardTreeNode)localObject1).leftContour;
          paramStandardTreeNode.rightContour.next = ((JGraphTreeLayout.StandardTreeNode)localObject1).rightContour;
        }
        else
        {
          localObject1 = paramStandardTreeNode.children.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            JGraphTreeLayout.StandardTreeNode localStandardTreeNode1 = (JGraphTreeLayout.StandardTreeNode)((Iterator)localObject1).next();
            localStandardTreeNode1.setDepth(paramStandardTreeNode.getDepth() + 1);
            layout(localStandardTreeNode1);
          }
          join(paramStandardTreeNode);
        }
      }
    }
  }
  
  protected class OrganizationalTreeNode
    extends JGraphTreeLayout.StandardTreeNode
  {
    protected boolean verticalStyleChildren = false;
    
    public OrganizationalTreeNode(Object paramObject)
    {
      super(paramObject);
      if ((OrganizationalChart.this.horizontalParentsSet != null) && (OrganizationalChart.this.horizontalParentsSet.contains(paramObject))) {
        this.verticalStyleChildren = true;
      }
    }
    
    public void addChild(JGraphTreeLayout.StandardTreeNode paramStandardTreeNode)
    {
      this.children.add(paramStandardTreeNode);
      if (this.children.size() >= OrganizationalChart.this.childrenLimitOrientationSwitch) {
        this.verticalStyleChildren = true;
      }
    }
    
    public boolean isVerticalStyleChildren()
    {
      return this.verticalStyleChildren;
    }
    
    public void setVerticalStyleChildren(boolean paramBoolean)
    {
      this.verticalStyleChildren = paramBoolean;
    }
    
    public void setDepth(int paramInt)
    {
      super.setDepth(paramInt);
      if (paramInt >= OrganizationalChart.this.vertexDepthOrientationSwitch) {
        this.verticalStyleChildren = true;
      }
    }
    
    public void setPosition(Point2D paramPoint2D, double paramDouble)
    {
      double d = 0.0D;
      Object localObject = this.children.iterator();
      while (((Iterator)localObject).hasNext()) {
        d = Math.max(d, ((JGraphTreeLayout.StandardTreeNode)((Iterator)localObject).next()).height);
      }
      localObject = OrganizationalChart.this.graph.getLocation(this.cell);
      if (localObject == null) {
        localObject = new Point2D.Double(0.0D, 0.0D);
      }
      Iterator localIterator;
      if (paramPoint2D == null)
      {
        if ((OrganizationalChart.this.orientation == 7) || (OrganizationalChart.this.orientation == 3)) {
          ((Point2D)localObject).setLocation(((Point2D)localObject).getY(), ((Point2D)localObject).getX());
        }
        if ((OrganizationalChart.this.orientation == 1) || (OrganizationalChart.this.orientation == 7)) {
          paramPoint2D = new Point2D.Double(((Point2D)localObject).getX() + this.width / 2.0D, ((Point2D)localObject).getY() + this.height);
        } else if ((OrganizationalChart.this.orientation == 5) || (OrganizationalChart.this.orientation == 3)) {
          paramPoint2D = new Point2D.Double(((Point2D)localObject).getX() + this.width / 2.0D, ((Point2D)localObject).getY());
        }
        if (this.verticalStyleChildren)
        {
          positionVerticalChildren(paramPoint2D);
        }
        else
        {
          localIterator = this.children.iterator();
          while (localIterator.hasNext()) {
            ((JGraphTreeLayout.StandardTreeNode)localIterator.next()).setPosition(paramPoint2D, d);
          }
        }
        return;
      }
      if (OrganizationalChart.this.combineLevelNodes) {
        paramDouble = this.levelheight;
      }
      localObject = new Point2D.Double(this.width, this.height);
      if ((OrganizationalChart.this.orientation == 1) || (OrganizationalChart.this.orientation == 7)) {
        ((Point2D)localObject).setLocation(this.x + paramPoint2D.getX() - this.width / 2.0D, paramPoint2D.getY() + OrganizationalChart.this.levelDistance);
      } else {
        ((Point2D)localObject).setLocation(this.x + paramPoint2D.getX() - this.width / 2.0D, paramPoint2D.getY() - OrganizationalChart.this.levelDistance - this.levelheight);
      }
      if (OrganizationalChart.this.alignment == 0) {
        ((Point2D)localObject).setLocation(((Point2D)localObject).getX(), ((Point2D)localObject).getY() + (paramDouble - this.height) / 2.0D);
      } else if (OrganizationalChart.this.alignment == 3) {
        ((Point2D)localObject).setLocation(((Point2D)localObject).getX(), ((Point2D)localObject).getY() + paramDouble - this.height);
      }
      if ((OrganizationalChart.this.orientation == 7) || (OrganizationalChart.this.orientation == 3)) {
        ((Point2D)localObject).setLocation(((Point2D)localObject).getY(), ((Point2D)localObject).getX());
      }
      OrganizationalChart.this.graph.setLocation(this.cell, ((Point2D)localObject).getX(), ((Point2D)localObject).getY());
      if ((OrganizationalChart.this.orientation == 1) || (OrganizationalChart.this.orientation == 7)) {
        this.y = ((int)(paramPoint2D.getY() + OrganizationalChart.this.levelDistance + paramDouble));
      } else {
        this.y = ((int)(paramPoint2D.getY() - OrganizationalChart.this.levelDistance - paramDouble));
      }
      if (this.verticalStyleChildren)
      {
        positionVerticalChildren((Point2D)localObject);
      }
      else
      {
        localIterator = this.children.iterator();
        while (localIterator.hasNext()) {
          ((JGraphTreeLayout.StandardTreeNode)localIterator.next()).setPosition(new Point2D.Double(this.x + paramPoint2D.getX(), this.y), d);
        }
      }
    }
    
    protected void positionVerticalChildren(Point2D paramPoint2D)
    {
      double d = OrganizationalChart.this.levelDistance;
      Iterator localIterator = this.children.iterator();
      while (localIterator.hasNext())
      {
        OrganizationalTreeNode localOrganizationalTreeNode = (OrganizationalTreeNode)localIterator.next();
        if (getParent() == null) {
          System.out.println("something to break on");
        }
        OrganizationalChart.this.graph.setLocation(localOrganizationalTreeNode.cell, paramPoint2D.getX() + OrganizationalChart.this.verticalEdgeLeftInset + OrganizationalChart.this.verticalEdgeRightInset, paramPoint2D.getY() + localOrganizationalTreeNode.getParent().height + d);
        d += OrganizationalChart.this.levelDistance + localOrganizationalTreeNode.height;
        List localList1 = OrganizationalChart.this.graph.getIncomingEdges(localOrganizationalTreeNode.cell, null, true, false);
        if ((localList1 != null) && (localList1.size() > 0))
        {
          Object localObject = localList1.get(0);
          List localList2 = OrganizationalChart.this.graph.getPoints(localObject);
          ArrayList localArrayList = new ArrayList(3);
          localArrayList.add(localList2.get(0));
          localArrayList.add(new Point2D.Double(paramPoint2D.getX() + OrganizationalChart.this.verticalEdgeLeftInset, paramPoint2D.getY() + d - localOrganizationalTreeNode.getParent().height));
          localArrayList.add(localList2.get(localList2.size() - 1));
          OrganizationalChart.this.graph.setPoints(localObject, localArrayList);
          OrganizationalChart.this.graph.disableRouting(localObject);
        }
      }
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/tree/OrganizationalChart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */