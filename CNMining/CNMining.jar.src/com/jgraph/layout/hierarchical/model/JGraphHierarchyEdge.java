package com.jgraph.layout.hierarchical.model;

import java.util.ArrayList;
import java.util.List;

public class JGraphHierarchyEdge
  extends JGraphAbstractHierarchyCell
{
  public List edges;
  public JGraphHierarchyNode source;
  public JGraphHierarchyNode target;
  protected boolean isReversed = false;
  
  public JGraphHierarchyEdge(List paramList)
  {
    this.edges = paramList;
  }
  
  public void invert()
  {
    JGraphHierarchyNode localJGraphHierarchyNode = this.source;
    this.source = this.target;
    this.target = localJGraphHierarchyNode;
    this.isReversed = (!this.isReversed);
  }
  
  public boolean isReversed()
  {
    return this.isReversed;
  }
  
  public void setReversed(boolean paramBoolean)
  {
    this.isReversed = paramBoolean;
  }
  
  public List getNextLayerConnectedCells(int paramInt)
  {
    if (this.nextLayerConnectedCells == null)
    {
      this.nextLayerConnectedCells = new ArrayList[this.temp.length];
      for (int i = 0; i < this.nextLayerConnectedCells.length; i++)
      {
        this.nextLayerConnectedCells[i] = new ArrayList(2);
        if (i == this.nextLayerConnectedCells.length - 1) {
          this.nextLayerConnectedCells[i].add(this.source);
        } else {
          this.nextLayerConnectedCells[i].add(this);
        }
      }
    }
    return this.nextLayerConnectedCells[(paramInt - this.minRank - 1)];
  }
  
  public List getPreviousLayerConnectedCells(int paramInt)
  {
    if (this.previousLayerConnectedCells == null)
    {
      this.previousLayerConnectedCells = new ArrayList[this.temp.length];
      for (int i = 0; i < this.previousLayerConnectedCells.length; i++)
      {
        this.previousLayerConnectedCells[i] = new ArrayList(2);
        if (i == 0) {
          this.previousLayerConnectedCells[i].add(this.target);
        } else {
          this.previousLayerConnectedCells[i].add(this);
        }
      }
    }
    return this.previousLayerConnectedCells[(paramInt - this.minRank - 1)];
  }
  
  public boolean isEdge()
  {
    return true;
  }
  
  public boolean isVertex()
  {
    return false;
  }
  
  public int getGeneralPurposeVariable(int paramInt)
  {
    return this.temp[(paramInt - this.minRank - 1)];
  }
  
  public void setGeneralPurposeVariable(int paramInt1, int paramInt2)
  {
    this.temp[(paramInt1 - this.minRank - 1)] = paramInt2;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/model/JGraphHierarchyEdge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */