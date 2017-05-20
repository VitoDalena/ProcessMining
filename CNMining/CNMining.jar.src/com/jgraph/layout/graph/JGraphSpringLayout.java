package com.jgraph.layout.graph;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.JGraphLayout.Stoppable;
import com.jgraph.layout.JGraphLayoutProgress;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @deprecated
 */
public class JGraphSpringLayout
  implements JGraphLayout, JGraphLayout.Stoppable
{
  protected transient Map displacement = new Hashtable();
  protected double replusiveForce = 10000.0D;
  protected double springForce = 0.2D;
  protected double springLength = 50.0D;
  protected int iteration;
  protected int maxIterations = 0;
  protected Object[] vertexArray;
  protected double[] dispX;
  protected double[] dispY;
  protected double[] cellLocationX;
  protected double[] cellLocationY;
  protected boolean[] isMoveable;
  protected int[][] neighbours;
  protected JGraphLayoutProgress progress = new JGraphLayoutProgress();
  
  public JGraphSpringLayout()
  {
    this(50);
  }
  
  public JGraphSpringLayout(int paramInt)
  {
    setMaxIterations(paramInt);
  }
  
  public JGraphLayoutProgress getProgress()
  {
    return this.progress;
  }
  
  public void run(JGraphFacade paramJGraphFacade)
  {
    boolean bool = paramJGraphFacade.isDirected();
    paramJGraphFacade.setDirected(true);
    Collection localCollection = paramJGraphFacade.getVertices();
    if (localCollection.isEmpty()) {
      return;
    }
    this.vertexArray = localCollection.toArray();
    this.dispX = new double[this.vertexArray.length];
    this.dispY = new double[this.vertexArray.length];
    this.cellLocationX = new double[this.vertexArray.length];
    this.cellLocationY = new double[this.vertexArray.length];
    this.isMoveable = new boolean[this.vertexArray.length];
    this.neighbours = new int[this.vertexArray.length][];
    if (this.maxIterations == 0) {
      this.maxIterations = (20 * (int)Math.sqrt(this.vertexArray.length));
    }
    this.progress.reset(this.maxIterations);
    Hashtable localHashtable = new Hashtable(this.vertexArray.length);
    for (int i = 0; i < this.vertexArray.length; i++) {
      localHashtable.put(this.vertexArray[i], new Integer(i));
    }
    for (i = 0; i < this.vertexArray.length; i++)
    {
      this.dispX[i] = 0.0D;
      this.dispY[i] = 0.0D;
      Point2D localPoint2D = paramJGraphFacade.getLocation(this.vertexArray[i]);
      this.cellLocationX[i] = localPoint2D.getX();
      this.cellLocationY[i] = localPoint2D.getY();
      this.isMoveable[i] = paramJGraphFacade.isMoveable(this.vertexArray[i]);
      Object[] arrayOfObject = paramJGraphFacade.getNeighbours(this.vertexArray[i], null, false).toArray();
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
    for (this.iteration = 0; (this.iteration < this.maxIterations) && (!this.progress.isStopped()); this.iteration += 1)
    {
      this.progress.setProgress(this.iteration);
      repulse();
      attract();
      reposition(paramJGraphFacade);
    }
    paramJGraphFacade.setDirected(bool);
  }
  
  protected void repulse()
  {
    for (int i = 0; i < this.vertexArray.length; i++) {
      for (int j = i; j < this.vertexArray.length; j++)
      {
        if (this.progress.isStopped()) {
          return;
        }
        if (i != j)
        {
          double d1 = this.cellLocationX[i] - this.cellLocationX[j];
          double d2 = this.cellLocationY[i] - this.cellLocationY[j];
          double d3 = Math.sqrt(d1 * d1 + d2 * d2);
          if (d3 < 0.1D)
          {
            d3 = 0.1D;
            d1 = 0.1D;
            d2 = 0.1D;
          }
          double d4 = this.replusiveForce / (d3 * d3);
          if (d4 > this.springLength) {
            d4 = this.springLength;
          } else if (d4 < -this.springLength) {
            d4 = -this.springLength;
          }
          double d5 = d1 / d3;
          double d6 = d5 * d4;
          double d7 = d2 / d3;
          double d8 = d7 * d4;
          if (this.isMoveable[i] != 0)
          {
            this.dispX[i] += d6;
            this.dispY[i] += d8;
          }
          if (this.isMoveable[j] != 0)
          {
            this.dispX[j] -= d6;
            this.dispY[j] -= d8;
          }
        }
      }
    }
  }
  
  protected void attract()
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
          double d1 = this.cellLocationX[i] - this.cellLocationX[k];
          double d2 = this.cellLocationY[i] - this.cellLocationY[k];
          double d3 = Math.sqrt(d1 * d1 + d2 * d2);
          double d4 = d3 - this.springLength;
          if (d3 < 1.0D) {
            d3 = 1.0D;
          }
          double d5 = d4 * this.springForce;
          double d6 = d1 / d3;
          double d7 = d2 / d3;
          double d8 = d6 * d5;
          double d9 = d7 * d5;
          if (this.isMoveable[i] != 0)
          {
            this.dispX[i] -= d8;
            this.dispY[i] -= d9;
          }
          if (this.isMoveable[k] != 0)
          {
            this.dispX[k] += d8;
            this.dispY[k] += d9;
          }
        }
      }
    }
  }
  
  protected void reposition(JGraphFacade paramJGraphFacade)
  {
    for (int i = 0; i < this.vertexArray.length; i++) {
      if (this.isMoveable[i] != 0)
      {
        this.cellLocationX[i] += this.dispX[i];
        this.cellLocationY[i] += this.dispY[i];
        this.dispX[i] = 0.0D;
        this.dispY[i] = 0.0D;
        if (this.iteration == this.maxIterations - 1) {
          paramJGraphFacade.setLocation(this.vertexArray[i], this.cellLocationX[i], this.cellLocationY[i]);
        }
      }
    }
  }
  
  public void setMaxIterations(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException("iterations must be a positive integer");
    }
    this.maxIterations = paramInt;
  }
  
  public int getMaxIterations()
  {
    return this.maxIterations;
  }
  
  public double getSpringLength()
  {
    return this.springLength;
  }
  
  public void setSpringLength(double paramDouble)
  {
    if (paramDouble < 0.001D) {
      throw new IllegalArgumentException("spring length must be postive and non-zero");
    }
    this.springLength = paramDouble;
  }
  
  public double getSpringForce()
  {
    return this.springForce;
  }
  
  public void setSpringForce(double paramDouble)
  {
    this.springForce = paramDouble;
  }
  
  public double getReplusiveForce()
  {
    return this.replusiveForce;
  }
  
  public void setReplusiveForce(double paramDouble)
  {
    this.replusiveForce = paramDouble;
  }
  
  public String toString()
  {
    return "Spring";
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/layout/graph/JGraphSpringLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */