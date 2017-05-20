package org.jgraph.util;

import java.awt.geom.Point2D;

public class Spline2D
{
  private double[] t;
  private Spline splineX;
  private Spline splineY;
  private double length;
  
  public Spline2D(Point2D[] paramArrayOfPoint2D)
  {
    double[] arrayOfDouble1 = new double[paramArrayOfPoint2D.length];
    double[] arrayOfDouble2 = new double[paramArrayOfPoint2D.length];
    for (int i = 0; i < paramArrayOfPoint2D.length; i++)
    {
      arrayOfDouble1[i] = paramArrayOfPoint2D[i].getX();
      arrayOfDouble2[i] = paramArrayOfPoint2D[i].getY();
    }
    init(arrayOfDouble1, arrayOfDouble2);
  }
  
  public Spline2D(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    init(paramArrayOfDouble1, paramArrayOfDouble2);
  }
  
  private void init(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    if (paramArrayOfDouble1.length != paramArrayOfDouble2.length) {
      throw new IllegalArgumentException("Arrays must have the same length.");
    }
    if (paramArrayOfDouble1.length < 2) {
      throw new IllegalArgumentException("Spline edges must have at least two points.");
    }
    this.t = new double[paramArrayOfDouble1.length];
    this.t[0] = 0.0D;
    for (int i = 1; i < this.t.length; i++)
    {
      double d1 = paramArrayOfDouble1[i] - paramArrayOfDouble1[(i - 1)];
      double d2 = paramArrayOfDouble2[i] - paramArrayOfDouble2[(i - 1)];
      if (0.0D == d1) {
        this.t[i] = Math.abs(d2);
      } else if (0.0D == d2) {
        this.t[i] = Math.abs(d1);
      } else {
        this.t[i] = Math.sqrt(d1 * d1 + d2 * d2);
      }
      this.length += this.t[i];
      this.t[i] += this.t[(i - 1)];
    }
    for (i = 1; i < this.t.length - 1; i++) {
      this.t[i] /= this.length;
    }
    this.t[(this.t.length - 1)] = 1.0D;
    this.splineX = new Spline(this.t, paramArrayOfDouble1);
    this.splineY = new Spline(this.t, paramArrayOfDouble2);
  }
  
  public double[] getPoint(double paramDouble)
  {
    double[] arrayOfDouble = new double[2];
    arrayOfDouble[0] = this.splineX.getValue(paramDouble);
    arrayOfDouble[1] = this.splineY.getValue(paramDouble);
    return arrayOfDouble;
  }
  
  public boolean checkValues()
  {
    return (this.splineX.checkValues()) && (this.splineY.checkValues());
  }
  
  public double getDx(double paramDouble)
  {
    return this.splineX.getDx(paramDouble);
  }
  
  public double getDy(double paramDouble)
  {
    return this.splineY.getDx(paramDouble);
  }
  
  public Spline getSplineX()
  {
    return this.splineX;
  }
  
  public Spline getSplineY()
  {
    return this.splineY;
  }
  
  public double getLength()
  {
    return this.length;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/util/Spline2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */