package com.jgraph.io.svg;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import org.jgraph.graph.CellView;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

public class SVGVertexRenderer
  extends VertexRenderer
{
  protected int shape = 0;
  
  public Point2D getPerimeterPoint(VertexView paramVertexView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    int i = SVGGraphConstants.getShape(paramVertexView.getAllAttributes());
    if (i == 1) {
      return getEllipsePerimeterPoint(paramVertexView, paramPoint2D1, paramPoint2D2);
    }
    return super.getPerimeterPoint(paramVertexView, paramPoint2D1, paramPoint2D2);
  }
  
  public void installAttributes(CellView paramCellView)
  {
    super.installAttributes(paramCellView);
    this.shape = SVGGraphConstants.getShape(paramCellView.getAllAttributes());
  }
  
  public Point2D getEllipsePerimeterPoint(VertexView paramVertexView, Point2D paramPoint2D1, Point2D paramPoint2D2)
  {
    Rectangle2D localRectangle2D = paramVertexView.getBounds();
    double d1 = localRectangle2D.getX();
    double d2 = localRectangle2D.getY();
    double d3 = (localRectangle2D.getWidth() + 1.0D) / 2.0D;
    double d4 = (localRectangle2D.getHeight() + 1.0D) / 2.0D;
    double d5 = d1 + d3;
    double d6 = d2 + d4;
    double d7 = paramPoint2D2.getX();
    double d8 = paramPoint2D2.getY();
    double d9 = d7 - d5;
    double d10 = d8 - d6;
    if (d9 == 0.0D) {
      return new Point((int)d5, (int)(d6 + d4 * d10 / Math.abs(d10)));
    }
    double d11 = d10 / d9;
    double d12 = d6 - d11 * d5;
    double d13 = d3 * d3 * d11 * d11 + d4 * d4;
    double d14 = -2.0D * d5 * d13;
    double d15 = d3 * d3 * d11 * d11 * d5 * d5 + d4 * d4 * d5 * d5 - d3 * d3 * d4 * d4;
    double d16 = Math.sqrt(d14 * d14 - 4.0D * d13 * d15);
    double d17 = (-d14 + d16) / (2.0D * d13);
    double d18 = (-d14 - d16) / (2.0D * d13);
    double d19 = d11 * d17 + d12;
    double d20 = d11 * d18 + d12;
    double d21 = Math.sqrt(Math.pow(d17 - d7, 2.0D) + Math.pow(d19 - d8, 2.0D));
    double d22 = Math.sqrt(Math.pow(d18 - d7, 2.0D) + Math.pow(d20 - d8, 2.0D));
    double d23;
    double d24;
    if (d21 < d22)
    {
      d23 = d17;
      d24 = d19;
    }
    else
    {
      d23 = d18;
      d24 = d20;
    }
    return new Point2D.Double(d23, d24);
  }
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/jgraph/io/svg/SVGVertexRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */