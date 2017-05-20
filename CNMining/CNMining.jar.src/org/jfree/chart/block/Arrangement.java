package org.jfree.chart.block;

import java.awt.Graphics2D;
import org.jfree.ui.Size2D;

public abstract interface Arrangement
{
  public abstract void add(Block paramBlock, Object paramObject);
  
  public abstract Size2D arrange(BlockContainer paramBlockContainer, Graphics2D paramGraphics2D, RectangleConstraint paramRectangleConstraint);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/Arrangement.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */