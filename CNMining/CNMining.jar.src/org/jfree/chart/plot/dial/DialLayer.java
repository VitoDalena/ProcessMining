package org.jfree.chart.plot.dial;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.EventListener;

public abstract interface DialLayer
{
  public abstract boolean isVisible();
  
  public abstract void addChangeListener(DialLayerChangeListener paramDialLayerChangeListener);
  
  public abstract void removeChangeListener(DialLayerChangeListener paramDialLayerChangeListener);
  
  public abstract boolean hasListener(EventListener paramEventListener);
  
  public abstract boolean isClippedToWindow();
  
  public abstract void draw(Graphics2D paramGraphics2D, DialPlot paramDialPlot, Rectangle2D paramRectangle2D1, Rectangle2D paramRectangle2D2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/DialLayer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */