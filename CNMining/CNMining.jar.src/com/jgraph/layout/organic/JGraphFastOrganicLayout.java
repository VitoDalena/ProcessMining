package com.jgraph.layout.organic;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.JGraphLayout.Stoppable;
import com.jgraph.layout.JGraphLayoutProgress;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class JGraphFastOrganicLayout
  implements JGraphLayout, JGraphLayout.Stoppable
{
  protected double forceConstant = 50.0D;
  protected double forceConstantSquared = 0.0D;
  protected double temperature = 0.0D;
  protected double initialTemp = 200.0D;
  protected int iteration = 0;
  protected int maxIterations = 0;
  protected Object[] vertexArray;
  protected double[] dispX;
  protected double[] dispY;
  protected double[][] cellLocation;
  protected double[] radius;
  protected double[] radiusSquared;
  protected boolean[] isMoveable;
  protected int[][] neighbours;
  protected JGraphLayoutProgress progress = new JGraphLayoutProgress();
  protected double minDistanceLimit = 2.0D;
  protected double minDistanceLimitSquared = 4.0D;
  
  public JGraphLayoutProgress getProgress()
  {
    return this.progress;
  }
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    boolean bool = paramJGraphFacade.isDirected();
    paramJGraphFacade.setDirected(false);
    this.vertexArray = paramJGraphFacade.getVertices().toArray();
    this.dispX = new double[this.vertexArray.length];
    this.dispY = new double[this.vertexArray.length];
    this.cellLocation = paramJGraphFacade.getLocations(this.vertexArray);
    this.isMoveable = new boolean[this.vertexArray.length];
    this.neighbours = new int[this.vertexArray.length][];
    this.radius = new double[this.vertexArray.length];
    this.radiusSquared = new double[this.vertexArray.length];
    Hashtable localHashtable = new Hashtable(this.vertexArray.length);
    if (this.forceConstant < 0.001D) {
      this.forceConstant = 0.001D;
    }
    this.forceConstantSquared = (this.forceConstant * this.forceConstant);
    Object localObject;
    for (int i = 0; i < this.vertexArray.length; i++)
    {
      localHashtable.put(this.vertexArray[i], new Integer(i));
      localObject = paramJGraphFacade.getBounds(this.vertexArray[i]);
      double d1 = ((Rectangle2D)localObject).getWidth();
      double d2 = ((Rectangle2D)localObject).getHeight();
      this.cellLocation[i][0] += d1 / 2.0D;
      this.cellLocation[i][1] += d2 / 2.0D;
      this.radius[i] = Math.min(d1, d2);
      this.radiusSquared[i] = (this.radius[i] * this.radius[i]);
    }
    for (i = 0; i < this.vertexArray.length; i++)
    {
      this.dispX[i] = 0.0D;
      this.dispY[i] = 0.0D;
      this.isMoveable[i] = paramJGraphFacade.isMoveable(this.vertexArray[i]);
      localObject = paramJGraphFacade.getNeighbours(this.vertexArray[i], null, false).toArray();
      this.neighbours[i] = new int[localObject.length];
      for (int j = 0; j < localObject.length; j++)
      {
        Integer localInteger = (Integer)localHashtable.get(localObject[j]);
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
    this.temperature = this.initialTemp;
    if (this.maxIterations == 0) {
      this.maxIterations = (20 * (int)Math.sqrt(this.vertexArray.length));
    }
    this.progress.reset(this.maxIterations);
    for (this.iteration = 0; (this.iteration < this.maxIterations) && (!this.progress.isStopped()); this.iteration += 1)
    {
      this.progress.setProgress(this.iteration);
      calcRepulsion();
      calcAttraction();
      calcPositions();
      reduceTemperature();
    }
    for (i = 0; i < this.vertexArray.length; i++)
    {
      localObject = paramJGraphFacade.getBounds(this.vertexArray[i]);
      this.cellLocation[i][0] -= ((Rectangle2D)localObject).getWidth() / 2.0D;
      this.cellLocation[i][1] -= ((Rectangle2D)localObject).getHeight() / 2.0D;
    }
    paramJGraphFacade.setLocations(this.vertexArray, this.cellLocation);
    paramJGraphFacade.setDirected(bool);
  }
  
  public void calcPositions()
  {
    for (int i = 0; i < this.vertexArray.length; i++) {
      if (this.isMoveable[i] != 0)
      {
        double d1 = Math.sqrt(this.dispX[i] * this.dispX[i] + this.dispY[i] * this.dispY[i]);
        if (d1 < 0.001D) {
          d1 = 0.001D;
        }
        double d2 = this.dispX[i] / d1 * Math.min(d1, this.temperature);
        double d3 = this.dispY[i] / d1 * Math.min(d1, this.temperature);
        this.dispX[i] = 0.0D;
        this.dispY[i] = 0.0D;
        this.cellLocation[i][0] += d2;
        this.cellLocation[i][1] += d3;
      }
    }
  }
  
  public void calcAttraction()
  {
    for (int i = 0; i < this.vertexArray.length; i++) {
      for (int j = 0; j < this.neighbours[i].length; j++)
      {
        if (this.progress.isStopped()) {
          return;
        }
        int k = this.neighbours[i][j];
        if (i != k)
        {
          double d1 = this.cellLocation[i][0] - this.cellLocation[k][0];
          double d2 = this.cellLocation[i][1] - this.cellLocation[k][1];
          double d3 = d1 * d1 + d2 * d2 - this.radiusSquared[i] - this.radiusSquared[k];
          if (d3 < this.minDistanceLimitSquared) {
            d3 = this.minDistanceLimitSquared;
          }
          double d4 = Math.sqrt(d3);
          double d5 = d3 / this.forceConstant;
          double d6 = d1 / d4 * d5;
          double d7 = d2 / d4 * d5;
          if (this.isMoveable[i] != 0)
          {
            this.dispX[i] -= d6;
            this.dispY[i] -= d7;
          }
          if (this.isMoveable[k] != 0)
          {
            this.dispX[k] += d6;
            this.dispY[k] += d7;
          }
        }
      }
    }
  }
  
  public void calcRepulsion()
  {
    for (int i = 0; i < this.vertexArray.length; i++) {
      for (int j = i; j < this.vertexArray.length; j++)
      {
        if (this.progress.isStopped()) {
          return;
        }
        if (j != i)
        {
          double d1 = this.cellLocation[i][0] - this.cellLocation[j][0];
          double d2 = this.cellLocation[i][1] - this.cellLocation[j][1];
          if (d1 == 0.0D) {
            d1 = 0.01D + Math.random();
          }
          if (d2 == 0.0D) {
            d2 = 0.01D + Math.random();
          }
          double d3 = Math.sqrt(d1 * d1 + d2 * d2);
          double d4 = d3 - this.radius[i] - this.radius[j];
          if (d4 < this.minDistanceLimit) {
            d4 = this.minDistanceLimit;
          }
          double d5 = this.forceConstantSquared / d4;
          double d6 = d1 / d3 * d5;
          double d7 = d2 / d3 * d5;
          if (this.isMoveable[i] != 0)
          {
            this.dispX[i] += d6;
            this.dispY[i] += d7;
          }
          if (this.isMoveable[j] != 0)
          {
            this.dispX[j] -= d6;
            this.dispY[j] -= d7;
          }
        }
      }
    }
  }
  
  private void reduceTemperature()
  {
    this.temperature = (this.initialTemp * (1.0D - this.iteration / this.maxIterations));
  }
  
  public double getForceConstant()
  {
    return this.forceConstant;
  }
  
  public void setForceConstant(double paramDouble)
  {
    this.forceConstant = paramDouble;
  }
  
  public int getMaxIterations()
  {
    return this.maxIterations;
  }
  
  public void setMaxIterations(int paramInt)
  {
    this.maxIterations = paramInt;
  }
  
  public double getInitialTemp()
  {
    return this.initialTemp;
  }
  
  public void setInitialTemp(double paramDouble)
  {
    this.initialTemp = paramDouble;
  }
  
  public String toString()
  {
    return "Fast Organic";
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/organic/JGraphFastOrganicLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */