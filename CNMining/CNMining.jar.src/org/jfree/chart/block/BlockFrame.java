package org.jfree.chart.block;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.ui.RectangleInsets;

public abstract interface BlockFrame
{
  public abstract RectangleInsets getInsets();
  
  public abstract void draw(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/BlockFrame.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */