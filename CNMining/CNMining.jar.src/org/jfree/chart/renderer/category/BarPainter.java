package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import org.jfree.ui.RectangleEdge;

public abstract interface BarPainter
{
  public abstract void paintBar(Graphics2D paramGraphics2D, BarRenderer paramBarRenderer, int paramInt1, int paramInt2, RectangularShape paramRectangularShape, RectangleEdge paramRectangleEdge);
  
  public abstract void paintBarShadow(Graphics2D paramGraphics2D, BarRenderer paramBarRenderer, int paramInt1, int paramInt2, RectangularShape paramRectangularShape, RectangleEdge paramRectangleEdge, boolean paramBoolean);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/BarPainter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */