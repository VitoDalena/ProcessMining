package com.jgraph.layout.simple;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

public class SimpleGridLayout
  implements JGraphLayout
{
  protected int numCellsPerRow = 0;
  protected int heightSpacing = 20;
  protected int widthSpacing = 20;
  protected int offsetX = this.widthSpacing;
  protected int offsetY = this.heightSpacing;
  protected boolean actOnUnconnectedVerticesOnly = true;
  protected boolean ordered = false;
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    Object[] arrayOfObject = null;
    if (this.actOnUnconnectedVerticesOnly) {
      arrayOfObject = paramJGraphFacade.getUnconnectedVertices(this.ordered).toArray();
    } else {
      arrayOfObject = paramJGraphFacade.getVertices().toArray();
    }
    if ((arrayOfObject == null) || (arrayOfObject.length == 0)) {
      return;
    }
    int i = this.numCellsPerRow;
    if (this.numCellsPerRow == 0) {
      i = (int)Math.sqrt(arrayOfObject.length);
    }
    if (i == 0) {
      return;
    }
    double d1 = 0.0D;
    double d2 = 0.0D;
    for (int j = 0; j < arrayOfObject.length; j++)
    {
      Rectangle2D localRectangle2D = paramJGraphFacade.getBounds(arrayOfObject[j]);
      if (localRectangle2D.getWidth() > d1) {
        d1 = localRectangle2D.getWidth();
      }
      if (localRectangle2D.getHeight() > d2) {
        d2 = localRectangle2D.getHeight();
      }
    }
    j = arrayOfObject.length;
    int k = this.offsetX;
    int m = this.offsetY;
    int n = j / i;
    if (j % i > 0) {
      n++;
    }
    for (int i1 = 0; i1 < n; i1++)
    {
      for (int i2 = 0; (i2 < i) && (i2 + i1 * i < j); i2++)
      {
        paramJGraphFacade.setLocation(arrayOfObject[(i2 + i1 * i)], k, m);
        k = (int)(k + (d1 + this.widthSpacing));
      }
      k = this.widthSpacing;
      m = (int)(m + (d2 + this.heightSpacing));
    }
  }
  
  public int getNumCellsPerRow()
  {
    return this.numCellsPerRow;
  }
  
  public void setNumCellsPerRow(int paramInt)
  {
    this.numCellsPerRow = paramInt;
  }
  
  public boolean isActOnUnconnectedVerticesOnly()
  {
    return this.actOnUnconnectedVerticesOnly;
  }
  
  public void setActOnUnconnectedVerticesOnly(boolean paramBoolean)
  {
    this.actOnUnconnectedVerticesOnly = paramBoolean;
  }
  
  public int getHeightSpacing()
  {
    return this.heightSpacing;
  }
  
  public void setHeightSpacing(int paramInt)
  {
    this.heightSpacing = paramInt;
  }
  
  public int getWidthSpacing()
  {
    return this.widthSpacing;
  }
  
  public void setWidthSpacing(int paramInt)
  {
    this.widthSpacing = paramInt;
  }
  
  public boolean isOrdered()
  {
    return this.ordered;
  }
  
  public void setOrdered(boolean paramBoolean)
  {
    this.ordered = paramBoolean;
  }
  
  public int getOffsetX()
  {
    return this.offsetX;
  }
  
  public void setOffsetX(int paramInt)
  {
    this.offsetX = paramInt;
  }
  
  public int getOffsetY()
  {
    return this.offsetY;
  }
  
  public void setOffsetY(int paramInt)
  {
    this.offsetY = paramInt;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/simple/SimpleGridLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */