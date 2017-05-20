package com.jgraph.layout.hierarchical.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class JGraphHierarchyNode
  extends JGraphAbstractHierarchyCell
{
  public static transient Collection emptyConnectionMap = new ArrayList(0);
  public static final int CELL_TYPE_NONE = 0;
  public static final int CELL_TYPE_START = 1;
  public static final int CELL_TYPE_END = 2;
  public static final int CELL_TYPE_BRANCH = 3;
  public static final int CELL_TYPE_JOIN = 4;
  public Object cell = null;
  public int cellType = 0;
  public Collection connectsAsTarget = emptyConnectionMap;
  public Collection connectsAsSource = emptyConnectionMap;
  public int[] hashCode;
  
  public JGraphHierarchyNode(Object paramObject)
  {
    this.cell = paramObject;
  }
  
  public int getRankValue()
  {
    return this.maxRank;
  }
  
  public List getNextLayerConnectedCells(int paramInt)
  {
    if (this.nextLayerConnectedCells == null)
    {
      this.nextLayerConnectedCells = new ArrayList[1];
      this.nextLayerConnectedCells[0] = new ArrayList(this.connectsAsTarget.size());
      Iterator localIterator = this.connectsAsTarget.iterator();
      while (localIterator.hasNext())
      {
        JGraphHierarchyEdge localJGraphHierarchyEdge = (JGraphHierarchyEdge)localIterator.next();
        if ((localJGraphHierarchyEdge.maxRank == -1) || (localJGraphHierarchyEdge.maxRank == paramInt + 1))
        {
          this.nextLayerConnectedCells[0].add(localJGraphHierarchyEdge.source);
          if (localJGraphHierarchyEdge.source.maxRank == paramInt + 1) {}
        }
        else
        {
          this.nextLayerConnectedCells[0].add(localJGraphHierarchyEdge);
        }
      }
    }
    return this.nextLayerConnectedCells[0];
  }
  
  public List getPreviousLayerConnectedCells(int paramInt)
  {
    if (this.previousLayerConnectedCells == null)
    {
      this.previousLayerConnectedCells = new ArrayList[1];
      this.previousLayerConnectedCells[0] = new ArrayList(this.connectsAsSource.size());
      Iterator localIterator = this.connectsAsSource.iterator();
      while (localIterator.hasNext())
      {
        JGraphHierarchyEdge localJGraphHierarchyEdge = (JGraphHierarchyEdge)localIterator.next();
        if ((localJGraphHierarchyEdge.minRank == -1) || (localJGraphHierarchyEdge.minRank == paramInt - 1))
        {
          this.previousLayerConnectedCells[0].add(localJGraphHierarchyEdge.target);
          if (localJGraphHierarchyEdge.target.maxRank == paramInt - 1) {}
        }
        else
        {
          this.previousLayerConnectedCells[0].add(localJGraphHierarchyEdge);
        }
      }
    }
    return this.previousLayerConnectedCells[0];
  }
  
  public boolean isEdge()
  {
    return false;
  }
  
  public boolean isVertex()
  {
    return true;
  }
  
  public int getGeneralPurposeVariable(int paramInt)
  {
    return this.temp[0];
  }
  
  public void setGeneralPurposeVariable(int paramInt1, int paramInt2)
  {
    this.temp[0] = paramInt2;
  }
  
  public boolean isAncestor(JGraphHierarchyNode paramJGraphHierarchyNode)
  {
    if ((paramJGraphHierarchyNode != null) && (this.hashCode != null) && (paramJGraphHierarchyNode.hashCode != null) && (this.hashCode.length < paramJGraphHierarchyNode.hashCode.length))
    {
      if (this.hashCode == paramJGraphHierarchyNode.hashCode) {
        return true;
      }
      if ((this.hashCode == null) || (this.hashCode == null)) {
        return false;
      }
      for (int i = 0; i < this.hashCode.length; i++) {
        if (this.hashCode[i] != paramJGraphHierarchyNode.hashCode[i]) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/model/JGraphHierarchyNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */