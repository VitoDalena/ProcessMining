package org.jgraph.util;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Bezier
{
  private static final float AP = 0.5F;
  private Point2D[] bPoints;
  
  public Bezier(Point2D[] paramArrayOfPoint2D)
  {
    int i = paramArrayOfPoint2D.length;
    if (i < 3) {
      return;
    }
    this.bPoints = new Point[2 * (i - 2)];
    double d3 = paramArrayOfPoint2D[0].getX();
    double d4 = paramArrayOfPoint2D[0].getY();
    double d5 = paramArrayOfPoint2D[1].getX();
    double d6 = paramArrayOfPoint2D[1].getY();
    for (int j = 0; j < i - 2; j++)
    {
      double d1 = d3;
      double d2 = d4;
      d3 = d5;
      d4 = d6;
      d5 = paramArrayOfPoint2D[(j + 2)].getX();
      d6 = paramArrayOfPoint2D[(j + 2)].getY();
      double d7 = d3 - d1;
      double d8 = d4 - d2;
      double d9 = d5 - d1;
      double d10 = d6 - d2;
      double d11 = Math.sqrt(d9 * d9 + d10 * d10);
      d9 /= d11;
      d10 /= d11;
      double d12 = d7 * d9 + d8 * d10;
      d12 = d12 < 0.0D ? -d12 : d12;
      double d13 = d12 * d9;
      double d14 = d12 * d10;
      double d15 = d3 - 0.5D * d13;
      double d16 = d4 - 0.5D * d14;
      this.bPoints[(2 * j)] = new Point((int)d15, (int)d16);
      d9 = -d9;
      d10 = -d10;
      double d17 = d3 - d5;
      double d18 = d4 - d6;
      d12 = d17 * d9 + d18 * d10;
      d12 = d12 < 0.0D ? -d12 : d12;
      d13 = d12 * d9;
      d14 = d12 * d10;
      double d19 = d3 - 0.5D * d13;
      double d20 = d4 - 0.5D * d14;
      this.bPoints[(2 * j + 1)] = new Point((int)d19, (int)d20);
    }
  }
  
  public Point2D[] getPoints()
  {
    return this.bPoints;
  }
  
  public int getPointCount()
  {
    return this.bPoints.length;
  }
  
  public Point2D getPoint(int paramInt)
  {
    return this.bPoints[paramInt];
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/util/Bezier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */