package com.jgraph.layout.hierarchical.model;

import java.util.List;

public abstract class JGraphAbstractHierarchyCell
{
  public int maxRank = -1;
  public int minRank = -1;
  public double[] x = new double[1];
  public double[] y = new double[1];
  public double width = 0.0D;
  public double height = 0.0D;
  protected List[] nextLayerConnectedCells = null;
  protected List[] previousLayerConnectedCells = null;
  public int[] temp = new int[1];
  
  public abstract List getNextLayerConnectedCells(int paramInt);
  
  public abstract List getPreviousLayerConnectedCells(int paramInt);
  
  public abstract boolean isEdge();
  
  public abstract boolean isVertex();
  
  public abstract int getGeneralPurposeVariable(int paramInt);
  
  public abstract void setGeneralPurposeVariable(int paramInt1, int paramInt2);
  
  public void setX(int paramInt, double paramDouble)
  {
    if (isVertex()) {
      this.x[0] = paramDouble;
    } else if (isEdge()) {
      this.x[(paramInt - this.minRank - 1)] = paramDouble;
    }
  }
  
  public double getX(int paramInt)
  {
    if (isVertex()) {
      return this.x[0];
    }
    if (isEdge()) {
      return this.x[(paramInt - this.minRank - 1)];
    }
    return 0.0D;
  }
  
  public void setY(int paramInt, double paramDouble)
  {
    if (isVertex()) {
      this.y[0] = paramDouble;
    } else if (isEdge()) {
      this.y[(paramInt - this.minRank - 1)] = paramDouble;
    }
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/hierarchical/model/JGraphAbstractHierarchyCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */