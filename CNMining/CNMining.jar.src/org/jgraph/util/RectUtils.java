package org.jgraph.util;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class RectUtils
{
  public static Rectangle2D union(Rectangle2D paramRectangle2D1, Rectangle2D paramRectangle2D2)
  {
    Rectangle2D.Double localDouble = null;
    if ((paramRectangle2D1 == null) && (paramRectangle2D2 == null))
    {
      localDouble = null;
    }
    else
    {
      double d1;
      double d2;
      double d3;
      double d4;
      if ((paramRectangle2D1 != null) && (paramRectangle2D2 != null))
      {
        d1 = Math.min(paramRectangle2D1.getMinX(), paramRectangle2D2.getMinX());
        d2 = Math.min(paramRectangle2D1.getMinY(), paramRectangle2D2.getMinY());
        d3 = Math.max(paramRectangle2D1.getMaxX(), paramRectangle2D2.getMaxX());
        d4 = Math.max(paramRectangle2D1.getMaxY(), paramRectangle2D2.getMaxY());
        localDouble = new Rectangle2D.Double();
        localDouble.setFrameFromDiagonal(d1, d2, d3, d4);
      }
      else if (paramRectangle2D1 != null)
      {
        d1 = paramRectangle2D1.getMinX();
        d2 = paramRectangle2D1.getMinY();
        d3 = paramRectangle2D1.getMaxX();
        d4 = paramRectangle2D1.getMaxY();
        localDouble = new Rectangle2D.Double();
        localDouble.setFrameFromDiagonal(d1, d2, d3, d4);
      }
      else
      {
        d1 = paramRectangle2D2.getMinX();
        d2 = paramRectangle2D2.getMinY();
        d3 = paramRectangle2D2.getMaxX();
        d4 = paramRectangle2D2.getMaxY();
        localDouble = new Rectangle2D.Double();
        localDouble.setFrameFromDiagonal(d1, d2, d3, d4);
      }
    }
    return localDouble;
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jgraph/util/RectUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */