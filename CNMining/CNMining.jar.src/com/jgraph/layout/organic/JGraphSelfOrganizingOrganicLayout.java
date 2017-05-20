package com.jgraph.layout.organic;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class JGraphSelfOrganizingOrganicLayout
  implements JGraphLayout
{
  protected Rectangle2D bounds = null;
  protected int totalIterations = 0;
  protected int maxIterationsMultiple = 20;
  protected int iteration = 1;
  protected int radius = 0;
  protected int startRadius = 0;
  protected int minRadius = 1;
  protected double densityFactor = 0.0D;
  protected int narrowingInterval = 0;
  protected double adaption = 0.0D;
  protected double maxAdaption = 0.8D;
  protected double minAdaption = 0.1D;
  protected double coolingFactor = 1.0D;
  protected Stack stack = null;
  protected int[][] neighbours;
  protected Object[] vertexArray;
  protected boolean[] vertexVisited;
  protected int[] vertexDistance;
  protected double[][] cellLocation;
  protected double randomX;
  protected double randomY;
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    boolean bool = paramJGraphFacade.isDirected();
    paramJGraphFacade.setDirected(false);
    this.vertexArray = paramJGraphFacade.getVertices().toArray();
    this.vertexVisited = new boolean[this.vertexArray.length];
    this.vertexDistance = new int[this.vertexArray.length];
    this.cellLocation = paramJGraphFacade.getLocations(this.vertexArray);
    this.neighbours = new int[this.vertexArray.length][];
    Hashtable localHashtable = new Hashtable(this.vertexArray.length);
    this.bounds = paramJGraphFacade.getGraphBounds();
    if ((this.densityFactor != 0.0D) && (this.bounds != null))
    {
      double d1 = this.bounds.getWidth() * this.bounds.getHeight();
      double d2 = this.densityFactor * this.vertexArray.length;
      double d3 = Math.sqrt(d2 / d1);
      this.bounds.setFrame(this.bounds.getX(), this.bounds.getY(), this.bounds.getWidth() * d3, this.bounds.getHeight() * d3);
    }
    for (int i = 0; i < this.vertexArray.length; i++) {
      localHashtable.put(this.vertexArray[i], new Integer(i));
    }
    for (i = 0; i < this.vertexArray.length; i++)
    {
      localHashtable.put(this.vertexArray[i], new Integer(i));
      Object[] arrayOfObject = paramJGraphFacade.getNeighbours(this.vertexArray[i], null, true).toArray();
      this.neighbours[i] = new int[arrayOfObject.length];
      for (int j = 0; j < arrayOfObject.length; j++)
      {
        Integer localInteger = (Integer)localHashtable.get(arrayOfObject[j]);
        if (localInteger != null)
        {
          int k = localInteger.intValue();
          this.neighbours[i][j] = k;
        }
        else
        {
          this.neighbours[i][j] = i;
        }
      }
    }
    this.adaption = this.maxAdaption;
    if (this.startRadius == 0) {
      this.startRadius = 3;
    }
    this.radius = this.startRadius;
    this.totalIterations = (this.vertexArray.length * this.maxIterationsMultiple);
    if (this.totalIterations < 100) {
      this.totalIterations = 100;
    }
    if (this.narrowingInterval == 0)
    {
      i = this.startRadius - this.minRadius + 1;
      if (i < 1) {
        i = 1;
      }
      this.narrowingInterval = (this.totalIterations / i);
    }
    for (this.iteration = 1; this.iteration <= this.totalIterations; this.iteration += 1)
    {
      updateToRandomNode();
      updateRadius();
    }
    paramJGraphFacade.setLocations(this.vertexArray, this.cellLocation);
    paramJGraphFacade.setDirected(bool);
  }
  
  protected void updateToRandomNode()
  {
    double d1 = Math.exp(-this.coolingFactor * (1.0D * this.iteration / this.totalIterations));
    this.adaption = Math.max(this.minAdaption, d1 * this.maxAdaption);
    this.randomX = (Math.random() * this.bounds.getWidth());
    this.randomY = (Math.random() * this.bounds.getHeight());
    int i = -1;
    double d2 = Double.MAX_VALUE;
    for (int j = 0; j < this.vertexArray.length; j++)
    {
      this.vertexDistance[j] = 0;
      this.vertexVisited[j] = false;
      double d3 = (this.randomX - this.cellLocation[j][0]) * (this.randomX - this.cellLocation[j][0]) + (this.randomY - this.cellLocation[j][1]) * (this.randomY - this.cellLocation[j][1]);
      if (d3 < d2)
      {
        i = j;
        d2 = d3;
      }
    }
    if (i > -1) {
      moveVertex(i);
    }
  }
  
  private void updateRadius()
  {
    if ((this.radius > this.minRadius) && (this.iteration % this.narrowingInterval == 0)) {
      this.radius -= 1;
    }
  }
  
  private void moveVertex(int paramInt)
  {
    if (this.stack == null) {
      this.stack = new Stack();
    }
    this.vertexVisited[paramInt] = true;
    this.stack.push(new Integer(paramInt));
    while (!this.stack.isEmpty())
    {
      int i = ((Integer)this.stack.pop()).intValue();
      double d1 = this.randomX - this.cellLocation[i][0];
      double d2 = this.randomY - this.cellLocation[i][1];
      double d3 = this.adaption / (1 << this.vertexDistance[i]);
      this.cellLocation[i][0] += d3 * d1;
      this.cellLocation[i][1] += d3 * d2;
      if (this.vertexDistance[i] < this.radius) {
        for (int j = 0; j < this.neighbours[paramInt].length; j++)
        {
          int k = this.neighbours[paramInt][j];
          if ((paramInt != k) && (this.vertexVisited[k] == 0))
          {
            this.vertexVisited[k] = true;
            this.vertexDistance[i] += 1;
            this.stack.push(new Integer(k));
          }
        }
      }
    }
  }
  
  public double getCoolingFactor()
  {
    return this.coolingFactor;
  }
  
  public void setCoolingFactor(double paramDouble)
  {
    this.coolingFactor = paramDouble;
  }
  
  public int getMaxIterationsMultiple()
  {
    return this.maxIterationsMultiple;
  }
  
  public void setMaxIterationsMultiple(int paramInt)
  {
    this.maxIterationsMultiple = paramInt;
  }
  
  public double getMinAdaption()
  {
    return this.minAdaption;
  }
  
  public void setMinAdaption(double paramDouble)
  {
    this.minAdaption = paramDouble;
  }
  
  public int getStartRadius()
  {
    return this.startRadius;
  }
  
  public void setStartRadius(int paramInt)
  {
    this.startRadius = paramInt;
  }
  
  public double getMaxAdaption()
  {
    return this.maxAdaption;
  }
  
  public void setMaxAdaption(double paramDouble)
  {
    this.maxAdaption = paramDouble;
  }
  
  public int getMinRadius()
  {
    return this.minRadius;
  }
  
  public void setMinRadius(int paramInt)
  {
    this.minRadius = paramInt;
  }
  
  public double getDensityFactor()
  {
    return this.densityFactor;
  }
  
  public void setDensityFactor(double paramDouble)
  {
    this.densityFactor = paramDouble;
  }
  
  public String toString()
  {
    return "Self Organizing";
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/organic/JGraphSelfOrganizingOrganicLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */